/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FactoryUiSwitch_Hxhk.java
 *
 * Created on 2012-8-27, 10:28:15
 */
package com.bca.toolkit.top.tools.sql.impl.boncLink;

import com.bca.api.pub.Ret;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Numtool;
import com.bca.pub.tools.Wftool;
import com.bca.templ.pub.AbstractStudioApp;
import com.bca.toolkit.top.tools.sql.I_UiSwitch;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.SrcFactoryBean;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author pxz
 */
public class FactoryUiSwitch_BoncLink extends javax.swing.JDialog implements I_UiSwitch {

    SqlCreateModel sqlModel;
    private boolean reloadModelRequest = false;

    /** Creates new form FactoryUiSwitch_Hxhk */
    public FactoryUiSwitch_BoncLink() {
        super(AbstractStudioApp.getMainFrameOnPossible(), true);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        criteriaBox = new javax.swing.JCheckBox();
        listdevBox = new javax.swing.JCheckBox();
        addBox = new javax.swing.JCheckBox();
        updateBox = new javax.swing.JCheckBox();
        viewBox = new javax.swing.JCheckBox();
        expBox = new javax.swing.JCheckBox();
        impBox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deleteBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        compPerRowField = new javax.swing.JTextField();
        confirmReplaceAtDosBox = new javax.swing.JCheckBox();
        restButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        saveButton = new javax.swing.JButton();
        prtBox = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UI开关 -- 国信框架");
        setName("Form"); // NOI18N

        jLabel1.setText("UI开关选项:");
        jLabel1.setName("jLabel1"); // NOI18N

        criteriaBox.setText("查询条件");
        criteriaBox.setName("criteriaBox"); // NOI18N

        listdevBox.setText("listdev");
        listdevBox.setName("listdevBox"); // NOI18N

        addBox.setText("add");
        addBox.setName("addBox"); // NOI18N

        updateBox.setText("update");
        updateBox.setName("updateBox"); // NOI18N

        viewBox.setText("view");
        viewBox.setName("viewBox"); // NOI18N

        expBox.setText("exp");
        expBox.setName("expBox"); // NOI18N

        impBox.setText("imp");
        impBox.setName("impBox"); // NOI18N

        okButton.setText("确定");
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("取消");
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        deleteBox.setText("delete");
        deleteBox.setName("deleteBox"); // NOI18N

        jLabel2.setText("详细表单行的控件数:");
        jLabel2.setName("jLabel2"); // NOI18N

        compPerRowField.setText(org.openide.util.NbBundle.getMessage(FactoryUiSwitch_BoncLink.class, "FactoryUiSwitch_BoncLink.compPerRowField.text")); // NOI18N
        compPerRowField.setName("compPerRowField"); // NOI18N

        confirmReplaceAtDosBox.setSelected(true);
        confirmReplaceAtDosBox.setText("覆盖前确认");
        confirmReplaceAtDosBox.setName("confirmReplaceAtDosBox"); // NOI18N

        restButton.setText("恢复模型");
        restButton.setName("restButton"); // NOI18N
        restButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restButtonActionPerformed(evt);
            }
        });

        jSeparator2.setName("jSeparator2"); // NOI18N

        saveButton.setText("保存模型");
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        prtBox.setText("prt");
        prtBox.setName("prtBox"); // NOI18N

        jSeparator3.setName("jSeparator3"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(criteriaBox)
                                    .addComponent(addBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listdevBox)
                                    .addComponent(deleteBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(updateBox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(expBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(impBox))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compPerRowField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirmReplaceAtDosBox)))
                        .addGap(18, 18, 18)
                        .addComponent(prtBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(saveButton)
                .addGap(31, 31, 31)
                .addComponent(restButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addGap(75, 75, 75)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(listdevBox)
                                    .addComponent(expBox)
                                    .addComponent(impBox)
                                    .addComponent(prtBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(deleteBox)
                                    .addComponent(updateBox)
                                    .addComponent(viewBox)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(criteriaBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addBox)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(compPerRowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmReplaceAtDosBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(restButton))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    updateUiSwitches();
    this.setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
    updateUiSwitches();
    //保存表中注释一列的内容
    saveSqlComment();
    String dir = System.getProperty("user.dir") + "\\model";
    Filetool.createPaths(dir);
    File file = new File(dir + "\\t_" + sqlModel.dbTable.getName() + ".smodel");
    JFileChooser fc = new JFileChooser(dir);
    fc.setSelectedFile(file);
    fc.showOpenDialog(AbstractStudioApp.getMainFrameOnPossible());
    File f = fc.getSelectedFile();
    //
    if (f != null) {
        Ret r = Filetool.saveSerializeFile(f.getAbsolutePath(), sqlModel.activeFactory);
        Wftool.messageRet(r, "保存模型", "ok:" + r.getInfoString(), "错误:" + r.getInfoString());
    }
}//GEN-LAST:event_saveButtonActionPerformed

private void restButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restButtonActionPerformed
    String dir = System.getProperty("user.dir") + "\\model";
    Filetool.createPaths(dir);
    File file = new File(dir + "\\t_" + sqlModel.dbTable.getName() + ".smodel");
    JFileChooser fc = new JFileChooser(dir);
    fc.setSelectedFile(file);
    fc.showOpenDialog(AbstractStudioApp.getMainFrameOnPossible());
    File f = fc.getSelectedFile();
    //
    if (f != null) {
        Ret r = new Ret();
        SrcFactoryBean bean = (SrcFactoryBean) Filetool.restoreObjFromSerialzeFile(f.getAbsolutePath(), r);
        sqlModel.loadFrom(bean.getSmodel());
        sqlModel.activeFactory.loadFrom(bean);
        // 
        Wftool.messageRet(r, "恢复模型", "ok:" + r.getInfoString(), "错误:" + r.getInfoString());
        if (r.isRetSuccess()) {
            loadSwitches(sqlModel);
            reloadModelRequest = true;
        }
    }
}//GEN-LAST:event_restButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField compPerRowField;
    private javax.swing.JCheckBox confirmReplaceAtDosBox;
    private javax.swing.JCheckBox criteriaBox;
    private javax.swing.JCheckBox deleteBox;
    private javax.swing.JCheckBox expBox;
    private javax.swing.JCheckBox impBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JCheckBox listdevBox;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox prtBox;
    private javax.swing.JButton restButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox updateBox;
    private javax.swing.JCheckBox viewBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void loadSwitches(SqlCreateModel sqlModel) {
        this.sqlModel = sqlModel;
        criteriaBox.setSelected(!sqlModel.isFieldMasked("ui", "Info.jsp"));
        listdevBox.setSelected(!sqlModel.isFieldMasked("ui", "Info-listdev.jsp"));
        addBox.setSelected(!sqlModel.isFieldMasked("ui", "Info-add.jsp"));
        updateBox.setSelected(!sqlModel.isFieldMasked("ui", "Info-update.jsp"));
        viewBox.setSelected(!sqlModel.isFieldMasked("ui", "Info-view.jsp"));
        expBox.setSelected(!sqlModel.isFieldMasked("ui", "exp"));
        impBox.setSelected(!sqlModel.isFieldMasked("ui", "imp"));
        deleteBox.setSelected(!sqlModel.isFieldMasked("ui", "delete"));
        //
        compPerRowField.setText(Integer.toString(sqlModel.getCompPerRow()));
        this.confirmReplaceAtDosBox.setSelected(sqlModel.isConfirmReplaceAtDos());
    }

    private void setMaskSwitch(boolean selected, String uiModuleName) {
        if (selected) {
            sqlModel.removeFieldMask("ui", uiModuleName);
        } else {
            sqlModel.maskField("ui", uiModuleName);
        }
    }

    /**
     * @return the reloadModelRequest
     */
    @Override
    public boolean isReloadModelRequest() {
        return reloadModelRequest;
    }

    private void updateUiSwitches() {
        setMaskSwitch(criteriaBox.isSelected(), "Info.jsp");
        setMaskSwitch(listdevBox.isSelected(), "Info-listdev.jsp");
        setMaskSwitch(addBox.isSelected(), "Info-add.jsp");
        setMaskSwitch(updateBox.isSelected(), "Info-update.jsp");
        setMaskSwitch(viewBox.isSelected(), "Info-view.jsp");
        setMaskSwitch(expBox.isSelected(), "exp");
        setMaskSwitch(impBox.isSelected(), "imp");
        setMaskSwitch(deleteBox.isSelected(), "delete");
        setMaskSwitch(prtBox.isSelected(), "print");
        

        sqlModel.setCompPerRow(Numtool.parseInt(compPerRowField.getText(), 2));
        sqlModel.setConfirmReplaceAtDos(confirmReplaceAtDosBox.isSelected());
    }
//保存表中注释一列的内容
    private void saveSqlComment() {
       sqlModel.saveSqlComment();
    }
}
