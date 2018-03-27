/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author pxz
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QbLinkBean implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    private final int connectionId;
    private QbPinBean spin;
    private QbPinBean tpin;
//    private String connectionName;
//    private Object exprId = "";
//    private int sourceNodeId = 0;
//    private int targetNodeId = 0;

//    private QueryBuilderLinkBean() {   // for jaxb.
//        this(0);
//    }
    public QbLinkBean(int connectionId, QbPinBean spin, QbPinBean tpin) {
        this.connectionId = connectionId;
        this.spin = spin;
        this.tpin = tpin;
    }

//    public String getConnectionName() {
//        return connectionName;
//    }
//
//    public void setConnectionName(String connectionName) {
//        this.connectionName = connectionName;
//    }
    public int getConnectionId() {
        return connectionId;
    }

//    public Object getExprId() {
//        return exprId;
//    }
//
//    public void setExprId(Object exprId) {
//        this.exprId = exprId;
//    }
//
//    public int getSourceNodeId() {
//        return sourceNodeId;
//    }
//
//    public void setSourceNodeId(int sourceNodeId) {
//        this.sourceNodeId = sourceNodeId;
//    }
//
//    public int getTargetNodeId() {
//        return targetNodeId;
//    }
//
//    public void setTargetNodeId(int targetNodeId) {
//        this.targetNodeId = targetNodeId;
//    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("connectionId=").append(connectionId);
        return sb.toString();
    }

    /**
     * @return the spin
     */
    public QbPinBean getSpin() {
        return spin;
    }

    /**
     * @return the tpin
     */
    public QbPinBean getTpin() {
        return tpin;
    }
//    public void setConnectionId(int connectionId) {
//        this.connectionId = connectionId;
//    }
}
