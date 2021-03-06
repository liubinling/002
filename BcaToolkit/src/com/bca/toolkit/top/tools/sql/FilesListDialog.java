/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.util.Exceptions;

/**
 *
 * @author ur
 */
public class FilesListDialog extends javax.swing.JDialog {

    /**
     * @return the isDefaultClose
     */
    public boolean isIsDefaultClose() {
        return isDefaultClose;
    }

    /**
     * @param isDefaultClose the isDefaultClose to set
     */
    public void setIsDefaultClose(boolean isDefaultClose) {
        this.isDefaultClose = isDefaultClose;
    }

  
    private DefaultListModel listModel2;
    private JPanel fileListPanel;
    private Map<String, Boolean> filesAndStateMap;
    private Map<String, String> relateFiles;
    private boolean isDefaultClose=false;
    /**
     * Creates new form FilesListDialog
     */
    public FilesListDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public FilesListDialog(Map<String, String> files, java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        wfinit(files);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        overrideScrollPane = new javax.swing.JScrollPane();
        non_overrideScrollPane = new javax.swing.JScrollPane();
        non_overrideFilesList = new javax.swing.JList();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        runButton1 = new javax.swing.JButton();

        setTitle("文件部署对话框");
        setModal(true);

        overrideScrollPane.setBorder(null);
        overrideScrollPane.setPreferredSize(new java.awt.Dimension(1000, 1000));

        non_overrideScrollPane.setBorder(null);
        non_overrideScrollPane.setPreferredSize(new java.awt.Dimension(750, 200));

        non_overrideFilesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        non_overrideScrollPane.setViewportView(non_overrideFilesList);

        cancelButton.setText(org.openide.util.NbBundle.getMessage(FilesListDialog.class, "FilesListDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText(org.openide.util.NbBundle.getMessage(FilesListDialog.class, "FilesListDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        runButton1.setText(org.openide.util.NbBundle.getMessage(FilesListDialog.class, "FilesListDialog.runButton1.text")); // NOI18N
        runButton1.setToolTipText(org.openide.util.NbBundle.getMessage(FilesListDialog.class, "FilesListDialog.runButton1.toolTipText")); // NOI18N
        runButton1.setEnabled(false);
        runButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(overrideScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(non_overrideScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(47, 47, 47)
                .addComponent(cancelButton)
                .addGap(47, 47, 47)
                .addComponent(runButton1)
                .addGap(308, 308, 308))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overrideScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(non_overrideScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(runButton1))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//点击确定按钮，保存用户选择状态
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelCreateCode();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        saveUserSelection();
    }//GEN-LAST:event_okButtonActionPerformed

    private void runButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButton1ActionPerformed
        try {
            SqlCreateModel.browse(SqlCreateModel.getUrl());
        } catch (Exception ex) {
            System.out.println("打开项目默认访问页面时出现错误： "+ex.toString());
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_runButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getInstalledLookAndFeels()[3].getClassName());
                    break;
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FilesListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilesListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilesListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilesListDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Map<String, String> files = new HashMap<String,String>();
                files.put("file 1", null);
                files.put("file 2", null);
                files.put("file 3", null);
                files.put("file 11", null);
                files.put("file 12", null);
                files.put("file 13", null);
                files.put("file 21", null);
                files.put("file 22", null);
                files.put("file 23", null);
                files.put("file 4", "time4");
                files.put("file 5", "time4");
                files.put("file 6", "time4");
                files.put("file 7", "time4");
                files.put("file 8", "time4");
                files.put("file 9", "time4");
                files.put("file 10", "time4");
                files.put("file 14", "time4");
                
                FilesListDialog dialog = new FilesListDialog(files,new javax.swing.JFrame(), true);
                SwingUtilities.updateComponentTreeUI(dialog);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JList non_overrideFilesList;
    private javax.swing.JScrollPane non_overrideScrollPane;
    private javax.swing.JButton okButton;
    private javax.swing.JScrollPane overrideScrollPane;
    private javax.swing.JButton runButton1;
    // End of variables declaration//GEN-END:variables
    //初始化对话框中的两个列表框
    private void wfinit(Map<String, String> files) {
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        addWindowListener(new WindowAdapter(){
            @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        setVisible(false);
                        BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);
                        isDefaultClose=true;
                       // System.exit(0);
                    }
        });
        //为窗体设置一个图标
        URL url=this.getClass().getResource("/res/img/filesList.jpg");
        Image image=Toolkit.getDefaultToolkit().getImage(url).getScaledInstance(-1, 26, Image.SCALE_DEFAULT);
        this.setIconImage(image);
        //图标设置完毕
        setTitle("文件部署对话框");
        cancelButton.setText("取消");
        setLocation(300,200);
        //创建一个边框
        Border overrideBorder = BorderFactory.createEtchedBorder();
        Border non_overrideBorder = BorderFactory.createEtchedBorder();
        overrideBorder = BorderFactory.createTitledBorder(overrideBorder, "与目标目录有重名的文件", TitledBorder.LEFT, TitledBorder.TOP, getFont(), getForeground());
               
        non_overrideBorder = BorderFactory.createTitledBorder(non_overrideBorder, "与目标目录无重名的文件", TitledBorder.LEFT, TitledBorder.TOP, getFont(), getForeground());
        overrideScrollPane.setBorder(overrideBorder);
        non_overrideScrollPane.setBorder(non_overrideBorder);
        listModel2 = new DefaultListModel();
        // 创建一个面板用于展示有重名的文件列表
        fileListPanel=new JPanel();
     //   fileListPanel.setSize(new Dimension(frameWidth/5*4, (int)(frameHeight/2.2))); 
        fileListPanel.setLayout(new GridLayout(files.size()+1, 1));
        relateFiles=new HashMap<String, String>();
        for(Map.Entry<String, String> entry:files.entrySet()){
            if(entry.getValue()==null) listModel2.addElement(entry.getKey());   //无重名的文件
            else {
                relateFiles.put(entry.getKey()+" "+entry.getValue(), entry.getKey()+"");
                JCheckBox temp=new JCheckBox(entry.getKey()+" "+entry.getValue(), true);
                temp.setBackground(new Color(204,232,207));
                fileListPanel.add(temp);   //有重名的文件
            }
        }
        if(fileListPanel.getComponents().length>0){    //存在重名文件，才添加全选复选框
            JCheckBox allSelectedItem = new JCheckBox("全选", true);
            allSelectedItem.setBackground(new Color(204,232,207));
            allSelectedItem.addChangeListener(new ChangeListener(){
                //点击全选复选框，改变所有复选框的状态
                @Override
                public void stateChanged(ChangeEvent e) {
                   JCheckBox temp=(JCheckBox) e.getSource();
                   Component[] files=fileListPanel.getComponents();
                   for(Component file:files){
                       JCheckBox file1=(JCheckBox)file;
                       file1.setSelected(temp.isSelected());
                   }
                }
            });
            fileListPanel.add(allSelectedItem,0);
        } 
        overrideScrollPane.setViewportView(fileListPanel);
        non_overrideFilesList.setModel(listModel2);
    }  
      /**
     * @return the filesAndStateMap
     */
    public Map<String, Boolean> getFilesAndStateMap() {
        return filesAndStateMap;
    }

    /**
     * @param filesAndStateMap the filesAndStateMap to set
     */
    public void setFilesAndStateMap(Map<String, Boolean> filesAndStateMap) {
        this.filesAndStateMap = filesAndStateMap;
    }
//点击确定按钮，保存用户选择状态
    private void saveUserSelection() {
        setFilesAndStateMap((Map<String, Boolean>) new HashMap());
        Component[] files=fileListPanel.getComponents();   //保存有重名文件及其选择状态
            for(int i=1;i<files.length;i++){
                   JCheckBox file1=(JCheckBox)files[i];
                   getFilesAndStateMap().put((String)(relateFiles.get(file1.getText())),file1.isSelected());
            }
            int[] indices = new int[non_overrideFilesList.getModel().getSize()];
            for(int i=0;i<non_overrideFilesList.getModel().getSize();i++){
                indices[i]=i;
            }
            non_overrideFilesList.setSelectedIndices(indices);
            for(Object str:non_overrideFilesList.getSelectedValues()){  //保存无重名文件，状态均为true
                String str1=(String)str;
                getFilesAndStateMap().put(str1, true);
            }
           setVisible(false);
           
           
    }
    //点击取消按钮，取消创建代码
    private void cancelCreateCode(){
        setVisible(false);
        BcaTool_SqlBuilderTopForm.setIsDirectlyCopyFiles(false);
        isDefaultClose=true;
    }

    /**
     * @return the runButton1
     */
    public javax.swing.JButton getRunButton1() {
        return runButton1;
    }

    /**
     * @param runButton1 the runButton1 to set
     */
    public void setRunButton1(javax.swing.JButton runButton1) {
        this.runButton1 = runButton1;
    }
   }
