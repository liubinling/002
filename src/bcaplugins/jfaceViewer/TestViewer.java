package bcaplugins.jfaceViewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.bca.db.meta.unit.FilesInfo;

public class TestViewer {
	public static void main(String[] args) {
		Display display=Display.getDefault();
		Shell shell=new Shell(display, SWT.SHELL_TRIM);
		GridLayout layout=new GridLayout();
		layout.marginTop=10;
		layout.marginBottom=10;
		layout.marginLeft=10;
		layout.marginRight=10;
		layout.numColumns=1;
		shell.setLayout(layout);
		shell.setBounds(300, 200, 500, 300);
		Tree tree=new Tree(shell, SWT.NONE);
		GridData treeGridData=new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tree.setLayoutData(treeGridData);
		tree.setBackground(new Color(display, 255,255,255));
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn column=new TreeColumn(tree, SWT.NONE);
		column.setText("文件列表列");
		column.setWidth(300);
		
		
		TreeViewer treeViewer=new TreeViewer(tree);
		treeViewer.setContentProvider(new FilesListProvider());
		treeViewer.setLabelProvider(new FilesListLabelProvider());
		FilesInfo root=new FilesInfo("项目名称", "", "", "");
		FilesInfo newNode=new FilesInfo("node 1", "", "", "");
		root.getChildren().add(newNode);
		newNode.setParent(root);
		FilesListModel input=new FilesListModel(root);
		treeViewer.setInput(input);
		
		tree.getItem(0).setExpanded(true);
		
		
		shell.open();
		shell.layout();
		while(!shell.isDisposed()){
			if(display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();	
	}

}
