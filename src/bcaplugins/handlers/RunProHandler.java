package bcaplugins.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.bca.toolkit.top.tools.sql.SqlCreateModel;

public class RunProHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("URL=====================================================");
			System.out.println(SqlCreateModel.getUrl()+"==============================");
			SqlCreateModel.browse(SqlCreateModel.getUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return arg0;
	}

}
