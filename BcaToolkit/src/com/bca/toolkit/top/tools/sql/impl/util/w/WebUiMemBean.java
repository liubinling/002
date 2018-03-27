/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.util.w;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author admin
 */
public class WebUiMemBean implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    private String xpath = "";
    private String styleName = "";
    private WebUi_ControlType ctype = WebUi_ControlType.Unknown;
    private Map<String, UiMemMap> v_label = new LinkedHashMap<String, UiMemMap>();   // key: value of UiMemMap by static
    private String sqlForKvMap = "∏Ò Ω:Select KEY_FIELD, VALUE_FIELD from TABLE_NAME order by KEY_FIELD";
    private String templateContentName = "";
    private String varPostfix = "";
    private UiMemMapMode kvMapMode = UiMemMapMode.MapByStatic;
    private Map<String, WebUiTemplUnit> estyleTemplUnits = new TreeMap<String, WebUiTemplUnit>();

    /**
     * @return the kvMapMode
     */
    public UiMemMapMode getKvMapMode() {
        return kvMapMode;
    }

    /**
     * @param kvMapMode the kvMapMode to set
     */
    public void setKvMapMode(UiMemMapMode kvMapMode) {
        this.kvMapMode = kvMapMode;
    }

    /**
     * @return the sqlForKvMap
     */
    public String getSqlForKvMap() {
        return sqlForKvMap;
    }

    /**
     * @param sqlForKvMap the sqlForKvMap to set
     */
    public void setSqlForKvMap(String sqlForKvMap) {
        this.sqlForKvMap = sqlForKvMap;
    }

    /**
     * @return the estyleTemplUnits
     */
    public Map<String, WebUiTemplUnit> getEstyleTemplUnits() {
        return estyleTemplUnits;
    }

    /**
     * @param estyleTemplUnits the estyleTemplUnits to set
     */
    public void checkObj() {
        if (estyleTemplUnits == null) {
            this.estyleTemplUnits = new TreeMap<String, WebUiTemplUnit>();
        }
    }

    /**
     * @param estyleTemplUnits the estyleTemplUnits to set
     */
    public void setEstyleTemplUnits(Map<String, WebUiTemplUnit> estyleTemplUnits) {
        this.estyleTemplUnits = estyleTemplUnits;
    }

    public enum UiMemMapMode {

        MapByStatic, MapBySql, MapByHkTemplate
    }

    public WebUiMemBean() {
    }

    public String getFullPath() {
        return xpath + '/' + styleName;
    }

    /**
     * @return the ctype
     */
    public WebUi_ControlType getCtype() {
        return ctype;
    }

    /**
     * @param ctype the ctype to set
     */
    public void setCtype(WebUi_ControlType ctype) {
        this.ctype = ctype;
    }

    /**
     * @return the v_label
     */
    public Map<String, UiMemMap> getV_label() {
        return v_label;
    }

    /**
     * @param v_label the v_label to set
     */
    public void setV_label(Map<String, UiMemMap> v_label) {
        this.v_label = v_label;
    }

    /**
     * @return the templateContentName
     */
    public String getTemplateContentName() {
        return templateContentName;
    }

    /**
     * @return the varPostfix
     */
    public String getVarPostfix() {
        return varPostfix;
    }

    /**
     * @param varPostfix the varPostfix to set
     */
    public void setVarPostfix(String varPostfix) {
        this.varPostfix = varPostfix;
    }

    /**
     * @return the xpath
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * @param xpath the xpath to set
     */
    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    /**
     * @return the styleName
     */
    public String getStyleName() {
        return styleName;
    }

    /**
     * @param styleName the styleName to set
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Override
    public String toString() {
        return getFullPath();
    }
}
