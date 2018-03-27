/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.kernel.BcaSerialData;

/**
 *
 * @author pxz
 */
public class QbPinBean implements java.io.Serializable {

    private static final long serialVersionUID = BcaSerialData.serialVersionUID;
    private final String colName;  // l = left side, r = right side
    private final char side;

    public QbPinBean(String colName, char side) {
        this.colName = colName;
        this.side = side;
    }

    boolean canCreateOutCn() {
        return true;
    }

    boolean canCreateInCn() {
        return true;
    }

    /**
     * @return the colName
     */
    public String getColName() {
        return colName;
    }

    /**
     * @return the side
     */
    public char getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "col=" + colName + ",side=" + side;
    }
}
