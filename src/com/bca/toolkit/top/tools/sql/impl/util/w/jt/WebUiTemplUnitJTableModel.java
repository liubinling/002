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
 * WEB_UI_TEMPL_UNIT���� jtableģ��
 *
 * @author �������ĺ��ƿƼ����޹�˾ http://www.hxhk.com.cn 2013-04-09 14:25:40.453
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
        columnAliasMap.put("hiddenColumn", new TableColumnAliasUtil(col++, "������", "hiddenColumn", 0));
        columnAliasMap.put("estyleArgName", new TableColumnAliasUtil(col++, "ģ�������", "estyleArgName", 120));
        columnAliasMap.put("estyleContent", new TableColumnAliasUtil(col++, "ģ������", "estyleContent", 200));
        columnAliasMap.put("scriptLevel", new TableColumnAliasUtil(col++, "�ű�����(t/f)", "scriptLevel", 100));
        columnAliasMap.put("idx", new TableColumnAliasUtil(col++, "˳���", "idx", 80));
        columnAliasMap.put("note", new TableColumnAliasUtil(col++, "˵��", "note", 120));

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
        return String.format("ɾ������WEB_UI_TEMPL_UNIT:%s\nȷ����?", sb.toString());
//        return "������ɾ��";
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
                return false;   // ���� ����������ֵ��
            case 2:     // 
                e.setEstyleContent((java.lang.String) val);
                break;

            default:
                return false;   // ��Ӧ���е��˴���
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
