/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QbTablePopDialog.java
 *
 * Created on 2010-11-8, 23:00:43
 */
package com.bca.toolkit.top.tools.sql.impl.qry;

import com.bca.templ.pub.AbstractApp;
import com.bca.toolkit.top.tools.sql.qb.QbJTable;
import com.bca.toolkit.top.tools.sql.qb.I_ColBatchDlg;

/**
 *
 * @author pxz
 */
public class QbTablePopDialog_SQL extends javax.swing.JDialog implements I_ColBatchDlg {

    private QbJTable qbJTable;

    /** Creates new form QbTablePopDialog */
    public QbTablePopDialog_SQL() {
        super(AbstractApp.getMainFrameOnPossible(), false);
//        this.qbJTable = qbJTable;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        outputFieldsButton = new javax.swing.JButton();
        cancelOutputButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        groupButton = new javax.swing.JButton();
        cancelGroupButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        orderButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("����������"); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("�ֶ����"));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        outputFieldsButton.setText("����ֶ�"); // NOI18N
        buttonGroup1.add(outputFieldsButton);
        outputFieldsButton.setName("outputFieldsButton"); // NOI18N
        outputFieldsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFieldsButtonActionPerformed(evt);
            }
        });
        jPanel1.add(outputFieldsButton);

        cancelOutputButton.setText("ȡ�����"); // NOI18N
        buttonGroup1.add(cancelOutputButton);
        cancelOutputButton.setName("cancelOutputButton"); // NOI18N
        cancelOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOutputButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelOutputButton);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("group by"));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        groupButton.setText("�����ֶ�"); // NOI18N
        buttonGroup2.add(groupButton);
        groupButton.setEnabled(false);
        groupButton.setName("groupButton"); // NOI18N
        groupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupButtonActionPerformed(evt);
            }
        });
        jPanel2.add(groupButton);

        cancelGroupButton.setText("ȡ������"); // NOI18N
        buttonGroup2.add(cancelGroupButton);
        cancelGroupButton.setEnabled(false);
        cancelGroupButton.setName("cancelGroupButton"); // NOI18N
        cancelGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelGroupButtonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelGroupButton);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("order By"));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        orderButton.setText("�����ֶ�"); // NOI18N
        buttonGroup3.add(orderButton);
        orderButton.setEnabled(false);
        orderButton.setName("orderButton"); // NOI18N
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        jPanel3.add(orderButton);

        cancelOrderButton.setText("ȡ������"); // NOI18N
        buttonGroup3.add(cancelOrderButton);
        cancelOrderButton.setEnabled(false);
        cancelOrderButton.setName("cancelOrderButton"); // NOI18N
        cancelOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOrderButtonActionPerformed(evt);
            }
        });
        jPanel3.add(cancelOrderButton);

        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        getQbJTable().onPopDialogClosed();
    }//GEN-LAST:event_formWindowClosed

    private void outputFieldsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputFieldsButtonActionPerformed
//        getQbJTable().outputSelectedFields(true);
    }//GEN-LAST:event_outputFieldsButtonActionPerformed

    private void cancelOutputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOutputButtonActionPerformed
//        getQbJTable().outputSelectedFields(false);
    }//GEN-LAST:event_cancelOutputButtonActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        getQbJTable().orderbySelectedFields(true);
    }//GEN-LAST:event_orderButtonActionPerformed

    private void cancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOrderButtonActionPerformed
        getQbJTable().orderbySelectedFields(false);
    }//GEN-LAST:event_cancelOrderButtonActionPerformed

    private void groupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupButtonActionPerformed
        getQbJTable().groupbySelectedFields(true);
    }//GEN-LAST:event_groupButtonActionPerformed

    private void cancelGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelGroupButtonActionPerformed
        getQbJTable().groupbySelectedFields(false);
    }//GEN-LAST:event_cancelGroupButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton cancelGroupButton;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JButton cancelOutputButton;
    private javax.swing.JButton groupButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton orderButton;
    private javax.swing.JButton outputFieldsButton;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the qbJTable
     */
    @Override
    public QbJTable getQbJTable() {
        return qbJTable;
    }

    /**
     * @param qbJTable the qbJTable to set
     */
    public void setQbJTable(QbJTable qbJTable) {
        this.qbJTable = qbJTable;
    }

    @Override
    public void wfinit(QbJTable jtable) {
        this.qbJTable = jtable;
        this.setTitle(String.format("��<%s>:����������", qbJTable.getModel().getTbean().getTableDetail().getName()));
    }
}