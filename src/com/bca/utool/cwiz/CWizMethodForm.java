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

/**
 *
 * @author pxz
 */
public class CWizMethodForm extends javax.swing.JPanel {

    private static CWizMethodForm inst = null;

    /**
     * @return the inst
     */
    public static CWizMethodForm getInst() {
        if (inst == null) {
            inst = new CWizMethodForm();
        }
        return inst;
    }

    /** Creates new form CWizDirForm */
    public CWizMethodForm() {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
