/*
 * NCanvasForm.java
 *
 * Created on 2008锟斤拷8锟斤拷21锟斤拷, 锟斤拷锟斤拷6:54
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.toolkit.top.tools.orm.I_PojoSourceCreator;
import com.bca.pub.gui.BcaDockingForm;
import com.bca.pub.gui.BcaDockingUnit;
import com.bca.pub.gui.WideEditorPane;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.I_PojoFactoryForm;
import com.bca.toolkit.top.tools.sql.BcaTool_SqlBuilderTopForm;
import com.bca.toolkit.top.tools.sql.I_SqlFactoryForm;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.SrcFactoryBean;
import com.bca.utool.cwiz.CWizDbresForm;
import com.bca.utool.cwiz.CWizHelpForm;
import com.bca.utool.cwiz.CWizMethodForm;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.title.DockingWindowTitleProvider;

/**
 *
 * @author pxz
 */
public class QbDockForm extends BcaDockingForm implements DockingWindowTitleProvider, I_PojoFactoryForm, ListSelectionListener {

    public final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final SqlCreateModel model;  // = new SqlCreateModel(this);
    final BcaTool_SqlBuilderTopForm parentPanel;
    //
    private BcaDockingUnit[] leftDockUnit = new BcaDockingUnit[3];
    private BcaDockingUnit[] centerDockUnit = new BcaDockingUnit[1];
    private BcaDockingUnit[] rightDockUnit = new BcaDockingUnit[1];
    //private BcaDockingUnit[] bottomDockUnit = new BcaDockingUnit[2];
    private BcaDockingUnit[] bottomDockUnit = null;
//    final DynamicViewTitle canvasTitleProvider = new DynamicViewTitle();
//    private static BcaTool_PojoFac_QueryBuilder inst = null;
//
//    public static BcaTool_PojoFac_QueryBuilder getInst() {
//        if (inst == null) {
//            inst = new BcaTool_PojoFac_QueryBuilder();
//        }
//        return inst;
//    }
    final QbScene scene;  // = new QueryBuilderScene();
    //private javax.swing.JEditorPane sqlEditPane;
    //private javax.swing.JScrollPane sqlScrollPane;
    private QbRightPanel qbRightPanel;

    /**
     * Creates new form BeanForm
     */
    public QbDockForm(BcaTool_SqlBuilderTopForm pojoCreatePanel) {
//        initComponents();
        //
        this.parentPanel = pojoCreatePanel;
        model = this.parentPanel.getModel();
        scene = new QbScene(parentPanel);
        model.setSceneModel(scene.getModel());
        //


        //initComponents();
        setLayout(new java.awt.BorderLayout());

        // wfinit 在 createViews 前面
        wfinit();

        super.doCreateDockWindows();
        createViews(); //create views
        super.showViews();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    //Create views

    private void createViews() {
//        leftDockUnit[0] = new BcaDockingUnit(CWizDirForm.getInst(), 0.5f, null, "工厂目录", super.getVIEW_ICON(), false);
//        leftDockUnit[1] = new BcaDockingUnit(CWizDbresForm.getInst(), 0.5f, null, "数据资源", super.getVIEW_ICON(), false);



        JComponent sceneView = scene.getView();
        if (sceneView == null) {
            sceneView = scene.createView();
        }
        JScrollPane sceneScroll = new JScrollPane(sceneView);
        sceneScroll.getHorizontalScrollBar().setUnitIncrement(32);
        sceneScroll.getHorizontalScrollBar().setBlockIncrement(256);
        sceneScroll.getVerticalScrollBar().setUnitIncrement(32);
        sceneScroll.getVerticalScrollBar().setBlockIncrement(256);

        leftDockUnit[0] = new BcaDockingUnit(CWizDbresForm.getInst(), 1.0f, null, "数据库", super.getVIEW_ICON(), false);
        leftDockUnit[1] = new BcaDockingUnit(QbLeftCenForm.getInst(), 1.0f, null, "输出", super.getVIEW_ICON(), false);
        //修改布局，把卫星视图挪至左侧最下边，原来是在下侧最左边（修改时间2017/05/31，修改人：孙天用）
        leftDockUnit[2] = new BcaDockingUnit(scene.createSatelliteView(), 0.9f, null, "卫星视图", super.getVIEW_ICON(), false);
        
        centerDockUnit[0] = new BcaDockingUnit(sceneScroll, 1.0f, null, "SQL画板", super.getVIEW_ICON(), false);
//        
//        centerDockUnit[1] = new BcaDockingUnit(FlowWorkSheetTopComponent.findInstance(), 0.7f, null, "锟斤拷锟教伙拷锟斤拷", super.getVIEW_ICON(), false);
//        QbTopBar qbTopBar = new QbTopBar();
//        centerDockUnit[0].getDockingWindow() .getCustomTabComponents().add(qbTopBar);
        //右侧变为SQL预览，原来SQL预览在下侧的右边（修改时间2017/05/31，修改人：孙天用）
        rightDockUnit[0] = new BcaDockingUnit(qbRightPanel, 0.8f, null, "SQL预览", super.getVIEW_ICON(), false);
        //rightDockUnit[1] = new BcaDockingUnit(CWizMethodForm.getInst(), 0.3f, null, "where", super.getVIEW_ICON(), false);
        //rightDockUnit[2] = new BcaDockingUnit(CWizHelpForm.getInst(), 0.2f, null, "order", super.getVIEW_ICON(), false);

//        javax.swing.JScrollPane logScrollPane = new javax.swing.JScrollPane();
//        logScrollPane.setViewportView(this.sqlEditPane);

//        javax.swing.JScrollPane satelliteScrollPane = new javax.swing.JScrollPane();
//        satelliteScrollPane.setViewportView(scene.createSatelliteView());

       // bottomDockUnit[0] = new BcaDockingUnit(scene.createSatelliteView(), 0.2f, null, "卫星视图", super.getVIEW_ICON(), false);
       // bottomDockUnit[1] = new BcaDockingUnit(qbBottomPane, 0.8f, null, "SQL预览", super.getVIEW_ICON(), false);
        // 
        super.setHorizontalLayout(0.20f, 0.5f, 0.30f);
       // super.setVerticalLayout(0.7f, 0.3f);
        super.loadDockingForms(leftDockUnit, rightDockUnit, bottomDockUnit, centerDockUnit);
        // 
        leftDockUnit[1].getDockingWindow().getWindowProperties().setTitleProvider(QbLeftCenForm.getInst());
        QbLeftCenForm.getInst().setDock(leftDockUnit[1].getDockingWindow());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.FlowLayout());
    }// </editor-fold>//GEN-END:initComponents
//    public RootWindow getRootWindowForShow() {
//        return super.getRootWindow();
//    }
//    public void setCanvasTitle(String title) {
//        canvasTitleProvider.setTitle(title);
////        FlowWorkSheetTopComponent.findInstance().repaint();
////        canvasTitleProvider.notifyAll();
////        views[3].updateUI();
//    }
    String title = "QueryBuilder";

    public void setTitle(final String title) {
        this.title = title;

//             this.repaint();
//        this.updateUI();
//
//        views[3].updateUI();
//        views[3].doLayout();
//        SwingUtilities.updateComponentTreeUI(this);
//        SwingUtilities.updateComponentTreeUI(views[3]);

        centerDockUnit[1].getDockingWindow().getWindowProperties().setTitleProvider(new DockingWindowTitleProvider() {
            @Override
            public String getTitle(DockingWindow window) {
                return QbDockForm.this.title;
            }
        });
        centerDockUnit[1].getDockingWindow().getWindowProperties().setTitleProvider(this);
//        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public String getTitle(DockingWindow window) {
        return title;
    }
    //

    private void wfinit() {
        qbRightPanel = new QbRightPanel();
       /* sqlEditPane = new WideEditorPane();
        sqlEditPane.setEditable(false);

        sqlScrollPane.setViewportView(sqlEditPane);*/
    }

    @Override
    public void reloadORM() {
    }

    @Override
    public I_PojoSourceCreator getSourceCreator() {
        return null;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    public void refreshSqlPreview(final String sql) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QbRightPanel.getSqlEditPane().setText(sql);
            }
        });
    }

    public void refillSqlModel() {
        scene.refillSqlModel();
    }

//    public void setActiveFactory(SrcFactoryBean activeFactory) {
//        this.scene.getModel().setActiveFactory(activeFactory);
//    }
    public void setSqlCreateModel(SqlCreateModel model) {
        scene.setSqlCreateModel(model);
    }

//    public void onSelect(I_SqlFactoryForm factForm) {
//        if (factForm == null) {
//            return;
//        }
//        factForm.loadLeftCenForm(QbLeftCenForm.getInst());
//    }

    public void onSelect(SrcFactoryBean factBean) {
        if (factBean == null) {
            return;
        }
        factBean.uiTool.loadLeftCenForm(QbLeftCenForm.getInst());
    }
//设置SQL预览区域的可编辑性
    void setSqlPreviewState(boolean b) {
        QbRightPanel.getSqlEditPane().setEditable(b);
    }

    String getSqlPreview() {
        return QbRightPanel.getSqlEditPane().getText();
    }
}
