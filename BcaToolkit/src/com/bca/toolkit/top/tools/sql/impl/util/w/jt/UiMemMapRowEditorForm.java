package com.bca.toolkit.top.tools.sql.impl.util.w.jt;

import com.bca.pub.gui.jtablex.I_JTableDataHandler;
import com.bca.pub.gui.jtablex.I_TableRowGuiBinder;
import com.bca.pub.tools.Numtool;
import com.bca.toolkit.top.tools.sql.impl.util.w.UiMemMap;
import java.sql.Timestamp;

/**
 * 界面元素登记表管理：详细编辑form
 * @author 北京华夏翰科科技有限公司 http://www.hxhk.com.cn
 * 2013-02-14 13:12:31.21
 */
public class UiMemMapRowEditorForm extends javax.swing.JPanel implements I_TableRowGuiBinder<UiMemMap> {

    /** Creates new form UiMemMapRowEditorForm */
    public UiMemMapRowEditorForm() {
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

        valueLabel = new javax.swing.JLabel();
        valueField = new javax.swing.JTextField();
        labelLabel = new javax.swing.JLabel();
        labelField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        valueLabel.setText("存储值:");
        valueLabel.setName("valueLabel"); // NOI18N

        valueField.setEditable(false);
        valueField.setName("valueField"); // NOI18N

        labelLabel.setText("显示值:");
        labelLabel.setName("labelLabel"); // NOI18N

        labelField.setName("labelField"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(valueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelField)))
                        .addGap(6, 6, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(okButton)
                        .addGap(34, 34, 34)
                        .addComponent(cancelButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valueLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLabel)
                    .addComponent(labelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    if (h != null && bindingBean != null) {
        this.updateBean();
        this.h.fillRow(this.bindingRow, this.bindingBean);
    }
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.rebind(bindingRow, bindingBean);
}//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField labelField;
    private javax.swing.JLabel labelLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField valueField;
    private javax.swing.JLabel valueLabel;
    // End of variables declaration//GEN-END:variables
    int bindingRow = 0;
    UiMemMap bindingBean = null;
    I_JTableDataHandler h;

    @Override
    public void rebind(int rowNo, UiMemMap e) {
        this.bindingBean = e;
        this.bindingRow = rowNo;

        valueField.setText(e==null || e.getValue()==null ? "" : e.getValue());
        labelField.setText(e==null || e.getLabel()==null ? "" : e.getLabel());

    }

    @Override
    public int getBindingRow() {
        return bindingRow;
    }

    @Override
    public UiMemMap getBindingBean() {
        return bindingBean;
    }

    @Override
    public void setTableDataHandler(I_JTableDataHandler h) {
        this.h = h;
    }

    @Override
    public UiMemMap updateBean() {
        // 从界面取对象....      
        if (bindingBean == null) {
            return null;
        }
        bindingBean.setValue(valueField.getText());
        bindingBean.setLabel(labelField.getText());

        return this.bindingBean;
    }

    @Override
    public boolean isSaveEnable() {
        return true;
    }

    @Override
    public void setPkFieldsEditable(boolean b) {
        valueField.setEditable(b);

    }
}
