/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author pxz
 */
public interface I_ColBatchDlg {

    void setVisible(boolean b);

    void setLocation(Point locationOnScreen);

    Dimension getPreferredSize();

    QbJTable getQbJTable();

    void setSize(Dimension preferredSize);

    void wfinit(QbJTable jtable);

    boolean isVisible();
}
