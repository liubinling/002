package bcaplugins.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
/**
 * 创建一个新建项目向导，它将包含4页向导页
 * @author ur
 *
 */
public class NewMicroServiceWizard extends Wizard implements INewWizard {

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void addPages(){
		NewMicroServicePage_1 page1=new NewMicroServicePage_1("page1");
		addPage(page1);
		
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		Display display=Display.getDefault();
		Shell shell=new Shell(display, SWT.SHELL_TRIM);
		IWizard wizard=new NewMicroServiceWizard();
		WizardDialog wdialog=new WizardDialog(shell,wizard);
		wdialog.open();

	}

}
