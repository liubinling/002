/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.sh;

import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Stringtool.TargetCase;
import com.bca.pub.tools.Timetool;
import com.bca.studio.BStudioConfigBean;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.sql.I_SqlCreator;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import java.awt.Component;
import java.io.File;
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
public class Creator_4_ShouHenFrame implements I_SqlCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
//    String alias;
    SqlCreateModel smodel;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
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
    public void fixCompareScript(String srcOutDir) {

    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String sh_FRAME = "sh.ccFrame";
        public final static String sh_UTIL = "sh.util";
//        public final static String HBM_FRAME = "ETL.FRAME";
//        public final static String ETL_ITEM = "ETL.ITEM";
//        public final static String ETL_SQL = "ETL.SQL";
//        public final static String ETL_FUNC = "ETL.FUNC";
    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public Creator_4_ShouHenFrame() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();

    }

    @Override
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> aaa, Meta_Table dbTable, List<Map<String, String>> uiColSort) {
//        this.alias = alias;
        this.smodel = smodel;
        this.pojoAttr = pojoAttr;
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

        PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
        PojoSourceCreatorUnit actionSrcUnit = new PojoSourceCreatorUnit("action");
        PojoSourceCreatorUnit jspListSrcUnit = new PojoSourceCreatorUnit("jspList");
        PojoSourceCreatorUnit jspInputSrcUnit = new PojoSourceCreatorUnit("jspInput");
        PojoSourceCreatorUnit struts2SourceUnit = new PojoSourceCreatorUnit("struts2");

        pojoSourceUnit.setFileName(String.format("%sPojo.java", pojoAttr.className));
        actionSrcUnit.setFileName(String.format("%sAction.java", pojoAttr.className));
        jspListSrcUnit.setFileName(String.format("%sList.jsp", pojoAttr.className));
        jspInputSrcUnit.setFileName(String.format("%sInput.jsp", pojoAttr.className));
        struts2SourceUnit.setFileName(String.format("struts_%s.xml", pojoAttr.className));

        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
        sourceUnits.put(actionSrcUnit.getCreatorId(), actionSrcUnit);
        sourceUnits.put(jspListSrcUnit.getCreatorId(), jspListSrcUnit);
        sourceUnits.put(jspInputSrcUnit.getCreatorId(), jspInputSrcUnit);
        sourceUnits.put(struts2SourceUnit.getCreatorId(), struts2SourceUnit);
    }

    private void checkUiMasks_var(String uiModuleName, String tvarName) {
        env.put(tvarName, smodel.isFieldMasked("ui", uiModuleName) ? "false" : "true");
    }

    private void checkUiMasks_file(String uiModuleName) {
        if (smodel.isFieldMasked("ui", uiModuleName)) {
            sourceUnits.remove(uiModuleName);
        }
    }

    private void initEnv() {
        env.put("uiSwitch_list", "true");
        env.put("uiSwitch_input", "true");
        env.put("uiSwitch_exp", "true");
        env.put("uiSwitch_imp", "true");
        // 
        if (smodel.isFieldMasked("ui", "Info.jsp")) {
            smodel.maskAllFields("criteria");
        }
        checkUiMasks_var("jspList", "uiSwitch_list");
        checkUiMasks_var("jspInput", "uiSwitch_input");
        checkUiMasks_var("exp", "uiSwitch_exp");
        checkUiMasks_var("imp", "uiSwitch_imp");
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
            r0 = makeSrc(p);

            checkUiMasks_file("jspList");
            checkUiMasks_file("jspInput");
        }
        // 
        p.close();
        return r0;
    }

    private Ret checkAllEtlCodeTemplatesExist() {
        sourceTemplatePool = CodeTemplatePool.getPool("DSS");   // 这样，重装的时候才会生效。
        int absentContents = 0;
        errInfo.setLength(0);
        // 
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_FRAME, "sh.ActionFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_FRAME, "sh.InputFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_FRAME, "sh.ListFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_FRAME, "sh.PojoFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_FRAME, "sh.StrutsXml", true, errInfo) ? 0 : 1;

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_UTIL, "DeclareField", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_UTIL, "fieldGetSetUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_UTIL, "jspListTitleUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_UTIL, "jspListDataUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.sh_UTIL, "pkFillBySeq", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.sh_FRAME, getClass(), "hxhk");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.sh_UTIL, getClass(), "hxhk");

        if (absentContents == 0) {
            return Ret.getSuccessRet();
        }
//        String s = String.format("缺少模板，无法生成ETL模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
//        JOptionPane.showMessageDialog(dwws.app.getMainFrame(), s, "错误信息", JOptionPane.ERROR_MESSAGE);
        return Ret.getFailureRet("缺少模板，无法生成SQL代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }
    // 
    StringBuilder pojoFieldsText = new StringBuilder();
    // 
    //        com.bca.templ.po.TlTemplateTextReg@1c047f0[templDomainId=1024,templId=1234,contentId=9,syntaxTypeId=0,contentName=namedQueryDeclareField,contentTxtName=namedQueryDeclareField,syntaxTagEnable=false,helpInfo=]
    // 
    StringBuilder namedQueryDeclareByAllSource = new StringBuilder();
    StringBuffer namedQueryDeclareSource = new StringBuffer();
    StringBuilder varsOperatorSource = new StringBuilder();
    // 
    StringBuffer pkColNameList_Source = new StringBuffer();
    StringBuilder pojoGetSetText_Source = new StringBuilder();
    StringBuilder jspListTitle_Source = new StringBuilder();        // jspListTitleUnit
    StringBuilder jspListData_Source = new StringBuilder();         // jspListDataUnit        
    StringBuilder jspInputBodyText_Source = new StringBuilder();    // jspInputBodyText
    // 
    StringBuffer pkConstructor_para_Source = new StringBuffer();
    StringBuffer pkConstructor_body_Source = new StringBuffer();
    StringBuffer wholeConstructor_para_Source = new StringBuffer();
    StringBuffer wholeConstructor_body_Source = new StringBuffer();
    // 
    StringBuilder toStringAppendSource = new StringBuilder();
    StringBuilder equalsAppendSource = new StringBuilder();
    StringBuilder hashAppendSource = new StringBuilder();
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
    String firstColName = "";
//    int field_idx = 0;

    private void clearBuf() {
        pojoFieldsText.setLength(0);
        namedQueryDeclareByAllSource.setLength(0);
        namedQueryDeclareSource.setLength(0);
        varsOperatorSource.setLength(0);
        pkColNameList_Source.setLength(0);
        pojoGetSetText_Source.setLength(0);
        jspListTitle_Source.setLength(0);        // jspListTitleUnit
        jspListData_Source.setLength(0);         // jspListDataUnit        
        jspInputBodyText_Source.setLength(0);    // jspInputBodyText
        pkConstructor_para_Source.setLength(0);
        pkConstructor_body_Source.setLength(0);
        wholeConstructor_para_Source.setLength(0);
        wholeConstructor_body_Source.setLength(0);
        toStringAppendSource.setLength(0);
        equalsAppendSource.setLength(0);
        hashAppendSource.setLength(0);
        pkHbmXml.setLength(0);
        fieldsHbmXml.setLength(0);

        insert_FieldList.setLength(0);
        insert_ValuesList.setLength(0);
        update_Body.setLength(0);
        select_FieldList.setLength(0);
        pkCriteria.setLength(0);
        pkVarsList.setLength(0);
        firstColName = "";
//        field_idx = 0;
    }
    // ActionFrame

    private Ret makeSrc(ProgressbarWindow p) {
        PojoSourceCreatorUnit pojoSrcUnit = sourceUnits.get("pojo");
        PojoSourceCreatorUnit actionSrcUnit = sourceUnits.get("action");
        PojoSourceCreatorUnit jspListSrcUnit = sourceUnits.get("jspList");
        PojoSourceCreatorUnit jspInputSrcUnit = sourceUnits.get("jspInput");
        PojoSourceCreatorUnit struts2SrcUnit = sourceUnits.get("struts2");

        assert pojoSrcUnit != null;
        assert actionSrcUnit != null;
        assert jspListSrcUnit != null;
        assert jspInputSrcUnit != null;
        assert struts2SrcUnit != null;

        // 临时的变量指定。 实际上是需要界面选择的。。。
        env.put("order_AscDesc", "DESC");
//        
//        env.put("actionHome", "hxhk.phoneTest");
//        
        env.put("jspPath", "/app/hxhk/tssnt");
        // endof 临时的变量指定。 实际上是需要界面选择的。。。

        clearBuf();

        String s = "";

        env.put("pkgName", pojoAttr.packageName);
        env.put("mainName", pojoAttr.className);
        env.put("objVarName", Stringtool.changeLeadingCharCase(pojoAttr.className, TargetCase.LowerCase, 1));
        int i = pojoAttr.packageName.indexOf(".");
        String actionHome = i >= 0 ? pojoAttr.packageName.substring(i + 1) : pojoAttr.packageName;
        actionHome += "." + Stringtool.changeLeadingCharCase(pojoAttr.className, TargetCase.LowerCase, 1);
        env.put("actionHome", actionHome);
        env.put("jspPath", smodel.getJspPath());

        // 
        env.put("author", BStudioConfigBean.getInst().getCodeFactory_author());
        env.put("orgnization", BStudioConfigBean.getInst().getCodeFactory_orgnization());
        env.put("encoding", BStudioConfigBean.getInst().getCodeFactory_encoding());
        env.put("entityLable", Stringtool.isStringEmpty(pojoAttr.tableComment) ? pojoAttr.tableName : pojoAttr.tableComment);

        p.incValue(5);

        for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
        p.incValue(10);
        Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("pkFieldName", pkColNameList_Source.toString());
        env.put("pkFieldName_lowerCase", pkColNameList_Source.toString().toLowerCase());
        // env.put("pkColName", pkColNameList_Source.toString());

        //
        s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "namedQueryByAlllDeclare", env, true, errInfo);
        namedQueryDeclareByAllSource.append(s);
        env.put("namedQueryDeclareByAllSource", namedQueryDeclareByAllSource.toString());
        int field_idx = 0;
        for (PojoField f : pojoFields.values()) {
            putEnvAtPojoField(f);

            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "DeclareField", env, true, errInfo);
            pojoFieldsText.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "fieldGetSetUnit", env, true, errInfo);
            pojoGetSetText_Source.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "namedQueryDeclareField", env, true, errInfo);
            namedQueryDeclareSource.append(s);
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "setOperator", env, true, errInfo));
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "getOperator", env, true, errInfo));
            // irapid...
            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "insert_FieldName", env, true, errInfo);
            insert_FieldList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "insert_ValueExpr", env, true, errInfo);
            insert_ValuesList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "update_Expr", env, true, errInfo);
            update_Body.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "select_Field", env, true, errInfo);
            select_FieldList.append(s);
            // ...

            if (f.col.isPk()) {
                pkConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
                pkConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

                // <key-property name="${varname}" type="${vartype}" column="${colname}" length="${length}"/>
                pkHbmXml.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "pkMapString", env, true, errInfo));

                //
                s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "pkCriteriaItem", env, true, errInfo);
                pkCriteria.append(s);

                s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "pkVarItem", env, true, errInfo);
                pkVarsList.append(s);

                env.put("pkVarName", f.varName);   // 
                env.put("pkVarType", f.varType);   // 
                env.put("keyVarName", f.varName);   // 
                env.put("pkVarNameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
            }
            s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "fieldMapString", env, true, errInfo);
            fieldsHbmXml.append(s);

            wholeConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
            wholeConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

            toStringAppendSource.append(f.varName).append("=\" + ").append(f.varName).append(" + \", ");
            if (f.inHashCode) {
                hashAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "hashCode", env, true, errInfo));
            }
            p.incValue(1);
        }

        for (Map<String, String> name : uiColSort) {
            for(Map.Entry<String, String> colName : name.entrySet()){
            PojoField f = pojoFields.get(colName.getKey());
            if (f == null) {
                log.error("PojoField 未找到：%s, 跳过该列!", colName.getKey());
                continue;
            }
            putEnvAtPojoField(f);
           
            }
            jspListTitle_Source.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "jspListTitleUnit", env, true, errInfo));  // jspListTitleUnit
            jspListData_Source.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "jspListDataUnit", env, true, errInfo));  // jspListDataeUnit
            field_idx++;
            env.put("jspColorOddEven", ((field_idx & 0x01) == 0) ? 2 : 1);
            jspInputBodyText_Source.append(sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "jspInputDataUnit", env, true, errInfo));  // jspListDataeUnit
        }

        Stringtool.reduceLength(pkConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(pkConstructor_body_Source, "\r\n".length());
        Stringtool.reduceLength(wholeConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(wholeConstructor_body_Source, "\r\n".length());

        Stringtool.reduceLength(toStringAppendSource, 6);

        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        env.put("tableName_lowerCase", this.pojoAttr.tableName.toLowerCase());

        env.put("tableRemark", this.pojoAttr.tableComment == null ? "" : pojoAttr.tableComment);
        env.put("createTime", Timetool.getSysTimestamp().toString());

        env.put("fieldName_4_criteria", firstColName);
        env.put("pojoFieldsText", pojoFieldsText.toString());
        env.put("pojoGetSetText", pojoGetSetText_Source.toString());
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

        env.put("toStringAppendString", toStringAppendSource.toString());
        env.put("pojoToString", toStringAppendSource.toString());
        env.put("hashAppendString", hashAppendSource.toString());

        s = sourceTemplatePool.makeSource(CodeTempIds.sh_UTIL, "pkFillBySeq", env, true, errInfo);
        p.incValue(10);
        s = s == null ? "" : Stringtool.removeTrailingChars(s, '\n', '\r');

        env.put("pkFillBySeqText", s);

        pojoSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.sh_FRAME, "sh.PojoFrame", env, true, errInfo));
        p.incValue(10);
        actionSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.sh_FRAME, "sh.ActionFrame", env, true, errInfo));
        p.incValue(10);

        env.put("jspListTitle", jspListTitle_Source.toString());
        env.put("jspListData", jspListData_Source.toString());
        env.put("jspInputBodyText", jspInputBodyText_Source.toString());

        // jsp列表文件
        String src = sourceTemplatePool.makeSource(CodeTempIds.sh_FRAME, "sh.ListFrame", env, true, errInfo);
        src = src == null ? "" : src.replace("_SH_DOLLAR_", "$");
        jspListSrcUnit.setCode(src);
        p.incValue(10);
        // jsp输入文件 
        src = sourceTemplatePool.makeSource(CodeTempIds.sh_FRAME, "sh.InputFrame", env, true, errInfo);
        src = src == null ? "" : src.replace("_SH_DOLLAR_", "$");
        jspInputSrcUnit.setCode(src);
        p.incValue(10);

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

        struts2SrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.sh_FRAME, "sh.StrutsXml", env, true, errInfo));
        p.incValue(10);

        p.setValue(100);

        return Ret.getSuccessRet();
    }

    private void putEnvAtPojoField(PojoField f) {
        if (f.varName == null || f.varName.length() == 0) {
            return;
        }
        if (firstColName.isEmpty()) {
            firstColName = f.col.getSqlName();
        }
        // 首亨：所有数字，必须是double类型
        if (isVarNumeberType(f.varType)) {
            f.varType = "double";
            env.put("annotateAux", ", colType=SHColumnType.DOUBLE");
        } else {
            env.put("annotateAux", "");
        }
        //     ${scope}    ${vartype}  ${varname};		// field ${colname} : ${colDataType}
        env.put("varName", f.varName);
        env.put("vartype", f.varType);
        env.put("scope", f.scope);
        env.put("fieldName", f.col.getSqlName());
        env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
        env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
        env.put("length", f.col.getSize());
        env.put("jdbcType", f.col.getSqlTypeName());
        env.put("fieldLabel", f.col.getColComment() == null ? "" : f.col.getColComment());   // 字段备注。暂用字段名称。

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
            s = sourceTemplatePool.makeSourceA(CodeTempIds.sh_UTIL, "q_field", env, true, errInfo);
            fields.append(s);
            //
            Meta_Table mt = u.getJtableModel().getTbean().getTableDetail();
            String tableName = mt.getSqlName();
            if (!dirtyTables.contains(tableName)) {
                env.put("tableName", tableName);
                env.put("tableAlias", u.getJtableModel().getTbean().getTableAlias());
                env.put("tableComment", mt.getTableComment().replace('r', ' ').replace('\n', ' '));
                // 
                s = sourceTemplatePool.makeSourceA(CodeTempIds.sh_UTIL, "q_tables", env, true, errInfo);
                tables.append(s);
                dirtyTables.add(tableName);
            }
        }

        env.put("queryFieldsBody", fields.toString());
        env.put("tablesBody", tables.toString());
        s = sourceTemplatePool.makeSourceA(CodeTempIds.sh_FRAME, "select", env, true, errInfo);

        env.clear();
        return s;
    }
    final Set<String> NUMBER_TYPES = new TreeSet<String>();

    private boolean isVarNumeberType(String varType) {
        if (NUMBER_TYPES.isEmpty()) {
            NUMBER_TYPES.add("int");
            NUMBER_TYPES.add("Integer");
            NUMBER_TYPES.add("long");
            NUMBER_TYPES.add("Long");
            NUMBER_TYPES.add("short");
            NUMBER_TYPES.add("Short");
            NUMBER_TYPES.add("float");
            NUMBER_TYPES.add("Float");
        }
        return NUMBER_TYPES.contains(varType);
    }

    @Override
    public String[] getSubPaths() {
        return new String[]{};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_ShouHen.class.getName();
    }

    @Override
    public void setSModel(SqlCreateModel smodel) {
        this.smodel = smodel;
    }

    @Override
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
        boolean listdev = smodel.isFieldMasked("listdev", col.getSqlName());
        boolean add = smodel.isFieldMasked("add", col.getSqlName());
        boolean update = smodel.isFieldMasked("update", col.getSqlName());
        boolean view = smodel.isFieldMasked("view", col.getSqlName());
        boolean exp = smodel.isFieldMasked("exp", col.getSqlName());
        boolean imp = smodel.isFieldMasked("imp", col.getSqlName());
        // 
        final StringBuilder sb = new StringBuilder();
        sb.append(listdev ? 'L' : ' ');
        sb.append(add ? 'a' : ' ');
        sb.append(update ? 'u' : ' ');
        sb.append(view ? 'v' : ' ');
        sb.append(exp ? 'x' : ' ');
        sb.append(imp ? 'm' : ' ');
        return new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JLabel(sb.toString());
            }
        };
    }
}
