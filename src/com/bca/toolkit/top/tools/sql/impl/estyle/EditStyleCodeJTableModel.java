package com.bca.toolkit.top.tools.sql.impl.estyle;

import com.bca.api.local.PersistClientApi;
import com.bca.api.pub.Ret;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import com.bca.pub.tools.Stringtool;
import com.bca.service.ps.PoUpdateAction;
import com.bca.templ.pub.AbstractStudioApp;
import java.util.Collection;
import java.util.List;

/**
 * 编辑风格编码表管理 jtable模型 
 * @author 北京华夏翰科科技有限公司 http://www.hxhk.com.cn
 * 2012-10-02 23:06:11.656
 */
public class EditStyleCodeJTableModel extends TableModelWithAliasCol<EditStyleCode> {

    /** Creates a new instance of HttpFormControlTableModel */
    public EditStyleCodeJTableModel() {
        int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "隐藏列", "hiddenColumn", 0));
        columnAliasMap.put("itemCode", new TableColumnAliasUtil(col++, "存储代码", "itemCode", 80));
        columnAliasMap.put("compLabel", new TableColumnAliasUtil(col++, "控件标签", "compLabel", 80));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);

        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
    final Ret ret = new Ret();

    @Override
    public boolean loadAllDatas() {
        return refreshAll();
    }

    @Override
    public Object[] getRowData(EditStyleCode o) {
        return new Object[]{o, o.getItemCode(), o.getCompLabel()};
    }

    @Override
    public String getDeleteWarnMessage(Collection<EditStyleCode> deleteTargets) {
        if (deleteTargets.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (EditStyleCode h : deleteTargets) {
            sb.append(h).append(", ");
        }
        Stringtool.reduceLength(sb, 2);
        return String.format("删除如下编辑风格编码表:%s\n确认吗?", sb.toString());
//        return "不允许删除";
    }

    @Override
    public EditStyleCode createDefaultBean() {
        EditStyleCode b = new EditStyleCode("", "", 0);
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
    protected void addBeanAux(EditStyleCode e) {
    }
    
    @Override
    public boolean updateCellToBean(int row, int col) {
        EditStyleCode e = (EditStyleCode) this.getValueAt(row, 0);
        Object val = this.getValueAt(row, col);
        switch (col) {   
            case 1:     // 存储代码
                return false;   // 主键 不可以设置值。
            case 2:     // 控件标签
                e.setCompLabel((java.lang.String)val);
                break;

            default:
                return false;   // 不应运行到此处。
        }
        this.saveBeanAux(row, e);
        return true;
    }

    @Override
    protected void saveBeanAux(int row, EditStyleCode e) {
        PersistClientApi psapi = getPsApi();
        int handle = psapi.getHandle("test01", ret);
        // 
        psapi.updatePo(handle, PoUpdateAction.SaveOrUpdate, e, ret);
        // 
        psapi.freeHandle(handle, ret);
    }

    @Override
    protected EditStyleCode removeBeanEx(EditStyleCode e) {
        PersistClientApi psapi = getPsApi();
        int handle = psapi.getHandle("test01", ret);
        psapi.updatePo(handle, PoUpdateAction.Delete, e, ret);
        psapi.freeHandle(handle, ret);
        return e;
    }

    public boolean refreshAll() {
//        // 放置数据，为行代表的对象。
//        PersistClientApi psapi = getPsApi();
//        int handle = psapi.getHandle("test01", ret);
//        psapi.checkPoClassLoaded(handle, EditStyleCode.class.getName(), ret);
//        // 
//        String hql = String.format("from %s t", EditStyleCode.class.getName());
//        List<EditStyleCode> datas = psapi.hqlQuery(handle, hql, ret);
//        // 
//        psapi.freeHandle(handle, ret);
//        // 
//        super.clear();
//        for (EditStyleCode bean : datas) {
//            this.addRow(bean);
//        }
        // 
        return true;
    }

    private PersistClientApi getPsApi() {
        AbstractStudioApp app = AbstractStudioApp.getApp();
        app.checkPersistClientReady();
        return app.getPersistClientApi();
    }
}
