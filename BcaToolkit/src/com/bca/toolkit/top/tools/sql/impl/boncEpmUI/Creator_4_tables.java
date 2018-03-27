/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.boncEpmUI;

import com.bca.api.pub.Ret;
import com.bca.db.DbConst;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_NamedQryElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.cfg.SGlobal;
import com.bca.pub.tools.Filetool;
import com.bca.pub.tools.Numtool;
import com.bca.pub.tools.ProgressbarWindow;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Templtool;
import com.bca.pub.tools.Timetool;
import com.bca.pub.tools.Wftool;
import com.bca.studio.BStudioConfigBean;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.templ.pub.I_StudioConfig;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.toolkit.top.tools.sql.I_SqlCreator;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import com.bca.toolkit.top.tools.sql.impl.gx.PxzHashMap;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiTemplUnit;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import com.bca.toolkit.top.tools.sql.qb.QbSceneModel;
import com.bca.toolkit.top.tools.sql.qb.QbTableBean;
import com.bca.toolkit.top.tools.sql.qb.QbTable_JTableModel;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
 * @author eve
 */
public class Creator_4_tables implements I_SqlCreator{
    
    //主表
    private QbTableBean mainTb;
    //子表
    private QbTableBean logTb;
    //码表
    Map<String,QbTableBean> clockTbs;
    private int clockTbNum = 0;
    
//    public void init(QbSceneModel sceneModel){
//        clockTbs = new HashMap<String, QbTableBean>();
//        for (QbTableBean tb : sceneModel.getNodes().values()) {
//            
//            if(tb.getTableAlias().contains("t")){
//                mainTb = tb;
//            }else if(tb.getTableAlias().contains("l")){
//                logTb = tb;
//            }else{
//                String s = "0" + clockTbNum++;
//                clockTbs.put(s, tb);
//            }
//           
//        }
//    }
    
    
    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final PxzHashMap env = new PxzHashMap();
    final StringBuffer errInfo = new StringBuffer();
//    String alias;
    public SqlCreateModel smodel;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    QbTable_JTableModel jtm;
//    public final List<String> fieldsUiSort = new ArrayList<String>();   // 在界面中的输出顺序。
    Meta_Table dbTable;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;
    private List<Map<String, String>> uiColSort;
    private String modelType = new String();
    //boncEpmUI 需要联动的图的数目
     
    private List<String> projNameList = new ArrayList();
            
    @Override
    public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
        return sourceUnits;
    }

    @Override
    public List<String> projNameList(){
        return projNameList;
    }
    @Override
    public String[] getSubPaths() {
        return new String[]{};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_BoncEpmUI.class.getName();
    }

    @Override
    public void setSModel(SqlCreateModel smodel) {
        this.smodel = smodel;
    }

    @Override
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable, List<Map<String, String>> uiColSort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String boncEpmUI_FRAME = "boncEpmUI.Frame";
        public final static String boncEpmUI_UTIL = "boncEpmUI.util";  

    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public Creator_4_tables() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();
        sourceTemplatePool.logEnvOnSrcCreate = app.cfg.getCfgBean().logEnvOnSrcCreate;
        env.setLogLeading("--cfenv--");
    }

   
    public void init(SqlCreateModel smodel) {
//        this.alias = alias;
        this.smodel = smodel;
//        this.pojoAttr = pojoAttr;
//        this.dbTable = dbTable;
        this.pojoFields = new LinkedHashMap<String, PojoField>();
        boolean numberPackFlag = Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag"));
        boolean databaseChange = Numtool.parseBoolean(pojoAttr.getProperty("databaseChange"));
        
        env.clear();
        env.put("databaseChange",databaseChange);
        sourceUnits.clear();
        projNameList.clear();
        
        clockTbs = new HashMap<String, QbTableBean>();
        for (QbTableBean tb : smodel.sceneModel.getNodes().values()) {
            
            if(tb.getTableAlias().contains("t")){
                mainTb = tb;
            }else if(tb.getTableAlias().contains("l")){
                logTb = tb;
            }else{
                String s = "0" + clockTbNum++;
                clockTbs.put(s, tb);
            }
           
        }
       
        
        
      
        //createPojoSourceCreatorUnit  创建单元？  添加代码
        //createPojoSourceCreatorUnit(String.format("%s.jsx", modelType), String.format("%s.jsx", "ss"), pojoAttr.getProperty("codeCharset"),"qq");
        createPojoSourceCreatorUnit("createProj.bat", "createProj.bat", pojoAttr.getProperty("dosScriptCharset"),null);
        createPojoSourceCreatorUnit("ModifyCompare.bat", "ModifyCompare.bat", pojoAttr.getProperty("dosScriptCharset"),null);
        createPojoSourceCreatorUnit("deleteProj.bat", "deleteProj.bat", pojoAttr.getProperty("dosScriptCharset"),null);
          
       }
    
    //给原来的pojoFields和uiColSort赋值，给各个表用
    private void getPojoFieldAndColSort(QbTableBean tableBean){
        
        pojoFields.clear();
        dbTable = tableBean.getTableDetail();
        jtm = tableBean.getTableModel();
        boolean numberPackFlag = Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag"));
        uiColSort = jtm.getColNames();
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
//            else if(f.varType == "java.sql.Timestamp" || f.varType == "java.sql.Date"){
//                f.varType = "java.lang.String";
//            }
            if (numberPackFlag) {
                f.changeNumberTypeToPackedMode();
            }
            pojoFields.put(f.col.getSqlName(), f);
            
        }    
    }

    private void createPojoSourceCreatorUnit(String creatorId, String fileName, String charset, String targetPath) {
        PojoSourceCreatorUnit srcUnit = new PojoSourceCreatorUnit(creatorId);
        srcUnit.setFileName(fileName);
        srcUnit.setTargetPath(targetPath);
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
        env.put("bindingKeyOnTable", false);
        env.put("bindingValueOnTable", false);
        env.put("bindingItemOnTable", false);
        //
        env.put("hxhkBatRem", smodel.activeFactory.pojoAttr.getProperty("hxhkBatRem"));
        env.put("uiSwitch_criteria", true);
        env.put("uiSwitch_add", true);
        env.put("uiSwitch_delete", true);
        env.put("uiSwitch_update", true);
        env.put("uiSwitch_exp", true);
        env.put("uiSwitch_imp", true);
        
        env.put("table_where", false);
        env.put("init_query", false);
        env.put("where_only",false);
        //解决$消失的问题
        env.put("doller", "$");
        env.put("brace1", "{");
        env.put("brace2", "}");
//        env.put("color0", "###,###,##0");

        env.put("formItemUnit","");
        env.put("jsxTrimUnit","");
        env.put("formDataUnit","");
        
        if (smodel.isFieldMasked("ui", "Info.jsp")) {
            smodel.maskAllFields("criteria");
        }
        checkUiMasks_var("criteria", "uiSwitch_criteria");
        checkUiMasks_var("add", "uiSwitch_add");
        checkUiMasks_var("delete", "uiSwitch_delete");
        checkUiMasks_var("update", "uiSwitch_update");
        checkUiMasks_var("view", "uiSwitch_view");
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
            if (pojoAttr.dbObjType != DbConst.DbObjType.Table) {
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

    private Ret checkAllEtlCodeTemplatesExist() {
        sourceTemplatePool = CodeTemplatePool.getPool("DSS");   // 这样，重装的时候才会生效。

        int absentContents = 0;
        errInfo.setLength(0);
//检出模版是否存在，若不存在则在log打出  补充代码
        
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%s.jsx",modelType), true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_UTIL, String.format("sumUnit",modelType), true, errInfo) ? 0 : 1;
//    

        //增加使用者信息
        sourceTemplatePool.regTemplateUsing(CodeTempIds.boncEpmUI_FRAME, getClass(), "norten");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.boncEpmUI_UTIL, getClass(), "norten");

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
            env.put("pkField_varType", f.varType);
        }
        // 首亨：所有数字，必须是double类型
//            if (isVarNumeberT   ype(f.varType)) {
//                f.varType = "double";
//                env.put("annotateAux", ", colType=SHColumnType.DOUBLE");
//            } else {
//                env.put("annotateAux", "");
//            }
        //     ${scope}    ${varType}  ${varname};		// field ${colname} : ${colDataType}
        env.put("varName", f.varName);
        env.put("varType", f.varType);
        env.put("scope", f.scope);
        env.put("fieldName", f.col.getSqlName());
        env.put("fieldName_lowerCase", f.col.getSqlName().toLowerCase());
        env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
        env.put("varNameCap", Stringtool.changeLeadingCharCase(f.varName, Stringtool.TargetCase.UpperCase, 1));
        env.put("length", f.col.getSize());
        env.put("jdbcType", f.col.getSqlTypeName());
        env.put("fieldLabel", f.col.getColComment().equals("") ? f.col.getSqlName() : f.col.getColComment());   // 字段备注。暂用字段名称。

        // 
        boolean colSW = !smodel.isFieldMasked("verify", f.col.getSqlName());
        env.put("verifyCol", colSW);
        colSW = !smodel.isFieldMasked("viewBindingCol", f.col.getSqlName());
        env.put("viewBindingCol", colSW);
        if (colSW) {
            viewByColFlag |= colSW;
            env.put("viewByColFlag", viewByColFlag);
        }
        colSW = !smodel.isFieldMasked("bindingSeqCol_0", f.col.getSqlName());   // 列与seq绑定....
        env.put("bindingSeqCol", colSW);
        if (colSW) {
            env.put("seqBindVarName", env.get("varName"));
            env.put("seqBindFieldName", env.get("fieldName"));
            env.put("seqBindVarNameCap", env.get("varNameCap"));    // 绑定seq的成员变量名称，变成了首字母大写...
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
        
       //模板内容获取?？  代码添加
        //PojoSourceCreatorUnit jsxUnit = sourceUnits.get(String.format("%s.jsx",modelType));
        
        PojoSourceCreatorUnit batSrcUnit = sourceUnits.get("createProj.bat");
        PojoSourceCreatorUnit ModifyCompareSrcUnit = sourceUnits.get("ModifyCompare.bat");
        PojoSourceCreatorUnit deleteBatSrcUnit = sourceUnits.get("deleteProj.bat");
        //检查模板内容是否存在
        //assert jsxUnit != null;
        assert ModifyCompareSrcUnit != null;
        assert batSrcUnit !=null;
        assert deleteBatSrcUnit != null;

        // 临时的变量指定。 实际上是需要界面选择的。。。
        env.put("order_AscDesc", "DESC");
//        
//        env.put("actionHome", "hxhk.phoneTest");
//        
        env.put("jspPath", "/app/hxhk/tssnt");
        // endof 临时的变量指定。 实际上是需要界面选择的。。。

        // 
        // com.bca.templ.po.TlTemplateTextReg@1c047f0[templDomainId=1024,templId=1234,contentId=9,syntaxTypeId=0,contentName=namedQueryDeclareField,contentTxtName=namedQueryDeclareField,syntaxTagEnable=false,helpInfo=]
        // 
      
        //新建StringBuilder  代码补充

        StringBuffer pkColNameList_Source = new StringBuffer();
        
        
        String s = "";

        env.put("projSrcDir", pojoAttr.getProperty("projSrcDir"));
        env.put("pkgHome", pojoAttr.getProperty("pkgHome"));
        //boncEpm-ui
        env.put("showId", Cf_BoncEpmUIOptionDialog.showId);
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
        env.put("daoPrefixCap", Stringtool.changeLeadingCharCase(pojoAttr.getProperty("daoPrefix"), Stringtool.TargetCase.UpperCase, 1));
        // 如下3个参数,用于sqlplus自动注册模块及授权.
        env.put("dbups", pojoAttr.getProperty("dbups"));
        env.put("busiDBups", pojoAttr.getProperty("busiDBups"));
        env.put("parenetMenuName", pojoAttr.getProperty("parenetMenuName"));
        env.put("authRole", pojoAttr.getProperty("authRole"));
        env.put("pkgHome", pojoAttr.getProperty("pkgHome"));

        env.put("projName", pojoAttr.getProperty("projName"));
        env.put("mainName", pojoAttr.className);
        env.put("objVarName", Stringtool.changeLeadingCharCase(pojoAttr.className, Stringtool.TargetCase.LowerCase, 1));
        int i = pojoAttr.packageName.indexOf(".");
        String actionHome = i >= 0 ? pojoAttr.packageName.substring(i + 1) : pojoAttr.packageName;
        actionHome += "." + Stringtool.changeLeadingCharCase(pojoAttr.className, Stringtool.TargetCase.LowerCase, 1);
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
        if (pojoAttr.dbObjType == DbConst.DbObjType.NamedQry) {
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
        //String a = pojoAttr.getProperty("projHomeDir").substring(0, 1);
        env.put("projHomeDirHead", pojoAttr.getProperty("projHomeDir").substring(0, 1));
        p.incValue(5);

        for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
        p.incValue(10);
        Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("pkFieldName", pkColNameList_Source.toString());
        env.put("pkFieldName_lowerCase", pkColNameList_Source.toString().toLowerCase());
        env.put("pkField_varType", "String");
        
        env.put("primaryId", pkColNameList_Source.toString());
        env.put("primaryIdLowerCase", Stringtool.getRecommendName(pkColNameList_Source.toString(), Stringtool.RecommendNameType.VarName));
        env.put("primaryIdCap", Stringtool.changeLeadingCharCase((String)env.get("primaryIdLowerCase"), Stringtool.TargetCase.UpperCase, 1));
        
        env.put("isOracle", false);
        env.put("isMySql", false);
        if (dbTable.getSchema() != null) 
            env.put("isOracle", true);
        else if (dbTable.getTableElement().getCatalog() != null)
            env.put("isMySql", true);

// env.put("pkColName", pkColNameList_Source.toString());
        //
//        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "namedQueryByAlllDeclare", env, true, errInfo);
//        namedQueryDeclareByAllSource.append(s);
//        env.put("namedQueryDeclareByAllSource", namedQueryDeclareByAllSource.toString());
        firstColName = "";
        // expSetHeadSrc.append(pojoAttr.tableComment).append(',');

        //分别遍历主表，子表，码表获取自己相应的信息
        getPojoFieldAndColSort(mainTb);
        for(PojoField f : pojoFields.values()){
        }
        for(Map<String, String> name : uiColSort) {
        }
        getPojoFieldAndColSort(logTb);
        for(PojoField f : pojoFields.values()){
        }
        for(Map<String, String> name : uiColSort) {
        }
        for(QbTableBean tb : clockTbs.values()){
            getPojoFieldAndColSort(tb);
            for(PojoField f : pojoFields.values()){
            }
            for(Map<String, String> name : uiColSort) {
            }  
        }
        

//        if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
//                env.put("table_where", false);
//            int chartIndex;
//            for (PojoField f : pojoFields.values()) {
//                fieldScanIndex++;
//                boolean isLastField = fieldScanIndex == pojoFields.size();
//                putEnvAtPojoField(f);
//                putEStypeEnvAtField(f);
//
//                // 存储过程：add
//                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
//                env.put("douHao", isLastField ? "" : ",");
//
//                p.incValue(1);
//                
//                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) 
//                { // select  没有屏蔽，则输出
//                    
//                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
//                    /*chartIndex = s.indexOf(",");
//                    s = s.substring(0, chartIndex+1);*/
//                    selectSet.append(s);
//                }
//                if (smodel.isGroupField("where_0", f.col.getSqlName())) {
//                    env.put("table_where", true);
//                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
//                    whereSet.append(s);
//                }
//                
//               
//            }

//         
//        
//       log.debug("--estypeTableLevelSrc_map--.size:%d", estypeTableLevelSrc_map.size());
        for (Map.Entry<String, StringBuffer> e : estypeTableLevelSrc_map.entrySet()) {
            log.debug("--estypeTableLevelSrc_map--:k=<%s>,c=<%s>", e.getKey(), e.getValue());
            env.put(e.getKey(), e.getValue().toString());
        }
     
        
//        Stringtool.reduceLength(toStringAppendSource, 6);
        env.put("fieldName_4_criteria", firstColName);
        
        
       //给env增加参数，通过模板获取的代码碎片  代码添加
       
        env.put("compareTool", cfg.getExternalCompareTool());   // 比较工具
        
        //数据库切换代码片段
        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "databaseChangeFront", env, true, errInfo);
        env.put("databaseChangeFront",s);
        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "databaseChangeBack", env, true, errInfo);
        env.put("databaseChangeBack",s);


       p.incValue(10);
        String compareBat = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "ModifyCompare.bat", env, true, errInfo);
        ModifyCompareSrcUnit.setCode(compareBat);
        batSrcUnit.setCode(sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_FRAME, "createProj.bat", env, true, errInfo));
        p.incValue(10);
        deleteBatSrcUnit.setCode(sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_FRAME, "deleteProj.bat", env, true, errInfo));
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
        if (uiBean.getKvMapMode() == WebUiMemBean.UiMemMapMode.MapByHkTemplate) {
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
        if (pojoAttr.dbObjType == DbConst.DbObjType.Table) {
            env.put("dbObjType", DbConst.DbObjType.View.toString());
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
        return homeDir + File.separator + "src" + File.separator + pojoAttr.className + modelType;
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
            s = sourceTemplatePool.makeSourceA(CodeTempIds.boncEpmUI_UTIL, "q_field", env, true, errInfo);
            fields.append(s);
            //
            Meta_Table mt = u.getJtableModel().getTbean().getTableDetail();
            String tableName = mt.getSqlName();
            if (!dirtyTables.contains(tableName)) {
                env.put("tableName", tableName);
                env.put("tableAlias", u.getJtableModel().getTbean().getTableAlias());
                env.put("tableComment", mt.getTableComment().replace('r', ' ').replace('\n', ' '));
                // 
                s = sourceTemplatePool.makeSourceA(CodeTempIds.boncEpmUI_UTIL, "q_tables", env, true, errInfo);
                tables.append(s);
                dirtyTables.add(tableName);
            }
        }

        env.put("queryFieldsBody", fields.toString());
        env.put("tablesBody", tables.toString());
        s = sourceTemplatePool.makeSourceA(CodeTempIds.boncEpmUI_FRAME, "select", env, true, errInfo);

        env.clear();
        return s;
    }


    @Override
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
        boolean init = !smodel.isFieldMasked("init"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean select = !smodel.isFieldMasked("select"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean kpiId = !smodel.isFieldMasked("kpiId"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean parentId = !smodel.isFieldMasked("parentId"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean param = !smodel.isFieldMasked("param"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean kpiDesc = !smodel.isFieldMasked("kpiDesc"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean key = !smodel.isFieldMasked("key"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean value = !smodel.isFieldMasked("value"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean item = !smodel.isFieldMasked("item"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean bindingSeqCol = !smodel.isFieldMasked("bindingSeqCol"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean desc = !smodel.isFieldMasked("desc"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        
        //以下是对group、order和where的判断
        boolean group = smodel.isGroupField("group"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean order = smodel.isGroupField("order"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        boolean where = smodel.isGroupField("where"+"_"+QbTablePopDialog_BoncEpmUI.selectNO, col.getSqlName());
        
        
        // 
        final StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(init ? "I" : ' ');
        sb.append(select ? "S" : ' ');
        if ("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sb.append(kpiId ? "A" : ' ');
        } else {
            sb.append(kpiId ? "K" : ' ');
        }
        sb.append(parentId ? "P" : ' ');
        sb.append(group ? "G" : ' ');
        sb.append(where ? "W" : ' ');
        sb.append(order ? "O" : ' ');
        if ("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sb.append(param ? "U" : ' ');
        } else {
            sb.append(param ? "A" : ' ');
        }
        sb.append(kpiDesc ? "D" : ' ');
        sb.append(bindingSeqCol ? "Q" : ' ');
        sb.append(desc ? "d" : ' ');
        sb.append(key ? "k" : ' ');
        sb.append(value ? "v" : ' ');
        sb.append(item ? "i" : ' ');
                
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
