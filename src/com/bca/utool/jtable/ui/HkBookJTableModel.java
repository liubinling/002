/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.utool.jtable.ui;

import com.bca.api.local.PersistClientApi;
import com.bca.api.pub.Ret;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import com.bca.pub.tools.Stringtool;
import com.bca.service.ps.PoUpdateAction;
import com.bca.templ.pub.AbstractStudioApp;
import com.bca.utool.jtable.po.HkBook;
import java.util.Collection;
import java.util.List;

/**
 * ͼ����� ����ģ�� 
 * @author �������ĺ��ƿƼ����޹�˾ http://www.hxhk.com.cn
 */
public class HkBookJTableModel extends TableModelWithAliasCol<HkBook> {

    /** Creates a new instance of HttpFormControlTableModel */
    public HkBookJTableModel() {
        int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "������", "hiddenColumn", 0));
        columnAliasMap.put("bookId", new TableColumnAliasUtil(col++, "ͼ����", "id", 90));
        columnAliasMap.put("bookName", new TableColumnAliasUtil(col++, "����", "bookName", 60));
        columnAliasMap.put("bookAuthor", new TableColumnAliasUtil(col++, "����", "bookAuthor", 60));
        columnAliasMap.put("bookDate", new TableColumnAliasUtil(col++, "��������", "bookDate", 60));
        columnAliasMap.put("bookPublish", new TableColumnAliasUtil(col++, "������", "bookPublish", 60));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);

        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
    final Ret ret = new Ret();

    @Override
    public boolean loadAllDatas() {
        return refreshAll();
    }

    @Override
    public Object[] getRowData(HkBook o) {
        return new Object[]{o, o.getBookId(), o.getBookName(), o.getBookAuthor(), o.getBookDate(), o.getBookPublish()};
    }

    @Override
    public String getDeleteWarnMessage(Collection<HkBook> deleteTargets) {
        if (deleteTargets.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (HkBook h : deleteTargets) {
            sb.append(h.getBookName()).append(", ");
        }
        Stringtool.reduceLength(sb, 2);
        return String.format("ɾ�������鼮:%s\nȷ����?", sb.toString());
        
        
//        return "������ɾ��";
    }

    @Override
    public HkBook createDefaultBean() {
        HkBook b = new HkBook(0, "", "", null, "");
        return b;
    }

    @Override
    public boolean isAppendEnable() {
        return true;
    }

    @Override
    public boolean isSaveEnable() {
        return true;
    }

    @Override
    public boolean isDeleteEnable() {
        return true;
    }

    @Override
    public boolean isAutoUpdateRow() {
        return true;
    }

    @Override
    protected void addBeanAux(HkBook e) {
    }

    @Override
    public boolean updateCellToBean(int row, int col) {
        HkBook e = (HkBook) this.getValueAt(row, 0);
        Object val = this.getValueAt(row, col);
        switch (col) {   // o.getBookId(), o.getBookName(), o.getBookAuthor(), o.getBookDate(), o.getBookPublish()};
            case 1:
                // e.setBookId(col);
                return false;   // ���� ����������ֵ��
            case 2:
                e.setBookName((String) val);
            case 5:
                e.setBookPublish((String)val);
                break;
        }
        this.saveBeanAux(row, e);
        return true;
    }

    @Override
    protected void saveBeanAux(int row, HkBook e) {
        PersistClientApi psapi = getPsApi();
        int handle = psapi.getHandle("test01", ret);
        // 
        psapi.updatePo(handle, PoUpdateAction.SaveOrUpdate, e, ret);
        // 
        psapi.freeHandle(handle, ret);
    }

    @Override
    protected HkBook removeBeanEx(HkBook e) {
        PersistClientApi psapi = getPsApi();
        int handle = psapi.getHandle("test01", ret);
        psapi.updatePo(handle, PoUpdateAction.Delete, e, ret);
        psapi.freeHandle(handle, ret);
        return e;
    }

    public boolean refreshAll() {
        // �������ݣ�Ϊ�д���Ķ���
        PersistClientApi psapi = getPsApi();
        int handle = psapi.getHandle("test01", ret);
        psapi.checkPoClassLoaded(handle, HkBook.class.getName(), ret);
        // 
        String hql = String.format("from %s t", HkBook.class.getName());
        List<HkBook> datas = psapi.hqlQuery(handle, hql, ret);
        // 
        psapi.freeHandle(handle, ret);
        // 
        super.clear();
        for (HkBook bean : datas) {
            this.addRow(bean);
        }
        // 
        return true;
    }

    private PersistClientApi getPsApi() {
        AbstractStudioApp app = AbstractStudioApp.getApp();
        app.checkPersistClientReady();
        return app.getPersistClientApi();
    }
}
