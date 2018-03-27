/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import com.bca.toolkit.top.tools.orm.PojoAttribute;

/**
 *
 * @author pxz
 */
public class SrcFactoryBean implements java.io.Serializable {
    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;

    public final String factoryName;
    public final transient I_SqlCreator creator;
    public final transient I_FactoryOptDlg optDialog;
    public final transient I_UiSwitch uiSwitchDialog;
    public final transient I_FactoryUiTool uiTool;
    public PojoAttribute pojoAttr;
    private SqlCreateModel smodel;

    public SrcFactoryBean(String factoryName, I_SqlCreator creator, I_FactoryOptDlg optDialog, I_UiSwitch uiSwitchDialog, I_FactoryUiTool uiTool) {
        this.factoryName = factoryName;
        this.creator = creator;
        this.optDialog = optDialog;
        this.uiSwitchDialog = uiSwitchDialog;
        this.pojoAttr = new PojoAttribute();
        this.uiTool = uiTool;
    }

    /**
     * @return the smodel
     */
    public SqlCreateModel getSmodel() {
        return smodel;
    }

    /**
     * @param smodel the smodel to set
     */
    public void setSmodel(SqlCreateModel smodel) {
        this.smodel = smodel;
    }

    public void loadFrom(SrcFactoryBean orig) {
        this.pojoAttr = orig.pojoAttr;
    }
}
