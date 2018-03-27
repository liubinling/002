/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import com.bca.toolkit.top.tools.orm.PojoAttribute;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author pxz
 */
public interface I_FactoryOptDlg {

//    void setPkgN?ame(String packageName);
    Dimension getPreferredSize();

    void setVisible(boolean b);

    boolean isVisible();

//    String getPkgName();
//
//    String getJspPath();
    void setBounds(Rectangle centerBounds);

//    void setJspPath(String property);
    void loadOptions(SrcFactoryBean activeFactory);

    PojoAttribute getPojoAttribute();
    //完成项目设置
     void setProjectInfo();
}
