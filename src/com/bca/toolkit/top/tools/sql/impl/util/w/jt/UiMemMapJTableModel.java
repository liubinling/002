package com.bca.toolkit.top.tools.sql.impl.util.w.jt;

import com.bca.api.pub.Ret;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Toolfunc;
import com.bca.toolkit.top.tools.sql.impl.util.w.UiMemMap;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import java.util.Collection;

/**
 * 界面元素登记表管理 jtable模型
 *
 * @author 北京华夏翰科科技有限公司 http://www.hxhk.com.cn 2013-02-14 13:12:31.21
 */
public class UiMemMapJTableModel extends TableModelWithAliasCol<UiMemMap> {

    WebUiMemBean uiMemBean;

    /**
     * Creates a new instance of HttpFormControlTableModel
     *
     */
    public UiMemMapJTableModel() {
        int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "隐藏列", "hiddenColumn", 0));
        columnAliasMap.put("value", new TableColumnAliasUtil(col++, "存储值", "value", 80));
        columnAliasMap.put("label", new TableColumnAliasUtil(col++, "显示值", "label", 150));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);

        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
    final Ret ret = new Ret();

    @Override
    public boolean loadAllDatas() {
        return refreshAll();
    }

    @Override
    public Object[] getRowData(UiMemMap o) {
        return new Object[]{o, o.getValue(), o.getLabel()};
    }

    @Override
    public String getDeleteWarnMessage(Collection<UiMemMap> deleteTargets) {
        if (deleteTargets.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (UiMemMap h : deleteTargets) {
            sb.append(h).append(", ");
        }
        Stringtool.reduceLength(sb, 2);
        return String.format("删除如下界面元素登记表:%s\n确认吗?", sb.toString());
//        return "不允许删除";
    }

    @Override
    public UiMemMap createDefaultBean() {
        UiMemMap b = new UiMemMap("", "");
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
    protected void addBeanAux(UiMemMap e) {
    }

    @Override
    public boolean updateCellToBean(int row, int col) {
        UiMemMap e = (UiMemMap) this.getValueAt(row, 0);
        Object val = this.getValueAt(row, col);
        switch (col) {
            case 1:     // 存储值
                return false;   // 主键 不可以设置值。
            case 2:     // 显示值
                e.setLabel((java.lang.String) val);
                break;
            default:
                return false;   // 不应运行到此处。
        }
        this.saveBeanAux(row, e);
        return true;
    }

    @Override
    protected void saveBeanAux(int row, UiMemMap e) {
        // log.debug("saveBeanAux:%d:%s at:%s", row, e, Toolfunc.getCallStack());
        if (Stringtool.isStringEmpty(e.getValue())) {
            return;
        }
        uiMemBean.getV_label().put(e.getValue(), e);
    }

    @Override
    protected UiMemMap removeBeanEx(UiMemMap e) {
//        PersistClientApi psapi = getPsApi();
//        int handle = psapi.getHandle("test01", ret);
//        psapi.updatePo(handle, PoUpdateAction.Delete, e, ret);
//        psapi.freeHandle(handle, ret);
        uiMemBean.getV_label().remove(e.getValue());
        return e;
    }

    public boolean refreshAll() {
//        // 放置数据，为行代表的对象。
//        PersistClientApi psapi = getPsApi();
//        int handle = psapi.getHandle("test01", ret);
//        psapi.checkPoClassLoaded(handle, UiMemMap.class.getName(), ret);
//        // 
//        String hql = String.format("from %s t", UiMemMap.class.getName());
//        List<UiMemMap> datas = psapi.hqlQuery(handle, hql, ret);
//        // 
//        psapi.freeHandle(handle, ret);
//        // 
//        super.clear();
//        for (UiMemMap bean : datas) {
//            this.addRow(bean);
//        }
        // 
        return true;
    }

//    private PersistClientApi getPsApi() {
//        AbstractStudioApp app = AbstractStudioApp.getApp();
//        app.checkPersistClientReady();
//        return app.getPersistClientApi();
//    }
    public void load(WebUiMemBean bean) {
        this.uiMemBean = bean;
        super.clear();
        for (UiMemMap m : bean.getV_label().values()) {
            this.addRow(m);
        }
    }
}
