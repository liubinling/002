package bcaplugins.jfaceViewer;

import java.io.InputStream;
import java.util.logging.Logger;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.bca.db.meta.unit.FilesInfo;

import bcaplugins.handlers.OpenFilesListAction;

public class FilesListLabelProvider implements ITableLabelProvider{
	private Logger logger=OpenFilesListAction.getLogger();
	@Override
	public void addListener(ILabelProviderListener arg0) {}

	@Override
	public void dispose() {}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		if(arg1==0){
			
			//String filename="C:\\Users\\ur\\git\\Plug_In_Project\\icons\\rainDrops.jpg";
			String filename="/icons/cupid.jpg";
			InputStream in=FilesListLabelProvider.class.getResourceAsStream(filename);
			ImageData imageData=new ImageData(in);
			imageData=imageData.scaledTo(18, 13);
			return new Image(Display.getDefault(),imageData);
		}
		logger.info("getImage����������");
		return null;
		
	}
	/**
	 * ��Ԫ��ת�����ı�����
	 * @param arg0
	 */
	
	@Override
	public String getColumnText(Object arg0, int arg1) {
		logger.info("getText����������");
		
		if(arg0 instanceof FilesInfo&&arg1==0){
			logger.info("��ǩ�ṩ�߷��ص��ı��� "+((FilesInfo)arg0).getFilePath());
			return ((FilesInfo)arg0).getFilePath();
		}
		logger.info("FilesListLabelProvider.getText(Object arg0)��    �Ӳ鿴�����ݹ�����Ԫ�ز���FilesInfo����");
		return null;
	}

}
