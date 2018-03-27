/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QbTablePopDialog.java
 *
 * Created on 2010-11-8, 23:00:43
 */
package com.bca.toolkit.top.tools.sql.impl.gx;

import com.bca.db.meta.I_WfColumn;
import com.bca.templ.pub.AbstractApp;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.QbJTable;
import com.bca.toolkit.top.tools.sql.qb.I_ColBatchDlg;

/**
 *
 * @author pxz
 */
public class QbTablePopDialog_GuoXin extends javax.swing.JDialog implements I_ColBatchDlg {

    private QbJTable qbJTable;

    /**
     * Creates new form QbTablePopDialog
     */
    public QbTablePopDialog_GuoXin() {
        super(AbstractApp.getMainFrameOnPossible(), false);
//        this.qbJTable = qbJTable;
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        openAllFieldsButton = new javax.swing.JButton();
        criteriaOpen = new javax.swing.JButton();
        criteriaMask = new javax.swing.JButton();
        listdevOpen = new javax.swing.JButton();
        listDevMask = new javax.swing.JButton();
        addOpen = new javax.swing.JButton();
        addMask = new javax.swing.JButton();
        updateOpen = new javax.swing.JButton();
        updateMask = new javax.swing.JButton();
        viewOpen = new javax.swing.JButton();
        viewMask = new javax.swing.JButton();
        expOpen = new javax.swing.JButton();
        expMask = new javax.swing.JButton();
        impMask = new javax.swing.JButton();
        impOpen = new javax.swing.JButton();
        verifyOpenButton = new javax.swing.JButton();
        verifyCloseButton = new javax.swing.JButton();
        viewBindOpenButton = new javax.swing.JButton();
        viewBindCloseButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        groupButton = new javax.swing.JButton();
        cancelGroupButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        orderButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        setAsPkButton = new javax.swing.JButton();
        maskPkButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        bindingSeqButton = new javax.swing.JButton();
        unbindingSeqButton = new javax.swing.JButton();
        delEStyleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("����������"); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        openAllFieldsButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.openAllFieldsButton.text")); // NOI18N
        openAllFieldsButton.setName("openAllFieldsButton"); // NOI18N
        openAllFieldsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openAllFieldsButtonActionPerformed(evt);
            }
        });

        criteriaOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.criteriaOpen.text")); // NOI18N
        criteriaOpen.setName("criteriaOpen"); // NOI18N
        criteriaOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criteriaOpenActionPerformed(evt);
            }
        });

        criteriaMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.criteriaMask.text")); // NOI18N
        criteriaMask.setName("criteriaMask"); // NOI18N
        criteriaMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criteriaMaskActionPerformed(evt);
            }
        });

        listdevOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.listdevOpen.text")); // NOI18N
        listdevOpen.setName("listdevOpen"); // NOI18N
        listdevOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listdevOpenActionPerformed(evt);
            }
        });

        listDevMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.listDevMask.text")); // NOI18N
        listDevMask.setName("listDevMask"); // NOI18N
        listDevMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listDevMaskActionPerformed(evt);
            }
        });

        addOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.addOpen.text")); // NOI18N
        addOpen.setName("addOpen"); // NOI18N
        addOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOpenActionPerformed(evt);
            }
        });

        addMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.addMask.text")); // NOI18N
        addMask.setName("addMask"); // NOI18N
        addMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaskActionPerformed(evt);
            }
        });

        updateOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.updateOpen.text")); // NOI18N
        updateOpen.setName("updateOpen"); // NOI18N
        updateOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOpenActionPerformed(evt);
            }
        });

        updateMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.updateMask.text")); // NOI18N
        updateMask.setName("updateMask"); // NOI18N
        updateMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMaskActionPerformed(evt);
            }
        });

        viewOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.viewOpen.text")); // NOI18N
        viewOpen.setName("viewOpen"); // NOI18N
        viewOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewOpenActionPerformed(evt);
            }
        });

        viewMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.viewMask.text")); // NOI18N
        viewMask.setName("viewMask"); // NOI18N
        viewMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMaskActionPerformed(evt);
            }
        });

        expOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.expOpen.text")); // NOI18N
        expOpen.setName("expOpen"); // NOI18N
        expOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expOpenActionPerformed(evt);
            }
        });

        expMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.expMask.text")); // NOI18N
        expMask.setName("expMask"); // NOI18N
        expMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expMaskActionPerformed(evt);
            }
        });

        impMask.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.impMask.text")); // NOI18N
        impMask.setName("impMask"); // NOI18N
        impMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impMaskActionPerformed(evt);
            }
        });

        impOpen.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.impOpen.text")); // NOI18N
        impOpen.setName("impOpen"); // NOI18N
        impOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impOpenActionPerformed(evt);
            }
        });

        verifyOpenButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.verifyOpenButton.text")); // NOI18N
        verifyOpenButton.setName("verifyOpenButton"); // NOI18N
        verifyOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyOpenButtonActionPerformed(evt);
            }
        });

        verifyCloseButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.verifyCloseButton.text")); // NOI18N
        verifyCloseButton.setName("verifyCloseButton"); // NOI18N
        verifyCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyCloseButtonActionPerformed(evt);
            }
        });

        viewBindOpenButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.viewBindOpenButton.text")); // NOI18N
        viewBindOpenButton.setName("viewBindOpenButton"); // NOI18N
        viewBindOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBindOpenButtonActionPerformed(evt);
            }
        });

        viewBindCloseButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.viewBindCloseButton.text")); // NOI18N
        viewBindCloseButton.setName("viewBindCloseButton"); // NOI18N
        viewBindCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBindCloseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(criteriaOpen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(listdevOpen))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(criteriaMask)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(listDevMask))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(verifyOpenButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewBindOpenButton)))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addMask)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateMask)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewMask))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addOpen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateOpen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewOpen))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(verifyCloseButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewBindCloseButton))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(expMask)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(impOpen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(impMask))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openAllFieldsButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(viewOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listdevOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(criteriaOpen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(criteriaMask)
                    .addComponent(listDevMask)
                    .addComponent(addMask)
                    .addComponent(updateMask)
                    .addComponent(viewMask))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verifyOpenButton)
                    .addComponent(viewBindOpenButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verifyCloseButton)
                    .addComponent(viewBindCloseButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(openAllFieldsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(expOpen)
                            .addComponent(expMask)
                            .addComponent(impOpen)
                            .addComponent(impMask))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        setAsPkButton.setText("��Ϊ����");
        setAsPkButton.setName("setAsPkButton"); // NOI18N
        setAsPkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAsPkButtonActionPerformed(evt);
            }
        });
        jPanel4.add(setAsPkButton);

        maskPkButton.setText("�ر�����");
        maskPkButton.setName("maskPkButton"); // NOI18N
        maskPkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maskPkButtonActionPerformed(evt);
            }
        });
        jPanel4.add(maskPkButton);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        bindingSeqButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.bindingSeqButton.text")); // NOI18N
        bindingSeqButton.setName("bindingSeqButton"); // NOI18N
        bindingSeqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bindingSeqButtonActionPerformed(evt);
            }
        });
        jPanel5.add(bindingSeqButton);

        unbindingSeqButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.unbindingSeqButton.text")); // NOI18N
        unbindingSeqButton.setName("unbindingSeqButton"); // NOI18N
        unbindingSeqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unbindingSeqButtonActionPerformed(evt);
            }
        });
        jPanel5.add(unbindingSeqButton);

        delEStyleButton.setText(org.openide.util.NbBundle.getMessage(QbTablePopDialog_GuoXin.class, "QbTablePopDialog_GuoXin.delEStyleButton.text")); // NOI18N
        delEStyleButton.setName("delEStyleButton"); // NOI18N
        delEStyleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delEStyleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delEStyleButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(delEStyleButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        getQbJTable().onPopDialogClosed();
    }//GEN-LAST:event_formWindowClosed

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

private void openAllFieldsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAllFieldsButtonActionPerformed
    SqlCreateModel sm = qbJTable.getScene().getModel().getSqlModel();
    sm.removeAllFieldMasks();
    updateJTableUI();
}//GEN-LAST:event_openAllFieldsButtonActionPerformed

private void criteriaOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criteriaOpenActionPerformed
    setSelectedFieldsSW("criteria", true);
}//GEN-LAST:event_criteriaOpenActionPerformed

private void criteriaMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criteriaMaskActionPerformed
    setSelectedFieldsSW("criteria", false);
}//GEN-LAST:event_criteriaMaskActionPerformed

private void listdevOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listdevOpenActionPerformed
    setSelectedFieldsSW("listdev", true);
}//GEN-LAST:event_listdevOpenActionPerformed

private void listDevMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDevMaskActionPerformed
    setSelectedFieldsSW("listdev", false);
}//GEN-LAST:event_listDevMaskActionPerformed

private void addOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOpenActionPerformed
    setSelectedFieldsSW("add", true);
}//GEN-LAST:event_addOpenActionPerformed

private void addMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaskActionPerformed
    setSelectedFieldsSW("add", false);
}//GEN-LAST:event_addMaskActionPerformed

private void updateOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOpenActionPerformed
    setSelectedFieldsSW("update", true);
}//GEN-LAST:event_updateOpenActionPerformed

private void updateMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMaskActionPerformed
    setSelectedFieldsSW("update", false);
}//GEN-LAST:event_updateMaskActionPerformed

private void viewOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewOpenActionPerformed
    setSelectedFieldsSW("view", true);
}//GEN-LAST:event_viewOpenActionPerformed

private void viewMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMaskActionPerformed
    setSelectedFieldsSW("view", false);
}//GEN-LAST:event_viewMaskActionPerformed

private void expOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expOpenActionPerformed
    setSelectedFieldsSW("exp", true);
}//GEN-LAST:event_expOpenActionPerformed

private void expMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expMaskActionPerformed
    setSelectedFieldsSW("exp", false);
}//GEN-LAST:event_expMaskActionPerformed

private void impOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impOpenActionPerformed
    setSelectedFieldsSW("imp", true);
}//GEN-LAST:event_impOpenActionPerformed

private void impMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impMaskActionPerformed
    setSelectedFieldsSW("imp", false);
}//GEN-LAST:event_impMaskActionPerformed

private void setAsPkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setAsPkButtonActionPerformed
    setSelectedFieldsPK(true);
}//GEN-LAST:event_setAsPkButtonActionPerformed

private void maskPkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maskPkButtonActionPerformed
    setSelectedFieldsPK(false);
}//GEN-LAST:event_maskPkButtonActionPerformed

private void verifyOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyOpenButtonActionPerformed
    setSelectedFieldsSW("verify", true);
}//GEN-LAST:event_verifyOpenButtonActionPerformed

private void verifyCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyCloseButtonActionPerformed
    setSelectedFieldsSW("verify", false);
}//GEN-LAST:event_verifyCloseButtonActionPerformed

private void viewBindOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBindOpenButtonActionPerformed
    setSelectedFieldsSW("viewBindingCol", true);
}//GEN-LAST:event_viewBindOpenButtonActionPerformed

private void viewBindCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBindCloseButtonActionPerformed
    setSelectedFieldsSW("viewBindingCol", false);
}//GEN-LAST:event_viewBindCloseButtonActionPerformed

private void bindingSeqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bindingSeqButtonActionPerformed
    setSelectedFieldsSW("bindingSeqCol", true);
}//GEN-LAST:event_bindingSeqButtonActionPerformed

private void unbindingSeqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unbindingSeqButtonActionPerformed
    setSelectedFieldsSW("bindingSeqCol", false);
}//GEN-LAST:event_unbindingSeqButtonActionPerformed

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
    private javax.swing.JButton bindingSeqButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton cancelGroupButton;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JButton criteriaMask;
    private javax.swing.JButton criteriaOpen;
    private javax.swing.JButton delEStyleButton;
    private javax.swing.JButton expMask;
    private javax.swing.JButton expOpen;
    private javax.swing.JButton groupButton;
    private javax.swing.JButton impMask;
    private javax.swing.JButton impOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton listDevMask;
    private javax.swing.JButton listdevOpen;
    private javax.swing.JButton maskPkButton;
    private javax.swing.JButton openAllFieldsButton;
    private javax.swing.JButton orderButton;
    private javax.swing.JButton setAsPkButton;
    private javax.swing.JButton unbindingSeqButton;
    private javax.swing.JButton updateMask;
    private javax.swing.JButton updateOpen;
    private javax.swing.JButton verifyCloseButton;
    private javax.swing.JButton verifyOpenButton;
    private javax.swing.JButton viewBindCloseButton;
    private javax.swing.JButton viewBindOpenButton;
    private javax.swing.JButton viewMask;
    private javax.swing.JButton viewOpen;
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
        this.setTitle(String.format("��<%s>:���������� (���ſ��)", qbJTable.getModel().getTbean().getTableDetail().getName()));
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
        qbJTable.refreshSqlPreview(false);
        updateJTableUI();
    }

    private void setSelectedFieldsPK(boolean isPK) {
        // Meta_Table m = qbJTable.getModel().getTbean().getTableDetail();
        for (int row : qbJTable.getSelectedRows()) {
            I_WfColumn col = (I_WfColumn) qbJTable.getModel().getValueAt(row, 0);
            if (col != null) {
                col.setPk(isPK);
                qbJTable.getModel().fillRow(row, col);
            }
        }
    }
}