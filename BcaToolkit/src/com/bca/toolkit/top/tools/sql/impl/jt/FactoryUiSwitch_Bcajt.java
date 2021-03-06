/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FactoryUiSwitch_Bcajt.java
 *
 * Created on 2012-9-3, 22:38:47
 */
package com.bca.toolkit.top.tools.sql.impl.jt;

import com.bca.templ.pub.AbstractStudioApp;
import com.bca.toolkit.top.tools.sql.I_UiSwitch;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;

/**
 *
 * @author pxz
 */
public class FactoryUiSwitch_Bcajt extends javax.swing.JDialog implements I_UiSwitch {

    SqlCreateModel sqlModel;

    /** Creates new form FactoryUiSwitch_GuoXin */
    public FactoryUiSwitch_Bcajt() {
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

        detailBox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        detailBox.setText(org.openide.util.NbBundle.getMessage(FactoryUiSwitch_Bcajt.class, "FactoryUiSwitch_Bcajt.detailBox.text")); // NOI18N
        detailBox.setName("detailBox"); // NOI18N

        okButton.setText(org.openide.util.NbBundle.getMessage(FactoryUiSwitch_Bcajt.class, "FactoryUiSwitch_Bcajt.okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(org.openide.util.NbBundle.getMessage(FactoryUiSwitch_Bcajt.class, "FactoryUiSwitch_Bcajt.cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FactoryUiSwitch_Bcajt.class, "FactoryUiSwitch_Bcajt.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detailBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(okButton)
                        .addGap(38, 38, 38)
                        .addComponent(cancelButton)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(detailBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    setMaskSwitch(detailBox.isSelected(), "detailForm");

    this.setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox detailBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void loadSwitches(SqlCreateModel sqlModel) {
        this.sqlModel = sqlModel;
        detailBox.setSelected(!sqlModel.isFieldMasked("ui", "detailForm"));
    }

    private void setMaskSwitch(boolean selected, String uiModuleName) {
        if (selected) {
            sqlModel.removeFieldMask("ui", uiModuleName);
        } else {
            sqlModel.maskField("ui", uiModuleName);
        }
    }

    @Override
    public boolean isReloadModelRequest() {
        return false;
    }
}
