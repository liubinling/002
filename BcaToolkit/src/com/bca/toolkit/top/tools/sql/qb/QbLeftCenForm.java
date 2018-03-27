/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CWizDirForm.java
 *
 * Created on 2010-8-19, 12:25:28
 */
package com.bca.toolkit.top.tools.sql.qb;

import javax.swing.JComponent;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.title.DockingWindowTitleProvider;

/**
 *
 * @author pxz
 */
public class QbLeftCenForm extends javax.swing.JPanel implements DockingWindowTitleProvider {

    private static QbLeftCenForm inst = null;
    private String title = "���";

    /**
     * @return the inst
     */
    public static QbLeftCenForm getInst() {
        if (inst == null) {
            inst = new QbLeftCenForm();
        }
        return inst;
    }

    /**
     * Creates new form CWizDirForm
     */
    public QbLeftCenForm() {
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

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle(DockingWindow dw) {
        return title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
        dock.getWindowProperties().setTitleProvider(new DockingWindowTitleProvider() {

            @Override
            public String getTitle(DockingWindow window) {
                return "";
            }
        });
        
        dock.getWindowProperties().setTitleProvider(this);
    }
    DockingWindow dock;
    JComponent activeComp;

    public JComponent getActiveComp() {
        return this.activeComp;
    }

    public void loadComp(JComponent c) {
        this.add(c, java.awt.BorderLayout.CENTER);
        this.activeComp = c;
    }

    public void setDock(DockingWindow dock) {
        this.dock = dock;
    }
}
