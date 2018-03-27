/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.jt;

import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.tools.Numtool;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Stringtool.TargetCase;
import com.bca.pub.tools.Timetool;
import com.bca.pub.tools.Wftool;
import com.bca.studio.BStudioConfigBean;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.templ.pub.I_StudioConfig;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.sql.I_SqlCreator;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pxz
 */
public class Creator_4_Bcajt implements I_SqlCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
//    String alias;
    public SqlCreateModel smodel;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    public final List<String> fieldsUiSort = new ArrayList<String>();   // 在界面中的输出顺序。
    Meta_Table dbTable;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;
    private List<Map<String, String>> uiColSort;

    @Override
    public List<String> projNameList() {
       return  null;
    }
    
    @Override
    public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
        return sourceUnits;
    }

    @Override
    public String[] getSubPaths() {
        return new String[]{};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_Bcajt.class.getName();
    }

    @Override
    public void setSModel(SqlCreateModel smodel) {
        this.smodel = smodel;
    }

    @Override
    public void fixCompareScript(String srcOutDir) {
        
    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String jt_FRAME = "BcaJTable.Frame";
        public final static String jt_UTIL = "BcaJTable.util";
//        public final static String HBM_FRAME = "ETL.FRAME";
//        public final static String ETL_ITEM = "ETL.ITEM";
//        public final static String ETL_SQL = "ETL.SQL";
//        public final static String ETL_FUNC = "ETL.FUNC";
    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public Creator_4_Bcajt() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();
    }

    @Override
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> aaa, Meta_Table dbTable, List<Map<String, String>> uiColSort) {
//        this.alias = alias;
        this.smodel = smodel;
        this.pojoAttr = pojoAttr;
        this.pojoAttr.alias = alias;
        this.dbTable = dbTable;
        this.pojoFields = new LinkedHashMap<String, PojoField>();
        this.uiColSort = uiColSort;

        for (I_WfColumn col : dbTable.getColumns()) {
            PojoField f = new PojoField();
            f.col = col;
            f.varName = Stringtool.getRecommendName(col.getSqlName(), Stringtool.RecommendNameType.VarName);  // (pojoAttr.tabelName);
            f.varType = app.getBcaToolkitConfig().getDataWizConfig().getConvertedJavaType(col.getSqlName(), f.col.getSqlTypeName());
            if (f.varType == null || f.varType.isEmpty()) {
                f.varType = col.getJavaVarType();
            }
            pojoFields.put(f.col.getSqlName(), f);
        }

        createPojoSourceCreatorUnit("JTableModel", String.format("%sJTableModel.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("JTableForm.java", String.format("%sJTableForm.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("JTableForm.form", String.format("%sJTableForm.form", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("RowEditorForm.java", String.format("%sRowEditorForm.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("RowEditorForm.form", String.format("%sRowEditorForm.form", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("cp_file_2_dest.bat", String.format("cp_file_2_dest.bat", pojoAttr.className), pojoAttr.getProperty("dosScriptCharset"));
        createPojoSourceCreatorUnit("ModifyCompare.bat", String.format("ModifyCompare.bat", pojoAttr.className), pojoAttr.getProperty("dosScriptCharset"));
    }

    private void createPojoSourceCreatorUnit(String creatorId, String fileName, String charset) {
        PojoSourceCreatorUnit srcUnit = new PojoSourceCreatorUnit(creatorId);
        srcUnit.setFileName(fileName);
        srcUnit.setCharset(charset);
        sourceUnits.put(srcUnit.getCreatorId(), srcUnit);
    }

    private void initEnv() {
        env.put("hxhkBatRem", smodel.activeFactory.pojoAttr.getProperty("hxhkBatRem"));
//        if (true) {
//            return;
//        }
//        env.put("uiSwitch_add", true);
//        env.put("uiSwitch_delete", true);
//        env.put("uiSwitch_update", true);
//        env.put("uiSwitch_exp", true);
//        env.put("uiSwitch_imp", true);

//        if (smodel.isFieldMasked("ui", "Info.jsp")) {
//            smodel.maskAllFields("criteria");
//        }
        checkUiMasks_var("detailForm", "uiSwitch_detailForm");
//        checkUiMasks_var("Info-add.jsp", "uiSwitch_add");
//        checkUiMasks_var("delete", "uiSwitch_delete");
//        checkUiMasks_var("Info-update.jsp", "uiSwitch_update");
//        checkUiMasks_var("Info-view.jsp", "uiSwitch_view");
//        checkUiMasks_var("exp", "uiSwitch_exp");
//        checkUiMasks_var("imp", "uiSwitch_imp");
    }

    @Override
    public Ret createAllPojoSource() {
//    public Ret createAllPojoSource(PojoSourceCreatorUnit pojoSourceUnit, PojoSourceCreatorUnit mapSourceUnit) {
//        public Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource) {
        if (sourceUnits == null) {
            return Ret.getFailureRet("sourceUnits is null");
        }
        ProgressbarWindow p = new ProgressbarWindow("代码生成中....");
        p.setValue(0);
        p.setVisible(true);
        Ret r0 = checkAllEtlCodeTemplatesExist();
        p.setValue(10);
        if (r0.isRetSuccess()) {
            initEnv();
            // 
            r0 = makeSrc(p);
            // 
            // checkUiMasks("Info.jsp");
            // 
//            checkUiMasks_file("Info-listdev.jsp");
//            checkUiMasks_file("detailForm");
//            checkUiMasks_file("Info-update.jsp");
//            checkUiMasks_file("Info-view.jsp");
            if (smodel.isFieldMasked("ui", "detailForm")) {
                sourceUnits.remove("RowEditorForm.java");
                sourceUnits.remove("RowEditorForm.form");
            }
        }
        // 
        p.close();
        return r0;
    }

    private void checkUiMasks_var(String uiModuleName, String tvarName) {
        env.put(tvarName, smodel.isFieldMasked("ui", uiModuleName) ? false : true);
    }

    private void checkUiMasks_file(String uiModuleName) {
        if (smodel.isFieldMasked("ui", uiModuleName)) {
            sourceUnits.remove(uiModuleName);
        }
    }

    private Ret checkAllEtlCodeTemplatesExist() {
        sourceTemplatePool = CodeTemplatePool.getPool("DSS");   // 这样，重装的时候才会生效。

        int absentContents = 0;
        errInfo.setLength(0);

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_FRAME, "JTableForm.form", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_FRAME, "JTableForm.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_FRAME, "JTableModel", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_FRAME, "RowEditorForm.form", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_FRAME, "RowEditorForm.java", true, errInfo) ? 0 : 1;

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_UTIL, "DeclareField", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_UTIL, "fieldGetSetUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_UTIL, "jspListTitleUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_UTIL, "jspListDataUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.jt_UTIL, "pkFillBySeq", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.jt_FRAME, getClass(), "hxhk");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.jt_UTIL, getClass(), "hxhk");

        // if (absentContents == 0) {  
        if (true) {
            return Ret.getSuccessRet();
        }
        return Ret.getFailureRet("缺少模板，无法生成SQL代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }
    // 
    String firstColName = "";

    private void putEnvAtPojoField(PojoField f) {
        if (f.varName == null || f.varName.length() == 0) {
            return;
        }
        if (firstColName.isEmpty()) {
            firstColName = f.col.getSqlName();
        }
        String s = f.varType;
        // 
        env.put("isFieldPK", f.col.isPk());
        if (f.col.isPk()) {
            env.put("pkField_vartype", f.varType);
        }
        // 首亨：所有数字，必须是double类型
//            if (isVarNumeberType(f.varType)) {
//                f.varType = "double";
//                env.put("annotateAux", ", colType=SHColumnType.DOUBLE");
//            } else {
//                env.put("annotateAux", "");
//            }
        //     ${scope}    ${vartype}  ${varname};		// field ${colname} : ${colDataType}
        env.put("varName", f.varName);
        env.put("vartype", f.varType);
        String vartypeCap = Stringtool.changeLeadingCharCase(f.varType, TargetCase.UpperCase, 1);
        env.put("vartypeCap", vartypeCap);
        if (f.varType.equals("int")) {
            env.put("vartypeWrapped", "Integer");
        } else if (f.varType.contains("[]") || f.varType.contains(".")) {
            env.put("vartypeWrapped", f.varType);
        } else {
            env.put("vartypeWrapped", vartypeCap);
        }
        env.put("scope", f.scope);
        env.put("fieldName", f.col.getSqlName());
        env.put("fieldName_lowerCase", f.col.getSqlName().toLowerCase());
        env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
        env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
        env.put("length", f.col.getSize());
        env.put("jdbcType", f.col.getSqlTypeName());
        env.put("isVarNumberType", Numtool.isVarNumberType(f.varType));
        String comment = f.col.getColComment() == null ? "" : f.col.getColComment();
        env.put("fieldLabel", comment);   // 字段备注。暂用字段名称。

        // &#x51fa;
        String unicodeLabel = Stringtool.listByteArray_unicode(comment, 'l');
        env.put("fieldLabel_formXml", getFormXml(unicodeLabel));   // 字段备注。暂用字段名称。
        //        env.put("fieldLabel_formXml", comment);
    }

    private String getFormXml(String unicodeLabel) {
        StringBuilder sb = new StringBuilder();
        int cntr = 0;
        for (char c : unicodeLabel.toCharArray()) {
            if (cntr == 0) {
                sb.append("&#x");
            }
            sb.append(c);
            cntr++;
            if (cntr == 4) {
                sb.append(";");
                cntr = 0;
            }
        }
        return sb.toString();
    }

    // ActionFrame
    private Ret makeSrc(ProgressbarWindow p) {
        I_StudioConfig cfg = app.getStudioConfig();
        if (cfg.getExternalCompareTool() == null || cfg.getExternalCompareTool().length() == 0) {
            boolean b = Wftool.confirmDialog("比较工具未定义", "没有定义比较工具。现在选择吗?");
            if (b) {
                boolean b01 = app.chooseCompareTool();
            }
        }

//                createPojoSourceCreatorUnit("JTableModel", String.format("%sJTableModel.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
//        createPojoSourceCreatorUnit("JTableForm.java", String.format("%sJTableForm.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
//        createPojoSourceCreatorUnit("JTableForm.form", String.format("%sJTableForm.form", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
//        createPojoSourceCreatorUnit("RowEditorForm.java", String.format("%sRowEditorForm.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
//        createPojoSourceCreatorUnit("RowEditorForm.form", String.format("%sRowEditorForm.form", pojoAttr.className), pojoAttr.getProperty("codeCharset"));

//        PojoSourceCreatorUnit pojoSrcUnit = sourceUnits.get("pojo");

        PojoSourceCreatorUnit tableModel_SrcUnit = sourceUnits.get("JTableModel");
        PojoSourceCreatorUnit tableFormj_SrcUnit = sourceUnits.get("JTableForm.java");
        PojoSourceCreatorUnit tableFormx_SrcUnit = sourceUnits.get("JTableForm.form");
        PojoSourceCreatorUnit reFormj_srcUnit = sourceUnits.get("RowEditorForm.java");
        PojoSourceCreatorUnit reFormx_SrcUnit = sourceUnits.get("RowEditorForm.form");
        PojoSourceCreatorUnit modifyCompare_srcUnit = sourceUnits.get("ModifyCompare.bat");
        PojoSourceCreatorUnit cp_file_2_dest_srcUnit = sourceUnits.get("cp_file_2_dest.bat");

//        PojoSourceCreatorUnit exportUtilSrcUnit = sourceUnits.get("ExportUtil");
//        PojoSourceCreatorUnit jsSrcUnit = sourceUnits.get("Info.js");
//
////        PojoSourceCreatorUnit jobSrcUnit = sourceUnits.get("job");
//        PojoSourceCreatorUnit sqlmapSrcUnit = sourceUnits.get("sqlmap");
//        PojoSourceCreatorUnit strutsSrcUnit = sourceUnits.get("struts");
//        PojoSourceCreatorUnit noteSrcUnit = sourceUnits.get("note");
//        PojoSourceCreatorUnit batSrcUnit = sourceUnits.get("bat");
//        PojoSourceCreatorUnit ModifyCompareSrcUnit = sourceUnits.get("ModifyCompare");
//        PojoSourceCreatorUnit sqlplusSrcUnit = sourceUnits.get("reg_auth");
//        PojoSourceCreatorUnit reg_auth_moduleSrcUnit = sourceUnits.get("reg_auth_module");
//
//
//        PojoSourceCreatorUnit jspInfoSrcUnit = sourceUnits.get("Info.jsp");
//        PojoSourceCreatorUnit jspAddSrcUnit = sourceUnits.get("Info-add.jsp");
//        PojoSourceCreatorUnit jspListDivSrcUnit = sourceUnits.get("Info-listdiv.jsp");
//        PojoSourceCreatorUnit jspresultSrcUnit = sourceUnits.get("Info-result.jsp");
//        PojoSourceCreatorUnit jspupdateSrcUnit = sourceUnits.get("Info-update.jsp");
//        PojoSourceCreatorUnit jspviewSrcUnit = sourceUnits.get("Info-view.jsp");
//
//        assert pojoSrcUnit != null;
        assert tableModel_SrcUnit != null;
        assert tableFormj_SrcUnit != null;
        assert tableFormx_SrcUnit != null;
        assert reFormj_srcUnit != null;
        assert reFormx_SrcUnit != null;
//        assert exportUtilSrcUnit != null;
////        assert jobSrcUnit != null;
//        assert sqlmapSrcUnit != null;
//        assert strutsSrcUnit != null;
//        assert noteSrcUnit != null;
//        assert batSrcUnit != null;
//        assert ModifyCompareSrcUnit != null;
//        assert sqlplusSrcUnit != null;
//
//        assert jspInfoSrcUnit != null;
//        assert jspAddSrcUnit != null;
//        assert jspListDivSrcUnit != null;
//        assert jspresultSrcUnit != null;
//        assert jspupdateSrcUnit != null;
//        assert jspviewSrcUnit != null;

        // 临时的变量指定。 实际上是需要界面选择的。。。
        env.put("order_AscDesc", "DESC");
//        
//        env.put("actionHome", "hxhk.phoneTest");
//        
        env.put("jspPath", "/app/hxhk/tssnt");
        // endof 临时的变量指定。 实际上是需要界面选择的。。。

        StringBuilder tableColumnAliasUtil_Text = new StringBuilder();
        StringBuilder rowDataByFields_Text = new StringBuilder();
        StringBuilder createDefaultBean_args_Text = new StringBuilder();

        StringBuilder re_compCreate_Text = new StringBuilder();
        StringBuilder re_compDeclare_Text = new StringBuilder();
        StringBuilder re_rebind_fields_Text = new StringBuilder();
        StringBuilder re_update_fields_Text = new StringBuilder();
        StringBuilder rex_components_Text = new StringBuilder();
        StringBuilder setPkFieldsEditable_Text = new StringBuilder();

        StringBuilder updateCellToBean_case_Text = new StringBuilder();


        // 
        //        com.bca.templ.po.TlTemplateTextReg@1c047f0[templDomainId=1024,templId=1234,contentId=9,syntaxTypeId=0,contentName=namedQueryDeclareField,contentTxtName=namedQueryDeclareField,syntaxTagEnable=false,helpInfo=]
        // 
        StringBuilder namedQueryDeclareByAllSource = new StringBuilder();
        StringBuffer namedQueryDeclareSource = new StringBuffer();
        StringBuilder varsOperatorSource = new StringBuilder();

        StringBuilder sqlQryFieldSrc = new StringBuilder();
        StringBuilder sqlQry_DynCriteriaFieldSrc = new StringBuilder();
        StringBuilder sqlInsertFieldSrc = new StringBuilder();
        StringBuilder sqlInsertValueSrc = new StringBuilder();
        StringBuilder sqlUpdateExprSrc = new StringBuilder();
        // 
        StringBuffer pkColNameList_Source = new StringBuffer();
        StringBuilder pojoGetSetText_Source = new StringBuilder();
        StringBuilder boGetSetText_Source = new StringBuilder();

        StringBuilder expSetHeadSrc = new StringBuilder();
        StringBuilder expSetBodySrc = new StringBuilder();


        StringBuilder jspQueryTitle_Source = new StringBuilder();        // jspQueryItem
        StringBuilder jspListTitle_Source = new StringBuilder();        // jspListTitleUnit
        StringBuilder jspListData_Source = new StringBuilder();         // jspListDataUnit        
        StringBuilder jspAdd_BodySource = new StringBuilder();    // jspInputBodyText
        StringBuilder jspUpdate_BodySource = new StringBuilder();    // jspInputBodyText
        StringBuilder jspView_BodySource = new StringBuilder();    // jspInputBodyText

        // 
        StringBuffer pkConstructor_para_Source = new StringBuffer();
        StringBuffer pkConstructor_body_Source = new StringBuffer();
        StringBuffer wholeConstructor_para_Source = new StringBuffer();
        StringBuffer wholeConstructor_body_Source = new StringBuffer();
        // 
//        StringBuilder toStringAppendSource = new StringBuilder();
//        StringBuilder equalsAppendSource = new StringBuilder();
//        StringBuilder hashAppendSource = new StringBuilder();
        // 
        StringBuilder pkHbmXml = new StringBuilder();
        StringBuilder fieldsHbmXml = new StringBuilder();

        // irapid...
        StringBuffer insert_FieldList = new StringBuffer();
        StringBuffer insert_ValuesList = new StringBuffer();

        StringBuffer update_Body = new StringBuffer();
        StringBuffer select_FieldList = new StringBuffer();

        StringBuffer pkCriteria = new StringBuffer();
        StringBuffer pkVarsList = new StringBuffer();

        String s = "";

        env.put("projName", pojoAttr.getProperty("projName"));
        env.put("projHomeDir", pojoAttr.getProperty("projHomeDir"));
//        env.put("pkgHome", pojoAttr.getProperty("pkgHome"));
//        env.put("pkgTail", pojoAttr.getProperty("pkgTail"));
//        env.put("pkgHome_dir", pojoAttr.getProperty("pkgHome").replace('.', '\\'));
//        env.put("pkgTail_dir", pojoAttr.getProperty("pkgTail").replace('.', '\\'));
//        // 
//        env.put("pkgTail_dir_unix", pojoAttr.getProperty("pkgTail").replace('.', '/'));
//        env.put("pkgTail_dir_unix", pojoAttr.getProperty("pkgTail").replace('.', '/'));
        // 如下3个参数,用于sqlplus自动注册模块及授权.
        env.put("dbups", pojoAttr.getProperty("dbups"));
        env.put("parenetMenuName", pojoAttr.getProperty("parenetMenuName"));
        env.put("authRole", pojoAttr.getProperty("authRole"));

        env.put("pkgName", pojoAttr.packageName);
        env.put("pkgName_dir", pojoAttr.packageName.replace('.', '\\'));
        env.put("mainName", pojoAttr.className);
        env.put("objVarName", Stringtool.changeLeadingCharCase(pojoAttr.className, TargetCase.LowerCase, 1));
        int i = pojoAttr.packageName.indexOf(".");
        String actionHome = i >= 0 ? pojoAttr.packageName.substring(i + 1) : pojoAttr.packageName;
        actionHome += "." + Stringtool.changeLeadingCharCase(pojoAttr.className, TargetCase.LowerCase, 1);
        env.put("actionHome", actionHome);
        env.put("jspPath", smodel.getJspPath());    // 
        // 
        env.put("author", BStudioConfigBean.getInst().getCodeFactory_author());
        env.put("orgnization", BStudioConfigBean.getInst().getCodeFactory_orgnization());
        env.put("encoding", BStudioConfigBean.getInst().getCodeFactory_encoding());
        env.put("entityLabel", Stringtool.isStringEmpty(pojoAttr.tableComment) ? pojoAttr.tableName : pojoAttr.tableComment);

        p.incValue(5);


        for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
        p.incValue(10);
        Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("pkFieldName", pkColNameList_Source.toString());
        env.put("pkFieldName_lowerCase", pkColNameList_Source.toString().toLowerCase());
        env.put("pkField_vartype", "String");
        // env.put("pkColName", pkColNameList_Source.toString());
        //
//        s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "namedQueryByAlllDeclare", env, true, errInfo);
//        namedQueryDeclareByAllSource.append(s);
//        env.put("namedQueryDeclareByAllSource", namedQueryDeclareByAllSource.toString());
        firstColName = "";
        for (PojoField f : pojoFields.values()) {
            putEnvAtPojoField(f);

            s = f.getDefaultCreateArgText();
            createDefaultBean_args_Text.append(s).append(", ");
        }

        // 按照约定的列顺序，输出字段到UI
//        int field_idx_view = 0;
//        int field_idx_add = 0;
//        int field_idx_update = 0;
//        boolean tr_LineFeed = false;   // 表示应该加换行标记 </tr><tr>
        int tcolIdx = 0;
        for (Map<String, String> name : uiColSort) {
            for(Map.Entry<String, String> colName : name.entrySet()){
            PojoField f = pojoFields.get(colName.getKey());
            if (f == null) {
                log.error("PojoField 未找到：%s, 跳过该列!", colName.getKey());
                continue;
            }
            putEnvAtPojoField(f);
            env.put("fieldLabel", colName.getValue() == null ? "" : colName.getValue()); 
            

            if (!smodel.isFieldMasked("colSwitch_list", f.col.getSqlName())) { // colSwitch_list  没有屏蔽，则输出
                tcolIdx++;   // 第0列是对象，不可设置值。
                env.put("tcolIdx", tcolIdx);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "TableColumnAliasUtil", env, true, errInfo);
                tableColumnAliasUtil_Text.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "RowData_byField", env, true, errInfo);
                s = Stringtool.removeTrailingChars(s, '\r', '\n');
                rowDataByFields_Text.append(s);
                // 
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "updateCellToBean_case", env, true, errInfo);
                updateCellToBean_case_Text.append(s);
            }
            if (!smodel.isFieldMasked("colSwitch_detail", f.col.getSqlName())) { // colSwitch_detail  没有屏蔽，则输出
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "re_compCreate", env, true, errInfo);
                re_compCreate_Text.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "re_compDeclare", env, true, errInfo);
                re_compDeclare_Text.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "re_rebind_field", env, true, errInfo);
                re_rebind_fields_Text.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "re_update_field", env, true, errInfo);
                re_update_fields_Text.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "setPkFieldsEditable", env, true, errInfo);
                setPkFieldsEditable_Text.append(s);

                s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "rex_comp", env, true, errInfo);
                rex_components_Text.append(s);
            }
            

            wholeConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
            wholeConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));
            }
            p.incValue(1);
        }

        Stringtool.reduceLength(pkConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(pkConstructor_body_Source, "\r\n".length());
        Stringtool.reduceLength(wholeConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(wholeConstructor_body_Source, "\r\n".length());

//        Stringtool.reduceLength(toStringAppendSource, 6);

        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        env.put("tableName_lowerCase", this.pojoAttr.tableName.toLowerCase());

        env.put("tableRemark", this.pojoAttr.tableComment == null ? "" : pojoAttr.tableComment);
        env.put("createTime", Timetool.getSysTimestamp().toString());
        env.put("projHomeDir", pojoAttr.getProperty("projHomeDir"));

        env.put("fieldName_4_criteria", firstColName);
        env.put("tableColumnAliasUtil", tableColumnAliasUtil_Text.toString());
        env.put("RowData_byField", rowDataByFields_Text.toString());
        Stringtool.reduceLength(createDefaultBean_args_Text, 2);
        env.put("createDefaultBean_args", createDefaultBean_args_Text.toString());

        env.put("re_compCreate", re_compCreate_Text.toString());
        env.put("re_compDeclare", re_compDeclare_Text.toString());
        env.put("re_rebind_fields", re_rebind_fields_Text.toString());
        env.put("re_update_fields", re_update_fields_Text.toString());
        env.put("setPkFieldsEditable_body", setPkFieldsEditable_Text.toString());
        env.put("rex_components", rex_components_Text.toString());
        env.put("updateCellToBean_case", updateCellToBean_case_Text.toString());

        env.put("pojoGetSetText", pojoGetSetText_Source.toString());
        env.put("boGetSetText", boGetSetText_Source.toString());
        // 
        env.put("expSetHeadSrc", expSetHeadSrc.toString());
        env.put("expSetBodySrc", expSetBodySrc.toString());
        //
        Stringtool.trimStringBufferA(namedQueryDeclareSource, (char) 0x0a);
        Stringtool.trimStringBufferA(namedQueryDeclareSource, (char) 0x0d);
        Stringtool.trimStringBufferA(namedQueryDeclareSource, ',');
        env.put("namedQueryDeclareSource", namedQueryDeclareSource.toString());
        //
        env.put("varOperatorsSource", varsOperatorSource.toString());

        env.put("pkConstructor_parameters", pkConstructor_para_Source.toString());
        env.put("pkConstructor_body", pkConstructor_body_Source.toString());
        env.put("pojoFullConstructorArgs", wholeConstructor_para_Source.toString());
        env.put("pojoFullConstructorText", wholeConstructor_body_Source.toString());

//        env.put("toStringAppendString", toStringAppendSource.toString());
//        env.put("pojoToString", toStringAppendSource.toString());
//        env.put("hashAppendString", hashAppendSource.toString());

//        s = sourceTemplatePool.makeSource(CodeTempIds.jt_UTIL, "pkFillBySeq", env, true, errInfo);
        p.incValue(10);
        s = s == null ? "" : Stringtool.removeTrailingChars(s, '\n', '\r');

        env.put("pkFillBySeqText", s);
        Stringtool.removeLastChars(sqlQryFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlQryField", sqlQryFieldSrc.toString());
        Stringtool.removeLastChars(sqlQry_DynCriteriaFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlQry_DynCriteriaField", sqlQry_DynCriteriaFieldSrc.toString());
        Stringtool.removeLastChars(sqlInsertFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlInsertField", sqlInsertFieldSrc.toString());
        Stringtool.removeLastChars(sqlInsertValueSrc, '\r', '\n', ',', ' ');
        env.put("sqlInsertValue", sqlInsertValueSrc.toString());
        Stringtool.removeLastChars(sqlUpdateExprSrc, '\r', '\n', ',', ' ');
        env.put("sqlUpdateExpr", sqlUpdateExprSrc.toString());

        env.put("compareTool", cfg.getExternalCompareTool());   // 比较工具

//        pojoSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "PojoFrame", env, true, errInfo));
//        p.incValue(10);
        tableModel_SrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "JTableModel", env, true, errInfo));
        p.incValue(10);
        tableFormj_SrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "JTableForm.java", env, true, errInfo));
        p.incValue(10);
        tableFormx_SrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "JTableForm.form", env, true, errInfo));

        reFormj_srcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "RowEditorForm.java", env, true, errInfo));
        p.incValue(10);
        reFormx_SrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "RowEditorForm.form", env, true, errInfo));
        p.incValue(10);

        modifyCompare_srcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "ModifyCompare.bat", env, true, errInfo));
        p.incValue(10);
        cp_file_2_dest_srcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "cp_file_2_dest.bat", env, true, errInfo));
        p.incValue(10);

//        exportUtilSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "ExportUtil", env, true, errInfo));
//        p.incValue(10);
////        jobSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.sruan_FRAME, "JobFrame", env, true, errInfo));
////        p.incValue(10);
//        sqlmapSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "sqlmap", env, true, errInfo));
//        p.incValue(10);
//
//        noteSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "note", env, true, errInfo));
//        p.incValue(10);
//        batSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "createProj.bat", env, true, errInfo));
//        p.incValue(10);
//        ModifyCompareSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "ModifyCompare.bat", env, true, errInfo));
//        p.incValue(10);
//        sqlplusSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "reg_auth.bat", env, true, errInfo));
//        p.incValue(10);
//        reg_auth_moduleSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "reg_auth_module.sql", env, true, errInfo));
//        p.incValue(10);
//        strutsSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "struts-xframe", env, true, errInfo));
//        p.incValue(10);
//
//        jsSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info.js", env, true, errInfo));
//        p.incValue(10);

//        PojoSourceCreatorUnit jspInfoSrcUnit = sourceUnits.get("Info.jsp");
//        PojoSourceCreatorUnit jspAddSrcUnit = sourceUnits.get("Info-add.jsp");
//        PojoSourceCreatorUnit jspListDivSrcUnit = sourceUnits.get("Info-listdiv.jsp");
//        PojoSourceCreatorUnit jspresultSrcUnit = sourceUnits.get("Info-result.jsp");
//        PojoSourceCreatorUnit jspupdateSrcUnit = sourceUnits.get("Info-update.jsp");
//        PojoSourceCreatorUnit jspviewSrcUnit = sourceUnits.get("Info-view.jsp");



        env.put("jspQueryTitle", jspQueryTitle_Source.toString());
        env.put("jspListTitle", jspListTitle_Source.toString());
        env.put("jspListData", jspListData_Source.toString());
        env.put("jspAddDataUnit", jspAdd_BodySource.toString());
        env.put("jspUpdateDataUnit", jspUpdate_BodySource.toString());
        env.put("jspViewDataUnit", jspView_BodySource.toString());


//        jspInfoSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info.jsp", env, true, errInfo));
//        p.incValue(10);
//        jspAddSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info-add.jsp", env, true, errInfo));
//        p.incValue(10);
//        jspListDivSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info-listdiv.jsp", env, true, errInfo));
//        p.incValue(10);
//        jspresultSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info-result.jsp", env, true, errInfo));
//        p.incValue(10);
//        jspupdateSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info-update.jsp", env, true, errInfo));
//        p.incValue(10);
//        jspviewSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "Info-view.jsp", env, true, errInfo));
//        p.incValue(10);

        if (true) {
            return Ret.getSuccessRet();
        }

//        // jsp列表文件
//        String src = sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "ListFrame", env, true, errInfo);
//        src = src == null ? "" : src.replace("_SH_DOLLAR_", "$");
//        jspListDivSrcUnit.setCode(src);
//        p.incValue(10);
//        // jsp输入文件 
//        src = sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "InputFrame", env, true, errInfo);
//        src = src == null ? "" : src.replace("_SH_DOLLAR_", "$");
//        jspAddSrcUnit.setCode(src);
//        p.incValue(10);

        env.put("pkMapString", pkHbmXml.toString());
        env.put("fieldsMapString", fieldsHbmXml.toString());

        // irapid
        trimCommaAndCR(insert_FieldList);
        env.put("insert_FieldList", insert_FieldList.toString());
        //
        trimCommaAndCR(insert_ValuesList);
        env.put("insert_ValuesList", insert_ValuesList.toString());
        trimCommaAndCR(update_Body);
        env.put("update_Body", update_Body.toString());

        trimCommaAndCR(select_FieldList);
        env.put("select_FieldList", select_FieldList.toString());

        trimCommaAndCR(pkCriteria);
        env.put("pkCriteria", pkCriteria.toString());

        trimCommaAndCR(pkVarsList);
        env.put("pkVarsList", pkVarsList.toString());

//        jspresultSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.jt_FRAME, "StrutsXml", env, true, errInfo));
//        p.incValue(10);

        p.setValue(100);

        return Ret.getSuccessRet();
    }

    private void trimCommaAndCR(StringBuffer sb) {
        // Stringtool.trimLeadingStringBuffer(sb, ' ');
        Stringtool.trimStringBufferA(sb, '\n');
        Stringtool.trimStringBufferA(sb, '\r');
        Stringtool.trimStringBufferA(sb, ',');

        removeLastCommaBeforeComment(sb);
    }

    private void removeLastCommaBeforeComment(StringBuffer sb) {
        boolean mimus2_ready = false;
        boolean mimus12_ready = false;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            switch (c) {
                case '-':
                    if (mimus2_ready) {
                        mimus12_ready = true;
                    } else {
                        mimus2_ready = true;
                    }
                    break;
                case ' ':
                case (char) 0x09:
                    break;
                case ',':
                    if (mimus12_ready) {
                        sb.deleteCharAt(i);
                    }
                    break;
                default:
                    if (mimus12_ready) {
                        return;
                    }
                    break;
            }
        }

    }

    @Override
    public String getSaveDir(String homeDir) {
        return homeDir + File.separator + "src";
    }

    @Override
    public String createSelectSQL(TreeMap<Integer, QbOutputFieldUnit> outputFields) {
        Ret r0 = checkAllEtlCodeTemplatesExist();
        Set<String> dirtyTables = new TreeSet<String>();

        StringBuffer fields = new StringBuffer();
        StringBuffer tables = new StringBuffer();
        String s;

        int fieldsCount = 0;
        for (QbOutputFieldUnit u : outputFields.values()) {
            fieldsCount++;
            env.put("expr", u.getExpr_x());
            env.put("outFieldName", u.getOutFieldName());
            env.put("fieldComment", u.getColComment().replace('r', ' ').replace('\n', ' '));
            env.put("isLastField", fieldsCount == outputFields.size());
            s = sourceTemplatePool.makeSourceA(CodeTempIds.jt_UTIL, "q_field", env, true, errInfo);
            fields.append(s);
            //
            Meta_Table mt = u.getJtableModel().getTbean().getTableDetail();
            String tableName = mt.getSqlName();
            if (!dirtyTables.contains(tableName)) {
                env.put("tableName", tableName);
                env.put("tableAlias", u.getJtableModel().getTbean().getTableAlias());
                env.put("tableComment", mt.getTableComment().replace('r', ' ').replace('\n', ' '));
                // 
                s = sourceTemplatePool.makeSourceA(CodeTempIds.jt_UTIL, "q_tables", env, true, errInfo);
                tables.append(s);
                dirtyTables.add(tableName);
            }
        }

        env.put("queryFieldsBody", fields.toString());
        env.put("tablesBody", tables.toString());
        s = sourceTemplatePool.makeSourceA(CodeTempIds.jt_FRAME, "select", env, true, errInfo);

        env.clear();
        return s;
    }
//    final Set<String> NUMBER_TYPES = new TreeSet<String>();
//    private boolean isVarNumeberType(String varType) {
//        if (NUMBER_TYPES.isEmpty()) {
//            NUMBER_TYPES.add("int");
//            NUMBER_TYPES.add("Integer");
//            NUMBER_TYPES.add("long");
//            NUMBER_TYPES.add("Long");
//            NUMBER_TYPES.add("short");
//            NUMBER_TYPES.add("Short");
//            NUMBER_TYPES.add("float");
//            NUMBER_TYPES.add("Float");
//        }
//        return NUMBER_TYPES.contains(varType);
//    }

    @Override
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
        boolean criteria = !smodel.isFieldMasked("colSwitch_criteria", col.getSqlName());
        boolean list = !smodel.isFieldMasked("colSwitch_list", col.getSqlName());
        boolean detail = !smodel.isFieldMasked("colSwitch_detail", col.getSqlName());
        // 
        final StringBuilder sb = new StringBuilder();
        sb.append(criteria ? 'c' : ' ');
        sb.append(list ? 'L' : ' ');
        sb.append(detail ? 'T' : ' ');
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JLabel(sb.toString());
            }
        };
    }
}
