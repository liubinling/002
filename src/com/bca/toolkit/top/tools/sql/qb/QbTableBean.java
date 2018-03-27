            /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.db.meta.unit.Meta_Table;
import com.bca.db.meta.unit.Meta_AbstractTableElement;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pxz
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QbTableBean implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
//    public enum MchNodeType {
//
//        Start, End, Act, Proc, Decide
//    }
    private String cnAlias;
    private String tableAlias = "";
//    private int nodeId;
    private Meta_AbstractTableElement tableElement;
    private QbNodeType nodeType;
//    private String drawInfo = "";
    private String expression = "";
    private final Map<Object, QbLinkBean> outConnections = new HashMap<Object, QbLinkBean>();   // key: exprId.
    private final Map<Integer, QbLinkBean> inConnections = new HashMap<Integer, QbLinkBean>();   // key: connectionId
    private String toolTipText = "";
    private transient QbTable_JTableModel tableModel;

    private QbTableBean() {   // for: jaxb
        this("", null, QbNodeType.Table, "");
    }

    public QbTableBean(String cnAlias, Meta_AbstractTableElement te, QbNodeType nodeType, String tableAlias) {
        this.cnAlias = cnAlias;
        this.tableAlias = tableAlias;
        this.tableElement = te;
        this.nodeType = nodeType;
    }

    public QbNodeType getNodeType() {
        return nodeType;
    }

    public @XmlTransient
    QbLinkBean getUniqOutCn() {
        if (outConnections.isEmpty()) {
            return null;
        }
        assert outConnections.size() == 1;
        for (QbLinkBean cn : outConnections.values()) {
            return cn;
        }
        return null;
    }

    public void setNodeType(QbNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return tableElement.getName();
    }

////    public void setNodeName(String nodeName) {
////        this.te = nodeName;
////    }
//    public int getNodeId() {
//        return nodeId;
//    }
    public boolean canCreateOutCn() {
        switch (nodeType) {
//            case End:
//                return false;
//            case Decide:
//                return outConnections.size() < 2;
//            case Start:
//            case Act:
//            case Proc:
            default:
                return outConnections.isEmpty();
        }
    }
//    private final Map<Object, QueryBuilderLinkBean> outConnections = new HashMap<Object, QueryBuilderLinkBean>();
//    private final Map<Object, QueryBuilderLinkBean> inConnections = new HashMap<Object, QueryBuilderLinkBean>();

    public boolean canCreateInCn() {
        switch (nodeType) {
//            case Start:
//                return false;
//            case End:
//            case Act:
//            case Proc:
//            case Decide:
//                return true;
            default:
                return inConnections.isEmpty();
        }
    }

    public Map<Object, QbLinkBean> getOutConnections() {
        return outConnections;
    }

    public Map<Integer, QbLinkBean> getInConnections() {
        return inConnections;
    }

    public void addInConnection(QbLinkBean cnBean) {
//        assert cnBean.getExprId() != null : getExprIdNullMsg(cnBean);
        inConnections.put(cnBean.getConnectionId(), cnBean);
    }

    public void removeInConnection(QbLinkBean cnBean) {
//        assert cnBean.getExprId() != null : getExprIdNullMsg(cnBean);
//        inConnections.remove(cnBean.getExprId());
        inConnections.remove(cnBean.getConnectionId());
    }

    public void addOutConnection(QbLinkBean cnBean) {
//        assert cnBean.getExprId() != null : getExprIdNullMsg(cnBean);
        outConnections.put(cnBean.getConnectionId(), cnBean);
    }

    public void removeOutConnection(QbLinkBean cnBean) {
//        assert cnBean.getExprId() != null : getExprIdNullMsg(cnBean);
        outConnections.remove(cnBean.getConnectionId());
    }

//    private String getExprIdNullMsg(QueryBuilderLinkBean cnBean) {
//        return "ExprId 不允许为空：应先初始化:" + cnBean.getConnectionName();
//    }
    public Object getOutCnExprSelection() {
        switch (nodeType) {
//            case Decide:
//                if (outConnections.isEmpty()) {
//                    PxzVlDecideSelectForm f = new PxzVlDecideSelectForm();
//                    return f.work();
//                } else {
//                    return !outConnections.containsKey(true);
//                }
//            case Start:
//            case End:
//            case Act:
//            case Proc:
            default:
                return "";
        }
    }

//    public String getDrawInfo() {
//        return drawInfo;
//    }
//
//    public void setDrawInfo(String drawInfo) {
//        this.drawInfo = drawInfo;
//    }
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

//    public void setNodeId(int nodeId) {
//        this.nodeId = nodeId;
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nodeName=").append(tableElement).append(",nodeType=").append(nodeType).append(",expression=").append(expression).append(",outConnections=").append(outConnections.size()).append(",inConnections=").append(inConnections.size());
        return sb.toString();
    }

    public String getToolTipText() {
        switch (nodeType) {
//            case Decide:
//                return "Decision:" + this.expression;
//            case Proc:
//                return "Proc:" + this.expression;
//            case Start:
//                return "Start node: create one route.";
//            case End:
//                return "End node: kill on route.";
//            case Act:
            default:
                return "Unknown node.";
        }
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    /**
     * @return the tableModel
     */
    public QbTable_JTableModel getTableModel() {
        return tableModel;
    }

    /**
     * @param tableModel the tableModel to set
     */
    public void setTableModel(QbTable_JTableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * @return the tableElement
     */
    public Meta_AbstractTableElement getTableElement() {
        return tableElement;
    }

    /**
     * @param tableElement the tableElement to set
     */
    public void setTableElement(Meta_AbstractTableElement tableElement) {
        this.tableElement = tableElement;
    }

    public Dimension getPreferredSize() {
// new Dimension(575, 20);
        if (tableDetail == null) {
            return new Dimension(580, 125);
        }
        int height = 25 + tableDetail.getColumns().size() * 20;
        return new Dimension(580, height);
    }
    private Meta_Table tableDetail = null;

    public void setTableDetail(Meta_Table mt) {
        this.tableDetail = mt;
    }

    /**
     * @return the tableDetail
     */
    public Meta_Table getTableDetail() {
        return tableDetail;
    }

    public String getMetaMapsKey() {
        return tableDetail.getMetaMapsKey();
    }

    /**
     * @return the cnAlias
     */
    public String getCnAlias() {
        return cnAlias;
    }

    /**
     * @param cnAlias the cnAlias to set
     */
    public void setCnAlias(String cnAlias) {
        this.cnAlias = cnAlias;
    }

    /**
     * @return the tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * @param tableAlias the tableAlias to set
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
