/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.g.VMDPinWidget;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.model.StateModel;
import org.netbeans.api.visual.widget.ComponentWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author pxz
 */
public class QbTableNodeWidget extends Widget {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
//    String toolTipText = "";
    final QbScene scene;
    final QbTableBean tbean;
    private StateModel stateModel = new StateModel(2);
    //
//    private AtomicInteger outEdgeCounter = new AtomicInteger(0);
//    private AtomicInteger inEdgeCounter = new AtomicInteger(0);

    public QbTableNodeWidget(QbScene scene, QbTableBean tbean) {
        super(scene);           // TextOrientation.BOTTOM_CENTER);
        this.tbean = tbean;
        this.scene = scene;
        wfinit();
    }
    
    

//    public int getOutEdgeCounterValue() {
//        return outEdgeCounter.intValue();
//    }
//
//    public int getInEdgeCounterValue() {
//        return inEdgeCounter.intValue();
//    }
//    public int getNextRightEdgeGap() {
//        int i = outEdgeCounter.intValue();
//        outEdgeCounter.incrementAndGet();
//        return getGapByIdx(i);
//    }
//
//    public int getNextLeftEdgeGap() {
//        int i = inEdgeCounter.intValue();
//        inEdgeCounter.incrementAndGet();
//        return getGapByIdx(i);
//    }
//
//    private static int getGapByIdx(int i) {
////        if (i < 10) {
//        boolean odd = (i & 0x01) == 1;
//        int g = ((i + 1) / 2) * 2;
//        return odd ? g : g * (-1);
////        }
//    }
//    public static void main(String[] args) {
////        MchNodeWidget w = new MchNodeWidget(null);
//
//        for (int i = 0; i <= 10; i++) {
//            log.debug("i:%d, g: %d", i, MchNodeWidget.getGapByIdx(i));
//        }
//    }
    @Override
    public void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state);
        this.repaint();
    }

    @Override
    protected void paintBorder() {
        if (this.getBorder() != null) {
            super.paintBorder();
        }
    }

    public void attachPinWidget(VMDPinWidget pinWidget) {
        pinWidget.setCheckClipping(true);

        addChild(pinWidget);
        if (stateModel.getBooleanState() && isMinimizableWidget(pinWidget)) {
            pinWidget.setPreferredBounds(new Rectangle());
        } else {
            QbPinBean bean = pinWidget.getPinBean();
            Rectangle rect;
            Point p = getLocation();
            switch (bean.getSide()) {
                case 'l':
                case 'L':
                    rect = new Rectangle(0, 0, 0, 0);
                    p.x += 100;
                    break;
                case 'r':
                case 'R':
                    rect = new Rectangle(0, 0, 0, 0);
                    break;
                default:
                    rect = new Rectangle(0, 0, 0, 0);
                    break;
            }
            pinWidget.setPreferredBounds(rect);
            pinWidget.resolveBounds(p, rect);
        }
    }

    /**
     * Called to check whether a particular widget is minimizable. By default it returns true.
     * The result have to be the same for whole life-time of the widget. If not, then the revalidation has to be invoked manually.
     * An anchor (created by <code>VMDNodeWidget.createPinAnchor</code> is not affected by this method.
     * @param pinWidget the widget
     * @return true, if the widget is minimizable; false, if the widget is not minimizable
     */
    protected boolean isMinimizableWidget(Widget widget) {
        return true;
    }

    /**
     * Check the minimized state.
     * @return true, if minimized
     */
    public boolean isMinimized() {
        return stateModel.getBooleanState();
    }

    /**
     * Set the minimized state. This method will show/hide child widgets of this Widget and switches anchors between
     * node and pin widgets.
     * @param minimized if true, then the widget is going to be minimized
     */
    public void setMinimized(boolean minimized) {
        stateModel.setBooleanState(minimized);
    }

    /**
     * Toggles the minimized state. This method will show/hide child widgets of this Widget and switches anchors between
     * node and pin widgets.
     */
    public void toggleMinimized() {
        stateModel.toggleBooleanState();
    }

    private void wfinit() {
        QbTable_JTableModel tm = new QbTable_JTableModel(tbean);
        tm.setSqlModel(scene.getModel().getSqlModel());
        QbJTable jxtable = new QbJTable(tm, (QbScene) super.getScene());
       // jxtable.setRowSorter(null);    //关闭表头排序功能
       // jxtable.setAutoCreateRowSorter(false);
        tbean.setTableModel(tm);
       /*  JTableHeader header = jxtable.getTableHeader();
        
       //把jxtable包在JScrollPane里，使它能显示表头
        JScrollPane jspt = new JScrollPane(jxtable);
       // jspt.setBounds(300, 400, 800, 800);
        jspt.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);*/
      /*  JPanel jp = new JPanel();
        jp.add(header, BorderLayout.NORTH);
        jp.add(jxtable, BorderLayout.CENTER);
        jp.setVisible(true);*/
       
//        JXTable jxtable = new JXTable(tm) {
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return column == 1;
//            }
//        };
        //
        setLayout(LayoutFactory.createVerticalFlowLayout());
        //setBorder(BorderFactory.createLineBorder());      //已经把jxtable放在JScrollPane里了，不需要加边框了

        String title = String.format("%s: %s", tbean.getCnAlias(), tbean.getNodeName());
        if(tbean.getTableDetail().getTableComment() != null && !tbean.getTableDetail().getTableComment().isEmpty()) {
            title += " (" + tbean.getTableDetail().getTableComment().replace('r', ' ').replace('\n', ' ') + ')';
        }
        // LabelWidget label = new LabelWidget(getScene(), title);
        QbJTableTitlePane titlePane = new QbJTableTitlePane(title, jxtable);
        ComponentWidget label = new ComponentWidget(super.getScene(), titlePane);
        label.setOpaque(true);
        label.setBackground(new Color(0xcc, 0xff, 0x33));
        addChild(label);

        ComponentWidget componentWidget = new ComponentWidget(super.getScene(), jxtable);
       // ComponentWidget componentWidget = new ComponentWidget(super.getScene(), jp);
        addChild(componentWidget);
    }

    void addAllFieldsToOutputModel(SqlCreateModel crModel) {
        for (int row = 1; row < tbean.getTableModel().getRowCount(); row++) {
            crModel.addFieldOut(tbean.getTableModel(), row);
        }

    }
}
