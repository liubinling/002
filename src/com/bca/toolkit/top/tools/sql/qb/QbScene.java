/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.db.DbConst;
import com.bca.db.DbConst.DbObjType;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.g.VMDPinWidget;
import com.bca.toolkit.top.tools.sql.qb.g.VMDFactory;
import com.bca.toolkit.top.tools.sql.qb.g.VMDConnectionWidget;
import com.bca.toolkit.top.tools.sql.qb.g.VMDColorScheme;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.db.meta.unit.Meta_AbstractTableElement;
import com.bca.db.meta.unit.Meta_SQLElement;
import com.bca.pub.gui.util.TransferableArrayList;
import com.bca.pub.tools.Toolfunc;
import com.bca.pub.tools.Wftool;
import com.bca.toolkit.top.tools.sql.BcaTool_SqlBuilderTopForm;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Cf_BoncEpmUIOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.ReconnectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.graph.GraphPinScene;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 * @author David Kaspar
 */
public class QbScene extends GraphPinScene<QbTableBean, QbLinkBean, QbPinBean> implements DropTargetListener {

    final com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaTool_SqlBuilderTopForm sqlBuilderPanel;
    private final QbSceneModel model; // = new QueryBuilderSceneModel();
    DropTarget dropTarget_table;
//    final LayerWidget layer;
//    DropTarget dropTarget_tableSP;
    private final WidgetAction moveAction = ActionFactory.createMoveAction();
    private int pos = 0;
    private LayerWidget backgroundLayer = new LayerWidget(this);
    private LayerWidget mainLayer = new LayerWidget(this);
    private LayerWidget connectionLayer = new LayerWidget(this);
    private LayerWidget interractionLayer = new LayerWidget(this);
    private WidgetAction connectAction = ActionFactory.createExtendedConnectAction(interractionLayer, new SceneConnectProvider());
    private WidgetAction reconnectAction = ActionFactory.createReconnectAction(new SceneReconnectProvider());
    private WidgetAction moveControlPointAction = ActionFactory.createOrthogonalMoveControlPointAction();
    private QbTNodeMenu tnodeMenu = new QbTNodeMenu(this);
    private MchEdgeMenu edgeMenu = new MchEdgeMenu(this);
    private VMDColorScheme scheme = VMDFactory.getOriginalScheme();
    private Router router;
    private SQLInputFrm sqlInputFrm;  //SQL输入窗口
    private static QbRightPanel sqlPreviewPanel;  //SQL预览面板

    public QbScene(BcaTool_SqlBuilderTopForm parentPanel) {
        this.sqlBuilderPanel = parentPanel;
        model = new QbSceneModel();

        addChild(backgroundLayer);
        addChild(mainLayer);
        addChild(connectionLayer);
        addChild(interractionLayer);



//        layer = new LayerWidget(this);
//        addChild(layer);

//        mainLayer.addChild(new LabelWidget(this, "Scroll mouse-wheel button to zoom"));
//
//        mainLayer.addChild(createMoveableComponent(new JLabel("Swing JLabel component integrated")));
//        mainLayer.addChild(createMoveableComponent(new JComboBox(new String[]{"First", "Second", "Third"})));
//        mainLayer.addChild(createMoveableComponent(new JList(new String[]{"First", "Second", "Third"})));

        wfinit();
    }

    private void wfinit() {
        initGrids();
        dropTarget_table = new DropTarget(getView(), this);
        initActionMaps();
        router = RouterFactory.createOrthogonalSearchRouter(mainLayer, connectionLayer);
//        router = RouterFactory.createDirectRouter();
//        router = RouterFactory.createFreeRouter();

//        dropTarget_table = new DropTarget(layer, model);
//        dropTarget_table = new DropTarget(this.parentPanel, model);
//        dropTarget_tableSP = new DropTarget(getView(), model);

    }

    public void initGrids() {
        Image sourceImage = Utilities.loadImage("res/img/paper_grid17.png"); // NOI18N
        int width = sourceImage.getWidth(null);
        int height = sourceImage.getHeight(null);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(sourceImage, 0, 0, null);
        graphics.dispose();
        TexturePaint PAINT_BACKGROUND = new TexturePaint(image, new Rectangle(0, 0, width, height));
        setBackground(PAINT_BACKGROUND);
        repaint();
        revalidate(false);
        validate();
    }

//    private Widget createMoveableComponent(Component component) {
//        Widget widget = new Widget(this);
//        widget.setLayout(LayoutFactory.createVerticalFlowLayout());
//        widget.setBorder(BorderFactory.createLineBorder());
//        widget.getActions().addAction(moveAction);
//
//        LabelWidget label = new LabelWidget(this, "Drag this to move widget");
//        label.setOpaque(true);
//        label.setBackground(Color.LIGHT_GRAY);
//        widget.addChild(label);
//
//        ComponentWidget componentWidget = new ComponentWidget(this, component);
//        widget.addChild(componentWidget);
//
//        pos += 100;
//        widget.setPreferredLocation(new Point(pos, pos));
//        return widget;
//    }
    @Override
    public JComponent getView() {
        JComponent c = super.getView();
        if (c != null) {
            return c;
        }
        return super.createView();
    }

    @Override
    protected Widget attachNodeWidget(QbTableBean node) {
        Widget w = null;
        switch (node.getNodeType()) {
            case Table:
                w = createTableWidget(node);
                break;
            case Query:
                w = createTableWidget(node);
                break;

        }
        if (w != null) {
            w.getActions().addAction(ActionFactory.createPopupMenuAction(tnodeMenu));
            w.getActions().addAction(createSelectAction());
            w.getActions().addAction(createObjectHoverAction());
//            w.getActions().addAction(connectAction);
            w.getActions().addAction(moveAction);

            mainLayer.addChild(w);
        }

        return w;
    }

    @Override
    protected Widget attachEdgeWidget(QbLinkBean edge) {
        VMDConnectionWidget connectionWidget = new VMDConnectionWidget(this, scheme);
        connectionWidget.setForeground(Color.red);
        connectionWidget.setRouter(router);

        connectionWidget.setSourceAnchorShape(AnchorShape.NONE);   // out
        connectionWidget.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);   // 画出箭头.

        connectionLayer.addChild(connectionWidget);

        connectionWidget.getActions().addAction(createObjectHoverAction());
        connectionWidget.getActions().addAction(createSelectAction());
        connectionWidget.getActions().addAction(moveControlPointAction);

        return connectionWidget;

    }

//    @Override
//    protected void attachEdgeSourceAnchor(QueryBuilderLinkBean edge, QueryBuilderTableBean oldSourceNode, QueryBuilderTableBean sourceNode) {
//    }
//
//    @Override
//    protected void attachEdgeTargetAnchor(QueryBuilderLinkBean edge, QueryBuilderTableBean oldTargetNode, QueryBuilderTableBean targetNode) {
//    }
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (flavors[i].isMimeTypeEqual(DataFlavor.javaSerializedObjectMimeType)) { // .isFlavorJavaFileListType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Object o = tr.getTransferData(flavors[i]);
                    if (o instanceof TransferableArrayList) {
                        TransferableArrayList<Meta_AbstractTableElement> list = (TransferableArrayList<Meta_AbstractTableElement>) o;
                        String id = list.getDndIdentify();
                        if ("DataObjects".equals(id)) {
                            String cnAlias = list.getProperties().getProperty("alias");  // 这是连接的alias
                            Point p = dtde.getLocation();
                             if(list.size() >0 && list.get(0) instanceof Meta_SQLElement){
                                   Meta_SQLElement sqlInputLabel = (Meta_SQLElement)list.get(0);
                      //判断传过来的List中存放的是否是Meta_SQLElement，如果是，则表明拖过来的可能是SQL输入节点
                                   if(!sqlInputLabel.getDisplayText().equals("SQL输入"))
                                       return;
                                   sqlInputFrm = new SQLInputFrm(this, sqlInputLabel, cnAlias, p);
                                   sqlInputFrm.setVisible(true);
                                   return;
                        }

                            for (Meta_AbstractTableElement t : list) {
                                DbConst.DbObjType ot = t.getDbObjType();
                                createTableCell_byDND(cnAlias, t, p, QbNodeType.Table);
                                p.x += 200;
                            }
                        }
                        //如果获取的对象是java.lang.String类型，则表明拖进来的是输入的SQL语句
                    }else if(o instanceof String){
                        String targetSql = (String) o;  //获取SQL语句
                    }else {
                        log.warn("unknown transfered object in DND:%s. ignored.", o.getClass().getName());
                    }
                }
                    return;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(com.bca.pub.tools.Toolfunc.getCallLocation(e.getStackTrace()) + ":" + e.getMessage(), e);
        } finally {
             try {
                dtde.dropComplete(true);
                } catch (Exception ex) {
                }
        }
    }

    void createTableCell_byDND(String cnAlias, Meta_AbstractTableElement te, Point p0, QbNodeType nt) {
//        int nodeId = 0; // model.getNextNodeId();
        String tableAlias ;  // = model.getRecommendedTableAlias(te.getName());
        if (te.getDbObjType() == DbObjType.NamedQry) {
            tableAlias = model.getRecommendedTableAlias(te.getName());
        } else {  //改为使用自定义的输入窗体，支持输入表别名和模型名称两项信息
            TableAliasAndModelNameDlg tableAliasAndModelNameDlg = new TableAliasAndModelNameDlg(null, model.getRecommendedTableAlias(te.getName()));
            String[] info = tableAliasAndModelNameDlg.getInfo();
            tableAlias = info[0];
            Cf_BoncEpmUIOptionDialog.setModelCategory(info[1]);
           // tableAlias = Wftool.inputDialog("别名", JOptionPane.INFORMATION_MESSAGE, model.getRecommendedTableAlias(te.getName()), "请输入表别名:");
            while (true) {
                if (tableAlias.isEmpty()) {
                    break;
                }
                boolean b = model.checkTableAliasUsable(tableAlias);
                if (b) {
                    break;
                }
                tableAliasAndModelNameDlg = new TableAliasAndModelNameDlg("别名<" + tableAlias + ">不可用请输入新别名:", model.getRecommendedTableAlias(te.getName()));
                tableAlias = tableAliasAndModelNameDlg.getInfo()[0];
                //tableAlias = Wftool.inputDialog("别名", JOptionPane.INFORMATION_MESSAGE, tableAlias, "别名<" + tableAlias + ">不可用请输入新别名:");
            }
        }
        if(te instanceof Meta_SQLElement)      //判断是否手输SQL
            te.setName(SQLInputFrm.mainName);   //将手输SQL面板上输入的主类名MainName存入te，后面生成表格和创建代码时将会引用它
        QbTableBean nb = new QbTableBean(cnAlias, te, nt, tableAlias);
        model.fetchTableDetail(nb);
         //如果是执行手输SQL得到的元数据，并且元数据为空，则需要处理一下异常
        if(nb.getTableDetail() == null && nt == QbNodeType.Query){
            String errInfoFrmSQL;
            errInfoFrmSQL  = "SQL语句有错或者未选择数据库（点击左上角\"选择库\"按钮可以选择数据库）\n异常信息： " + model.getErrInfoFrmSQL();
            sqlInputFrm.showErrInfoFrmSQL(errInfoFrmSQL);
            return;
        }else if (nt == QbNodeType.Query){
            sqlInputFrm.dispose();
            SqlSourceCreator_4_Query.setGetMetaDataFrom("ManuallyTypeSQL"); //设定表示元数据获取方式的变量值
        }else{
            SqlSourceCreator_4_Query.setGetMetaDataFrom("DragTable"); //设定表示元数据获取方式的变量值
        }

        QbTableNodeWidget widget = (QbTableNodeWidget) addNode(nb);     // .setPreferredLocation(widget.convertLocalToScene(event.getPoint()));
        Point p = widget.convertLocalToScene(p0);
        if (widget != null) {
            if (nb.getTableDetail() == null) {
                return;
            }
            Dimension size = nb.getPreferredSize();
            Rectangle rect = new Rectangle(p.x, p.y, size.width, size.height);
            widget.setPreferredSize(size);
            widget.setPreferredLocation(p);
            widget.resolveBounds(p, rect);
            //
            model.addNodeBean(nb);
            //
//            testPin(nb);
            widget.addAllFieldsToOutputModel(sqlBuilderPanel.getModel());
        }
        QbTNodeMenu.setIsDeleteTable(false);  //刷新SQL预览前先取消对删除表格动作事件的标识
        SqlSourceCreator_4_Query.isFirstRefresh = true;   //标识这是创建出表格后首次生成SQL预览
        if(sqlPreviewPanel!=null)
            sqlPreviewPanel.refreshSQLPreview();
    }

    private Widget createTableWidget(QbTableBean tbean) {


//        JScrollPane sp = new JScrollPane();
//        sp.setViewportView(jxtable);

        QbTableNodeWidget widget = new QbTableNodeWidget(this, tbean);
        widget.getActions().addAction(moveAction);

        return widget;
    }

    private void initActionMaps() {
        getActions().addAction(ActionFactory.createRectangularSelectAction(this, backgroundLayer));
        getActions().addAction(ActionFactory.createZoomAction());

        InputMap inputMap = new InputMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), "QbVlAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "QbVlAction");

        ActionMap keyActionMap = new ActionMap();
        keyActionMap.put("QbVlAction", new QbVlAction());
        getActions().addAction(ActionFactory.createActionMapAction(inputMap, keyActionMap));
    }

    @Override
    protected Widget attachPinWidget(QbTableBean node, QbPinBean pin) {
//        if (pin.endsWith(PIN_ID_DEFAULT_SUFFIX)) {
//            return null;
//        }

        VMDPinWidget widget = new VMDPinWidget(this, pin, scheme);

//        Widget w = findWidget(node);
//        log.debug("w:%s(%s)", w.getClass().getName(), w);


        ((QbTableNodeWidget) findWidget(node)).attachPinWidget(widget);
        widget.getActions().addAction(createObjectHoverAction());
        widget.getActions().addAction(createSelectAction());

        return widget;
    }

    @Override
    protected void attachEdgeSourceAnchor(QbLinkBean edge, QbPinBean oldSourcePin, QbPinBean sourcePin) {
        Widget w = sourcePin != null ? findWidget(sourcePin) : null;
        ((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));
//
//        Anchor anchor = AnchorFactory.createFixedAnchor(new Point(100, 100));
//        ((ConnectionWidget) findWidget(edge)).setSourceAnchor(anchor);
    }

    @Override
    protected void attachEdgeTargetAnchor(QbLinkBean edge, QbPinBean oldTargetPin, QbPinBean targetPin) {
        Widget w = targetPin != null ? findWidget(targetPin) : null;
        ((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
    }

//    private void testPin(QueryBuilderTableBean nb) {
//        for (I_WfColumn col : nb.getTableDetail().getColumns()) {
//            //            super.addPin(nb, new QbPinBean(col.getSqlName(), 'r'));
//            createPin(nb, col);
//        }
//    }
//    private void createPin(QueryBuilderTableBean nb, I_WfColumn col) {
////    createPin (VMDGraphScene scene, String nodeID, String pinID, Image image, String name, String type) {
//        super.addPin(nb, new QbPinBean(col.getSqlName(), 'r'));
////        QbPinBean pinWidget =  super.addPin(nb, new QbPinBean(col.getSqlName(), 'r'));  // ((VMDPinWidget) scene.addPin(nodeID, pinID));
////        pinWidget.setProperties(name, null);
//    }
    public void linkTables(Meta_Table stable, I_WfColumn scol, Meta_Table ttable, I_WfColumn tcol) {
        QbTableBean stbean = model.getNode(stable.getMetaMapsKey());
        QbTableBean ttbean = model.getNode(ttable.getMetaMapsKey());
        // 加入连线...
        QbPinBean spin = new QbPinBean(scol.getSqlName(), 'r');
        QbPinBean tpin = new QbPinBean(tcol.getSqlName(), 'l');
        addPin(stbean, spin);
        addPin(ttbean, tpin);

        QbLinkBean edge = model.newEdge(spin, tpin);

//        ConnectionWidget connection = new ConnectionWidget(this);
//        connection.setSourceAnchor(AnchorFactory.createCircularAnchor(spinWidget, 32));
//        connection.setTargetAnchor(AnchorFactory.createCircularAnchor(tpinWidget, 32));
//        connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);

        addEdge(edge);
        setEdgeSource(edge, spin);
        setEdgeTarget(edge, tpin);


//        connectionLayer.addChild(connection);

        model.addConnection(edge);
    }

    public void refillSqlModel() {
        model.refillSqlModel(this.sqlBuilderPanel.getModel());
    }

    /**
     * @return the model
     */
    public QbSceneModel getModel() {
        return model;
    }

    public void setSqlCreateModel(SqlCreateModel sqlModel) {
        this.model.setSqlCreateModel(sqlModel);
    }

    private class SceneConnectProvider implements ConnectProvider {

        private QbPinBean source = null;
        private QbPinBean target = null;

        @Override
        public boolean isSourceWidget(Widget sourceWidget) {
            Object object = findObject(sourceWidget);
            source = isPin(object) ? (QbPinBean) object : null;
            if (source != null) {
                if (source.canCreateOutCn()) {
                    return true;
                } else {
                    // frame.setStatus("源节点拒绝链接。");
                    return false;
                }
            }
            return source != null;
        }

        @Override
        public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
            Object object = findObject(targetWidget);
            target = isPin(object) ? (QbPinBean) object : null;
            if (target != null) {
                if (target.canCreateInCn()) {
                    return !source.equals(target) ? ConnectorState.ACCEPT : ConnectorState.REJECT_AND_STOP;
                } else {
                    //  frame.setStatus("目标节点拒绝链接。");
                    return ConnectorState.REJECT_AND_STOP;
                }
            }
            return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
        }

        @Override
        public boolean hasCustomTargetWidgetResolver(Scene scene) {
            return false;
        }

        @Override
        public Widget resolveTargetWidget(Scene scene, Point sceneLocation) {
            return null;
        }

        @Override
        public void createConnection(Widget sourceWidget, Widget targetWidget) {
            int edgeId = getModel().getNextEdgeId();
            QbLinkBean edge = new QbLinkBean(edgeId, (QbPinBean) findObject(sourceWidget), (QbPinBean) findObject(targetWidget));

            addEdge(edge);
            setEdgeSource(edge, source);
            setEdgeTarget(edge, target);

            getModel().addConnection(edge);
        }
    }

    private class SceneReconnectProvider implements ReconnectProvider {

        QbLinkBean edge;
        QbPinBean originalNode;
        QbPinBean replacementNode;

        @Override
        public void reconnectingStarted(ConnectionWidget connectionWidget, boolean reconnectingSource) {
        }

        @Override
        public void reconnectingFinished(ConnectionWidget connectionWidget, boolean reconnectingSource) {
        }

        @Override
        public boolean isSourceReconnectable(ConnectionWidget connectionWidget) {
            Object object = findObject(connectionWidget);
            edge = isEdge(object) ? (QbLinkBean) object : null;
            originalNode = edge != null ? getEdgeSource(edge) : null;
            return originalNode != null;
        }

        @Override
        public boolean isTargetReconnectable(ConnectionWidget connectionWidget) {
            Object object = findObject(connectionWidget);
            edge = isEdge(object) ? (QbLinkBean) object : null;
            originalNode = edge != null ? getEdgeTarget(edge) : null;
            return originalNode != null;
        }

        @Override
        public ConnectorState isReplacementWidget(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
            Object object = findObject(replacementWidget);
            replacementNode = isPin(object) ? (QbPinBean) object : null;
            if (replacementNode != null) {
                return ConnectorState.ACCEPT;
            }
            return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
        }

        @Override
        public boolean hasCustomReplacementWidgetResolver(Scene scene) {
            return false;
        }

        @Override
        public Widget resolveReplacementWidget(Scene scene, Point sceneLocation) {
            return null;
        }

        @Override
        public void reconnect(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
            if (replacementWidget == null) {
                removeEdge(edge);
            } else if (reconnectingSource) {
                setEdgeSource(edge, replacementNode);
            } else {
                setEdgeTarget(edge, replacementNode);
            }
        }
    }

    public void onNodeRemoved(QbTableBean tbean) {
        model.removeNode(tbean);
        sqlBuilderPanel.getModel().onTableRemoved(tbean);
        if (this.model.getNodes().isEmpty()) {   // 既然表都以掉了，那么将所有的列屏蔽清除。避免引起错误。
            sqlBuilderPanel.getModel().resetFieldMasks();
        }
        sqlBuilderPanel.refreshSqlPreview(false);

    }

    /**
     * @return the sqlPreviewPanel
     */
    public static QbRightPanel getSqlPreviewPanel() {
        return sqlPreviewPanel;
    }

    /**
     * 将SQL预览面板引入到本类中，用于SQL预览刷新
     * @param sqlPreviewPanel the sqlPreviewPanel to set
     */
    public static void setSqlPreviewPanel(QbRightPanel sqlPreviewPanel) {
        QbScene.sqlPreviewPanel = sqlPreviewPanel;
    }

    private class QbVlAction extends AbstractAction {

        final com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();

        public QbVlAction() {
            super("QbVlAction");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog (null, "My Action has been invoked");
//        log.debug(Toolfunc.getCurrentFunctionName());
            Wftool.messageDialog(true, Toolfunc.getCurrentFunctionName());
        }
    }
}
