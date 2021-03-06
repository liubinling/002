/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservice_framework.newboncproject;

import java.awt.Font;

/**
 *
 * @author ur
 */
public class OtherSourcePanel extends javax.swing.JPanel {

    /**
     * @return the otherURLTFD
     */
    public javax.swing.JTextField getOtherURLTFD() {
        return otherURLTFD;
    }

    /**
     * @param otherURLTFD the otherURLTFD to set
     */
    public void setOtherURLTFD(javax.swing.JTextField otherURLTFD) {
        this.otherURLTFD = otherURLTFD;
    }

    /**
     * @return the otherURLLabel
     */
    public javax.swing.JLabel getOtherURLLabel() {
        return otherURLLabel;
    }

    /**
     * @param otherURLLabel the otherURLLabel to set
     */
    public void setOtherURLLabel(javax.swing.JLabel otherURLLabel) {
        this.otherURLLabel = otherURLLabel;
    }

    /**
     * @return the otherTypeLabel
     */
    public javax.swing.JLabel getOtherTypeLabel() {
        return otherTypeLabel;
    }

    /**
     * @param otherTypeLabel the otherTypeLabel to set
     */
    public void setOtherTypeLabel(javax.swing.JLabel otherTypeLabel) {
        this.otherTypeLabel = otherTypeLabel;
    }

    /**
     * @return the otherTypeCBox
     */
    public javax.swing.JComboBox getOtherTypeCBox() {
        return otherTypeCBox;
    }

    /**
     * @param otherTypeCBox the otherTypeCBox to set
     */
    public void setOtherTypeCBox(javax.swing.JComboBox otherTypeCBox) {
        this.otherTypeCBox = otherTypeCBox;
    }

    /**
     * @return the otherPswTFD
     */
    public javax.swing.JTextField getOtherPswTFD() {
        return otherPswTFD;
    }

    /**
     * @param otherPswTFD the otherPswTFD to set
     */
    public void setOtherPswTFD(javax.swing.JTextField otherPswTFD) {
        this.otherPswTFD = otherPswTFD;
    }

    /**
     * @return the otherPswLabel
     */
    public javax.swing.JLabel getOtherPswLabel() {
        return otherPswLabel;
    }

    /**
     * @param otherPswLabel the otherPswLabel to set
     */
    public void setOtherPswLabel(javax.swing.JLabel otherPswLabel) {
        this.otherPswLabel = otherPswLabel;
    }

    /**
     * @return the otherKeyTFD
     */
    public javax.swing.JTextField getOtherKeyTFD() {
        return otherKeyTFD;
    }

    /**
     * @param otherKeyTFD the otherKeyTFD to set
     */
    public void setOtherKeyTFD(javax.swing.JTextField otherKeyTFD) {
        this.otherKeyTFD = otherKeyTFD;
    }

    /**
     * @return the otherKeyBox
     */
    public javax.swing.JCheckBox getOtherKeyBox() {
        return otherKeyBox;
    }

    /**
     * @param otherKeyBox the otherKeyBox to set
     */
    public void setOtherKeyBox(javax.swing.JCheckBox otherKeyBox) {
        this.otherKeyBox = otherKeyBox;
    }

    /**
     * @return the otherAccountTFD
     */
    public javax.swing.JTextField getOtherAccountTFD() {
        return otherAccountTFD;
    }

    /**
     * @param otherAccountTFD the otherAccountTFD to set
     */
    public void setOtherAccountTFD(javax.swing.JTextField otherAccountTFD) {
        this.otherAccountTFD = otherAccountTFD;
    }

    /**
     * @return the otherAccountLabel
     */
    public javax.swing.JLabel getOtherAccountLabel() {
        return otherAccountLabel;
    }

    /**
     * Creates new form OtherSourcePanel
     */
    public OtherSourcePanel() {
        initComponents();
        otherKeyTFD.setEnabled(true);
        final String[] dataSources={"Oracle", "MySQL", "XCloud2.0.8"};
        for (String dataSource : dataSources) {
            otherTypeCBox.addItem(dataSource);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        otherTypeLabel = new javax.swing.JLabel();
        otherTypeLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        otherTypeCBox = new javax.swing.JComboBox();
        otherURLLabel = new javax.swing.JLabel();
        otherURLLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        otherURLTFD = new javax.swing.JTextField();
        otherAccountLabel = new javax.swing.JLabel();
        otherAccountLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        otherAccountTFD = new javax.swing.JTextField();
        otherPswLabel = new javax.swing.JLabel();
        otherPswLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        otherPswTFD = new javax.swing.JTextField();
        otherKeyTFD = new javax.swing.JTextField();
        otherKeyBox = new javax.swing.JCheckBox();
        otherKeyBox.setFont(new Font("宋体", Font.PLAIN, 12));

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(530, 160));

        otherTypeLabel.setText("数据源类型:");

        otherURLLabel.setText("URL:");

        otherAccountLabel.setText("账户:");

        otherPswLabel.setText("密码:");

        otherKeyBox.setText("Key:");
        otherKeyBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                otherKeyBoxStateChanged(evt);
            }
        });
        otherKeyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherKeyBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherTypeLabel)
                    .addComponent(otherURLLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherKeyBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(otherAccountTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(otherPswLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(otherPswTFD, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(otherKeyTFD)
                    .addComponent(otherTypeCBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otherURLTFD))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(otherKeyTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(otherKeyBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otherTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherTypeCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otherURLTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otherURLLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(otherAccountTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(otherAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(otherPswTFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(otherPswLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void otherKeyBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_otherKeyBoxStateChanged
   
    }//GEN-LAST:event_otherKeyBoxStateChanged

    private void otherKeyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherKeyBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otherKeyBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel otherAccountLabel;
    private javax.swing.JTextField otherAccountTFD;
    private javax.swing.JCheckBox otherKeyBox;
    private javax.swing.JTextField otherKeyTFD;
    private javax.swing.JLabel otherPswLabel;
    private javax.swing.JTextField otherPswTFD;
    private javax.swing.JComboBox otherTypeCBox;
    private javax.swing.JLabel otherTypeLabel;
    private javax.swing.JLabel otherURLLabel;
    private javax.swing.JTextField otherURLTFD;
    // End of variables declaration//GEN-END:variables
}
