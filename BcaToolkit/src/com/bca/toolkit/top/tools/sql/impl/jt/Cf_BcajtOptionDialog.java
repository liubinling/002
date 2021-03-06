/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cf_BcajtOptionDialog.java
 *
 * Created on 2012-9-3, 22:34:55
 */
package com.bca.toolkit.top.tools.sql.impl.jt;

import com.bca.api.pub.Ret;
import com.bca.pub.tools.Filetool;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.sql.I_FactoryOptDlg;
import com.bca.toolkit.top.tools.sql.SrcFactoryBean;
import com.bca.toolkit.top.tools.sql.opt.CodeFactoryOptions;
import java.io.File;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

/**
 *
 * @author pxz
 */
public class Cf_BcajtOptionDialog extends javax.swing.JDialog implements I_FactoryOptDlg {

    public Cf_BcajtOptionDialog() {
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        miLocateSrc = new javax.swing.JMenuItem();
        projHomeDirField = new javax.swing.JTextField();
        remCompareBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pkgNameBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        dosScriptCharsetBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        codeCharsetBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        miLocateSrc.setText(org.openide.util.NbBundle.getMessage(Cf_BcajtOptionDialog.class, "Cf_BcajtOptionDialog.miLocateSrc.text_1")); // NOI18N
        miLocateSrc.setName("miLocateSrc"); // NOI18N
        miLocateSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLocateSrcActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miLocateSrc);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        projHomeDirField.setText(org.openide.util.NbBundle.getMessage(Cf_BcajtOptionDialog.class, "Cf_BcajtOptionDialog.projHomeDirField.text_1")); // NOI18N
        projHomeDirField.setComponentPopupMenu(jPopupMenu1);
        projHomeDirField.setName("projHomeDirField"); // NOI18N

        remCompareBox.setText(org.openide.util.NbBundle.getMessage(Cf_BcajtOptionDialog.class, "Cf_BcajtOptionDialog.remCompareBox.text_1")); // NOI18N
        remCompareBox.setName("remCompareBox"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        okButton.setText("确定"); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("取消"); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("目标包名:"); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        pkgNameBox.setEditable(true);
        pkgNameBox.setName("pkgNameBox"); // NOI18N
        pkgNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pkgNameBoxActionPerformed(evt);
            }
        });

        jLabel9.setText(org.openide.util.NbBundle.getMessage(Cf_BcajtOptionDialog.class, "Cf_BcajtOptionDialog.jLabel9.text_1")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        dosScriptCharsetBox.setEditable(true);
        dosScriptCharsetBox.setName("dosScriptCharsetBox"); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(Cf_BcajtOptionDialog.class, "Cf_BcajtOptionDialog.jLabel8.text_1")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        codeCharsetBox.setEditable(true);
        codeCharsetBox.setName("codeCharsetBox"); // NOI18N

        jLabel4.setText("目标项目home目录:");
        jLabel4.setName("jLabel4"); // NOI18N

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pkgNameBox, 0, 187, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(remCompareBox))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projHomeDirField, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelButton)
                            .addComponent(okButton)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dosScriptCharsetBox, 0, 140, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(projHomeDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pkgNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(remCompareBox)
                        .addComponent(cancelButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(codeCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dosScriptCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    pkgName = (String) pkgNameBox.getSelectedItem();

    boolean b1 = opt.regPkgHome(pkgName);
//    boolean b15 = opt.regPkgTail(pkgTail);
    boolean b2 = opt.regprojName(projName);

    codeCharset = (String) codeCharsetBox.getSelectedItem();
    dosScriptCharset = (String) dosScriptCharsetBox.getSelectedItem();

    opt.regMemListName(dbups, "dbups");
    opt.regMemListName(parenetMenuName, "parenetMenuName");
    opt.regMemListName(authRole, "authRole");
    opt.regMemListName(codeCharset, "codeCharset");
    opt.regMemListName(dosScriptCharset, "dosScriptCharset");

    opt.setProperty("projHomeDir", projHomeDirField.getText());
    // if (b1 || b15 || b2) {
    Filetool.saveSerializeFile("status" + File.separator + "CodeFact.cfg", opt);
    //}
    pojoAttr.packageName = pkgName;
//    pojoAttr.setProperty("pkgName", pkgName);
//    pojoAttr.setProperty("pkgTail", pkgTail);
    pojoAttr.setProperty("projName", projName);
    pojoAttr.setProperty("projHomeDir", projHomeDirField.getText());

    pojoAttr.setProperty("hxhkBatRem", remCompareBox.isSelected() ? "rem" : "");

    pojoAttr.setProperty("dbups", dbups);
    pojoAttr.setProperty("parenetMenuName", parenetMenuName);
    pojoAttr.setProperty("authRole", authRole);
    pojoAttr.setProperty("codeCharset", codeCharset);
    pojoAttr.setProperty("dosScriptCharset", dosScriptCharset);

    setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

private void pkgNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pkgNameBoxActionPerformed
//    if (opt == null) {   // 界面尚未打开。
//        return;
//    }
//    String s = (String) this.pkgNameBox.getSelectedItem();
//    if (s == null || s.isEmpty()) {
//        return;
//    }
//    boolean b = opt.regPackage(s);
//    if (b) {
//        Filetool.saveSerializeFile("status" + File.separator + "CodeFact.cfg", opt);
//    }
}//GEN-LAST:event_pkgNameBoxActionPerformed

private void miLocateSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLocateSrcActionPerformed
    JFileChooser fc = new JFileChooser(projHomeDirField.getText());
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.showSaveDialog(BcaToolkit.getApp().getMainFrame());
    if (fc == null) {
        
        return;
    }
    File f = fc.getSelectedFile();
    if (f == null) {
        return;
    }
    projHomeDirField.setText(f.getAbsolutePath());
}//GEN-LAST:event_miLocateSrcActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox codeCharsetBox;
    private javax.swing.JComboBox dosScriptCharsetBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem miLocateSrc;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox pkgNameBox;
    private javax.swing.JTextField projHomeDirField;
    private javax.swing.JCheckBox remCompareBox;
    // End of variables declaration//GEN-END:variables
    String pkgName = "";
//    String pkgTail = "";
    String projName = "";
    String dbups = "";
    String parenetMenuName = "";
    String authRole = "";
    String codeCharset = "";
    String dosScriptCharset = "";

//    public void setPkgName(String packageName) {
//    }
    private void setPkgName(String pkgName) {
        if (pkgName != null && !pkgName.isEmpty()) {
            this.pkgName = pkgName;
            this.pkgNameBox.setSelectedItem(pkgName);
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

        for (String pkg : opt.recentUsedPackageHome) {
            if (pkg != null && !pkg.isEmpty()) {
                this.pkgNameBox.addItem(pkg);
            }
        }
        if (opt.recentUsedPackageHome.isEmpty()) {
            pkgNameBox.addItem("");
        }
        pkgNameBox.setSelectedIndex(0);

        loadOptText(this.codeCharsetBox, "codeCharset");
        loadOptText(this.dosScriptCharsetBox, "dosScriptCharset");

        this.projHomeDirField.setText(opt.getProperty("projHomeDir"));
    }

    /**
     * @return the projName
     */
    public String getprojName() {
        return projName;
    }
    PojoAttribute pojoAttr;

    @Override
    public void loadOptions(SrcFactoryBean f) {
        this.pojoAttr = f.pojoAttr;
        String s = f.pojoAttr.getProperty("projHomeDir");
        if (!s.isEmpty()) {
            projHomeDirField.setText(s);
        }
        setPkgName(f.pojoAttr.getProperty("pkgName"));
    }

    @Override
    public PojoAttribute getPojoAttribute() {
        return pojoAttr;
    }

    private void loadOptText(JComboBox box, String grp) {
        List<String> list = this.opt.getMemList(grp);
        for (String s : list) {
            box.addItem(s);
        }
        if (list.isEmpty()) {
            box.addItem("");
        }
        box.setSelectedIndex(0);
    }

    @Override
    public void setProjectInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
