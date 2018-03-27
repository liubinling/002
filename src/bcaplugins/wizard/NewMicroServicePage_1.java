package bcaplugins.wizard;

import java.io.InputStream;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;

public class NewMicroServicePage_1 extends WizardPage{
	private Text artifactIdTFD;
    private Text departmentTFD;
    private Text emailTFD;
    private Combo frameworkVersionBox;
    private Text groupIdTFD;
    private Label projectNameLabel;
    private Label groupIdLabel;
    private Label artifactIdLabel;
    private Label versionLabel;
    private Label jdkVersionLabel;
    private Label frameworkVersionLabel;
    private Label portNumberLabel;
    private Label locationLabel;
    private Label departmentLabel;
    private Label groupLabel;
    private Label emailLabel;
    private Group c1;
    private Group c2;
    private Combo jdkVersionBox;
    private Button openDirectoryButton;
    private Text portNumberTFD;
    private Text proGroupTFD;
    private Button proLocationCBox;
    private Text proLocationTFD;
    private Text projectNameTFD;
    private Text versionTFD;

	protected NewMicroServicePage_1(String pageName) {
		super(pageName);
		setTitle("MicroService Project");
		setDescription("微服务项目配置");
		//为向导页设置图标
		try{
			InputStream in=this.getClass().getResourceAsStream("images/EclipseNewFile.png");
			ImageData iData=new ImageData(in).scaledTo(75, 66);
			Image image=new Image(Display.getDefault(), iData);
			ImageDescriptor iDesc=ImageDescriptor.createFromImage(image);
			setImageDescriptor(iDesc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void createControl(Composite parent) {
		Composite container=new Composite(parent, SWT.BORDER_SOLID);
		GridLayout gl_container=new GridLayout();
		gl_container.marginLeft=5;
		gl_container.marginRight=5;
		gl_container.marginBottom=5;
		gl_container.marginTop=5;
		gl_container.horizontalSpacing=20;
		gl_container.verticalSpacing=6;
		gl_container.numColumns=2;
		container.setLayout(gl_container);
		//初始化所有子控件
		initControls(container, gl_container);
		
		
		//开始添加基础信息部分控件，它们处于页面的上部分
		
		
		setControl(container);
		setPageComplete(false);
		
	}
	/**
	 * 初始化所有子控件
	 * @param container
	 */
	private void initControls(Composite c, Layout layout) {
		
			GridData gd_10=new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			GridData gd_11=new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		//以下是基本信息配置控件	
			projectNameLabel=new Label(c, SWT.NONE);
			projectNameLabel.setText(" Project name:");
			projectNameLabel.setLayoutData(gd_11);
			projectNameTFD=new Text(c,SWT.NONE);
			projectNameTFD.setLayoutData(gd_10);
			
			
			groupIdLabel=new Label(c, SWT.NONE);
			groupIdLabel.setText(" Group Id:");
			groupIdLabel.setLayoutData(gd_11);
			groupIdTFD=new Text(c, SWT.NONE);
			groupIdTFD.setLayoutData(gd_10);
			
			artifactIdLabel=new Label(c, SWT.NONE);
			artifactIdLabel.setText(" Artifact Id:");
		    artifactIdLabel.setLayoutData(gd_11);
			artifactIdTFD=new Text(c,SWT.NONE);
		    artifactIdTFD.setLayoutData(gd_10);
		    
		    
		    versionLabel=new Label(c, SWT.NONE);
		    versionLabel.setText(" Version:");
		    versionLabel.setLayoutData(gd_11);
		    versionTFD=new Text(c,SWT.NONE);
		    versionTFD.setLayoutData(gd_10);
		    
		    jdkVersionLabel=new Label(c, SWT.NONE);
		    jdkVersionLabel.setText(" jdk Version:");
		    jdkVersionLabel.setLayoutData(gd_11);
		    jdkVersionBox=new Combo(c, SWT.NONE);
		    jdkVersionBox.setLayoutData(gd_10);
		    jdkVersionBox.add("1.7");
		    jdkVersionBox.add("1.8");
		    
		    
		    Composite c3=new Composite(c, SWT.NONE);
		    GridLayout layout_1=new GridLayout();
		    layout_1.numColumns=2;
		    layout_1.marginLeft=0;
		    c3.setLayout(layout_1);
		    GridData gd_20=new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		    GridData gd_21=new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		    c3.setLayoutData(gd_20);
		    frameworkVersionLabel=new Label(c3, SWT.NONE);
		    frameworkVersionLabel.setText("MicroService Framework Version:");
		    frameworkVersionLabel.setLayoutData(gd_21);
	        frameworkVersionBox=new Combo(c3,SWT.NONE);
	        frameworkVersionBox.setLayoutData(gd_21);
	        frameworkVersionBox.add("1.0.0");
	        
	        portNumberLabel=new Label(c, SWT.NONE);
	        portNumberLabel.setText(" Port Number:");
	        portNumberLabel.setLayoutData(gd_11);
	        portNumberTFD=new Text(c,SWT.NONE);
	        portNumberTFD.setLayoutData(gd_10);
	 //基本信息配置控件添加完毕
	        
	 //以下是项目位置配置控件
	        c1=new Group(c, SWT.BORDER_SOLID);
	        c1.setText("Project location");
	        GridData gd_30=new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 2);
			GridData gd_31=new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
			GridData gd_32=new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			GridData gd_33=new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			GridLayout layout_2=new GridLayout();
			layout_2.numColumns=3;
			c1.setLayout(layout_2);
			c1.setLayoutData(gd_30);
			
			proLocationCBox=new Button(c1, SWT.CHECK);
			proLocationCBox.setText("Use default location");
			proLocationCBox.setLayoutData(gd_31);
			proLocationCBox.setSelection(true);
			proLocationCBox.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					proLocationTFD.setEnabled(proLocationCBox.getSelection());
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {}
			});
			
			locationLabel=new Label(c1, SWT.NONE);
			locationLabel.setText("Location:");
			locationLabel.setLayoutData(gd_32);
			
			proLocationTFD=new Text(c1,SWT.NONE);
			proLocationTFD.setLayoutData(gd_33);
			proLocationTFD.setEnabled(false);
			
			openDirectoryButton=new Button(c1, SWT.NONE);
			openDirectoryButton.setText("Browse");
			openDirectoryButton.setLayoutData(gd_32);
	 //项目位置配置控件添加完毕
			
	 //以下是项目位置配置控件
			c2=new Group(c, SWT.BORDER_SOLID);
	        c2.setText("项目信息");	
	        GridData gd_40=new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 3);
	        GridData gd_41=new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
	        GridData gd_42=new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
	        GridLayout layout_3=new GridLayout();
	        layout_3.numColumns=2;
	        c2.setLayout(layout_3);
	        c2.setLayoutData(gd_40);
	        
	        departmentLabel=new Label(c2, SWT.NONE);
	        departmentLabel.setLayoutData(gd_42);
	        departmentLabel.setText("部门：");
	        departmentTFD=new Text(c2,SWT.NONE);
	        departmentTFD.setLayoutData(gd_41);
	        
	        groupLabel=new Label(c2, SWT.NONE);
	        groupLabel.setLayoutData(gd_42);
	        groupLabel.setText("项目：");
	        proGroupTFD=new Text(c2,SWT.NONE);
	        proGroupTFD.setLayoutData(gd_41);
	        
	        emailLabel=new Label(c2, SWT.NONE);
	        emailLabel.setLayoutData(gd_42);
	        emailLabel.setText("联系邮箱：");
	        emailTFD=new Text(c2,SWT.NONE);
	        emailTFD.setLayoutData(gd_41);
	        
	 //项目信息配置控件添加完毕    
	}

}
