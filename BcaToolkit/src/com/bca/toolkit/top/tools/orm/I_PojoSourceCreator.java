/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm;

import com.bca.api.pub.Ret;
import com.bca.db.meta.unit.Meta_Table;
import java.util.Map;

/**
 *
 * @author pxz
 */
public interface I_PojoSourceCreator {

    Map<String, PojoSourceCreatorUnit> getSourceUnits();

    Ret createAllPojoSource();

    void init(String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable);
//    Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource);
    String getSaveDir(String homeDir);

     void setCharset(String charsetName);
}
