/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CWizDirForm.java
 *
 * Created on 2010-8-19, 12:25:28
 */
package com.bca.utool.cwiz;

import com.bca.agent.dagent.fb.DatabaseObjectTreePane;
import com.bca.agent.dagent.fb.HdcSelectionDialog;
import com.bca.pub.tools.Layouttool;
import com.bca.pub.tools.Wftool;
import com.bca.templ.pub.AbstractApp;
import java.util.List;

/**
 *
 * @author pxz
 */
public class CWizDbresForm extends javax.swing.JPanel {

    private static CWizDbresForm inst = null;

    /**
     * @return the inst
     */
    public static CWizDbresForm getInst() {
        if (inst == null) {
            inst = new CWizDbresForm();
        }
        return inst;
    }
    final DatabaseObjectTreePane dbTreePanel; // = new DatabaseObjectTreePane();

    /** Creates new form CWizDirForm */
    public CWizDbresForm() {
        initComponents();

        dbTreePanel = new DatabaseObjectTreePane();
        this.add(dbTreePanel, java.awt.BorderLayout.CENTER);
        dbTreePanel.wfinit();
        dbTreePanel.setVisibleChildren(true, true, false, true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public List<String> selectDB() {
        HdcSelectionDialog dlg = new HdcSelectionDialog(AbstractApp.getMainFrameOnPossible(), false);
        dlg.setBounds(Layouttool.getCenterBounds(400, 300));
        dlg.setTitle("����Դѡ��");
//        dlg.setRadioGroup(this.radioGroup);
        dlg.wfinit();
        dlg.setVisible(true);
        if (dlg.getSelectedAliases().isEmpty()) {
            return null;
        }

        dbTreePanel.setAlias(dlg.getSelectedAliases().get(0));
        Wftool.invokeLater(new Runnable() {

            @Override
            public void run() {
                dbTreePanel.refreshMeta();
            }
        });

        return dlg.getSelectedAliases();
    }
}
