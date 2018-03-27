/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.util;

import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author admin
 */
public class UiStylePool implements java.io.Serializable {
    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    // 
    private String fileName = "";
    private final Map<String, WebUiMemBean> styles = new TreeMap<String, WebUiMemBean>();
    // 
    public UiStylePool() {
    }

    /**
     * @return the styles
     */
    public Map<String, WebUiMemBean> getStyles() {
        return styles;
    }

    public void putWebUiMemBean(WebUiMemBean uiBean) {
        styles.put(uiBean.getFullPath(), uiBean);
    }

    public void removeWebUiMemBean(WebUiMemBean uiBean) {
        styles.remove(uiBean.getFullPath());
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
