/*
 * BcaTool_FilePositionTableModel.java
 *
 * Created on 2007年12月17日, 下午2:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.bca.toolkit.top.tools.util;

import com.bca.api.pub.Ret;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author pxz
 */
public class BcaTool_FilePositionTableModel extends TableModelWithAliasCol<File> {
    
    /** Creates a new instance of EtlWiz_TableMatchTableModel */
    public BcaTool_FilePositionTableModel() {
//        super(new String[] {"文件名"}, 0);
         int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "隐藏列", "hiddenColumn", 0));
        columnAliasMap.put("文件名", new TableColumnAliasUtil(col++, "文件名", "文件名", 500));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);

        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
    
    @Override
    public boolean loadAllDatas() {
        return true;
    }
    
//    final int[] colWidths = new int[] {1000};
//    public int[] getColWidths() {
//        return colWidths;
//    }
//    
//    public List<File> getDatas() {
//        return beansByRow;
//    }
    
    @Override
    public Object[] getRowData(File bean) {
        return new Object[] {bean, bean.getAbsolutePath()};
    }
    
//    public File getBeanByRow(int row) {
//        String controlName = (String)getValueAt(row, 0);
//        return dataModel.getControlBeansMap().get(controlName);
//    }
    
    @Override
    public String getDeleteWarnMessage(Collection<File> beans) {
        return "";
    }
    
//    public void deleteBeans(Collection<File> beans) {
//        for(File b : beans) {
////            controlBeans.remove(b);
//            beansByRow.remove(b);
//        }
//    }
    
    @Override
    public File createDefaultBean() {
//        File b = new File();
//        return b;
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @Override
    public boolean isAppendEnable() {
        return false;
    }
    
    @Override
    public boolean isSaveEnable() {
        return false;
    }
    
    public boolean isDeleteEnable() {
        return false;
    }
    
//    public Ret saveBean(File bean) {
    @Override
    public Ret saveBean(int row, File e) {
////        controlBeans.put(bean.getControlName(), bean);
////        return Ret.getSuccessRet();
////        throw new RuntimeException("不允许 saveBean");
//        assert row >= 0 && row < beansByRow.size();
//        this.beansByRow.set(row, e);
        return Ret.getSuccessRet();
    }
    
//    public void fillRow(int row, File e) {
////        for(int row = 0; row < getRowCount(); row++) {
////            String loginId = (String)getValueAt(row, 0);
////            if(loginId.equals(e.getControlName())) {
////                Object[] rd = getRowData(e);
////                fillRow(row, rd);
////                return;
////            }
////        }
//        throw new RuntimeException("不允许 fillRow");
//    }
    
//    public void fillRow(int row, Object[] rowdata) {
//        Uitool.fillTableRow(this, row, rowdata);
//    }
    
//    public void onRowClick(int clickedRow, File bean, int clickCount) {
 //   }
    
//    public void initDataModel(HttpFormControlModel dataModel) {
//        this.dataModel = dataModel;
//        setRowCount(0);
//        beansByRow.clear();
////        for(File bean : dataModel.controlBeans.values()) {
////            addRow(bean);
////        }
//    }
    
//    public void addRow(File e) {
//        addRow(getRowData(e));
//		  beansByRow.add(e);
//    }
    
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
//    public void insertRow(int row, File e) {
//        super.insertRow(row, getRowData(e));
//		beansByRow.add(row, e);
////        beansByRow.a
////        LinkedList a = new LinkedList();
////        beansByRow.
//    }
    
    public void saveDataModel() {
//        dataModel.controlBeans.clear();
//        for(File e : beansByRow) {
//            if(!Stringtool.isStringEmpty(e.getControlName(), true)) {
//                dataModel.controlBeans.put(e.getControlName(), e);
//            }
//        }
       throw new UnsupportedOperationException("Not yet implemented");
    }
    
//    public void addRow(Object[] rowData) {
//         throw new UnsupportedOperationException("Not yet implemented");
//    }
    
    public boolean isAutoUpdateRow() {
        return false;
    }
    
//    private I_TableRowGuiBinder<EtlWiz_TableMatchBean> tableMapRowListener = new I_TableRowGuiBinder<EtlWiz_TableMatchBean>() {
//        int rowNo = -1;
//        EtlWiz_TableMatchBean bean;
//        I_JTableDataHandler dataHandler;
//        public void rebind(int rowNo, EtlWiz_TableMatchBean e) {
//            this.rowNo = rowNo;
//            this.bean = e;
//            
//            beansByRow.clear();
//            setRowCount(0);
//            if(e == null) {
//                return ;
//            }
//            for(File m : e.getColMaps()) {
//                beansByRow.add(m);
//                addRow(m);
//            }
////            if(binder == null) {
////                binder = new CompObjBinder<EtlWiz_TableMatchBean>(e);
////                binder.bind("stable", stableBox);
////                binder.bind("dtable", dtableBox);
////                binder.loadAll();
////            } else {
////                binder.rebind(e, true);
////            }
//        }
//        public void setTableDataHandler(I_JTableDataHandler h) {
//            this.dataHandler = h;
//        }
//        public EtlWiz_TableMatchBean updateBean() {
//            return bean;
//        }
//        public boolean isSaveEnable() {
//            return false;
//        }
//        public int getBindingRow() {
//            return rowNo;
//        }
//    };
//    
//    public I_TableRowGuiBinder<EtlWiz_TableMatchBean> getTableMapRowListener() {
//        return tableMapRowListener;
//    }
//    
//    public void setTableMapRowListener(I_TableRowGuiBinder<EtlWiz_TableMatchBean> tableMapRowListener) {
//        this.tableMapRowListener = tableMapRowListener;
//    }
}
