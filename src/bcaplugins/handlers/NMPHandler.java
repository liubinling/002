package bcaplugins.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import microservice_framework.newboncproject.NewBoncProject;

public class NMPHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		String[] args={"1"};
		new NewBoncProject();
		NewBoncProject.main(args);
		return null;
	}

}
