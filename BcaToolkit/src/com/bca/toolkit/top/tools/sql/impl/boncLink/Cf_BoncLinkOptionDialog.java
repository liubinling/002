/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cf_ShFactoryOptionDialog.java
 *
 * Created on 2012-6-27, 12:07:47
 */
package com.bca.toolkit.top.tools.sql.impl.boncLink;

import com.bca.api.pub.Ret;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Numtool;
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
public class Cf_BoncLinkOptionDialog extends javax.swing.JDialog implements I_FactoryOptDlg {

    public Cf_BoncLinkOptionDialog() {
        super(BcaToolkit.getApp().getMainFrame(), true);
        initComponents();
        wfinit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        miLocateSrc = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        pkgHomeBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fatherFolderBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        projHomeDirField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        projNameBox = new javax.swing.JComboBox();
        remCompareBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dbupsBox = new javax.swing.JComboBox();
        parenetMenuNameBox = new javax.swing.JComboBox();
        authRoleBox = new javax.swing.JComboBox();
        codeCharsetBox = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        dosScriptCharsetBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        numberPackBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        busiDBupsBox = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        daoPrefixBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        childFolderBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        creatorComBox = new javax.swing.JComboBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        miLocateSrc.setText("定位...");
        miLocateSrc.setName("miLocateSrc"); // NOI18N
        miLocateSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLocateSrcActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miLocateSrc);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BCA代码工厂--选项(boncLink框架)"); // NOI18N
        setName("Form"); // NOI18N

        jLabel1.setText("pkg home:");
        jLabel1.setName("jLabel1"); // NOI18N

        pkgHomeBox.setEditable(true);
        pkgHomeBox.setName("pkgHomeBox"); // NOI18N
        pkgHomeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pkgHomeBoxActionPerformed(evt);
            }
        });

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

        jLabel3.setText(org.openide.util.NbBundle.getMessage(Cf_BoncLinkOptionDialog.class, "Cf_BoncLinkOptionDialog.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        fatherFolderBox.setEditable(true);
        fatherFolderBox.setName("fatherFolderBox"); // NOI18N
        fatherFolderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherFolderBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("目标项目home目录:");
        jLabel4.setName("jLabel4"); // NOI18N

        projHomeDirField.setComponentPopupMenu(jPopupMenu1);
        projHomeDirField.setName("projHomeDirField"); // NOI18N

        jLabel2.setText("项目名称:");
        jLabel2.setName("jLabel2"); // NOI18N

        projNameBox.setEditable(true);
        projNameBox.setName("projNameBox"); // NOI18N
        projNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projNameBoxActionPerformed(evt);
            }
        });

        remCompareBox.setText("rem比对脚本");
        remCompareBox.setName("remCompareBox"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel5.setText("授权db登录(user/pwd@sid):");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("上级菜单名称:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("授权给角色:");
        jLabel7.setName("jLabel7"); // NOI18N

        dbupsBox.setEditable(true);
        dbupsBox.setName("dbupsBox"); // NOI18N

        parenetMenuNameBox.setEditable(true);
        parenetMenuNameBox.setName("parenetMenuNameBox"); // NOI18N

        authRoleBox.setEditable(true);
        authRoleBox.setName("authRoleBox"); // NOI18N

        codeCharsetBox.setEditable(true);
        codeCharsetBox.setName("codeCharsetBox"); // NOI18N

        jLabel8.setText("代码字符集:");
        jLabel8.setName("jLabel8"); // NOI18N

        dosScriptCharsetBox.setEditable(true);
        dosScriptCharsetBox.setName("dosScriptCharsetBox"); // NOI18N

        jLabel9.setText("dos脚本字符集:");
        jLabel9.setName("jLabel9"); // NOI18N

        numberPackBox.setText("数据类型采用类包装");
        numberPackBox.setName("numberPackBox"); // NOI18N

        jLabel10.setText("业务db登录(user/pwd@sid):");
        jLabel10.setName("jLabel10"); // NOI18N

        busiDBupsBox.setEditable(true);
        busiDBupsBox.setName("busiDBupsBox"); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(Cf_BoncLinkOptionDialog.class, "Cf_BoncLinkOptionDialog.jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        daoPrefixBox.setEditable(true);
        daoPrefixBox.setName("daoPrefixBox"); // NOI18N
        daoPrefixBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daoPrefixBoxActionPerformed(evt);
            }
        });

        jLabel12.setText(org.openide.util.NbBundle.getMessage(Cf_BoncLinkOptionDialog.class, "Cf_BoncLinkOptionDialog.jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        childFolderBox.setEditable(true);
        childFolderBox.setName("childFolderBox"); // NOI18N
        childFolderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childFolderBoxActionPerformed(evt);
            }
        });

        jLabel13.setText(org.openide.util.NbBundle.getMessage(Cf_BoncLinkOptionDialog.class, "Cf_BoncLinkOptionDialog.jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        creatorComBox.setEditable(true);
        creatorComBox.setName("creatorComBox"); // NOI18N
        creatorComBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creatorComBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projHomeDirField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(okButton)
                            .addComponent(cancelButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dbupsBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parenetMenuNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(authRoleBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busiDBupsBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 57, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pkgHomeBox, 0, 93, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(fatherFolderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(childFolderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(remCompareBox)
                                .addGap(169, 169, 169))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(codeCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(daoPrefixBox, 0, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dosScriptCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numberPackBox)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel13)
                                .addGap(8, 8, 8)
                                .addComponent(creatorComBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(projHomeDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pkgHomeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(fatherFolderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(childFolderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(projNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton)
                    .addComponent(remCompareBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberPackBox)
                    .addComponent(jLabel13)
                    .addComponent(creatorComBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dbupsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(parenetMenuNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(authRoleBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(busiDBupsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(daoPrefixBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(codeCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(dosScriptCharsetBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    pkgHome = (String) pkgHomeBox.getSelectedItem();
    //尾包名中的父包名（后添加的）
    fatherFolder = (String) fatherFolderBox.getSelectedItem();
    //尾包名中的子包名（后添加的）
    childFolder = (String) childFolderBox.getSelectedItem();
    //创建人（后添加的）
    creator=(String)creatorComBox.getSelectedItem();
    projName = (String) projNameBox.getSelectedItem();

    daoPrefix = (String) daoPrefixBox.getSelectedItem();
    if (daoPrefix == null) {
        daoPrefix = "";
    }

    boolean b1 = opt.regPkgHome(pkgHome);
    //boolean b15 = opt.regPkgTail(pkgTail);
    //The methods below put the selected or typed items into corresponding Lists as the first element.
    boolean b15 = opt.regFatherFolder(fatherFolder);
    boolean b16 = opt.regChildFolder(childFolder);
    boolean b17 = opt.regCreator(creator);
    boolean b2 = opt.regprojName(projName);
    boolean b3 = opt.regDaoPrefix(daoPrefix);

    dbups = (String) dbupsBox.getSelectedItem();
    parenetMenuName = (String) parenetMenuNameBox.getSelectedItem();
    authRole = (String) authRoleBox.getSelectedItem();
    codeCharset = (String) codeCharsetBox.getSelectedItem();
    dosScriptCharset = (String) dosScriptCharsetBox.getSelectedItem();
    busiDBups = (String) busiDBupsBox.getSelectedItem();

    opt.regMemListName(dbups, "dbups");
    opt.regMemListName(parenetMenuName, "parenetMenuName");
    opt.regMemListName(authRole, "authRole");
    opt.regMemListName(codeCharset, "codeCharset");
    opt.regMemListName(dosScriptCharset, "dosScriptCharset");
    opt.regMemListName(busiDBups, "busiDBups");

    opt.setProperty("projHomeDir", projHomeDirField.getText());
    opt.setProperty("numberPackFlag", Boolean.toString(numberPackBox.isSelected()));
    // if (b1 || b15 || b2) {
    Filetool.saveSerializeFile("status" + File.separator + "CodeFact.cfg", opt);
    //}
//往pojoAttr中存入工厂选项面板中输入的各个属性值
    pojoAttr.setProperty("pkgHome", pkgHome);
    //pojoAttr.setProperty("pkgTail", pkgTail);
    //尾包名中的子包名（后添加的）
    pojoAttr.setProperty("childFolder", childFolder);
    //尾包名中的父包名（后添加的）
    pojoAttr.setProperty("fatherFolder", fatherFolder);
    //创建人（后添加的）
    pojoAttr.setProperty("creator", creator);
    pojoAttr.setProperty("projName", projName);
    pojoAttr.setProperty("projHomeDir", projHomeDirField.getText());
    pojoAttr.setProperty("daoPrefix", daoPrefix);

    pojoAttr.setProperty("hxhkBatRem", remCompareBox.isSelected() ? "rem" : "");

    pojoAttr.setProperty("dbups", dbups);
    pojoAttr.setProperty("busiDBups", busiDBups);
    pojoAttr.setProperty("parenetMenuName", parenetMenuName);
    pojoAttr.setProperty("authRole", authRole);
    pojoAttr.setProperty("codeCharset", codeCharset);
    pojoAttr.setProperty("dosScriptCharset", dosScriptCharset);
    pojoAttr.setProperty("numberPackFlag", Boolean.toString(numberPackBox.isSelected()));

    setVisible(false);
}//GEN-LAST:event_okButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

private void pkgHomeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pkgHomeBoxActionPerformed
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
}//GEN-LAST:event_pkgHomeBoxActionPerformed

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

private void projNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projNameBoxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_projNameBoxActionPerformed

    private void daoPrefixBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daoPrefixBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daoPrefixBoxActionPerformed

    private void fatherFolderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatherFolderBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fatherFolderBoxActionPerformed

    private void childFolderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_childFolderBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_childFolderBoxActionPerformed

    private void creatorComBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creatorComBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creatorComBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox authRoleBox;
    private javax.swing.JComboBox busiDBupsBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox childFolderBox;
    private javax.swing.JComboBox codeCharsetBox;
    private javax.swing.JComboBox creatorComBox;
    private javax.swing.JComboBox daoPrefixBox;
    private javax.swing.JComboBox dbupsBox;
    private javax.swing.JComboBox dosScriptCharsetBox;
    private javax.swing.JComboBox fatherFolderBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem miLocateSrc;
    private javax.swing.JCheckBox numberPackBox;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox parenetMenuNameBox;
    private javax.swing.JComboBox pkgHomeBox;
    private javax.swing.JTextField projHomeDirField;
    private javax.swing.JComboBox projNameBox;
    private javax.swing.JCheckBox remCompareBox;
    // End of variables declaration//GEN-END:variables
//    public String getPkgName() {
//        return pkgHome;
//    }
    String pkgHome = "";
    //String pkgTail = "";
    //尾部包名中的子包名（后添加的）
    String childFolder = "";
    //尾部包名中的父包名（后添加的）
    String fatherFolder = "";
    //创建人跟创建时间（后添加的）
    String creator="";
    String createTime="";
    String projName = "";
    String dbups = "";
    String busiDBups = "";
    String parenetMenuName = "";
    String authRole = "";
    String codeCharset = "";
    String dosScriptCharset = "";
    // 
    String daoPrefix = "";   // dao的前缀。用于区分不同的业务库

//    public void setPkgName(String packageName) {
//    }
    //设置三个包名下拉列表框和一个创建人下拉列表框的选中项
    private void setPkgName(String pkgHome, String childFolder, String fatherFolder, String creator) {
        if (pkgHome != null && !pkgHome.isEmpty()) {
            this.pkgHome = pkgHome;
            this.pkgHomeBox.setSelectedItem(pkgHome);
        }
          //尾部包名中的子包名（后添加的）
        if (childFolder != null && !childFolder.isEmpty()) {
            this.childFolder = childFolder;
            this.childFolderBox.setSelectedItem(childFolder);
        }
         //尾部包名中的父包名（后添加的）
        if (fatherFolder != null && !fatherFolder.isEmpty()) {
            this.fatherFolder = fatherFolder;
            this.fatherFolderBox.setSelectedItem(fatherFolder);
        }
        //创建人（后添加的）
         if (creator != null && !creator.isEmpty()) {
            this.creator = creator;
            this.creatorComBox.setSelectedItem(creator);
        }
    }
    // 
    final Ret ret = new Ret();
    CodeFactoryOptions opt;
//初始化下拉列表框，为它们加入可选项
    private void wfinit() {
        opt = (CodeFactoryOptions) Filetool.restoreObjFromSerialzeFile("status" + File.separator + "CodeFact.cfg", ret);
        if (opt == null) {
            opt = new CodeFactoryOptions();
        }
        opt.checkFields();

        for (String pkg : opt.recentUsedProjNames) {
            if (pkg != null && !pkg.isEmpty()) {
                this.projNameBox.addItem(pkg);
            }
        }
        if (opt.recentUsedProjNames.isEmpty()) {
            projNameBox.addItem("");
        }
        for (String pkg : opt.recentUsedPackageHome) {
            if (pkg != null && !pkg.isEmpty()) {
                this.pkgHomeBox.addItem(pkg);
            }
        }
        if (opt.recentUsedPackageHome.isEmpty()) {
            pkgHomeBox.addItem("");
        }
//        for (String pkg : opt.recentUsedPackageTail) {
//            if (pkg != null && !pkg.isEmpty()) {
//                this.fatherFolderBox.addItem(pkg);
//            }
//        }
//        if (opt.recentUsedPackageTail.isEmpty()) {
//            fatherFolderBox.addItem("");
//        }
        //尾包名中的父包名（后添加的）
        for (String pkg : opt.recentUsedFatherFolder) {
            if (pkg != null && !pkg.isEmpty()) {
                this.fatherFolderBox.addItem(pkg);
            }
        }
        if (opt.recentUsedFatherFolder.isEmpty()) {
            fatherFolderBox.addItem("");
        }
        //尾包名中的子包名（后添加的）
        for (String pkg : opt.recentUsedChildFolder) {
            if (pkg != null && !pkg.isEmpty()) {
                this.childFolderBox.addItem(pkg);
            }
        }
        if (opt.recentUsedChildFolder.isEmpty()) {
            childFolderBox.addItem("");
        }
          //创建人（后添加的）
        for (String pkg : opt.recentUsedCreator) {
            if (pkg != null && !pkg.isEmpty()) {
                this.creatorComBox.addItem(pkg);
            }
        }
        if (opt.recentUsedCreator.isEmpty()) {
            creatorComBox.addItem("");
        }
        projNameBox.setSelectedIndex(0);
        pkgHomeBox.setSelectedIndex(0);
        fatherFolderBox.setSelectedIndex(0);
        childFolderBox.setSelectedIndex(0);
        creatorComBox.setSelectedIndex(0);
//        projNameBox.setSelectedIndex(0);

        loadOptText(this.dbupsBox, "dbups");
        loadOptText(busiDBupsBox, "busiDBups");
        loadOptText(this.parenetMenuNameBox, "parenetMenuName");
        loadOptText(this.authRoleBox, "authRole");
        loadOptText(this.codeCharsetBox, "codeCharset");
        loadOptText(this.dosScriptCharsetBox, "dosScriptCharset");

        this.projHomeDirField.setText(opt.getProperty("projHomeDir"));
        this.numberPackBox.setSelected(Numtool.parseBoolean(opt.getProperty("numberPackFlag")));
    }

    /**
     * @return the projName
     */
    public String getprojName() {
        return projName;
    }

    /**
     * @param projName the projName to set
     */
    public void setprojName(String projName) {
        if (projName != null && !projName.isEmpty()) {
            this.projName = projName;
            this.projNameBox.setSelectedItem(projName);
        }
    }

    public void setDaoPrefix(String daoPrefix) {
        if (daoPrefix != null && !daoPrefix.isEmpty()) {
            this.daoPrefix = daoPrefix;
            this.daoPrefixBox.setSelectedItem(daoPrefix);
        }
    }

    PojoAttribute pojoAttr;

    @Override
    public void loadOptions(SrcFactoryBean f) {
        this.pojoAttr = f.pojoAttr;
        String s = f.pojoAttr.getProperty("projHomeDir");
        if (!s.isEmpty()) {
            projHomeDirField.setText(s);
        }
        setPkgName(f.pojoAttr.getProperty("pkgHome"), f.pojoAttr.getProperty("childFolder"),f.pojoAttr.getProperty("fatherFolder"),f.pojoAttr.getProperty("creator"));
        setprojName(f.pojoAttr.getProperty("projName"));
        setDaoPrefix(f.pojoAttr.getProperty("daoPrefix"));

        // numberPackBox.setSelected(Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag")));
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
