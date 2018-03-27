/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.db.meta.I_WfColumn;

/**
 *
 * @author pxz
 */
public class QbOutputFieldUnit {

    private int index = 0;
    private int sourceType = 0;
    private transient QbTable_JTableModel jtableModel;
    private int row = 0;
    private String expr = "";
    private String outFieldName = "";

    public QbOutputFieldUnit() {
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the sourceType
     */
    public int getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the expr
     */
    public String getExpr() {
        return expr;
    }

    /**
     * @param expr the expr to set
     */
    public void setExpr(String expr) {
        this.expr = expr;
    }

    /**
     * @return the outFieldName
     */
    public String getOutFieldName() {
        return outFieldName;
    }

    /**
     * @param outFieldName the outFieldName to set
     */
    public void setOutFieldName(String outFieldName) {
        this.outFieldName = outFieldName;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the jtableModel
     */
    public QbTable_JTableModel getJtableModel() {
        return jtableModel;
    }

    /**
     * @param jtableModel the jtableModel to set
     */
    public void setJtableModel(QbTable_JTableModel jtableModel) {
        this.jtableModel = jtableModel;
        String tableAlias = jtableModel == null ? "" : jtableModel.getTbean().getTableAlias();
        tableAliasDot = tableAlias.isEmpty() ? "" : tableAlias + '.';
    }
    String tableAliasDot = "";

    public String getExpr_x() {
        if (this.expr == null || expr.isEmpty()) {
            I_WfColumn col = (I_WfColumn) this.jtableModel.getValueAt(row, 0);
            // 暂时没管表的别名....
            return col == null ? "" : tableAliasDot + col.getSqlName();
        } else {
            return expr;
        }
    }

    public String getColComment() {
        if (this.expr == null || expr.isEmpty()) {
            I_WfColumn col = (I_WfColumn) this.jtableModel.getValueAt(row, 0);
            // 暂时没管表的别名....
            return col == null ? "" : col.getColComment();
        } else {
            return "";
        }
    }
}
