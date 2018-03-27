/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *显示表别名和字段的树，用于字段之间的关联，
 * 叶子节点是复选框，支持选中/取消，其余节点是字符串
 * @author ur
 */
public class RelTree extends JTree{
    public static void main(String[] args){
        RelTreeNode root=new RelTreeNode("000"), r1=new RelTreeNode("001"), r2=new RelTreeNode("002");
        root.add(r1);
        root.add(r2);
        RelTreeNode r11=new RelTreeNode("0011");
        RelTreeNode r21=new RelTreeNode("0021");
        r1.add(r11);
        r2.add(r21);
        JTree tree=new JTree(root);
        tree.setCellRenderer(new RelTreeCellRenderer());
        tree.addMouseListener(new RelTreeListener());
        JFrame frm=new JFrame();
        frm.add(tree);
        frm.setBounds(400, 200, 500, 300);
        frm.setVisible(true);
    }

    RelTree(RelTreeNode root) {
        super(root);
    }
}
/**
 * 自定义树节点类，含有标识选中状态的属性isSelected
 * @author ur
 */
class RelTreeNode extends DefaultMutableTreeNode{
    private boolean isSelected;
    private final String value;
    public RelTreeNode(String value){
        super(value);
        this.value=value;
        isSelected=false;
    }
    public boolean isSelected(){
        return isSelected;
    }
    public void setSelected(boolean isSelected){
        this.isSelected=isSelected;
    }
    public String getValue(){
        return value;
    }
    @Override
    public String toString(){
        return value;
    }
    
}
/**
 * 自定义单元格渲染器，显示一个复选框和一个标签，用于用户选中/取消
 */
class RelTreeCellRenderer extends JPanel implements TreeCellRenderer{
    private JCheckBox check;
    private JLabel label;
    public RelTreeCellRenderer(){
        add(check=new JCheckBox());
        add(label=new JLabel());
    }
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if(check!=null){
            check.setSelected(((RelTreeNode)value).isSelected());
        }
        if(label!=null){
            label.setText(((RelTreeNode)value).getValue());
        }
        if(leaf)
            return this;
        else
            return new JLabel(((RelTreeNode)value).getValue());
    }
    @Override
    public Dimension getPreferredSize(){
        Dimension dcheck=check.getPreferredSize();
        Dimension dlabel=label.getPreferredSize();
        return new Dimension(dcheck.width+dlabel.width,dcheck.height<dlabel.height?dlabel.height:dcheck.height);
    }
    @Override
    public void doLayout(){
        Dimension dcheck=check.getPreferredSize();
        Dimension dlabel=label.getPreferredSize();
        int ycheck=0, ylabel=0;
        if(dcheck.height>dlabel.height){
            ylabel=(dcheck.height-dlabel.height)/2;
        }else{
            ycheck=(dlabel.height-dcheck.height)/2;
        }
        check.setBounds(0, ycheck, dcheck.width, dcheck.height);
        label.setBounds(dcheck.width, ylabel, dlabel.width, dlabel.height);
    }
    
}
/**
 * 鼠标事件监听器，用于监听树的鼠标事件
 * @author ur
 */
class RelTreeListener extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent evt){
        if(evt.getButton()!=MouseEvent.BUTTON1)
            return;
        JTree tree=(JTree)evt.getSource();
        int x=evt.getX();
        int y=evt.getY();
        int row=tree.getRowForLocation(x, y);
        TreePath path=tree.getPathForRow(row);
        if(path!=null){
            RelTreeNode node=(RelTreeNode)path.getLastPathComponent();
            if(node!=null){
                boolean isSelected=node.isSelected();
                node.setSelected(!isSelected);
                if(node.isSelected()){  //如果该节点选中，其他兄弟节点设为取消
                   Enumeration enu=node.getParent().children();
                   while(enu.hasMoreElements()){
                       RelTreeNode o=(RelTreeNode) enu.nextElement();
                       if(o!=node){
                           o.setSelected(false);
                       }
                   }
                }
                
                ((DefaultTreeModel)tree.getModel()).nodeStructureChanged(node);
            }
        }
    }
}
