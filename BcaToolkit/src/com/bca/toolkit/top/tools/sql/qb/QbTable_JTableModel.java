/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.db.meta.I_WfColumn;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pxz
 */
public class QbTable_JTableModel extends TableModelWithAliasCol<I_WfColumn> {

    private final QbTableBean tbean;
    private final Set<Integer> outputFields = new TreeSet<Integer>();
    private final Set<Integer> groupByFields = new TreeSet<Integer>();
    private final Set<Integer> orderByFields = new TreeSet<Integer>();
    private SqlCreateModel sqlModel;

    public QbTable_JTableModel(QbTableBean tbean) {
        this.tbean = tbean;
        int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "隐藏列", "hiddenColumn", 0));
        columnAliasMap.put("selected", new TableColumnAliasUtil(col++, "代码开关", "selected", 200));
        columnAliasMap.put("colname", new TableColumnAliasUtil(col++, "列名", "colname", 160));
        columnAliasMap.put("datatype", new TableColumnAliasUtil(col++, "数据类型", "datatype", 120));
        columnAliasMap.put("flags", new TableColumnAliasUtil(col++, "标志", "flags", 100));
        columnAliasMap.put("remark", new TableColumnAliasUtil(col++, "列注释", "remark", 200));
        columnAliasMap.put("note", new TableColumnAliasUtil(col++, "备注", "note", 150));   //后添加的一列， 备注
        columnAliasMap.put("estyle", new TableColumnAliasUtil(col++, "编辑风格", "estyle", 200));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);
        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
//    // 放置数据，为行代表的对象。
//    List<I_WfColumn> datas = new ArrayList<I_WfColumn>();

//    @Override
//    public List<I_WfColumn> getDatas() {
//        return tbean.getTableDetail().getColumns();
//    }
    @Override
    public boolean loadAllDatas() {
        return true;
    }

    @Override
    public Object[] getRowData(I_WfColumn o) {
        String editStyleStr = "";
        String note = "";
//        if (o.getEditStyle() != null) {
//            WebUiMemBean uibean = (WebUiMemBean) o.getEditStyle();
//            editStyleStr = uibean.getStyleName();
//        }
        return new Object[]{o, true, o.getSqlName(), o.getSqlTypeNameWithLenInfo(), o.getFlags(), o.getColComment(), note, editStyleStr};
    }

    @Override
    public String getDeleteWarnMessage(Collection<I_WfColumn> deleteTargets) {
        return "";
    }

    @Override
    public I_WfColumn createDefaultBean() {
        return null;
    }

    @Override
    public boolean isAppendEnable() {
        return false;
    }

    @Override
    public boolean isSaveEnable() {
        return false;
    }

    @Override
    public boolean isDeleteEnable() {
        return false;
    }

    @Override
    public boolean isAutoUpdateRow() {
        return false;
    }

    /**
     * @return the tbean
     */
    public QbTableBean getTbean() {
        return tbean;
    }

    /**
     * @return the outputFields
     */
    public Set<Integer> getOutputFields() {
        return outputFields;
    }

    /**
     * @return the groupByFields
     */
    public Set<Integer> getGroupByFields() {
        return groupByFields;
    }

    /**
     * @return the orderByFields
     */
    public Set<Integer> getOrderByFields() {
        return orderByFields;
    }

    public List<Map<String, String>> getColNames() {
        List<Map<String, String>> names = new ArrayList<Map<String, String>>();
        for (int row = 1; row < this.getRowCount(); row++) {
            I_WfColumn col_0 = (I_WfColumn) this.getValueAt(row, 0);
            String col_5 = (String) this.getValueAt(row, 5);
            String SqlName = col_0.getSqlName();
            String SqlComment = col_5;
            Map<String, String> name = new HashMap<String, String>();
            name.put(SqlName, SqlComment);
            names.add(name);
        }
        return names;
    }
    public List<Map<String, String>> getFieldLabel() {
        List<Map<String, String>> names = new ArrayList<Map<String, String>>();
        for (int i = 1; i < this.getRowCount(); i++) {
            I_WfColumn col_0 = (I_WfColumn) this.getValueAt(i, 0);
            String SqlName = col_0.getSqlName();
            String SqlComment = (String) this.getValueAt(i, 6);
            Map<String, String> name = new HashMap<String, String>();
            name.put(SqlName, SqlComment);
            names.add(name);
        }
        return names;
    }

    public void setSqlModel(SqlCreateModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    /**
     * @return the sqlModel
     */
    public SqlCreateModel getSqlModel() {
        return sqlModel;
    }
    
    public void reloadData() {
        super.clear();
        this.addRow(new String[]{"隐藏列", "代码开关", "列名", "数据类型", "标志", "列注释", "备注", "编辑风格"});
        for (String s : sqlModel.getColSortList()) {
            I_WfColumn col = sqlModel.dbTable.getColumn(s);
            if (col != null) {
                this.addRow(col);
            }
        }
    }
//把表字段注释内容添加到表格
    public void setColNames(List<Map<String, String>> sqlComment) {
        for (int row = 0; row < this.getRowCount(); row++) {
            I_WfColumn col_0 = (I_WfColumn) this.getValueAt(row, 0);
            String SqlName = col_0.getSqlName();
            String note = null;
            for(Map<String, String> str : sqlComment){
                if(str.containsKey(SqlName)){
                    note = str.get(SqlName);
                    break;
                }
            }
            if(note != null){
                this.setValueAt(note, row, 5);
            }
        }
    }
}
