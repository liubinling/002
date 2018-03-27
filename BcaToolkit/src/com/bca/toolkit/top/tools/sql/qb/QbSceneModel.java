/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.agent.dagent.de.fb.etl.canvas.EtlDlg_DynSql;
import com.bca.api.local.MetaClientApi;
import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_NamedQryElement;
import com.bca.db.meta.unit.Meta_SQLElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.kernel.LocalMetaDataContainer;
import com.bca.pub.tools.Layouttool;
import com.bca.pub.tools.Templtool;
import com.bca.pub.tools.Wftool;
import com.bca.templ.pub.AbstractStudioApp;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Cf_BoncEpmUIOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.QbTablePopDialog_BoncEpmUI;
import static com.bca.toolkit.top.tools.sql.impl.boncEpmUI.QbTablePopDialog_BoncEpmUI.selectNO;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.velocity.Template;

/**
 *
 * @author pxz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QbSceneModel implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    protected final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    //    private  Map<Integer, String> nxxxxodes = new HashMap<Integer, String>();
    private Map<String, QbTableBean> nodes = new TreeMap<String, QbTableBean>();
    private Map<Integer, QbLinkBean> connections = new TreeMap<Integer, QbLinkBean>();
    private transient Template templateOnDecide;
//    private final AtomicInteger nodeIdPointer = new AtomicInteger(0);
    private final AtomicInteger edgeIdPointer = new AtomicInteger(0);
    final AbstractStudioApp app;
    private SqlCreateModel sqlModel;
    private String errInfoFrmSQL;

//    private SrcFactoryBean activeFactory;

    public QbSceneModel() {
//        QueryBuilderTableBean b = new QueryBuilderTableBean( 1, "ddd", PxzNodeType.Proc);
//        b.setNodeName("abc");
//        nodes.put(b.getNodeId(), b);
//
//        QueryBuilderLinkBean cn = new QueryBuilderLinkBean(1, "pxzCn");
//        cn.setExprId(false);
//        connections.put(cn.getConnectionId(), cn);
        app = AbstractStudioApp.getApp();
        checkTemplates();
    }

    public void addConnection(QbLinkBean edge) {
        connections.put(edge.getConnectionId(), edge);
        // MchCtool.getInst().getAppModifyFlag().set(true);
    }

    private boolean canInCriteriaByDef(I_WfColumn col) {
        if (col.getSqlName().toLowerCase().contains("name")) {
            return true;
        }
        if (col.isPk()) {
            return col.getSqlTypeName().toLowerCase().contains("varchar");
        }
        return false;
    }

    public void fetchTableDetail(QbTableBean nb) {
        app.checkLocalMetaContainerReady();
        LocalMetaDataContainer mc = app.getLocalMetaDataContainer();
        Meta_Table mt = null;
        Ret ret = null;
        switch (nb.getTableElement().getDbObjType()) {
            case Table:
                mt = mc.getTableDetailInfoFromDataConnect(nb.getCnAlias(), nb.getTableElement().getSchema(), nb.getTableElement().getCatalog(), nb.getTableElement().getName(), true);
                break;
            case View:
                mt = mc.getViewDetailInfoFromDataConnect(nb.getCnAlias(), nb.getTableElement().getSchema(), nb.getTableElement().getCatalog(), nb.getTableElement().getName(), true);
                break;
            case NamedQry:
                mt = createTableMeta(nb.getCnAlias(), (Meta_NamedQryElement) nb.getTableElement());
                break;
            case Query:
                {//ͨ��ִ�������SQL�����Ʊ�����ʱ��2017/05/06 10:57   �����ã�
                    ret = new Ret();
                    Meta_SQLElement se = (Meta_SQLElement)nb.getTableElement();
                    mt = getResultMetaFromQuery(nb.getCnAlias(), se.getSQL(), ret);
                    if(mt != null){
                        //���ص�mt��ֻ�б��ֶ���Ϣ��û��se�еĽڵ���Ϣ���������潫��������Ϣ�ϲ�
                        Meta_Table temp = new Meta_Table(se);
                        for(I_WfColumn col : mt.getColumns()){
                            temp.addColumn(col);
                        }
                        mt = temp;   //�ϲ����
                        break;
                    }else{  //���mtΪnull��˵���������쳣����Ҫ�����쳣��Ϣ�������˳��÷���
                        errInfoFrmSQL = ret.getInfo().toString();
                        return;
                    }
                }
            default:
                return;
        }
        nb.setTableDetail(mt);
        sqlModel.dbTable = mt;
        // ȱʡ������(���ߺ�name���ֶ�)��Ϊ��ѯ�����ֶμ��ϡ�
        if (mt != null && mt.getColumns() != null) {
            for (I_WfColumn col : mt.getColumns()) {
 //       System.out.println("******" + col.getColComment() + "************�ֶ�ע��");
                if (canInCriteriaByDef(col)) {
                    this.sqlModel.removeFieldMask("criteria", col.getSqlName());
                } else {
                    this.sqlModel.maskField("criteria", col.getSqlName());
                }
//            // һ�㣺��������֤��
//            this.sqlModel.maskField("verify", col.getSqlName());
            }
        }
        // һ�㣺��������֤�� 
        sqlModel.maskAllFields("verify");
        sqlModel.maskAllFields("viewBindingCol");   // �鿴��ϸ�İ󶨣�һ��Ҳ���ӡ�
        sqlModel.maskAllFields("bindingSeqCol");   // seq�󶨣�һ��Ҳ���ӡ�

        sqlModel.maskField("ui", "exp");
        sqlModel.maskField("ui", "imp");
        //�������ε������ֶε����й��ܣ�ʹ�û���һ�Ű�ֽ���������
        //sqlModel.maskAllFields("select_"+QbTablePopDialog_BoncEpmUI.selectNO);
        if(!"��˷�ҳ��ɾ�Ĳ���".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            sqlModel.maskAllFields("kpiId_"+QbTablePopDialog_BoncEpmUI.selectNO);
            sqlModel.maskAllFields("param_"+QbTablePopDialog_BoncEpmUI.selectNO);
        }
  
        sqlModel.maskAllFields("parentId_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("init_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("kpiDesc_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("key_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("value_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("item_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("bindingSeqCol_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.maskAllFields("desc_"+QbTablePopDialog_BoncEpmUI.selectNO);
        sqlModel.removeAllGroupFields();   //��where��group��order�а����ֶ�ȫ���Ƴ�
        
        // 
        if (mt == null) {
            Wftool.messageDialog(false, "��ȡ��Ԫ����ʧ��:" + nb.getCnAlias() + ": " + nb.getTableElement().getName());
            return;
        }
    }
    //����ִ��SQL�ĳ��򣬻�ȡ���Ԫ����
    private Meta_Table getResultMetaFromQuery(String cnAlias, String SQL, Ret ret) {
        app.checkLocalMetaContainerReady();
        LocalMetaDataContainer mc = app.getLocalMetaDataContainer();
        MetaClientApi metaApi = mc.getMetaClientApi();
        Meta_Table mt = metaApi.getResultMetaFromQuery(cnAlias, SQL, ret);
        return mt;
    }


    public void addNodeBean(QbTableBean nb) {
        QbTable_JTableModel tm = nb.getTableModel();
        assert tm != null;
        Meta_Table mt = nb.getTableDetail();
        assert mt != null;
        tm.addRow(new String[]{"������", "���뿪��", "����", "��������", "��־", "��ע��", "��ע", "�༭���"});
        for (I_WfColumn col : mt.getColumns()) {
            tm.addRow(col);
        }
        nb.getMetaMapsKey();
        nodes.put(nb.getMetaMapsKey(), nb);
        // MchCtool.getInst().getAppModifyFlag().set(true);
    }

    public void clear() {
        nodes.clear();
        connections.clear();
        edgeIdPointer.set(0);
//        nodeIdPointer.set(0);
        // MchCtool.getInst().getAppModifyFlag().set(true);
    }

    public String createScript(Ret ret) {
        checkTemplates();

        QbTableBean start = findStartNode();
        if (start == null) {
            ret.setFailure("Start node not found.");
            return "";
        }
        final StringBuffer script = new StringBuffer();
//        script.setLength(0);
        make(script, start, 0);

        ret.setSuccess("");
        return script.toString();
    }

    public void fixPointers() {
        // ���޸�nodeid
        int maxid = 0;
//        for (QueryBuilderTableBean n : this.nodes.values()) {
//            int nid = n.getNodeId();
//            maxid = Math.max(nid, maxid);
//        }
//        if (maxid > nodeIdPointer.get()) {
//            nodeIdPointer.set(maxid);
//        }
        // ���޸�edgeid
        maxid = 0;
        for (QbLinkBean n : this.connections.values()) {
            int nid = n.getConnectionId();
            maxid = Math.max(nid, maxid);
        }
        if (maxid > edgeIdPointer.get()) {
            edgeIdPointer.set(maxid);
        }
    }

    public int getNextEdgeId() {
        return edgeIdPointer.incrementAndGet();
    }

//    public int getNextNodeId() {
//        return nodeIdPointer.incrementAndGet();
//    }
    public Map<String, QbTableBean> getNodes() {
        return nodes;
    }

    public QbTableBean getNode(String nid) {
        return nodes.get(nid);
    }

    public void removeConnection(QbLinkBean edge) {
        if (edge == null) {
            return;
        }
        connections.remove(edge.getConnectionId());
//        QueryBuilderTableBean sn = this.getNode(edge.getSourceNodeId());
//        if (sn != null) {
//            sn.removeOutConnection(edge);
//        }
//        QueryBuilderTableBean dn = this.getNode(edge.getTargetNodeId());
//        if (dn != null) {
//            dn.removeInConnection(edge);
//        }

        // MchCtool.getInst().getAppModifyFlag().set(true);
    }

    public void removeNode(QbTableBean node) {
        for (QbLinkBean cn : node.getOutConnections().values()) {
            removeConnection(cn);
        }
        for (QbLinkBean cn : node.getInConnections().values()) {
            removeConnection(cn);
        }
        nodes.remove(node.getMetaMapsKey());

        // MchCtool.getInst().getAppModifyFlag().set(true);
    }

    public void setNodes(Map<String, QbTableBean> nodes) {
        this.nodes = nodes;
    }

    public Map<Integer, QbLinkBean> getConnections() {
        return connections;
    }

    public void setConnections(Map<Integer, QbLinkBean> connections) {
        this.connections = connections;
    }

    private void checkTemplates() {
        if (templateOnDecide == null) {
            String s = "if (${condition}) {\n";
            s += "${scriptOnTrue}\n";
            s += "}\n";
            s += "else {\n";
            s += "${scriptOnFalse}\n";
            s += "}\n";
            StringBuffer info = new StringBuffer();
            templateOnDecide = Templtool.createVelocityTemplate(s, info);
        }
    }

//    private QueryBuilderTableBean findJoinNode(QueryBuilderLinkBean cnOnRoute, QueryBuilderLinkBean targetCn) {
//        QueryBuilderTableBean n01 = getNode(cnOnRoute.getTargetNodeId());
////        QueryBuilderTableBean n02 = getNode(targetCn.getTargetNodeId());
//        if (n01 == null) {
//            return null;
//        }
//        switch (n01.getNodeType()) {
////            case Proc:
////            case End:
////            case Decide:
////                int size = n01.getInConnections().size();
////                if (size >= 2) {
////                    for (QueryBuilderLinkBean cn : n01.getInConnections().values()) {
////                        if (cn.getConnectionId() == cnOnRoute.getConnectionId()) {   // �������ӱ������ٷ���顣
////                            continue;
////                        }
////                        Set<Integer> searchedNodes = new TreeSet<Integer>();
////                        boolean b = reverseSearchConnection(cn, targetCn, searchedNodes);
////                        if (b) {
////                            return n01;
////                        }
////                    }
////                }
////                if (n01.getNodeType() == MchNodeType.End) {
////                    return null;
////                }
////                if (n01.getNodeType() == MchNodeType.Proc) {
////                    QueryBuilderLinkBean cn = n01.getUniqOutCn();
////                    return cn == null ? null : findJoinNode(cn, targetCn);   // �ݹ���á�
////                }
////                if (n01.getNodeType() == MchNodeType.Decide) {
//////                    QueryBuilderTableBean nodeBean;
////                    QueryBuilderLinkBean cnOnTrue = n01.getOutConnections().get(true);
////                    QueryBuilderTableBean nodeBean = cnOnTrue == null ? null : findJoinNode(cnOnTrue, targetCn);   // �ݹ���á�
////                    if (nodeBean != null) {
////                        return nodeBean;
////                    }
////                    QueryBuilderLinkBean cnOnFalse = n01.getOutConnections().get(false);
////                    return cnOnFalse == null ? null : findJoinNode(cnOnFalse, targetCn);   // �ݹ���á�
////                }
////                return null;   // ��Ӧ���ܵ����
//////            case Decide:
//////                break;
//            default:
//                return null;
//        }
//    }
    private QbTableBean findStartNode() {
//        for (QueryBuilderTableBean nb : nodes.values()) {
//            if (nb.getNodeType() == MchNodeType.Start) {
//                return nb;
//            }
//        }
        return null;
    }

    // actCancelNodeId ���ٳ������Ŀ��ڵ㡣 0 Ϊֱ���յ㡣
    private void make(StringBuffer script, QbTableBean focusNode, int actCancelNodeId) {
//        switch (focusNode.getNodeType()) {
//            case Start:
//                break;
//            case End:
//                return;
//            case Decide:
//                StringBuffer scriptOnTrue = new StringBuffer();
//                StringBuffer scriptOnFalse = new StringBuffer();
//                QueryBuilderLinkBean cnTrue = focusNode.getOutConnections().get(true);
//                QueryBuilderLinkBean cnFalse = focusNode.getOutConnections().get(false);
//                QueryBuilderTableBean joinNode = null;
//                if (cnTrue != null && cnFalse != null) {
//                    joinNode = findJoinNode(cnTrue, cnFalse);
//                }
//                if (cnTrue != null) {
//                    QueryBuilderTableBean ntrue = getNode(cnTrue.getTargetNodeId());
//                    assert ntrue != null;
//                    make(scriptOnTrue, ntrue, joinNode == null ? 0 : joinNode.getNodeId());
//                }
//                if (cnFalse != null) {
//                    QueryBuilderTableBean nfalse = getNode(cnFalse.getTargetNodeId());
//                    assert nfalse != null;
//                    make(scriptOnFalse, nfalse, joinNode == null ? 0 : joinNode.getNodeId());
//                }
//                //
//                VelocityContext context = new VelocityContext();   // ���е����⣺û������
//                context.put("condition", focusNode.getExpression());
//                context.put("scriptOnTrue", scriptOnTrue.toString());
//                context.put("scriptOnFalse", scriptOnFalse.toString());
//                script.append(Templtool.createCodeByTemplate(templateOnDecide, context, new StringBuffer()));
//
//                // if/else �����ϵ��ˡ���Ҫ�������ɽű���
//                if (joinNode != null) {
//                    make(script, joinNode, 0);
//                }
//                return;
//            case Proc:
//                String expr = focusNode.getExpression();
//                if (expr.endsWith("\n")) {
//                    script.append(focusNode.getExpression());
//                } else {
//                    script.append(expr).append("\r\n");
//                }
//                break;
//        }
//        QueryBuilderLinkBean cn = focusNode.getUniqOutCn();
//        if (cn == null) {
//            return;
//        }
//        if (actCancelNodeId == cn.getTargetNodeId()) {   // ����ű������жϽڵ㡣(if/else��Ļ�ϴ�)
//            return;
//        }
//        QueryBuilderTableBean nextNode = getNode(cn.getTargetNodeId());
//        if (nextNode != null) {
//            make(script, nextNode, actCancelNodeId);    // �����˶�ջ���?
//        }
    }

    public QbLinkBean newEdge(QbPinBean spin, QbPinBean tpin) {
//        int id = edgeIdPointer.incrementAndGet();
        QbLinkBean lbean = new QbLinkBean(this.getNextEdgeId(), spin, tpin);
        this.connections.put(lbean.getConnectionId(), lbean);
        return lbean;
    }

    public synchronized void refillSqlModel(SqlCreateModel sqlModel) {
        //private Map<String, QbTableBean> nodes = new HashMap<String, QbTableBean>();
        // private final TreeMap<Integer, QbOutputFieldUnit> outputFields = new TreeMap<Integer, QbOutputFieldUnit>();
        this.sqlModel = sqlModel;
        sqlModel.clear();
        for (QbTableBean tbean : nodes.values()) {
            QbTable_JTableModel tm = tbean.getTableModel();
            for (int row = 0; row < tm.getRowCount(); row++) {
//                I_WfColumn col = (I_WfColumn) tm.getValueAt(row, 0);
                boolean outEnable = (Boolean) tm.getValueAt(row, 1);
                if (outEnable) {
                    sqlModel.addFieldOut(tm, row);
                }
            }

//  for (int row = 0; row < tbean.getTableModel().getRowCount(); row++) {
//            crModel.addFieldOut(tbean.getTableModel(), row);
//        }
        }
    }

    String getRecommendedTableAlias(String tableName) {
        if (nodes.isEmpty()) {
            return "t";
        }
        if (tableName.isEmpty()) {
            return "";
        }

        char c = Character.toLowerCase(tableName.charAt(0));
        for (int i = 1; i <= 100; i++) {
            String s = String.format("%c%02d", c, i);
            if (checkTableAliasUsable(s)) {
                return s;
            }
        }
        return "";
    }

    boolean checkTableAliasUsable(String tableAlias) {
        if (tableAlias.isEmpty()) {
            return true;
        }
        for (QbTableBean tbean : nodes.values()) {
            if (tbean.getTableAlias().equalsIgnoreCase(tableAlias)) {
                return false;
            }
        }
        return true;
    }
//    // searchedNodes ��Ϊ�˷�ֹ·�ɷ�������һ���ڵ�ʱ������ѭ����
//    private boolean reverseSearchConnection(QueryBuilderLinkBean routeCn, QueryBuilderLinkBean targetCn, Set<Integer> searchedNodes) {
//        assert routeCn != null;
//        if (routeCn.getConnectionId() == targetCn.getConnectionId()) {
//            return true;
//        }
//        if (searchedNodes.contains(routeCn.getSourceNodeId())) {
//            return false;
//        }
//        searchedNodes.add(routeCn.getSourceNodeId());
//        QueryBuilderTableBean snode = getNode(routeCn.getSourceNodeId());
//        if (snode == null) {
//            return false;
//        }
//        for (QueryBuilderLinkBean cn : snode.getInConnections().values()) {
//            if (reverseSearchConnection(cn, targetCn, searchedNodes)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public void addNodeBean(PxzNodeBean_Vir nb) {
//        nodes.put(nb.getNodeId(), nb);
//    }
//
//    public Map<Integer, PxzNodeBean_Vir> getNode() {
//        return nodes;
//    }
//
//    public Map<Integer, String> getNxxxxodes() {
//        return nxxxxodes;
//    }
//
//    public void setNxxxxodes(Map<Integer, String> nxxxxodes) {
//        this.nxxxxodes = nxxxxodes;
//    }

    /**
     * @return the sqlModel
     */
    public SqlCreateModel getSqlModel() {
        return sqlModel;
    }

//    /**
//     * @return the activeFactory
//     */
//    public SrcFactoryBean getActiveFactory() {
//        return activeFactory;
//    }
//
//    /**
//     * @param activeFactory the activeFactory to set
//     */
//    public void setActiveFactory(SrcFactoryBean activeFactory) {
//        this.activeFactory = activeFactory;
//    }
    public void setSqlCreateModel(SqlCreateModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    private Meta_Table createTableMeta(String alias, Meta_NamedQryElement te) {
        if ("__dyn_query__".equals(te.getName())) {
            EtlDlg_DynSql dlg = new EtlDlg_DynSql(alias, te);
            dlg.setIbatisWizEnable(true);
            dlg.setBounds(Layouttool.getCenterBounds(dlg.getPreferredSize()));
            dlg.setVisible(true);
            try {
//                te.setName(alias);
                return dlg.getGrabbedMeta();
            } catch (Exception ex) {
                log.error(com.bca.pub.tools.Toolfunc.getCallLocation(ex.getStackTrace()) + ":" + ex.getMessage(), ex);
                return null;
            }
        }
        return null;
    }
    //��ȡSQL�쳣��Ϣ
    public String getErrInfoFrmSQL(){
        return errInfoFrmSQL;
    }

}
