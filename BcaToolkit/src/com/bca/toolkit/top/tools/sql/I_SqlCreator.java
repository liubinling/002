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

    // ����ȶ���Ҫ�����ر�����ͬ���ļ���bat����ǰ��Ӹ�bat �����ȶ�  �������Ҫ�������ļ�д�����֮��������
    void fixCompareScript(String srcOutDir);   // ѡӴ�ԱȶԽű������޸��� ��ͬ���ļ�����һ�� rem 
}
