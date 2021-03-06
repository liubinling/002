/*
 * PojoCreatePanel.java
 *
 * Created on 2007年8月3日, 上午10:24
 */
package com.bca.toolkit.top.tools.sql.qb;

import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.sql.BcaTool_SqlBuilderTopForm;
import com.bca.toolkit.top.tools.sql.I_SqlCreator;
import com.bca.toolkit.top.tools.sql.I_SqlFactoryForm;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.SrcFactoryBean;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import java.awt.dnd.DropTarget;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author  pxz
 */
public class BcaTool_QueryBuilderPanel extends javax.swing.JPanel implements I_SqlFactoryForm, ListSelectionListener {

    public final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final SqlCreateModel model;  // = new SqlCreateModel(this);
//    DropTarget dropTarget_table;
//    DropTarget dropTarget_tableSP;
    final BcaTool_SqlBuilderTopForm parentPanel;
    /** Creates new form PojoCreatePanel */
    private final QbDockForm dockForm;

    public BcaTool_QueryBuilderPanel(BcaTool_SqlBuilderTopForm topForm) {
        initComponents();
        //
        this.parentPanel = topForm;
        model = this.parentPanel.getModel();
        this.dockForm = new QbDockForm(topForm);
        // 
        wfinit();
    }

//    private int getIndexOnBox(JComboBox box, Object o) {
//        for (int i = box.getItemCount() - 1; i >= 0; i--) {
//            if (o.equals(box.getItemAt(i))) {
//                return i;
//            }
//        }
//        return -1;
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleField = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        titleField.setBackground(new java.awt.Color(0, 51, 255));
        titleField.setEditable(false);
        titleField.setForeground(new java.awt.Color(255, 255, 0));
        titleField.setText("QueryBuilder"); // NOI18N
        add(titleField, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void wfinit() {
        this.add(dockForm.getRootWindow(), java.awt.BorderLayout.CENTER);
//        QueryBuilderScene scene = new QueryBuilderScene();
//        JComponent sceneView = scene.getView();
//        if (sceneView == null) {
//            sceneView = scene.createView();
//        }
//        JScrollPane panel = new JScrollPane(sceneView);
//        panel.getHorizontalScrollBar().setUnitIncrement(32);
//        panel.getHorizontalScrollBar().setBlockIncrement(256);
//        panel.getVerticalScrollBar().setUnitIncrement(32);
//        panel.getVerticalScrollBar().setBlockIncrement(256);
//
//        split.setLeftComponent(panel);

//        this.add(panel, java.awt.BorderLayout.CENTER);

////        DatabaseObjectTreePane dbTreePanel = new DatabaseObjectTreePane();
////        dbViewPanel.add(dbTreePanel, java.awt.BorderLayout.CENTER);
////        dbTreePanel.wfinit();
//        // "字段名", "数据类型", "标志", "变量名", "变量类型", "作用域", "长度", "toString", "equals", "hashCode"
//        Uitool.setTableColumnWidths(mapTable, new int[]{150, 120, 60, 120, 120, 120, 150, 40, 40, 40});
//        Uitool.alternateRowHighlighter_onJXTable(mapTable);
//
//        packageNameBox.addItem("");
//
//        for (String pkg : app.cfg.getDataWizConfig().getPojoPackages()) {
//            packageNameBox.addItem(pkg);
//        }
//
//        dropTarget_table = new DropTarget(mapTable, model);
//        dropTarget_tableSP = new DropTarget(tableSP, model);
//
//
//        mapTable.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void reloadSqlScene() {
//        aliasField.setText(model.alias);
//        tableNameField.setText(model.dbTable.getName());
//        packageNameBox.setSelectedItem(model.pojoAttr.packageName);
//        classNameField.setText(model.pojoAttr.className);
//
//        javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel) mapTable.getModel();
//        tm.setRowCount(0);
//        for (PojoField f : model.pojoFields.values()) {
//            //  "字段名", "数据类型", "标志", "变量名", "变量类型", "作用域", "toString", "equals", "hashCode"
//            I_WfColumn col = f.col;
//            f.varType = Toolfunc.getPackedTypeBySimpleType(f.varType);
//            tm.addRow(new Object[]{col.getSqlName(), col.getSqlTypeNameWithLenInfo(), col.getFlags(), f.varName, f.varType, f.scope, f.col.getColRemark(), f.inToString, f.inEquals, f.inHashCode});
//        }
//
//        this.titleField.setText(String.format("表<%s.%s(%s)>的持久化映射 (iRapid模版)", model.alias, model.dbTable.getName(), model.dbTable.getTableRemark()));
//        tabPane.setSelectedIndex(0);
    }
    String editingCol = "";

    PojoField pojoField;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
//    private void wfinit() {
//
//        Uitool.setTableColumnWidths(mapTable, new int[]{150, 120, 60, 120, 120, 120, 70, 70, 70});
//        Uitool.alternateRowHighlighter_onJXTable(mapTable);
//
//        packageNameBox.addItem("");
//
//        for (String pkg : app.cfg.getDataWizConfig().getPojoPackages()) {
//            packageNameBox.addItem(pkg);
//        }
//
//        dropTarget_table = new DropTarget(mapTable, model);
//        dropTarget_tableSP = new DropTarget(tableSP, model);
//    }
//
//    void reloadORM() {
//        aliasField.setText(model.alias);
//        tableNameField.setText(model.dbTable.getName());
//        packageNameBox.setSelectedItem(model.pojoAttr.packageName);
//        classNameField.setText(model.pojoAttr.className);
//
//        javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel) mapTable.getModel();
//        tm.setRowCount(0);
//        for (PojoField f : model.pojoFields.values()) {
//            //  "字段名", "数据类型", "标志", "变量名", "变量类型", "作用域", "toString", "equals", "hashCode"
//            I_WfColumn col = f.col;
//            tm.addRow(new Object[]{col.getSqlName(), col.getSqlTypeNameWithLenInfo(), col.getFlags(), f.varName, f.varType, f.scope, f.inToString, f.inEquals, f.inHashCode});
//        }
//
//        this.titleField.setText(String.format("表<%s:%s>的持久化映射", model.alias, model.dbTable.getName()));
//        tabPane.setSelectedIndex(0);
//    }
//    String editingCol = "";
//
//    private void editSelectedCol() {
//        int row = mapTable.getSelectedRow();
//        if (row == -1) {
//            return;
//        }
//        String colName = (String) mapTable.getValueAt(row, 0);
//        if (editingCol.equals(colName)) {
//            return;
//        }
//        PojoField f = model.pojoFields.get(colName);
//        loadPojoFieldUI(f);
//        tabPane.setSelectedIndex(1);
//
//        editingCol = colName;
//    }
//    PojoField pojoField;
//
//    private void loadPojoFieldUI(PojoField f) {
//        this.pojoField = f;
//        colNameField.setText(f.col.getSqlName());
//        datatypeField.setText(f.col.getSqlTypeNameWithLenInfo());
//        flagsField.setText(f.col.getFlags());
//        varnameField.setText(f.varName);
//        vartypeBox.setSelectedItem(f.varType);
//        scopeBox.setSelectedItem(f.scope);
//        toStringCheck.setSelected(f.inToString);
//        equalsCheck.setSelected(f.inEquals);
//        hashCheck.setSelected(f.inHashCode);
//    }
//

    @Override
    public void valueChanged(ListSelectionEvent e) {
//        if (e.getSource() == this.mapTable.getColumnModel().getSelectionModel() && mapTable.getColumnSelectionAllowed()) {
//            int firstRow = e.getFirstIndex();
//            int lastRow = e.getLastIndex();
//            // 事件处理...
//        }
//        this.editingCol();
//        editSelectedCol();
    }

    @Override
    public I_SqlCreator getSourceCreator() {
        return new SqlSourceCreator_4_Query();
    }

    @Override
    public void refreshSqlPreview(String sql) {
        dockForm.refreshSqlPreview(sql);
    }

    @Override
    public void refillSqlModel() {
        this.dockForm.refillSqlModel();
    }

//    public void setActiveFactory(SrcFactoryBean activeFactory) {
//        dockForm.setActiveFactory(activeFactory);
//    }

    public void setSqlCreateModel(SqlCreateModel model) {
        dockForm.setSqlCreateModel(model);
    }

    /**
     * @return the dockForm
     */
    public QbDockForm getDockForm() {
        return dockForm;
    }
//设置SQL预览区域的可编辑性
    public void setSqlPreviewState(boolean b) {
            dockForm.setSqlPreviewState(b);
    }
//保留SQL预览内容
    public String getSqlPreview() {
        return dockForm.getSqlPreview();
    }
}
