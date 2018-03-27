/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql;

import com.bca.db.meta.I_WfColumn;
import com.bca.toolkit.top.tools.orm.*;
import com.bca.api.pub.Ret;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pxz
 */
public interface I_SqlCreator {
    List<String> projNameList();

    Map<String, PojoSourceCreatorUnit> getSourceUnits();

    Ret createAllPojoSource();

    void setSModel(SqlCreateModel smodel);

    void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable, List<Map<String, String>> uiColSort);
//    Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource);

    String getSaveDir(String homeDir);

    String createSelectSQL(TreeMap<Integer, QbOutputFieldUnit> outputFields);

    String[] getSubPaths();

    String getColBatchDlgClass();

    TableCellRenderer getCellRenderer_sw(I_WfColumn col);

    // 代码比对需要做个特别处理：相同的文件，bat命令前面加个bat 跳过比对  并且这个要在所有文件写入完成之后再做。
    void fixCompareScript(String srcOutDir);   // 选哟对比对脚本进行修复。 相同的文件，加一个 rem 
}
