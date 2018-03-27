/*
 * PojoField.java
 *
 * Created on 2007年8月3日, 下午4:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm;

import com.bca.db.meta.I_WfColumn;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Toolfunc;
import java.util.Properties;

/**
 *
 * @author pxz
 */
public class PojoField implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    public String varName;
    public String scope = "private";
    public String varType;
//    int length;
    public boolean inToString = true, inHashCode = true, inEquals = true;
//    public boolean inQueryCriteria = false;    // 是否在查询条件里
//    public String columnName;
//    public String sqlType;
//    public String flags;
//    public int length;
    public I_WfColumn col;
    public final Properties properties = new Properties();

    /**
     * Creates a new instance of PojoField
     */
    public PojoField() {
    }

    public String getProperty(String key) {
        String s = properties.getProperty(key);
        return s == null ? "" : s;
    }

    public void setProperty(String key, String val) {
        properties.setProperty(key, val);
    }

    /**
     * 根据数据类型，在创建缺省对象时传递一个参数
     *
     * @return
     */
    public String getDefaultCreateArgText() {
        if (Stringtool.isStringInList(varType, "int", "long", "short", "byte", "float", "double")) {
            return "0";
        }
        if (Stringtool.isStringInList(varType, "boolean", "Boolean")) {
            return "false";
        }
        if (Stringtool.isStringInList(varType, "String", "java.lang.String")) {
            return "\"\"";
        }
        return "null";
    }

    public void changeNumberTypeToPackedMode() {
        String s = Toolfunc.getPackedTypeBySimpleType(varType);
        if (s != null && !s.isEmpty()) {
            varType = s;
        }
    }

    public String getColComment() {
        return col == null ? "" : col.getColComment() == null ? "" : col.getColComment();
    }

    public String getColComment_slashLeading() {
        return col == null ? "" : col.getColComment() == null ? "" : "// " + col.getColComment();
    }

    public String getColComment_singleLine() {
        if (col == null || col.getColComment() == null) {
            return "";
        }
        String s = col.getColComment();
        s = s.replace("\r", "");
        s = s.replace("\n", " ");
        return s;
    }
}
