package com.bca.toolkit.top.tools.sql.impl.util.w.jt;

import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiTemplUnit;
import com.bca.api.pub.Ret;
import com.bca.pub.gui.jtablex.TableColumnAliasUtil;
import com.bca.pub.gui.jtablex.TableColumnNameCollectionUtil;
import com.bca.pub.gui.jtablex.TableModelWithAliasCol;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Toolfunc;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * WEB_UI_TEMPL_UNIT管理 jtable模型
 *
 * @author 北京华夏翰科科技有限公司 http://www.hxhk.com.cn 2013-04-09 14:25:40.453
 */
public class WebUiTemplUnitJTableModel extends TableModelWithAliasCol<WebUiTemplUnit> {

    final WebUiTemplUnitJTableForm form;
    public final Map<String, WebUiTemplUnit> estyleTemplUnits = new TreeMap<String, WebUiTemplUnit>();

    /**
     * Creates a new instance of HttpFormControlTableModel
     */
    WebUiTemplUnitJTableModel(WebUiTemplUnitJTableForm aThis) {
        this.form = aThis;

        int col = 0;
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "隐藏列", "hiddenColumn", 0));
        columnAliasMap.put("estyleArgName", new TableColumnAliasUtil(col++, "模板参数名", "estyleArgName", 120));
        columnAliasMap.put("estyleContent", new TableColumnAliasUtil(col++, "模板内容", "estyleContent", 200));
        columnAliasMap.put("scriptLevel", new TableColumnAliasUtil(col++, "脚本级别(t/f)", "scriptLevel", 100));
        columnAliasMap.put("idx", new TableColumnAliasUtil(col++, "顺序号", "idx", 80));
        columnAliasMap.put("note", new TableColumnAliasUtil(col++, "说明", "note", 120));

        TableColumnNameCollectionUtil columnCollectionUtil = new TableColumnNameCollectionUtil(columnAliasMap);
        super.setColumnIdentifiers(columnCollectionUtil.getColumnNameArray());
    }
    final Ret ret = new Ret();

    @Override
    public boolean loadAllDatas() {
        return refreshAll();
    }

    @Override
    public Object[] getRowData(WebUiTemplUnit o) {
        return new Object[]{o, o.getEstyleArgName(), o.getEstyleContent(), o.getScope(), o.getIdx(), o.getNote()};
    }

    @Override
    public String getDeleteWarnMessage(Collection<WebUiTemplUnit> deleteTargets) {
        if (deleteTargets.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (WebUiTemplUnit h : deleteTargets) {
            sb.append(h).append(", ");
        }
        Stringtool.reduceLength(sb, 2);
        return String.format("删除如下WEB_UI_TEMPL_UNIT:%s\n确认吗?", sb.toString());
//        return "不允许删除";
    }

    @Override
    public WebUiTemplUnit createDefaultBean() {
        WebUiTemplUnit b = new WebUiTemplUnit("", "");
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
    protected void addBeanAux(WebUiTemplUnit e) {
    }

    @Override
    public boolean updateCellToBean(int row, int col) {
        WebUiTemplUnit e = (WebUiTemplUnit) this.getValueAt(row, 0);
        Object val = this.getValueAt(row, col);
        switch (col) {
            case 1:     // 
                return false;   // 主键 不可以设置值。
            case 2:     // 
                e.setEstyleContent((java.lang.String) val);
                break;

            default:
                return false;   // 不应运行到此处。
        }
        this.saveBeanAux(row, e);
        return true;
    }

    @Override
    protected void saveBeanAux(int row, WebUiTemplUnit e) {
        logArgSet();
        if (e.getEstyleArgName() == null || e.getEstyleArgName().isEmpty()) {
            log.warn("getEstyleArgName null at:%s", Toolfunc.getCallStack());
        } else {
            estyleTemplUnits.put(e.getEstyleArgName(), e);
        }
        logArgSet();
    }

    @Override
    protected WebUiTemplUnit removeBeanEx(WebUiTemplUnit e) {
        logArgSet();
        WebUiTemplUnit u = estyleTemplUnits.remove(e.getEstyleArgName());
        logArgSet();
        form.savePool();
        logArgSet();
        return u;
    }

    public boolean refreshAll() {
        super.clear();
        for (WebUiTemplUnit bean : estyleTemplUnits.values()) {
            this.addRow(bean);
        }
        return true;
    }

    public void load(Map<String, WebUiTemplUnit> m) {
        this.estyleTemplUnits.clear();
        if (m != null) {
            this.estyleTemplUnits.putAll(m);
            estyleTemplUnits.remove("");
        }
        // 
        refreshAll();
    }

    private void logArgSet() {
        log.debug("estyleTemplUnits:%s", estyleTemplUnits.keySet());
    }
}
