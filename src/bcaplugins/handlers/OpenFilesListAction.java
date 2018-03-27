package bcaplugins.handlers;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import bcaplugins.jdbcUtil.ConnectionUtil;
import bcaplugins.jfaceViewer.FilesListLabelProvider;
import bcaplugins.jfaceViewer.FilesListModel;
import bcaplugins.jfaceViewer.FilesListProvider;
import com.bca.db.meta.unit.FilesInfo;

public class OpenFilesListAction implements IObjectActionDelegate{
	private Shell shell;
	private ISelection selection;
	private static Logger logger=Logger.getLogger("getFilesListLogger");;
	public static Logger getLogger() {
		return logger;
	}
	@Override
	public void run(IAction arg0) {
		
		if(selection!=null && selection instanceof IStructuredSelection){
			IStructuredSelection ss=(IStructuredSelection)selection;
			Object element=ss.getFirstElement();
			if(element instanceof IProject){
				IProject project=(IProject)element;
				String projectName=project.getName();
				String projHome=project.getLocationURI().getPath().trim();
				if(projHome.startsWith("/")){
					projHome=projHome.substring(1);
				}
				projHome=projHome.replace("/", "\\");
				
				//获取文件列表信息
				Set<FilesInfo> filesSet=(HashSet<FilesInfo>) queryUserInfo(projHome);
				Set<FilesInfo> filesSet_1=new HashSet<FilesInfo>(filesSet);
				//返回数据为空时，弹窗提示
				if(filesSet==null||filesSet.isEmpty()){
					Shell shell=new Shell(Display.getDefault());
					MessageBox mbox=new MessageBox(shell, SWT.ICON_INFORMATION|SWT.OK);
					mbox.setMessage("该项目中没有BcaToolkit生成的文件");
					mbox.open();
					return;
				}
				//从返回的集合中，去除不存在项目中的文件对应的对象
				//IProject proj=ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				for (FilesInfo f : filesSet) {
					String filePath=f.getFilePath();
					int index=filePath.indexOf(projectName);
					filePath=filePath.substring(index+projectName.length());
					IFile file=project.getFile(filePath);
					if(!file.exists()){
						filesSet_1.remove(f);
					}
				}
				createFilesListDialog(projectName, filesSet_1);   //创建文件列表窗体
			}
		}
	}
	/**
	 * 获取选中项目的文件路径信息
	 */
	private HashSet<FilesInfo> queryUserInfo(String projHome) {
		String SQL="select filepath, projhome, modeltype, visiteurl from TiantianTest where projhome=? ";
		Connection con=ConnectionUtil.getConnection();
		if(con==null) return null;
		try {
			PreparedStatement ps=con.prepareStatement(SQL);
			ps.setString(1, projHome);
			ResultSet rst=ps.executeQuery();
			HashSet<FilesInfo> filesSet=new HashSet<FilesInfo>();
			while(rst.next()){
				filesSet.add(new FilesInfo(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
			}
			return filesSet;
		} catch (SQLException e) {
			logger.info("OpenFilesListAction.queryUserInfo从数据库获取文件路径信息时发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return new HashSet<FilesInfo>();
	}
	/**
	 * @author ONE_METER
	 * 创建文件列表窗体，显示BcaToolkit生成过的文件名称，双击文件名，即可打开文件。
	 * @param shell2
	 */

	private void createFilesListDialog(final String projectName, Set<FilesInfo> filesSet) {
		logger.info("开始创建文件列表窗体");
		Display display=Display.getDefault();
		shell=new Shell(display, SWT.SHELL_TRIM);
		//设计shell布局器
		GridLayout layout=new GridLayout();
		layout.marginTop=5;
		layout.marginBottom=5;
		layout.marginLeft=5;
		layout.marginRight=5;
		layout.numColumns=1;
		
		shell.setLayout(layout);
		shell.setText("文件列表对话框            项目名称： "+projectName);
		
		//设置shell大小
		shell.setBounds(400, 200, 800, 450);
		GridData listLayData=new GridData(SWT.FILL, SWT.FILL, true, true, 1,1);  //列表网格布局数据
		GridData labelLayData=new GridData(SWT.FILL, SWT.TOP, true, false, 1,1);  //标签网格布局数据
		//创建一个标签
		Label descLabel=new Label(shell, SWT.NONE);
		descLabel.setText("  单击左侧小三角展开列表，双击即可打开列表中的文件");
		descLabel.setLayoutData(labelLayData);
		//创建一个树
		final Tree tree=new Tree(shell, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		
		tree.setLayoutData(listLayData);
		TreeColumn column=new TreeColumn(tree, SWT.NONE);
		
		//column.setText("文件列表");
		String imageName="/icons/rainDrops.jpg";
		InputStream in=OpenFilesListAction.class.getResourceAsStream(imageName);
		column.setImage(new Image(display, new ImageData(in).scaledTo(16, 18)));
		column.setWidth(800);
		
		tree.setHeaderVisible(false);	//不显示列标题
		tree.setLinesVisible(true);
		tree.addSelectionListener(new SelectionListener(){
			//双击列表选项打开对应文件
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				IProject project=ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				TreeItem selectedItem=tree.getSelection()[0];
				String filePath=selectedItem.getText();
				int index=filePath.indexOf(projectName);
				filePath=filePath.substring(index+projectName.length());
				IFile file=project.getFile(filePath);
				if(file.exists()){
					IWorkbenchWindow window=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
					if(window==null) return;
					try {
						IDE.openEditor(window.getActivePage(), file, true);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}else{
					MessageBox msgBox=new MessageBox(shell, SWT.OK|SWT.ICON_INFORMATION);
					msgBox.setMessage("该文件已不存在。");
					msgBox.open();
				}
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		
		//创建列表查看器
		TreeViewer viewer=new TreeViewer(tree);
		viewer.setContentProvider(new FilesListProvider());
		viewer.setLabelProvider(new FilesListLabelProvider());
		FilesInfo root=new FilesInfo(projectName, "", "", "");
		
		FilesListModel input=new FilesListModel(root);
		Set<String> modelTypes=new HashSet<String>();	//储存模型名称（仅用于避免重复记录显示）
		Set<String> filePaths=new HashSet<String>();	//储存文件路径（仅用于避免重复记录显示）
		Map<String, FilesInfo> level_1_Nodes=new HashMap<String, FilesInfo>();	//储存一级子节点
		for(FilesInfo s: filesSet){
			if(!modelTypes.contains(s.getModelType())){
				modelTypes.add(s.getModelType());
				FilesInfo f=new FilesInfo(s.getModelType(), "", "", "");
				level_1_Nodes.put(s.getModelType(), f);
			}
			if(!filePaths.contains(s.getFilePath())){
				filePaths.add(s.getFilePath());
				FilesInfo temp=level_1_Nodes.get(s.getModelType());
				if(temp!=null){
					temp.getChildren().add(s);
					s.setParent(temp);
				}
			}
		}
		for(FilesInfo f:level_1_Nodes.values()){
			root.getChildren().add(f);
			f.setParent(root);
		}
		viewer.setInput(input);
		
		shell.open();
		shell.layout();
		
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		selection=arg1;
		
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		
	}

}
