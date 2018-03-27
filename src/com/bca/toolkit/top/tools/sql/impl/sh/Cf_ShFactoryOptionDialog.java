/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cf_ShFactoryOptionDialog.java
 *
 * Created on 2012-6-27, 12:07:47
 */
package com.bca.toolkit.top.tools.sql.impl.sh;

import com.bca.api.pub.Ret;
import com.bca.pub.tools.Filetool;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.sql.I_FactoryOptDlg;
import com.bca.toolkit.top.tools.sql.SrcFactoryBean;
import com.bca.toolkit.top.tools.sql.opt.CodeFactoryOptions;
import java.io.File;

/**
 *
 * @author pxz
 */
public class Cf_ShFactoryOptionDialog extends javax.swing.JDialog implements I_FactoryOptDlg {

    public Cf_ShFactoryOptionDialog() {
        super(BcaToolkit.getApp().getMainFrame(), true);
        initComponents();
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

        jLabel1 = new javax.swing.JLabel();
        pkgNameBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jspPathBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BCA���빤��--ѡ�� (�׺���)"); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText("�������:"); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        pkgNameBox.setEditable(true);
        pkgNameBox.setName("pkgNameBox"); // NOI18N

        okButton.setText("ȷ��"); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("ȡ��"); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("jsp·��:"); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jspPathBox.setEditable(true);
        jspPathBox.setName("jspPathBox"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jspPathBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(pkgNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pkgNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspPathBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    pkgName = (String) pkgNameBox.getSelectedItem();
    setJspPath((String) this.jspPathBox.getSelectedItem());
    boolean b1 = opt.regPackage(pkgName);
    boolean b2 = opt.regJspPath(this.jspPath);
    if (b1 || b2) {
        Filetool.saveSerializeFile("status" + File.separator + "CodeFact.cfg", opt);
    }
    setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox jspPathBox;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox pkgNameBox;
    // End of variables declaration//GEN-END:variables

    public String getPkgName() {
        return pkgName;
    }
    String pkgName = "";
    private String jspPath = "";

    public void setPkgName(String packageName) {
        if (packageName != null && !packageName.isEmpty()) {
            pkgName = packageName;
            this.pkgNameBox.setSelectedItem(packageName);
        }
    }
    final Ret ret = new Ret();
    CodeFactoryOptions opt;

    private void wfinit() {
        opt = (CodeFactoryOptions) Filetool.restoreObjFromSerialzeFile("status" + File.separator + "CodeFact.cfg", ret);
        if (opt == null) {
            opt = new CodeFactoryOptions();
        }
        opt.checkFields();
        for (String pkg : opt.recentUsedPackages) {
            this.pkgNameBox.addItem(pkg);
        }
        if (opt.recentUsedPackages.isEmpty()) {
            pkgNameBox.addItem("");
        }
        for (String p : opt.recentUsedJspPaths) {
            this.jspPathBox.addItem(p);
        }
        if (opt.recentUsedJspPaths.isEmpty()) {
            jspPathBox.addItem("");
        }
        // 
        pkgNameBox.setSelectedIndex(0);
        jspPathBox.setSelectedIndex(0);
    }

    /**
     * @return the jspPath
     */
    public String getJspPath() {
        return jspPath;
    }

    /**
     * @param jspPath the jspPath to set
     */
    public void setJspPath(String jspPath) {
        if (jspPath != null && !jspPath.isEmpty()) {
            this.jspPath = jspPath;
            this.jspPathBox.setSelectedItem(jspPath);
        }
    }
    PojoAttribute pojoAttr;

    @Override
    public void loadOptions(SrcFactoryBean f) {
        this.pojoAttr = f.pojoAttr;
        this.setPkgName(f.pojoAttr.packageName);
        this.setJspPath(f.pojoAttr.getProperty("jspPath"));
    }

    @Override
    public PojoAttribute getPojoAttribute() {
        return pojoAttr;
    }

    @Override
    public void setProjectInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}