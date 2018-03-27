package bcaplugins.jfaceViewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import com.bca.db.meta.unit.FilesInfo;

import bcaplugins.handlers.OpenFilesListAction;

public class FilesListProvider implements ITreeContentProvider, PropertyChangeListener{
	private TreeViewer viewer;   //储存查看器对象
	private Logger logger=OpenFilesListAction.getLogger();
	@Override
	public Object[] getElements(Object arg0) {
		if(arg0 instanceof FilesListModel)
			return new Object[]{((FilesListModel)arg0).getRoot()};
		return new Object[0];
	}
	/**
	 * 当所监听的模型对象发生更换时，触发该事件，去除对旧对象的监听，增加对新对象的监听
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput){
		this.viewer=(TreeViewer) viewer;
		if(oldInput instanceof FilesListModel){
			((FilesListModel)oldInput).removePropertyChangeListener(this);
		}
		if(newInput instanceof FilesListModel){
			((FilesListModel)newInput).addPropertyChangeListener(this);
		}
		
	}
	/**
	 * 当所监听的模型的数据发生修改时，触发该事件
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(FilesListModel.getADD_ELEMENT())){
			Object[] o=(Object[]) evt.getNewValue();
			viewer.add(o[0],o[1]);
		}else if(evt.getPropertyName().equals(FilesListModel.getREMOVE_ELEMENT())){
			viewer.remove(evt.getNewValue());
		}
		
	}
	@Override
	public Object[] getChildren(Object arg0) {
		return ((FilesInfo)arg0).getChildren().toArray();
	}
	@Override
	public Object getParent(Object arg0) {
		return ((FilesInfo)arg0).getParent();
	}
	@Override
	public boolean hasChildren(Object arg0) {
		ArrayList<FilesInfo> children=(ArrayList<FilesInfo>)((FilesInfo)arg0).getChildren();
		return !(children==null||children.isEmpty());
	}

}
