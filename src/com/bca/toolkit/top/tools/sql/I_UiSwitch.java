/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author pxz
 */
public interface I_UiSwitch {

    Dimension getPreferredSize();

    void setBounds(Rectangle centerBounds);

    void loadSwitches(SqlCreateModel sqlModel);

    boolean isReloadModelRequest();

    void setVisible(boolean b);
}
