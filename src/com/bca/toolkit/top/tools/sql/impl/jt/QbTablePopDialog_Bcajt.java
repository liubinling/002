/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QbTablePopDialog.java
 *
 * Created on 2010-11-8, 23:00:43
 */
package com.bca.toolkit.top.tools.sql.impl.jt;

import com.bca.db.meta.I_WfColumn;
import com.bca.templ.pub.AbstractApp;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.QbJTable;
import com.bca.toolkit.top.tools.sql.qb.I_ColBatchDlg;

/**
 *
 * @author pxz
 */
public class QbTablePopDialog_Bcajt extends javax.swing.JDialog implements I_ColBatchDlg {

    private QbJTable qbJTable;

    /** Creates new form QbTablePopDialog */
    public QbTablePopDialog_Bcajt() {
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
        criteriaOpen = new javax.swing.JButton();
        criteriaMask = new javax.swing.JButton();
        listdevOpen = new javax.swing.JButton();
        listDevMask = new javax.swing.JButton();
        addOpen = new javax.swing.JButton();
        addMask = new javax.swing.JButton();
        delEStyleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("列批量操作"); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.jPanel1.border.title_1"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        criteriaOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.criteriaOpen.text_1")); // NOI18N
        criteriaOpen.setName("criteriaOpen"); // NOI18N
        criteriaOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criteriaOpenActionPerformed(evt);
            }
        });

        criteriaMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.criteriaMask.text_1")); // NOI18N
        criteriaMask.setName("criteriaMask"); // NOI18N
        criteriaMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criteriaMaskActionPerformed(evt);
            }
        });

        listdevOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.listdevOpen.text_1")); // NOI18N
        listdevOpen.setName("listdevOpen"); // NOI18N
        listdevOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listdevOpenActionPerformed(evt);
            }
        });

        listDevMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.listDevMask.text_1")); // NOI18N
        listDevMask.setName("listDevMask"); // NOI18N
        listDevMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listDevMaskActionPerformed(evt);
            }
        });

        addOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.addOpen.text_1")); // NOI18N
        addOpen.setName("addOpen"); // NOI18N
        addOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOpenActionPerformed(evt);
            }
        });

        addMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.addMask.text_1")); // NOI18N
        addMask.setName("addMask"); // NOI18N
        addMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaskActionPerformed(evt);
            }
        });

        delEStyleButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_Bcajt.class, "QbTablePopDialog_Bcajt.delEStyleButton.text")); // NOI18N
        delEStyleButton.setName("delEStyleButton"); // NOI18N
        delEStyleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delEStyleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(criteriaOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(listdevOpen))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(criteriaMask)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(listDevMask)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addMask)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addOpen)
                        .addGap(18, 18, 18)
                        .addComponent(delEStyleButton)))
                .addGap(98, 98, 98))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(criteriaOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listdevOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delEStyleButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(criteriaMask)
                    .addComponent(listDevMask)
                    .addComponent(addMask)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        getQbJTable().onPopDialogClosed();
    }//GEN-LAST:event_formWindowClosed

private void criteriaOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criteriaOpenActionPerformed
    setSelectedFieldsSW("colSwitch_criteria", true);
}//GEN-LAST:event_criteriaOpenActionPerformed

private void criteriaMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criteriaMaskActionPerformed
    setSelectedFieldsSW("colSwitch_criteria", false);
}//GEN-LAST:event_criteriaMaskActionPerformed

private void listdevOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listdevOpenActionPerformed
    setSelectedFieldsSW("colSwitch_list", true);
}//GEN-LAST:event_listdevOpenActionPerformed

private void listDevMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDevMaskActionPerformed
    setSelectedFieldsSW("colSwitch_list", false);
}//GEN-LAST:event_listDevMaskActionPerformed

private void addOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOpenActionPerformed
    setSelectedFieldsSW("colSwitch_detail", true);
}//GEN-LAST:event_addOpenActionPerformed

private void addMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaskActionPerformed
    setSelectedFieldsSW("colSwitch_detail", false);
}//GEN-LAST:event_addMaskActionPerformed

    private void delEStyleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delEStyleButtonActionPerformed
        SqlCreateModel sm = qbJTable.getScene().getModel().getSqlModel();
        for (int row : qbJTable.getSelectedRows()) {
            I_WfColumn col = (I_WfColumn) qbJTable.getModel().getValueAt(row, 0);
            if (col != null) {
                sm.removeUiStyle(col.getSqlName());
            }
        }
        qbJTable.refreshSqlPreview(true);
        updateJTableUI();
    }//GEN-LAST:event_delEStyleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMask;
    private javax.swing.JButton addOpen;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton criteriaMask;
    private javax.swing.JButton criteriaOpen;
    private javax.swing.JButton delEStyleButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton listDevMask;
    private javax.swing.JButton listdevOpen;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the qbJTable
     */
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
        this.setTitle(String.format("表<%s>:列批量动作 (国信框架)", qbJTable.getModel().getTbean().getTableDetail().getName()));
    }

    private void updateJTableUI() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                qbJTable.updateUI();
            }
        });
    }

    private void setSelectedFieldsSW(String grp, boolean sw) {
        SqlCreateModel sm = qbJTable.getScene().getModel().getSqlModel();
        for (int row : qbJTable.getSelectedRows()) {
            I_WfColumn col = (I_WfColumn) qbJTable.getModel().getValueAt(row, 0);
            if (col != null) {
                if (sw) {
                    sm.removeFieldMask(grp, col.getSqlName());
                } else {
                    sm.maskField(grp, col.getSqlName());
                }
            }
        }
        qbJTable.refreshSqlPreview(true);
        updateJTableUI();
    }
}
