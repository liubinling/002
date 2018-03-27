/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QbJTableTitlePane.java
 *
 * Created on 2012-8-26, 20:38:15
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.pub.comm.IntegerComparatorDesc;
import com.bca.pub.tools.Layouttool;
import com.bca.pub.tools.Wftool;
import com.bca.toolkit.top.tools.sql.I_UiSwitch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author pxz
 */
public class QbJTableTitlePane extends javax.swing.JPanel {

    final com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final QbJTable jxtable;
    final QbTable_JTableModel tm;

    /** Creates new form QbJTableTitlePane */
    public QbJTableTitlePane(String title, QbJTable jxtable) {
        initComponents();
        this.jxtable = jxtable;
        this.tm = jxtable.getModel();
        jLabel1.setText(title + "    ");

        wfinit();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        uiSwButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        upButton.setText("up");
        upButton.setFocusable(false);
        upButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        upButton.setName("upButton"); // NOI18N
        upButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(upButton);

        downButton.setText("down");
        downButton.setFocusable(false);
        downButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downButton.setName("downButton"); // NOI18N
        downButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(downButton);

        uiSwButton.setText("UI页面开关");
        uiSwButton.setFocusable(false);
        uiSwButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uiSwButton.setName("uiSwButton"); // NOI18N
        uiSwButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        uiSwButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiSwButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(uiSwButton);

        add(jToolBar1, java.awt.BorderLayout.EAST);

        jLabel1.setText("表名等标题信息 ");
        jLabel1.setName("jLabel1"); // NOI18N
        add(jLabel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
    int[] rows = jxtable.getSelectedRows();
    
    if (rows.length == 0 || rows[0] < 1) {
        return;
    }
    if(rows[0] == 0)
        return;
    jxtable.clearSelection();
    for (int row : rows) {
        int destRow = row - 1;
        if(destRow == 0)
            return;
        tm.moveRow(row, row, destRow);
        jxtable.addRowSelectionInterval(destRow, destRow);
    }
}//GEN-LAST:event_upButtonActionPerformed

private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
    final TreeSet<Integer> ts = new TreeSet<Integer>(new IntegerComparatorDesc());
    int maxRowNum = -1;
    for (int row : jxtable.getSelectedRows()) {
        if(row == 0)
            return;
        ts.add(row);
        maxRowNum = row;
    }
    if (maxRowNum == -1 || maxRowNum >= tm.getRowCount() - 1) {
        return;
    }
    jxtable.clearSelection();
    for (int row : ts) {
        int destRow = row + 1;
        tm.moveRow(row, row, destRow);
        jxtable.addRowSelectionInterval(destRow, destRow);
    }
}//GEN-LAST:event_downButtonActionPerformed

private void uiSwButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiSwButtonActionPerformed
    //从包含字段名和注释的集合中抽离出只包含字段名的集合，修改时间： 2017/4/6 9:51 a.m.  孙天用
    List<Map<String, String>> names = tm.getColNames();
    List<String> colNames = new ArrayList<String>();
    for(Map<String, String> name : names){
        for(Map.Entry<String, String> entry : name.entrySet()){
            colNames.add(entry.getKey());
        }
    }
    //抽取结束
    tm.getSqlModel().setColSortList(colNames);
    I_UiSwitch dlg = tm.getSqlModel().activeFactory.uiSwitchDialog;
    dlg.setBounds(Layouttool.getCenterBounds(dlg.getPreferredSize()));
    dlg.loadSwitches(tm.getSqlModel());
    dlg.setVisible(true);

    if (dlg.isReloadModelRequest()) {
        Wftool.invokeLater_awtEventQueue(new Runnable() {

            @Override
            public void run() {
                tm.reloadData();
                tm.getSqlModel().setSqlComment();
                jxtable.updateUI();
            }
        });
    }

}//GEN-LAST:event_uiSwButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton uiSwButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    private void wfinit() {
        Wftool.setToolButtonIcon(upButton, "up.gif", "上移", "");
        Wftool.setToolButtonIcon(downButton, "down.gif", "下移", "");
    }
}
