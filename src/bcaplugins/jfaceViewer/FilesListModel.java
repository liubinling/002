package bcaplugins.jfaceViewer;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.sun.media.jfxmedia.logging.Logger;

public class FilesListModel {
	private com.bca.db.meta.unit.FilesInfo root;   			//�洢�ļ��б�
	private PropertyChangeSupport delegate;		//����ע����javabean�ϵļ������Ĵ���
	private final static String ADD_ELEMENT="add_element";
	private final static String REMOVE_ELEMENT="remove_element";
	public static String getADD_ELEMENT() {
		return ADD_ELEMENT;
	}

	public static String getREMOVE_ELEMENT() {
		return REMOVE_ELEMENT;
	}
	//���ظ��ڵ�
	public com.bca.db.meta.unit.FilesInfo getRoot(){
		return root;
	}
	public FilesListModel(com.bca.db.meta.unit.FilesInfo root){
		this.root=root;;
		delegate=new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		delegate.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		delegate.removePropertyChangeListener(listener);
	}
	/**
	 * ���һ��Ԫ��
	 * @param element
	 */
	public void add(int[] nodePath, com.bca.db.meta.unit.FilesInfo newNode){
		com.bca.db.meta.unit.FilesInfo parent=findNode(nodePath);
		if(parent!=null&&!parent.getChildren().contains(newNode)) {
			if(parent.getChildren().add(newNode)){
				newNode.setParent(parent);
				delegate.firePropertyChange(new PropertyChangeEvent(this, ADD_ELEMENT, null, new Object[]{nodePath, newNode}));
			}
			
		}
	}
	/**
	 * �����ж�λһ���ڵ�
	 */
	private com.bca.db.meta.unit.FilesInfo findNode(int[] nodePath){
		com.bca.db.meta.unit.FilesInfo current=root;
		if(current==null) return null;
		if(nodePath==null||nodePath.length==0) return current;
		for(int path:nodePath){
			ArrayList<com.bca.db.meta.unit.FilesInfo> children=current.getChildren();
			if(children==null||children.isEmpty()||path<0||path>=children.size()){
				return null;
			}
			current=children.get(path);
		}
		return current;
		
	}
	
	/**
	 * ɾ��һ��Ԫ��
	 * @param element
	 */
	public void remove(int[] nodePath){
		com.bca.db.meta.unit.FilesInfo delNode=findNode(nodePath);
		if(delNode!=null&&delNode.getParent().getChildren().remove(delNode)){
			delNode.setParent(null);
			delegate.firePropertyChange(new PropertyChangeEvent(this, REMOVE_ELEMENT, null, delNode));
		}
		
	}
}
