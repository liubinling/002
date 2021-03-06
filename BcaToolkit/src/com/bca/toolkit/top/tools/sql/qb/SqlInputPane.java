/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bca.toolkit.top.tools.sql.qb;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author ur
 */
public class SqlInputPane extends JScrollPane implements DragSourceListener, DragGestureListener{

    /**
     * Creates new form SqlInputPane
     */
    private JEditorPane sqlInputEditor;
    private static SqlInputPane inst;
    
    //DnD拖拽动作相关的属性
    private DragSource dragSource;
    
    public static SqlInputPane getInst(){
        if(inst == null)
            inst = new SqlInputPane();
        return inst;
    }
    private SqlInputPane() {
       // initComponents();
        wfinit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void wfinit() {
        setLayout(new ScrollPaneLayout());
        sqlInputEditor = new JEditorPane();
        sqlInputEditor.setEditable(true);
        setViewportView(sqlInputEditor);
        //构建DnD拖拽动作
        dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(sqlInputEditor, DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
       
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
      
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
       
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
        
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        JEditorPane sourceEditorPane = (JEditorPane) dge.getComponent();
        String sourceSql = sourceEditorPane.getText();
        TransferableForString transString = new TransferableForString(sourceSql);
        dge.startDrag(DragSource.DefaultCopyDrop, transString, this);
    }
//用于拖拽动作中数据传输的类
    private class TransferableForString implements Transferable{
        private String sourceSql;
        private DataFlavor[] flavors;
        public TransferableForString(String sourceSql) {
            this.sourceSql = sourceSql;
            flavors = new DataFlavor[]{DataFlavor.stringFlavor};
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            for(DataFlavor f : flavors ){
                if(f.equals(flavor))
                    return true;
            }
            return false;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if(isDataFlavorSupported(flavor)){
                return sourceSql;
            }
            return "";
        }
        
    }
}
