/*
 * PojoAttribute.java
 *
 * Created on 2007年8月3日, 下午3:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm;

import com.bca.db.DbConst.DbObjType;
import java.util.Properties;

/**
 *
 * @author pxz
 */
public class PojoAttribute implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    public String alias;
    public String schema;
    public String catalog = "";
    public String tableName;
    public String packageName;
    public String className;
    public String tableComment = "";
    public boolean usePackageClassFlag = false;
    public final Properties properties = new Properties();
    public DbObjType dbObjType = DbObjType.Unknown;
    public String modelCategory;
    public String showId;

    /** Creates a new instance of PojoAttribute */
    public PojoAttribute() {
    }

    public String getProperty(String key) {
        String s = properties.getProperty(key);
        return s == null ? "" : s;
    }

    public void setProperty(String key, String val) {
        properties.setProperty(key, val);
    }

}
