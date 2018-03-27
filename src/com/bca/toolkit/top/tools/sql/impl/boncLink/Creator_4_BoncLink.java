/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor. 
 */
package com.bca.toolkit.top.tools.sql.impl.boncLink;

import com.bca.api.pub.Ret;
import com.bca.db.DbConst.DbObjType;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_NamedQryElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.cfg.SGlobal;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Numtool;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Stringtool.TargetCase;
import com.bca.pub.tools.Templtool;
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
import com.bca.toolkit.top.tools.sql.impl.gx.PxzHashMap;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean.UiMemMapMode;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiTemplUnit;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
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
public class Creator_4_BoncLink implements I_SqlCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final PxzHashMap env = new PxzHashMap();
    final StringBuffer errInfo = new StringBuffer();
//    String alias;
    public SqlCreateModel smodel;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
//    public final List<String> fieldsUiSort = new ArrayList<String>();   // 在界面中的输出顺序。
    Meta_Table dbTable;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;
    private List<Map<String, String>> uiColSort;

    //boncLink 需要联动的图的数目
    private int chartNum;
            
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
        return new String[]{"jsp", "sql"};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_BoncLink.class.getName();
    }

    @Override
    public void setSModel(SqlCreateModel smodel) {
        this.smodel = smodel;
    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String boncLink_FRAME = "boncLink.Frame";
        public final static String boncLink_UTIL = "boncLink.util";  

    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public Creator_4_BoncLink() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();
        sourceTemplatePool.logEnvOnSrcCreate = app.cfg.getCfgBean().logEnvOnSrcCreate;
        env.setLogLeading("--cfenv--");
    }

    @Override
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> aaa, Meta_Table dbTable, List<Map<String, String>> uiColSort) {
//        this.alias = alias;
        this.smodel = smodel;
        this.pojoAttr = pojoAttr;
        this.dbTable = dbTable;
        this.pojoFields = new LinkedHashMap<String, PojoField>();
        this.uiColSort = uiColSort;
        boolean numberPackFlag = Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag"));
        chartNum = QbTablePopDialog_BoncLink.PicNum;
        for (I_WfColumn col : dbTable.getColumns()) {
            PojoField f = new PojoField();
            f.col = col;
            //下面这句话，把数据库字段名col.getSqlName()转换成javaBean中的属性变量名varName
            f.varName = Stringtool.getRecommendName(col.getSqlName(), Stringtool.RecommendNameType.VarName);  // (pojoAttr.tabelName);

            String sqltypen = f.col.getSqlTypeName().toLowerCase();
            if (sqltypen.startsWith("number") || sqltypen.startsWith("decimal") || sqltypen.startsWith("int")) {
                f.varType = app.getBcaToolkitConfig().getDataWizConfig().getConvertedJavaType(col.getSqlName(), f.col.getSqlTypeName());
            }
            if (f.varType == null || f.varType.isEmpty()) {
                f.varType = col.getJavaVarType();
            }
            if (numberPackFlag) {
                f.changeNumberTypeToPackedMode();
            }
            pojoFields.put(f.col.getSqlName(), f);
        }

        createPojoSourceCreatorUnit("Controller.java", String.format("%sController.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("Forecast.js", String.format("%s.js", pojoAttr.getProperty("fatherFolder")), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("Table.html", String.format("table.html", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("Mapper.java", String.format("%sMapper.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("Mapper.xml", String.format("%sMapper.xml", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("Service.java", String.format("%sService.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("ServiceImpl.java", String.format("%sServiceImpl.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("TableIndex.html", String.format("tableIndex.html", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        
        createPojoSourceCreatorUnit("note", String.format("框架内容生成说明_%s.txt", pojoAttr.className), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit("createProj.bat", "createProj.bat", pojoAttr.getProperty("dosScriptCharset"));
        createPojoSourceCreatorUnit("ModifyCompare.bat", "ModifyCompare.bat", pojoAttr.getProperty("dosScriptCharset"));

    }

    private void createPojoSourceCreatorUnit(String creatorId, String fileName, String charset) {
        PojoSourceCreatorUnit srcUnit = new PojoSourceCreatorUnit(creatorId);
        srcUnit.setFileName(fileName);
        srcUnit.setCharset(charset);
        sourceUnits.put(srcUnit.getCreatorId(), srcUnit);
    }

    private void initEnv() {
        // 先清除全局性编辑风格
        estypeTableLevelSrc_map.clear();
        env.put("estyleOnSql", "");
        env.put("estyleOnAction", "");
        env.put("estyleOnBusi", "");
        env.put("estyleOnDao", "");
        env.put("bindingSeqOnTable", false);
        //
        env.put("hxhkBatRem", smodel.activeFactory.pojoAttr.getProperty("hxhkBatRem"));
        env.put("uiSwitch_add", true);
        env.put("uiSwitch_delete", true);
        env.put("uiSwitch_update", true);
        env.put("uiSwitch_exp", true);
        env.put("uiSwitch_imp", true);
        
        //boncLink 先设定所有开关状态内容为空
        env.put("table_query",true);
        env.put("table_order",false);
        env.put("table_where",false);
        env.put("table_group",false);
        env.put("table_sum",true);
        
        for(int orderNum = 1; orderNum <= chartNum; orderNum++){
            env.put("chart_query" + orderNum,true);
            env.put("chart_order" + orderNum,false);
            env.put("chart_where" + orderNum,false);
            env.put("chart_group" + orderNum,false);
            env.put("chart_sum" + orderNum,true);
        }
        
        //解决$消失的问题
        env.put("doller", "$");
        env.put("color0", "###,###,##0");

        if (smodel.isFieldMasked("ui", "Info.jsp")) {
            smodel.maskAllFields("criteria");
        }

        checkUiMasks_var("exp", "uiSwitch_exp");
        checkUiMasks_var("imp", "uiSwitch_imp");
        checkUiMasks_var("print", "uiSwitch_print");
    }

    @Override
    public Ret createAllPojoSource() {

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

            if (smodel.isFieldMasked("ui", "exp")) {
                sourceUnits.remove("expAction");
                sourceUnits.remove("ExportUtil");
            }
            if (pojoAttr.dbObjType != DbObjType.Table) {
                sourceUnits.remove("view_ddl.sql");
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
//检出模版是否存在，若不存在则在log打出
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Controller.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Forecast.js", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "TableIndex.html", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Table.html", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Mapper.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Mapper.xml", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Service.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "ServiceImpl.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "createProj.bat", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "ModifyCompare.bat", true, errInfo) ? 0 : 1;
        
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "sumUnitItem", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "whereUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "orderUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "groupUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableControllerUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableServiceUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableServiceImplUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableMapperUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectChart1", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectChart1", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectTable", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectUnit", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableHeaderTh", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableHeaderTd", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableCommonTh", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "tableCommonTd", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "functionGetChart1", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "functionGetChart2", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "functionSetBarChart1", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "functionSetBarChart2", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "getChart1", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "getChart2", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "titleChartDivUnit", true, errInfo) ? 0 : 1;


        

        //增加使用者信息
        sourceTemplatePool.regTemplateUsing(CodeTempIds.boncLink_FRAME, getClass(), "norten");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.boncLink_UTIL, getClass(), "norten");

        //若模版库为空，则页面出提示
        if (absentContents == 0) {  
        //if (true) {
            return Ret.getSuccessRet();
        }
        return Ret.getFailureRet("缺少模板，无法生成SQL代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }
    // 
    String firstColName = "";
    boolean viewByColFlag = false;   // 如果已经在某个列上绑定了查看，则不需要显示“查看详细”标签了   这个是全局参数。

    
    private void putEnvAtPojoField(PojoField f) {
        if (f.varName == null || f.varName.length() == 0) {
            return;
        }
        if (firstColName.isEmpty()) {
            firstColName = f.col.getSqlName();
        }
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
        env.put("scope", f.scope);
        env.put("fieldName", f.col.getSqlName());
        env.put("fieldName_lowerCase", f.col.getSqlName().toLowerCase());
        env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
        env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
        env.put("length", f.col.getSize());
        env.put("jdbcType", f.col.getSqlTypeName());
        //env.put("fieldLabel", f.col.getColComment() == null ? "" : f.col.getColComment());   // 字段备注。暂用字段名称。
        // 
        boolean colSW = !smodel.isFieldMasked("verify", f.col.getSqlName());
        env.put("verifyCol", colSW);
        colSW = !smodel.isFieldMasked("viewBindingCol", f.col.getSqlName());
        env.put("viewBindingCol", colSW);
        if (colSW) {
            viewByColFlag |= colSW;
            env.put("viewByColFlag", viewByColFlag);
        }
        colSW = !smodel.isFieldMasked("bindingSeqCol", f.col.getSqlName());   // 列与seq绑定....
        env.put("bindingSeqCol", colSW);
        if (colSW) {
            env.put("seqBindVarNameCap", env.get("varnameCap"));    // 绑定seq的成员变量名称，变成了首字母大写...
            env.put("bindingSeqOnTable", true);
        }
    }
    final Set<String> estyleEnvKeys = new HashSet<String>();

    private void putEStypeEnvAtField(PojoField f) {
        WebUiMemBean mbean = smodel.getUiStyle(f.col.getSqlName());
        if (mbean == null) {
            for (String key : estyleEnvKeys) {
                env.put(key, "");
            }

            return;
        }
        switch (mbean.getKvMapMode()) {
            case MapByStatic:
                break;
            case MapBySql:
                break;
            case MapByHkTemplate:
                putEStypeEnvAtField_MapByHkTemplate(f, mbean);
                break;
        }
    }
    // 保存生命周期为表级别的编辑风格脚本。
    final Map<String, StringBuffer> estypeTableLevelSrc_map = new HashMap<String, StringBuffer>();

    private void putEStypeEnvAtField_MapByHkTemplate(PojoField f, WebUiMemBean mbean) {
        for (WebUiTemplUnit tu : mbean.getEstyleTemplUnits().values()) {
            String src = Templtool.createCodeByTemplate(tu.getEstyleContent(), env, errInfo);
            switch (tu.getScope()) {
                case 't':
                case 'T':
                    StringBuffer sb = estypeTableLevelSrc_map.get(tu.getEstyleArgName());
                    if (sb == null) {
                        sb = new StringBuffer();
                        estypeTableLevelSrc_map.put(tu.getEstyleArgName(), sb);
                    }
                    sb.append(src);
                    break;
                default:
                    env.put(tu.getEstyleArgName(), src);
                    estyleEnvKeys.add(tu.getEstyleArgName());
                    break;
            }
        }
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
        // 

        PojoSourceCreatorUnit controllerSrcUnit = sourceUnits.get("Controller.java");
        PojoSourceCreatorUnit ForecastJsUnit = sourceUnits.get("Forecast.js");
        PojoSourceCreatorUnit TableHtmlUnit = sourceUnits.get("Table.html");
        PojoSourceCreatorUnit TableIndexHtmlUnit = sourceUnits.get("TableIndex.html");
        PojoSourceCreatorUnit daoSrcUnit = sourceUnits.get("Mapper.java");
        PojoSourceCreatorUnit mapperXmlUnit = sourceUnits.get("Mapper.xml");
        PojoSourceCreatorUnit serviceSrcUnit = sourceUnits.get("Service.java");
        PojoSourceCreatorUnit serviceImplSrcUnit = sourceUnits.get("ServiceImpl.java");
        
        
        PojoSourceCreatorUnit batSrcUnit = sourceUnits.get("createProj.bat");
        PojoSourceCreatorUnit ModifyCompareSrcUnit = sourceUnits.get("ModifyCompare.bat");
       
        assert controllerSrcUnit != null;
        assert ForecastJsUnit != null;
        assert TableIndexHtmlUnit != null;
        assert TableHtmlUnit != null;
        assert daoSrcUnit != null;
        assert mapperXmlUnit != null;
        assert serviceSrcUnit != null;
        assert serviceImplSrcUnit != null;
        

        assert ModifyCompareSrcUnit != null;
        assert batSrcUnit !=null;


        // 临时的变量指定。 实际上是需要界面选择的。。。
        env.put("order_AscDesc", "DESC");
//        
//        env.put("actionHome", "hxhk.phoneTest");
//        
        env.put("jspPath", "/app/hxhk/tssnt");
        // endof 临时的变量指定。 实际上是需要界面选择的。。。

        StringBuilder pojoFieldsText = new StringBuilder();
        StringBuilder boFieldsText = new StringBuilder();
        StringBuilder pojocsvFieldsText = new StringBuilder();
        // 
        // com.bca.templ.po.TlTemplateTextReg@1c047f0[templDomainId=1024,templId=1234,contentId=9,syntaxTypeId=0,contentName=namedQueryDeclareField,contentTxtName=namedQueryDeclareField,syntaxTagEnable=false,helpInfo=]
        // 
        StringBuilder namedQueryDeclareByAllSource = new StringBuilder();
        StringBuffer namedQueryDeclareSource = new StringBuffer();
        StringBuilder varsOperatorSource = new StringBuilder();

        // Hxhk frame ....
        StringBuilder addByPro_Body = new StringBuilder();
        StringBuilder deleteByPro_Body = new StringBuilder();
        StringBuilder updateByPro_Body = new StringBuilder();
        StringBuilder addByPro_SqlXml = new StringBuilder();
        StringBuilder deleteByPro_SqlXml = new StringBuilder();
        StringBuilder updateByPro_SqlXml = new StringBuilder();
        StringBuilder addByPro_Symbols = new StringBuilder();
        StringBuilder deleteByPro_Symbols = new StringBuilder();
        StringBuilder updateByPro_Symbols = new StringBuilder();

        StringBuilder select_fieldList = new StringBuilder();  //view_ddl.sql
        StringBuilder viewddl_comments = new StringBuilder();  //view_ddl.sql
        
        //boncLink
        StringBuilder mapperSql = new StringBuilder();
        StringBuilder sqlSumAddSrc = new StringBuilder();
        StringBuilder tableSelectSet = new StringBuilder();
        StringBuilder tableGroupSet = new StringBuilder();
        StringBuilder tableWhereSet = new StringBuilder();
        StringBuilder tableOrderSet = new StringBuilder();
        StringBuilder tableControllerSet = new StringBuilder();
        StringBuilder tableServiceSet = new StringBuilder();
        StringBuilder tableServiceImplSet = new StringBuilder();
        StringBuilder tableMapperSet = new StringBuilder();
        StringBuilder functionGetChartSet = new StringBuilder();
        StringBuilder functionSetBarChartSet = new StringBuilder();
        StringBuilder getChartSet = new StringBuilder();
        StringBuilder tableCommonThSet = new StringBuilder();
        StringBuilder tableCommonTdSet = new StringBuilder();
        StringBuilder tableHeaderTh = new StringBuilder();
        StringBuilder tableHeaderTd = new StringBuilder();
        StringBuilder titleChartDivSet = new StringBuilder();
        StringBuilder barChartGetChartSet = new StringBuilder();

        // sp_ParaXml
        StringBuilder sqlQryFieldSrc = new StringBuilder();
        StringBuilder sqlQry_DynCriteriaFieldSrc = new StringBuilder();
        StringBuilder sqlViewCriteriaSrc = new StringBuilder();
        
        StringBuilder sqlInsertFieldSrc = new StringBuilder();
        StringBuilder sqlInsertValueSrc = new StringBuilder();
        StringBuilder sqlUpdateExprSrc = new StringBuilder();
        // 存储过程：add
        StringBuilder spAddArgList = new StringBuilder();
        StringBuilder spAddFieldsList = new StringBuilder();
        StringBuilder spAddValuesList = new StringBuilder();
        // 存储过程：delete
        StringBuilder spDeleteArgList = new StringBuilder();
        StringBuilder spDeleteWhereList = new StringBuilder();
        // 存储过程：update
        StringBuilder spUpdateArgList = new StringBuilder();
        StringBuilder spUpdateSetList = new StringBuilder();
        StringBuilder spUpdateWhereList = new StringBuilder();
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

        StringBuilder jsp_verifyRules_Source = new StringBuilder();    // jsp_verifyRules
        StringBuilder jsp_verifyMessages_Source = new StringBuilder();    // jsp_verifyMessages

        // 
        StringBuffer pkConstructor_para_Source = new StringBuffer();
        StringBuffer pkConstructor_body_Source = new StringBuffer();
        StringBuffer wholeConstructor_para_Source = new StringBuffer();
        StringBuffer wholeConstructor_body_Source = new StringBuffer();
      
        

        String s = "";

        env.put("projSrcDir", pojoAttr.getProperty("projSrcDir"));
        env.put("pkgHome", pojoAttr.getProperty("pkgHome"));
        //尾包名中的父包名（后添加的）
        String father = pojoAttr.getProperty("fatherFolder");
        if(father == null){
            father = "";
        }
        env.put("fatherFolder", father);
        //尾包名中的子包名（后添加的）
        String child = pojoAttr.getProperty("childFolder");
        if(child == null){
            child = "";
        }
        env.put("childFolder", child);
        //创建人（后添加的）
        String creator = pojoAttr.getProperty("creator");
        if(creator==null){
            creator="";
        }
        env.put("creator", creator);

        env.put("pkgHome_dir", pojoAttr.getProperty("pkgHome").replace('.', '\\'));
        env.put("pkgTail_dir", pojoAttr.getProperty("pkgTail").replace('.', '\\'));
        env.put("childFolder_dir", pojoAttr.getProperty("childFolder").replace('.', '\\'));
        env.put("fatherFolder_dir", pojoAttr.getProperty("fatherFolder").replace('.', '\\'));
        // 
        env.put("pkgTail_dir_unix", pojoAttr.getProperty("pkgTail").replace('.', '/'));
        env.put("pkgTail_dir_unix", pojoAttr.getProperty("pkgTail").replace('.', '/'));
        // 如下参数  用于区分业务dao
        env.put("daoPrefix", pojoAttr.getProperty("daoPrefix"));
        env.put("daoPrefixCap", Stringtool.changeLeadingCharCase(pojoAttr.getProperty("daoPrefix"), TargetCase.UpperCase, 1));
        // 如下3个参数,用于sqlplus自动注册模块及授权.
        env.put("dbups", pojoAttr.getProperty("dbups"));
        env.put("busiDBups", pojoAttr.getProperty("busiDBups"));
        env.put("parenetMenuName", pojoAttr.getProperty("parenetMenuName"));
        env.put("authRole", pojoAttr.getProperty("authRole"));
        env.put("pkgHome", pojoAttr.getProperty("pkgHome"));

        env.put("projName", pojoAttr.getProperty("projName"));
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
        env.put("entityLable", Stringtool.isStringEmpty(pojoAttr.tableComment) ? pojoAttr.tableName : pojoAttr.tableComment);
        env.put("dbObjType", pojoAttr.dbObjType.toString());
        viewByColFlag = false;
        env.put("viewByColFlag", viewByColFlag);

        env.put("replaceConfirmSymbol", smodel.isConfirmReplaceAtDos() ? "/-Y" : "");

        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        env.put("tableName_lowerCase", this.pojoAttr.tableName.toLowerCase());
        env.put("dbObjType", this.pojoAttr.dbObjType);
        if (pojoAttr.dbObjType == DbObjType.NamedQry) {
            Meta_NamedQryElement qe = (Meta_NamedQryElement) dbTable.getTableElement();
            env.put("querySQL", qe.getTsql());
            // 对于查询实体， 增删改、导入应该无效。
            env.put("uiSwitch_add", false);
            env.put("uiSwitch_delete", false);
            env.put("uiSwitch_update", false);
            env.put("uiSwitch_imp", false);
        }

        env.put("tableRemark", this.pojoAttr.tableComment == null ? "" : pojoAttr.tableComment);
        env.put("createTime", Timetool.getSysTimestamp().toString());
        env.put("projHomeDir", pojoAttr.getProperty("projHomeDir"));

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
//        s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "namedQueryByAlllDeclare", env, true, errInfo);
//        namedQueryDeclareByAllSource.append(s);
//        env.put("namedQueryDeclareByAllSource", namedQueryDeclareByAllSource.toString());
        firstColName = "";
        int fieldScanIndex = 0;
        // expSetHeadSrc.append(pojoAttr.tableComment).append(',');
        
        //boncLink 根据图的数目生成controller，service，mapper的java代码片段
        for (int orderNum = 2; orderNum <= chartNum; orderNum++){
            env.put("orderNum", orderNum);
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "getChart2", env, true, errInfo);
            barChartGetChartSet.append(s); 
        }
        Stringtool.removeLastChars(tableControllerSet, '\r', '\n', ',', ' ');
        env.put("barChartGetChartSet", barChartGetChartSet);
        for (int orderNum = 1; orderNum <= chartNum; orderNum++){
            env.put("orderNum", orderNum);
            env.put("orderNumAdd", orderNum + 1);
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableControllerUnit", env, true, errInfo);
            tableControllerSet.append(s);   
            
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableServiceUnit", env, true, errInfo);
            tableServiceSet.append(s);   
            
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableServiceImplUnit", env, true, errInfo);
            tableServiceImplSet.append(s);   
            
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableMapperUnit", env, true, errInfo);
            tableMapperSet.append(s); 
            
            s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "titleChartDivUnit", env, true, errInfo);
            titleChartDivSet.append(s);  
            
            if(orderNum < 3){
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "selectChart" + orderNum, env, true, errInfo);
                mapperSql.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "functionGetChart" + orderNum, env, true, errInfo);
                functionGetChartSet.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "functionSetBarChart" + orderNum, env, true, errInfo);
                functionSetBarChartSet.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "getChart" + orderNum, env, true, errInfo);
                getChartSet.append(s);  
                
                
               
            }else{
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "selectChart2", env, true, errInfo);
                mapperSql.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "functionGetChart2", env, true, errInfo);
                functionGetChartSet.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "functionSetBarChart2", env, true, errInfo);
                functionSetBarChartSet.append(s); 
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "getChart2", env, true, errInfo);
                getChartSet.append(s);  
               
            }
             
        }
        
        
        for (PojoField f : pojoFields.values()) {
            fieldScanIndex++;
            boolean isLastField = fieldScanIndex == pojoFields.size();
            putEnvAtPojoField(f);
            putEStypeEnvAtField(f);

            //boncLink
            if (!smodel.isFieldMasked("criteria_0", f.col.getSqlName())) { // query  没有屏蔽，则输出
                env.put("table_query",true);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "selectUnit", env, true, errInfo);
                
                tableSelectSet.append(s);
            }
            if (!smodel.isFieldMasked("add_0", f.col.getSqlName())) { // sum table  没有屏蔽，则输出
                env.put("table_sum",true);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "sumUnitItem", env, true, errInfo);
                
                sqlSumAddSrc.append(s);
            }
            if (smodel.isGroupField("group_0", f.col.getSqlName())) { // group table  没有屏蔽，则输出
                env.put("table_group",true);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "groupUnit", env, true, errInfo);
                
                tableGroupSet.append(s);
            }
            if (smodel.isGroupField("order_0", f.col.getSqlName())) { // order table  没有屏蔽，则输出
                env.put("table_order",true);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "orderUnit", env, true, errInfo);
                
                tableOrderSet.append(s);
            }
            if (smodel.isGroupField("where_0", f.col.getSqlName())) { // where table  没有屏蔽，则输出
                env.put("table_where",true);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "whereUnit", env, true, errInfo);
                
                tableWhereSet.append(s);
            }
            
            //boncLink

            // 存储过程：add
            boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
            env.put("douHao", isLastField ? "" : ",");

            p.incValue(1);
        }
        //boncLink 生成selectTable（sql片段）碎片
        env.put("selectUnitSet", tableSelectSet.toString());
        
        
        
        
        Stringtool.removeLastChars(tableGroupSet, '\r', '\n', ',', ' ');
        env.put("tableGroupSet", tableGroupSet.toString());
        
        Stringtool.removeLastChars(tableOrderSet, '\r', '\n', ',', ' ');
        env.put("tableOrderSet", tableOrderSet.toString());
        
        env.put("sumUnitSet", sqlSumAddSrc.toString());
        if(tableWhereSet.length()> 3){
            String tableWhereSetStr = tableWhereSet.toString().substring(0, tableWhereSet.length() - 5);
        env.put("tableWhereSet", tableWhereSetStr);
        }
        
        s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "selectTable", env, true, errInfo);
        mapperSql.append(s);
        
        
        
        // final Map<String, StringBuffer> estypeTableLevelSrc_map = new HashMap<String, StringBuffer>();
        log.debug("--estypeTableLevelSrc_map--.size:%d", estypeTableLevelSrc_map.size());
        for (Map.Entry<String, StringBuffer> e : estypeTableLevelSrc_map.entrySet()) {
            log.debug("--estypeTableLevelSrc_map--:k=<%s>,c=<%s>", e.getKey(), e.getValue());
            env.put(e.getKey(), e.getValue().toString());
        }
        // 按照约定的列顺序，输出字段到UI
        int field_idx_view = 0;
        int field_idx_add = 0;
        int field_idx_update = 0;
        boolean tr_LineFeed = false;   // 表示应该加换行标记 </tr><tr>
        int compPerRow = smodel.getCompPerRow();
        int uiIndex = 0;
        for (Map<String, String> name : uiColSort) {
            fieldScanIndex++;
            
            for(Map.Entry<String, String> colName : name.entrySet()){
            PojoField f = pojoFields.get(colName.getKey());
            if (f == null) {
                log.error("PojoField 未找到：%s, 跳过该列!", colName.getKey());
                continue;
            }
            putEnvAtPojoField(f);
            env.put("fieldLabel", colName.getValue() == null ? "" : colName.getValue()); 
            env.put("tr_LineFeed", false);
            
            //boncLink
            if (!smodel.isFieldMasked("add_0", f.col.getSqlName())) { // sum  没有屏蔽，则输出
                uiIndex++;
                
                if(uiIndex == 1){
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableHeaderTh", env, true, errInfo);
                    tableHeaderTh.append(s);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableHeaderTd", env, true, errInfo);
                    tableHeaderTd.append(s);
                }else{
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableCommonTh", env, true, errInfo);
                    tableCommonThSet.append(s);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "tableCommonTd", env, true, errInfo);
                    tableCommonTdSet.append(s);
                }
                
            }
            
            }
        }

        Stringtool.reduceLength(pkConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(pkConstructor_body_Source, "\r\n".length());
        Stringtool.reduceLength(wholeConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(wholeConstructor_body_Source, "\r\n".length());

//        Stringtool.reduceLength(toStringAppendSource, 6);
        env.put("fieldName_4_criteria", firstColName);
        env.put("pojoFieldsText", pojoFieldsText.toString());
        env.put("boFieldsText", boFieldsText.toString());
        env.put("pojoGetSetText", pojoGetSetText_Source.toString());
        env.put("boGetSetText", boGetSetText_Source.toString());
        // 
        env.put("expSetHeadSrc", expSetHeadSrc.toString());
        env.put("expSetBodySrc", expSetBodySrc.toString());   // expBodySrc
        // 
        env.put("addProBody", addByPro_Body.toString());
        env.put("deleteProBody", deleteByPro_Body.toString());
        env.put("updateProBody", updateByPro_Body.toString());

        env.put("viewColComments", viewddl_comments.toString());
        env.put("select_fieldList", select_fieldList.toString());

        // 
        env.put("spInsertParameters", addByPro_SqlXml.toString());
        env.put("spUpdateParameters", updateByPro_SqlXml.toString());
        env.put("spDeleteParameters", deleteByPro_SqlXml.toString());
        env.put("spInsertSymbols", addByPro_Symbols.toString());
        env.put("spDeleteSymbols", deleteByPro_Symbols.toString());
        env.put("spUpdateSymbols", updateByPro_Symbols.toString());
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

        s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "pkFillBySeq", env, true, errInfo);
        p.incValue(10);
        s = s == null ? "" : Stringtool.removeTrailingChars(s, '\n', '\r');

        env.put("pkFillBySeqText", s);
        Stringtool.removeLastChars(sqlQryFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlQryField", sqlQryFieldSrc.toString());
        Stringtool.removeLastChars(sqlQry_DynCriteriaFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlQry_DynCriteriaField", sqlQry_DynCriteriaFieldSrc.toString());
        Stringtool.removeLastChars(sqlViewCriteriaSrc, '\r', '\n', ',', ' ');
        env.put("viewUnit", sqlViewCriteriaSrc.toString());
       
        Stringtool.removeLastChars(sqlInsertFieldSrc, '\r', '\n', ',', ' ');
        env.put("sqlInsertField", sqlInsertFieldSrc.toString());
        Stringtool.removeLastChars(sqlInsertValueSrc, '\r', '\n', ',', ' ');
        env.put("sqlInsertValue", sqlInsertValueSrc.toString());
        Stringtool.removeLastChars(sqlUpdateExprSrc, '\r', '\n', ',', ' ');
        env.put("sqlUpdateExpr", sqlUpdateExprSrc.toString());
        
        
        //boncLink
        
        env.put("mapperSql", mapperSql.toString());
        Stringtool.removeLastChars(tableControllerSet, '\r', '\n', ',', ' ');
        env.put("tableControllerSet", tableControllerSet.toString());
        Stringtool.removeLastChars(tableServiceSet, '\r', '\n', ',', ' ');
        env.put("tableServiceSet", tableServiceSet.toString());
        Stringtool.removeLastChars(tableServiceImplSet, '\r', '\n', ',', ' ');
        env.put("tableServiceImplSet", tableServiceImplSet.toString());
        Stringtool.removeLastChars(tableMapperSet, '\r', '\n', ',', ' ');
        env.put("tableMapperSet", tableMapperSet.toString());
        Stringtool.removeLastChars(functionGetChartSet, '\r', '\n', ',', ' ');
        env.put("functionGetChartSet", functionGetChartSet.toString());
        Stringtool.removeLastChars(getChartSet, '\r', '\n', ',', ' ');
        env.put("getChartSet", getChartSet.toString());
        Stringtool.removeLastChars(tableCommonThSet, '\r', '\n', ',', ' ');
        env.put("tableCommonThSet", tableCommonThSet.toString());
        Stringtool.removeLastChars(tableCommonTdSet, '\r', '\n', ',', ' ');
        env.put("tableCommonTdSet", tableCommonTdSet.toString());
        Stringtool.removeLastChars(tableHeaderTh, '\r', '\n', ',', ' ');
        env.put("tableHeaderTh", tableHeaderTh.toString());
        Stringtool.removeLastChars(tableHeaderTd, '\r', '\n', ',', ' ');
        env.put("tableHeaderTd", tableHeaderTd.toString());
        Stringtool.removeLastChars(titleChartDivSet, '\r', '\n', ',', ' ');
        env.put("titleChartDivSet", titleChartDivSet.toString());
        Stringtool.removeLastChars(functionSetBarChartSet, '\r', '\n', ',', ' ');
        env.put("functionSetBarChartSet", functionSetBarChartSet.toString());
        
        

//        Stringtool.removeLastChars(spAddArgList, '\r', '\n', ',', ' ');
        // 存储过程: add
        env.put("proAdd_argList", spAddArgList.toString());
        Stringtool.removeLastChars(spAddFieldsList, '\r', '\n', ',', ' ');
        env.put("proAdd_fieldList", spAddFieldsList.toString());
        Stringtool.removeLastChars(spAddValuesList, '\r', '\n', ',', ' ');
        env.put("proAdd_valueList", spAddValuesList.toString());
        // 存储过程: update
        env.put("proUpdate_argList", spUpdateArgList.toString());
        Stringtool.removeLastChars(spUpdateSetList, '\r', '\n', ',', ' ');
        env.put("proUpdate_setList", spUpdateSetList.toString());
        Stringtool.removeLastChars(spUpdateWhereList, '\r', '\n', ',', ' ');
        env.put("proUpdate_whereList", spUpdateWhereList.toString());
        // 存储过程: delete
        env.put("proDelete_argList", spDeleteArgList.toString());
        Stringtool.removeLastChars(spDeleteWhereList, '\r', '\n', ',', ' ');
        env.put("proDelete_whereList", spDeleteWhereList.toString());

        env.put("jspQueryTitle", jspQueryTitle_Source.toString());
        env.put("jspListTitle", jspListTitle_Source.toString());
        env.put("jspListData", jspListData_Source.toString());
        env.put("jspAddDataUnit", jspAdd_BodySource.toString());
        env.put("jspUpdateDataUnit", jspUpdate_BodySource.toString());
        env.put("jspViewDataUnit", jspView_BodySource.toString());

        Stringtool.removeLastChars(jsp_verifyRules_Source, '\r', '\n', ',', ' ');
        Stringtool.removeLastChars(jsp_verifyMessages_Source, '\r', '\n', ',', ' ');
        env.put("jsp_verifyRules", jsp_verifyRules_Source.toString());
        env.put("jsp_verifyMessages", jsp_verifyMessages_Source.toString());

        env.put("compareTool", cfg.getExternalCompareTool());   // 比较工具
        env.put("pojoCsvBody", pojocsvFieldsText.toString());
        
        controllerSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Controller.java", env, true, errInfo));
        ForecastJsUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Forecast.js", env, true, errInfo));
        TableIndexHtmlUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "TableIndex.html", env, true, errInfo));
        TableHtmlUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Table.html", env, true, errInfo));
        daoSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Mapper.java", env, true, errInfo));
        mapperXmlUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Mapper.xml", env, true, errInfo));
        serviceSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "Service.java", env, true, errInfo));
        serviceImplSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "ServiceImpl.java", env, true, errInfo));
        

        batSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "createProj.bat", env, true, errInfo));
        p.incValue(10);
        String compareBat = sourceTemplatePool.makeSource(CodeTempIds.boncLink_FRAME, "ModifyCompare.bat", env, true, errInfo);
        ModifyCompareSrcUnit.setCode(compareBat);
         
        p.incValue(10);

        return Ret.getSuccessRet();
    }

//    /**
//     * 代码比对需要做个特别处理：相同的文件，bat命令前面加个bat 跳过比对
//     *
//     * @param compareBat
//     */
//    private String checkCompare_skipSameFile(String compareBat) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            BufferedReader fr = new BufferedReader(new StringReader(compareBat));
//            while (fr.ready()) {
//                String s = fr.readLine();
//                boolean b = isFileEquals_atBatLine(s);
//                if (b) {
//                    sb.append("rem __equals__ ").append(s).append("\r\n");
//                } else {
//                    sb.append(s).append("\r\n");
//                }
//            }
//            fr.close();
////            Filetool.saveStringToTextFile(batFileName + "_2", sb.toString());
//            return sb.toString();
//        } catch (Exception ex) {
//            log.error(com.bca.pub.tools.Toolfunc.getCallLocation(ex.getStackTrace()) + ":" + ex.getMessage(), ex);
////            ret.setFailure(ex.getMessage());
//            // 比对失败。返回原bat文本
//            return compareBat;
//        }
//    }
    private boolean isFileEquals_atBatLine(String batText, String srcOutDir) {
        //         env.put("compareTool", cfg.getExternalCompareTool());   // 比较工具
        String compareTool = (String) env.get("compareTool");  //  "F:\\sd\\wintools\\Beyond Compare\\Beyond Compare 2\\BC2.exe";
        if (batText == null || batText.isEmpty() || compareTool == null || compareTool.isEmpty()) {
            return false;
        }
        String text2 = batText.replace(compareTool, "_COMPARE_");
        text2 = Stringtool.removeLeadingChars(text2, ' ');
        while (text2.contains("  ")) {
            text2 = text2.replace("  ", " ");
        }
        String[] ss = text2.split(" ");
        if (ss.length < 3) {
            return false;
        }
        String fn1 = srcOutDir + File.separator + ss[1].replace("\"", "");   // 需要写绝对路径，比对函数isFileEquals才可以读出文件
        String fn2 = ss[2].replace("\"", "");
        boolean b = Filetool.isFileEquals(fn1, fn2);
        if (SGlobal.traceLogEnable) {
            log.trace("isFileEquals(%s,%s)=%b", fn1, fn2, b);
        }
        return b;
    }

    private void buildUiTags(WebUiMemBean uiBean) {
        if (uiBean == null) {
            env.put("jspEditStyleUnit", "");
            env.put("jspViewStyleUnit", "");
            return;
        }
        env.put("sql_estyleMap", uiBean.getSqlForKvMap());
        if (uiBean.getKvMapMode() == UiMemMapMode.MapByHkTemplate) {
            Map<String, WebUiTemplUnit> m = uiBean.getEstyleTemplUnits();
            if (m != null) {
                for (Map.Entry<String, WebUiTemplUnit> e : m.entrySet()) {
                    if (e.getValue().getScope() == 'f') {
                        String s = Templtool.createCodeByTemplate(e.getValue().getEstyleContent(), env, errInfo);
                        env.put(e.getKey(), s);
                    }
                }
            }
        }
//        WebUiTemplUnit u = uiBean.getEstyleTemplUnits().get("jspEditStyleUnit");  //  sourceTemplatePool.makeSource(CodeTempIds.hxhk_UTIL, "jspEditStyleUnit", env, true, errInfo);

    }

    private void switchDbtypeToview() {
        if (pojoAttr.dbObjType == DbObjType.Table) {
            env.put("dbObjType", DbObjType.View.toString());
            env.put("tableName", "HKV_" + pojoAttr.tableName);
        }
    }

    private void retoreDbType() {
        env.put("dbObjType", pojoAttr.dbObjType.toString());
        env.put("tableName", pojoAttr.tableName);
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
        return homeDir + File.separator + "src" + File.separator + pojoAttr.className;
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
            s = sourceTemplatePool.makeSourceA(CodeTempIds.boncLink_UTIL, "q_field", env, true, errInfo);
            fields.append(s);
            //
            Meta_Table mt = u.getJtableModel().getTbean().getTableDetail();
            String tableName = mt.getSqlName();
            if (!dirtyTables.contains(tableName)) {
                env.put("tableName", tableName);
                env.put("tableAlias", u.getJtableModel().getTbean().getTableAlias());
                env.put("tableComment", mt.getTableComment().replace('r', ' ').replace('\n', ' '));
                // 
                s = sourceTemplatePool.makeSourceA(CodeTempIds.boncLink_UTIL, "q_tables", env, true, errInfo);
                tables.append(s);
                dirtyTables.add(tableName);
            }
        }

        env.put("queryFieldsBody", fields.toString());
        env.put("tablesBody", tables.toString());
        s = sourceTemplatePool.makeSourceA(CodeTempIds.boncLink_FRAME, "select", env, true, errInfo);

        env.clear();
        return s;
    }


    @Override
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
        boolean query = !smodel.isFieldMasked("criteria"+"_"+QbTablePopDialog_BoncLink.selectNO, col.getSqlName());
        boolean sum = !smodel.isFieldMasked("add"+"_"+QbTablePopDialog_BoncLink.selectNO, col.getSqlName());
        
        //以下是对group、order和where的判断
        boolean group = smodel.isGroupField("group"+"_"+QbTablePopDialog_BoncLink.selectNO, col.getSqlName());
        boolean order = smodel.isGroupField("order"+"_"+QbTablePopDialog_BoncLink.selectNO, col.getSqlName());
        boolean where = smodel.isGroupField("where"+"_"+QbTablePopDialog_BoncLink.selectNO, col.getSqlName());
        // 
        final StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(query ? "Q_" : ' ');
        sb.append(sum ? "S_" : ' ');
        sb.append(group ? "G_" : ' ');
        sb.append(where ? "W_" : ' ');
        sb.append(order ? "O" : ' ');
        

        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel j = new JLabel(sb.toString());
                // j.setBackground(isSelected ? Color.GREEN : Color.white);                
                return j;
            }
        };
    }

    @Override
    public void fixCompareScript(String srcOutDir) {
        // 代码比对需要做个特别处理：相同的文件，bat命令前面加个bat 跳过比对  并且这个要在所有文件写入完成之后再做。
        PojoSourceCreatorUnit compareUnit = sourceUnits.get("ModifyCompare");
        if (compareUnit == null) {
            return;
        }
        // String batFileName = srcOutDir + File.separator + compareUnit.getFileName();
        String batFileName = srcOutDir + File.separator + compareUnit.getFileName();
//                "E:\\bca\\bin\\flowclasses\\po\\src\\AcdAuser\\ModifyCompare.bat";

        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader fr = new BufferedReader(new FileReader(batFileName));
            while (fr.ready()) {
                String s = fr.readLine();
                boolean b = isFileEquals_atBatLine(s, srcOutDir);
                if (b) {
                    sb.append("rem (equals) ").append(s).append("\r\n");
                } else {
                    sb.append(s).append("\r\n");
                }
            }
            fr.close();
            Filetool.saveStringToTextFile(batFileName, sb.toString());
        } catch (Exception ex) {
            log.error(com.bca.pub.tools.Toolfunc.getCallLocation(ex.getStackTrace()) + ":" + ex.getMessage(), ex);
//            ret.setFailure(ex.getMessage());
//            return;
        }
    }
}
