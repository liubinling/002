package com.bca.toolkit.top.tools.sql.impl.boncEpmUI;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.table.TableCellRenderer;

import com.bca.api.pub.Ret;
import com.bca.db.DbConst.DbObjType;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_NamedQryElement;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.tools.Numtool;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Timetool;
import com.bca.pub.tools.Stringtool.TargetCase;
import com.bca.studio.BStudioConfigBean;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.toolkit.app.BcaToolkit;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.toolkit.top.tools.sql.I_SqlCreator;
import com.bca.toolkit.top.tools.sql.SqlCreateModel;
import static com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Creator_4_BoncEpmUI.log;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query.CodeTempIds;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import com.bca.toolkit.top.tools.sql.qb.QbRightPanel;
import com.bca.toolkit.top.tools.sql.qb.SQLInputFrm;
import com.bca.tools.log.Log;

public class SqlPreview_EpmUI implements I_SqlCreator{
    private static SqlCreateModel smodel;
    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final static CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final static Map env = new HashMap<String, String>();
    final static StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;
    private List<Map<String, String>> uiColSort;
    private static SqlPreview_EpmUI inst;
    
    private String modelType = new String();
    private String getMetaDataFrom = ""; //获取元数据的方式
    public static String targetString;  //保存抽离出来的SQL预览中间部分
    
    
public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable, List<Map<String, String>> uiColSort){
	this.smodel = smodel;
        this.pojoAttr = pojoAttr;
        this.dbTable = dbTable;
        this.pojoFields = pojoFields;
        this.uiColSort = uiColSort;
        boolean numberPackFlag = Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag"));
        
        initModelState();
        
        if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "ExtendTable";
            env.put("ExtendTable", true);
        }else if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "PagiTable";
            env.put("PagiTable", true);
        }else if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "TableBar";
            env.put("TableBar", true);
        }else if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Bar";
            env.put("Bar", true);
        }else if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Pie";
            env.put("Pie", true);
        }else if("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Line";
            env.put("Line", true);
        }else if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "CRUD";
            env.put("CRUD", true);
        }else if("散点图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Scatter";
            env.put("Scatter", true);
        }else if("雷达图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Radar";
            env.put("Radar", true);
        }else if("仪表盘图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Gauge";
            env.put("Gauge", true);
        }else if("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "BackCRUD";
             env.put("isBackCRUD", true);
        }else{
            modelType = "PagiTable";
            env.put("PagiTable", true);
        }
        
        env.put("modelType", modelType);
        env.put("bindingSeqOnTable", false);
//        chartNum = QbTablePopDialog_BoncEpmUI.PicNum;
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
	
	private SqlPreview_EpmUI(){};
	public static SqlPreview_EpmUI getInst(){
		if(inst == null)
			inst = new SqlPreview_EpmUI();
		return inst;
	}
        
        private void initModelState(){
            env.put("ExtendTable", false);
            env.put("PagiTable", false);
            env.put("Bar", false);
            env.put("Pie", false);
            env.put("TableBar", false);
            env.put("Line", false);
            env.put("CRUD", false);
            env.put("Scatter", false);
            env.put("Radar", false);
            env.put("Gauge", false);
        }
        
         private void initWhereState(){
             env.put("table_where", false);
         }
	@SuppressWarnings("unchecked")
	public String createSqlPreview() {
            //如果显示数据来源的变量为空串，则直接返回空串
            if(getMetaDataFrom.equals(""))
                return "";
            StringBuilder selectSet = new StringBuilder();
            StringBuilder bodySet = new StringBuilder();
            StringBuilder sumSet = new StringBuilder();
            StringBuilder whereSet = new StringBuilder();
            StringBuilder whereSet1 = new StringBuilder();
            StringBuilder stateSet = new StringBuilder();
            StringBuilder formDataSet = new StringBuilder();
            StringBuilder formDataSet1 = new StringBuilder();
            StringBuilder formItemSet = new StringBuilder();
            StringBuilder selectSet2 = new StringBuilder();
            StringBuilder insertSet = new StringBuilder();
            StringBuilder updateSet = new StringBuilder();
            StringBuilder pojoFieldSet = new StringBuilder();
            StringBuilder pojoGetSetMethodSet = new StringBuilder();
            StringBuilder columnTitlePagiSet = new StringBuilder();
            StringBuilder tableTitleSet = new StringBuilder();
            StringBuilder tableMapSet = new StringBuilder();
            StringBuilder pkColNameList_Source = new StringBuilder();
            StringBuilder sqlIfSet = new StringBuilder();
            StringBuilder sqlValuesSet = new StringBuilder();
            StringBuilder sqlIfSetSet = new StringBuilder();
            StringBuilder jsxTrimSet = new StringBuilder(); 
            StringBuilder whereSearchSet = new StringBuilder();
            StringBuilder whereOnlySet = new StringBuilder();
            StringBuilder formItemSearchSet = new StringBuilder();        
            StringBuilder controllerParamSet = new StringBuilder();
            StringBuilder controllerIfSet = new StringBuilder();
            StringBuilder searchFormSet = new StringBuilder();
            StringBuilder paramAppendSet = new StringBuilder();
            StringBuilder formDataSet2 = new StringBuilder();
            StringBuilder beforeSubmitSet = new StringBuilder();
            StringBuilder formItemSet2 = new StringBuilder();        
            StringBuilder columnTitleSet = new StringBuilder();
            StringBuilder columnTitleSet1 = new StringBuilder();
            
                
        String s = "";
        int fieldScanIndex = 0;
        
        //Env初始化（非常重要）
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
        env.put("modelType",modelType);
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
        /*int i = pojoAttr.packageName.indexOf(".");
        String actionHome = i >= 0 ? pojoAttr.packageName.substring(i + 1) : pojoAttr.packageName;
        actionHome += "." + Stringtool.changeLeadingCharCase(pojoAttr.className, TargetCase.LowerCase, 1);
        env.put("actionHome", actionHome);*/
        env.put("jspPath", smodel.getJspPath());    // 
        // 
        env.put("author", BStudioConfigBean.getInst().getCodeFactory_author());
        env.put("orgnization", BStudioConfigBean.getInst().getCodeFactory_orgnization());
        env.put("encoding", BStudioConfigBean.getInst().getCodeFactory_encoding());
        env.put("entityLable", Stringtool.isStringEmpty(pojoAttr.tableComment) ? pojoAttr.tableName : pojoAttr.tableComment);
        env.put("dbObjType", pojoAttr.dbObjType.toString());
       // viewByColFlag = false;
       // env.put("viewByColFlag", viewByColFlag);

        env.put("replaceConfirmSymbol", smodel.isConfirmReplaceAtDos() ? "/-Y" : "");

        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        //env.put("tableName_lowerCase", this.pojoAttr.tableName.toLowerCase());
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
        
        env.put("doller", "$");
        env.put("brace1", "{");
        env.put("brace2", "}");

        env.put("tableRemark", this.pojoAttr.tableComment == null ? "" : pojoAttr.tableComment);
        env.put("createTime", Timetool.getSysTimestamp().toString());
        env.put("projHomeDir", pojoAttr.getProperty("projHomeDir"));
        /*for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
        p.incValue(10);
        Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("pkFieldName", pkColNameList_Source.toString());
        env.put("pkFieldName_lowerCase", pkColNameList_Source.toString().toLowerCase());*/
        env.put("pkField_varType", "String");
        
        env.put("uiSwitch_criteria", true);
        env.put("uiSwitch_add", true);
        env.put("uiSwitch_delete", true);
        env.put("uiSwitch_update", true);
        env.put("uiSwitch_exp", true);
        env.put("uiSwitch_imp", true);
        
        checkUiMasks_var("criteria", "uiSwitch_criteria");
        checkUiMasks_var("add", "uiSwitch_add");
        checkUiMasks_var("delete", "uiSwitch_delete");
        checkUiMasks_var("update", "uiSwitch_update");
        checkUiMasks_var("view", "uiSwitch_view");
        checkUiMasks_var("exp", "uiSwitch_exp");
        checkUiMasks_var("imp", "uiSwitch_imp");
        checkUiMasks_var("print", "uiSwitch_print");
        
         for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
         Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("primaryId", pkColNameList_Source.toString());
        env.put("primaryIdLowerCase", Stringtool.getRecommendName(pkColNameList_Source.toString(), Stringtool.RecommendNameType.VarName));
        env.put("primaryIdCap", Stringtool.changeLeadingCharCase((String)env.get("primaryIdLowerCase"), TargetCase.UpperCase, 1));
        
        env.put("isOracle", false);
        env.put("isMySql", false);
        if (dbTable.getSchema() != null) 
            env.put("isOracle", true);
        else if (dbTable.getTableElement().getCatalog() != null)
            env.put("isMySql", true);
        
        //如果显示数据来源的变量为ManuallyInputSQL，则调用一个空模板，把手输SQL放进去，作为预览SQL
            if(getMetaDataFrom.equals("ManuallyTypeSQL")){
                if(SqlSourceCreator_4_Query.isFirstRefresh){    //如果是第一次生成SQL预览
                env.put("sqlPreview", SQLInputFrm.sqlInputTxt);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "SQL_Preview.xml", env, true, errInfo);
                SqlSourceCreator_4_Query.isFirstRefresh = false;
                if(s == null || s.equals("")){
                    targetString = "";
                    return "";
                }
                //首先截去末尾的</mapper>
                targetString = cutTailMapper(s);
                if(targetString == null || targetString.equals("")){
                    targetString = "";
                    return s;
                }
                //截去完毕
                //调用截去开头<mapper namespace = "……">的方法
                targetString = cutHeadMapper(targetString, targetString.length() - 1);
                return s;
                }
                //如果不是第一次生成SQL预览，而是再次刷新，则需要另外处理
                String rawPreviewData;
                    if(!QbRightPanel.isIsCancel()){  
                        rawPreviewData = QbRightPanel.getSqlText();
                        if(rawPreviewData == null || rawPreviewData.equals(""))
                            return "";
                        //首先截去末尾的</mapper>
                    targetString = cutTailMapper(rawPreviewData);
                    if(targetString == null || targetString.equals(""))
                        return rawPreviewData;
                    //截去完毕
                        //调用截去开头<mapper namespace = "……">的方法
                    targetString = cutHeadMapper(targetString, targetString.length() - 1);
                    if(targetString == null || targetString.equals(""))
                        return rawPreviewData;
                }
                env.put("targetString", targetString);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "SQL_Preview_Refresh.xml", env, true, errInfo);
                return s;
            }
            if("BackCRUD".equals(modelType)){
                env.put("table_where", false);
            env.put("where_only", false);
            env.put("search_form", false);
            env.put("init_query", false);

            for(PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                //字段集合 cllumn_list
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
                s = s.substring(0, s.length() -2);
                selectSet2.append(s);
                
                
                //插入 sql模块
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "sqlIfUnit", env, true, errInfo);
                sqlIfSet.append(s);
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "sqlIfValuesUnit", env, true, errInfo);
                sqlValuesSet.append(s);
                //更新 sql模块
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "sqlIfSetUnit", env, true, errInfo);
                sqlIfSetSet.append(s);
                
                if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName())) {
                    //模糊查询where集合or 初始化where
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "whereSearchUnit", env, true, errInfo);
                    whereSearchSet.append(s);
                }
                
                if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName()) || !smodel.isFieldMasked("init_0", f.col.getSqlName())) {
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "controllerParamUnit", env, true, errInfo);
                    controllerParamSet.append(s);
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "controllerIfUnit", env, true, errInfo);
                    controllerIfSet.append(s);
                }
                           
                if(!smodel.isFieldMasked("kpiDesc_0", f.col.getSqlName())){
                    env.put("where_only",true);
                    //唯一性查询where集合
                    env.put("onlyCap", env.get("varNameCap"));
                    env.put("only", env.get("varName"));
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "whereOnlyUnit", env, true, errInfo);
                    whereOnlySet.append(s);
                }
                if(!smodel.isFieldMasked("desc_0", f.col.getSqlName())){
                    env.put("desc", env.get("fieldName"));
                }
                if(smodel.isGroupField("where_0", f.col.getSqlName())) {
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "whereUnit1", env, true, errInfo);
                    whereSet1.append(s);
                }
                
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "pojoFieldUnit", env, true, errInfo);
                pojoFieldSet.append(s);
                s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "pojoGetSetMethodUnit", env, true, errInfo);
                pojoGetSetMethodSet.append(s); 
//                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
//                selectSet2.append(s);
                
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
            }
                int fieldLabelNum = 0;
                int countNum = 0;
                for(Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                    env.put("formItemUnit","");
                    env.put("jsxTrimUnit","");
                    env.put("formDataUnit","");


                    putEnvAtPojoField(f);  
                    putEStypeEnvAtField(f);
                     env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                     env.put("tr_LineFeed", false);
                     
                     String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                     if (SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                         nodeKey = "_sql_";
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                    
                     
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "formDataUnit2", env, true, errInfo);
                        formDataSet2.append(s);
                    s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "formDataUnit1", env, true, errInfo);
                    formDataSet1.append(s);
                    if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "columnTitleUnit1", env, true, errInfo);
                        columnTitleSet1.append(s);
                    }
                    
                    if (!smodel.isFieldMasked("param_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "formItemUnit2", env, true, errInfo);
                        formItemSet2.append(s);
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "beforeSubmitUnit", env, true, errInfo);
                        beforeSubmitSet.append(s);
                    }
                    
                    if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName())){
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "searchFormUnit", env, true, errInfo);
                        searchFormSet.append(s);
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "paramAppendUnit", env, true, errInfo);
                        paramAppendSet.append(s);
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "formItemSeatchUnit", env, true, errInfo);
                        formItemSearchSet.append(s);
                    }
                    if(!smodel.isFieldMasked("kpiId_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "formItemUnit", env, true, errInfo);
                        formItemSet.append(s);
                        s = sourceTemplatePool.makeSource(Creator_4_BoncEpmUI.CodeTempIds.boncEpmUI_UTIL, "jsxTrimUnit", env, true, errInfo);
                        jsxTrimSet.append(s);
                    }

                }
            }
            }
            if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                initWhereState();
            for(PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "pojoFieldUnit", env, true, errInfo);
                pojoFieldSet.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "pojoGetSetMethodUnit", env, true, errInfo);
                pojoGetSetMethodSet.append(s); 
                
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                selectSet.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
                selectSet2.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "insertUnit", env, true, errInfo);
                insertSet.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "updateUnit", env, true, errInfo);
                updateSet.append(s);
                
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
                
            }
            int fieldLabelNum = 0;
            int countNum = 0;
            for(Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                     putEnvAtPojoField(f);  
                     env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                     env.put("tr_LineFeed", false);
                     env.put("fieldLabel1", this.smodel.sceneModel.getNode((dbTable.getSchema()+"."+this.dbTable.getName()).toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                      s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formDataUnit", env, true, errInfo);
                        formDataSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formDataUnit1", env, true, errInfo);
                        formDataSet1.append(s);
                        if(!env.get("varName").equals(env.get("primaryIdLowerCase"))) {
                            s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formItemUnit", env, true, errInfo);
                            formItemSet.append(s);
                        }
                     if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                        env.put("orderNum",countNum++);
                        env.put("orderNumAdd",countNum);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "columnTitlePagiUnit", env, true, errInfo);
                        columnTitlePagiSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "tableTitleUnit", env, true, errInfo);
                        tableTitleSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "tableMapUnit", env, true, errInfo);
                        tableMapSet.append(s);
                     }
                 }
            }
        }
            
        if("仪表盘图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                initWhereState();
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    selectSet.append(s);
                }
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
            }
            int indexN = 0;
            int fieldLabelNum = 0;
            for (Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                     putEnvAtPojoField(f);  
                     env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                     env.put("tr_LineFeed", false);
                     String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                     if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable"))   //如果是拖动单张表
                             nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                     
                     env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                     if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                     env.put("init_query", true);
                     s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                     stateSet.append(s);
                      if(indexN++ == 0){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s.replace("&",""));
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s);
                    }
                    
                }
                 }  
            }
        }
            
        if("雷达图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                initWhereState();
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    selectSet.append(s);
                }
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
            }
            int indexN = 0;
            int fieldLabelNum = 0;
            for (Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                     putEnvAtPojoField(f);  
                     env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                     env.put("tr_LineFeed", false);
                     String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                     if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable"))   //如果是拖动单张表
                             nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                     
                     env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                     if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                     env.put("init_query", true);
                     s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                     stateSet.append(s);
                      if(indexN++ == 0){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s.replace("&",""));
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s);
                    }
                    
                }
                 }  
            }
        }
        if("散点图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                initWhereState();
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    selectSet.append(s);
                }
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
               
            }
            int indexN = 0;
            int fieldLabelNum = 0;
            for (Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                     putEnvAtPojoField(f);  
                     env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                     env.put("tr_LineFeed", false);
                     env.put("fieldLabel1", this.smodel.sceneModel.getNode((dbTable.getSchema()+"."+this.dbTable.getName()).toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                     if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                     env.put("init_query", true);
                     s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                     stateSet.append(s);
                      if(indexN++ == 0){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s.replace("&",""));
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s);
                    }
                    
                }
                 }  
            }
        }
            if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                initWhereState();
                env.put("table_where", false);
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    
                    if(smodel.isFieldMasked("kpiId_0", f.col.getSqlName()) && smodel.isFieldMasked("parentId_0", f.col.getSqlName())){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                        selectSet.append(s);
                    }
               
                }
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                if (!smodel.isFieldMasked("kpiId_0", f.col.getSqlName())) { // kpiId_0  没有屏蔽，则输出
                   env.put("kpiId",f.col.getSqlName());
                }
                if (!smodel.isFieldMasked("parentId_0", f.col.getSqlName())) { // parentId_0  没有屏蔽，则输出
                    env.put("parentId",f.col.getSqlName());
                }
                
                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

            }
        }
        
            if("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                initWhereState();
                for (PojoField f : pojoFields.values()) {
                    fieldScanIndex++;
                    boolean isLastField = fieldScanIndex == pojoFields.size();
                    putEnvAtPojoField(f);
                    putEStypeEnvAtField(f);

                    if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                        selectSet.append(s);
                    }
                    if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                        env.put("table_where", true);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                        whereSet.append(s);
                    }
                    boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                    env.put("douHao", isLastField ? "" : ",");
            }
            int indexN = 0;
            for (Map<String, String> name : uiColSort) {
                 fieldScanIndex++;
                
                 for (Map.Entry<String, String> colName : name.entrySet()) {
                     PojoField f = pojoFields.get(colName.getKey());
                     if (f == null) {
                         log.error("PojoField 未找到: %s, 跳过该列!", colName.getKey());
                         continue;
                     }
                     putEnvAtPojoField(f);  
                     env.put("fieldLabel", colName.getValue() == null ? "" : colName.getValue());
                     env.put("tr_LineFeed", false);
                     
                     if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                     env.put("init_query", true);
                     s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                     stateSet.append(s);
                      if(indexN++ == 0){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s.replace("&",""));
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s);
                    }
                    
                }
                 }  
            }
        }
         if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                initWhereState();
            int chartIndex;
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

                
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) 
                { // select  没有屏蔽，则输出
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    /*chartIndex = s.indexOf(",");
                    s = s.substring(0, chartIndex+1);*/
                    selectSet.append(s);
                }
                if (smodel.isGroupField("where_0", f.col.getSqlName())) {
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                
               
            }
         }
          if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                initWhereState();
            
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
               
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    selectSet.append(s);
                }
                
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
                
                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

                
            }
            
            int indexN = 0;
            int fieldLabelNum = 0;
            for (Map<String, String> name : uiColSort) {
                fieldScanIndex++;

                for(Map.Entry<String, String> colName : name.entrySet()){
                PojoField f = pojoFields.get(colName.getKey());
                if (f == null) {
                    log.error("PojoField 未找到：%s, 跳过该列!", colName.getKey());
                    continue;
                }
                putEnvAtPojoField(f);
                env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue()); 
                env.put("tr_LineFeed", false);
                env.put("fieldLabel1", this.smodel.sceneModel.getNode((dbTable.getSchema()+"."+this.dbTable.getName()).toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                
                
                if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                   env.put("init_query", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                    s = s.substring(0, s.length() - 1);
                    bodySet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                    s = s.substring(0, s.length() - 1);
                    stateSet.append(s);
                    
                }
                
                
                }
            }
            
            
        }
          
        if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                initWhereState();
            int chartIndex;
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "sumUnit", env, true, errInfo);
                    sumSet.append(s);
                    
                }
              
            }
            
            int countNum = 0;
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
                }
            }
           
            
        }
        
        if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            initWhereState();
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
               
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit", env, true, errInfo);
                    selectSet.append(s);
                }
                
                if (smodel.isGroupField("where_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit", env, true, errInfo);
                    whereSet.append(s);
                }
              
            }
            
            int indexN = 0;
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
                
                
                if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) {          // select  没有屏蔽，则输出
                    if(indexN++ == 0){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s.replace("&",""));
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                        s = s.substring(0, s.length() - 1);
                        bodySet.append(s);
                    }
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                    stateSet.append(s);
                    
                }            
                }
            }
          
        }
        
        //boncEpmUI
        Stringtool.removeLastChars(selectSet, '\r', '\n', ',', ' ');
        env.put("selectSet", selectSet.toString());
        Stringtool.removeLastChars(sumSet, '\r', '\n', ',', ' ');
        env.put("sumSet", sumSet.toString());
        Stringtool.removeLastChars(whereSet, '\r', '\n', ',', ' ');
        if(whereSet.length()> 3){
            String whereSetStr = whereSet.toString().substring(0, whereSet.length() - 5);
            env.put("whereSet", whereSetStr);
        }
        Stringtool.removeLastChars(bodySet, '\r', '\n', ',', ' ');
        env.put("bodySet", bodySet.toString());
        Stringtool.removeLastChars(stateSet, '\r', '\n', ',', ' ');
        env.put("stateSet", stateSet.toString());
        Stringtool.removeLastChars(formDataSet, '\r', '\n', ',', ' ');
        env.put("formDataSet", formDataSet.toString());
        Stringtool.removeLastChars(formDataSet1, '\r', '\n', ',', ' ');
        env.put("formDataSet1", formDataSet1.toString());
        Stringtool.removeLastChars(formItemSet, '\r', '\n', ',', ' ');
        env.put("formItemSet", formItemSet.toString());
        Stringtool.removeLastChars(updateSet, '\r', '\n', ',', ' ');
        env.put("updateSet", updateSet.toString());
        Stringtool.removeLastChars(insertSet, '\r', '\n', ',', ' ');
        env.put("insertSet", insertSet.toString());
        Stringtool.removeLastChars(formItemSearchSet, '\r', '\n', ',', ' ');
        env.put("formItemSearchSet", formItemSearchSet.toString());
        Stringtool.removeLastChars(formDataSet2, '\r', '\n', ',', ' ');
        env.put("formDataSet2", formDataSet2.toString());
        Stringtool.removeLastChars(controllerParamSet, '\r', '\n', ',', ' ');
        env.put("controllerParamSet", controllerParamSet.toString());
        Stringtool.removeLastChars(controllerIfSet, '\r', '\n', ',', ' ');
        env.put("controllerIfSet", controllerIfSet.toString());
        Stringtool.removeLastChars(whereSearchSet, '\r', '\n', ',', ' ');
        env.put("whereSearchSet", whereSearchSet.toString());
        Stringtool.removeLastChars(selectSet2, '\r', '\n', ',', ' ');
        env.put("selectSet2", selectSet2.toString());
        Stringtool.removeLastChars(sqlIfSet, '\r', '\n', ',', ' ');
        env.put("sqlIfSet", sqlIfSet.toString());
        Stringtool.removeLastChars(sqlIfSetSet, '\r', '\n', ',', ' ');
        env.put("sqlIfSetSet", sqlIfSetSet.toString());
        Stringtool.removeLastChars(sqlValuesSet, '\r', '\n', ',', ' ');
        env.put("sqlValuesSet", sqlValuesSet.toString());
        Stringtool.removeLastChars(searchFormSet, '\r', '\n', ',', ' ');
        env.put("searchFormSet", searchFormSet.toString());
        Stringtool.removeLastChars(paramAppendSet, '\r', '\n', ',', ' ');
        env.put("paramAppendSet", paramAppendSet.toString());
        Stringtool.removeLastChars(jsxTrimSet, '\r', '\n', ',', ' ');
        env.put("jsxTrimSet", jsxTrimSet.toString());
        Stringtool.removeLastChars(beforeSubmitSet, '\r', '\n', ',', ' ');
        env.put("beforeSubmitSet", beforeSubmitSet.toString());
        Stringtool.removeLastChars(formItemSet2, '\r', '\n', ',', ' ');
        env.put("formItemSet2", formItemSet2.toString());
        if(whereOnlySet.length()> 3){
            String whereOnlySetStr = whereOnlySet.toString().substring(0, whereOnlySet.length() - 4);
        env.put("whereOnlySet", whereOnlySetStr);
        }
        env.put("whereSet1", whereSet1.toString());
        
        env.put("pojoFieldSet", pojoFieldSet.toString());
        env.put("pojoGetSetMethodSet", pojoGetSetMethodSet.toString());

        String sqlPreview = "";
   
        //后端增删改查表格
        if("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
           sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDMapper.xml", env, true, errInfo);
        }
        //树状表格
        if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
           sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "ExtendTableMapper.xml", env, true, errInfo);
        }
        
        //分页表格
        if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){

        	sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "PagiTableMapper.xml", env, true, errInfo);
            
        }  
        
        if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
              sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "PieMapper.xml", env, true, errInfo);
        }  
        
        if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
              sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BarMapper.xml", env, true, errInfo);
        } 
        
        if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
              sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "TableBarMapper.xml", env, true, errInfo);
        }
        if ("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "LineMapper.xml", env, true, errInfo);
        }
        if("散点图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "ScatterMapper.xml", env, true, errInfo);
        }
        if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "CRUDMapper.xml", env, true, errInfo);
        }
        if("雷达图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "RadarMapper.xml", env, true, errInfo);
        }
        if("仪表盘图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            sqlPreview = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "RadarMapper.xml", env, true, errInfo);
        }
        
        return sqlPreview;	
	}
	private static void putEStypeEnvAtField(PojoField f) {
		// TODO Auto-generated method stub
		
	}

	private static void putEnvAtPojoField(PojoField f) {
            if (f.varName == null || f.varName.length() == 0) {
             return;
            }
       
            env.put("isFieldPK", f.col.isPk());
            if (f.col.isPk()) {
                env.put("pkField_varType", f.varType);
            }

            env.put("varName", f.varName);
            env.put("varType", f.varType);
            env.put("scope", f.scope);
            env.put("fieldName", f.col.getSqlName());
            env.put("fieldName_lowerCase", f.col.getSqlName().toLowerCase());
            env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
            env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
            env.put("length", f.col.getSize());
            env.put("jdbcType", f.col.getSqlTypeName());
            //env.put("bindingSeqOnTable", false);
            //env.put("fieldLabel", f.col.getColComment() == null ? "" : f.col.getColComment());   // 字段备注。暂用字段名称。
            // 
            boolean colSW = !smodel.isFieldMasked("verify", f.col.getSqlName());
            env.put("verifyCol", colSW);
            
            colSW = !smodel.isFieldMasked("bindingSeqCol_0", f.col.getSqlName());   // 列与seq绑定....
            env.put("bindingSeqCol", colSW);
            if (colSW) {
                env.put("seqBindVarName", env.get("varName"));
                env.put("seqBindFieldName", env.get("fieldName"));
                env.put("seqBindVarNameCap", env.get("varNameCap"));    // 绑定seq的成员变量名称，变成了首字母大写...
                env.put("bindingSeqOnTable", true);
            }

            colSW = !smodel.isFieldMasked("key_0", f.col.getSqlName());   // 列与key绑定....
            env.put("bindingKey", colSW);
            if (colSW) {
                env.put("keyBindVarName", env.get("varName"));
                env.put("keyBindFieldName", env.get("fieldName"));
                env.put("keyBindVarNameCap", env.get("varNameCap"));    // 绑定key的成员变量名称，变成了首字母大写...
                env.put("bindingKeyOnTable", true);
            }

            colSW = !smodel.isFieldMasked("value_0", f.col.getSqlName());   // 列与value绑定....
            env.put("bindingValue", colSW);
            if (colSW) {
                env.put("valueBindVarName", env.get("varName"));
                env.put("valueBindFieldName", env.get("fieldName"));
                env.put("valueBindVarNameCap", env.get("varNameCap"));    // 绑定value的成员变量名称，变成了首字母大写...
                env.put("bindingValueOnTable", true);
            }

            colSW = !smodel.isFieldMasked("item_0", f.col.getSqlName());   // 列与item绑定....
            env.put("bindingItem", colSW);
            if (colSW) {
                env.put("itemBindVarName", env.get("varName"));
                env.put("itemBindFieldName", env.get("fieldName"));
                env.put("itemBindVarNameCap", env.get("varNameCap"));    // 绑定item的成员变量名称，变成了首字母大写...
                env.put("bindingItemOnTable", true);
            }
            
	}
        
        @Override
        public List<String> projNameList(){
            return null;
        }
	@Override
	public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Ret createAllPojoSource() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setSModel(SqlCreateModel smodel) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String getSaveDir(String homeDir) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createSelectSQL(TreeMap<Integer, QbOutputFieldUnit> outputFields) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getSubPaths() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getColBatchDlgClass() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void fixCompareScript(String srcOutDir) {
		// TODO Auto-generated method stub
		
	}
         //设置表示元数据获取方式的变量的值
        public void setMetaDataFrom(String getMetaDataFrom){
            this.getMetaDataFrom = getMetaDataFrom;
        }
        //获得表示元数据获取方式的变量值
        public String getMetaDataFrom(){
            return getMetaDataFrom;
        }
//截去开头<mapper namespace = "……">的方法
    private String cutHeadMapper(String sourceString, int beginIndex) {
        sourceString = sourceString.trim();
        int mapperIndex = sourceString.lastIndexOf("mapper", beginIndex);
        if(mapperIndex == -1)
            return "";
        int nspIndex = sourceString.lastIndexOf("namespace", beginIndex);
        if(nspIndex == -1)
            return "";
        int headIndex_01 = sourceString.indexOf(">", mapperIndex);
        int headIndex_02 = sourceString.indexOf(">", nspIndex);
        int headIndex_03;   //左侧小于号所在索引
        String targetLine = "";     //储存<mapper namespace=""/>这一行用于进一步验证
        if(headIndex_01 == headIndex_02){
            headIndex_03 = sourceString.lastIndexOf("<", mapperIndex);
            targetLine = sourceString.substring(headIndex_03, headIndex_01 + 1);
            if(targetLine.matches("^<\\s*mapper\\s*namespace\\s*=\\s*\".*\"\\s*>$")){
                //<mapper namespace="com.bonc.micro.mapper.666.ScatterMapper">
                targetString = sourceString.substring(++headIndex_01, sourceString.length());
                //除去两端空白，非常重要，否则框架和手输SQL之间的空白区域会越累积越大
                targetString = targetString.trim();
                return targetString;
            }
            cutHeadMapper(sourceString, mapperIndex);
        }else
            cutHeadMapper(sourceString, headIndex_01 > headIndex_02 ? headIndex_02 : headIndex_01);
        return targetString;
    
    }
//首先截去末尾的</mapper>
    private String cutTailMapper(String sourceString) {
        sourceString = sourceString.trim();   //去除两端空格，非常重要！！！
        int tailMapper = sourceString.lastIndexOf("mapper");
        if(tailMapper == -1)
            return "";
        tailMapper = sourceString.lastIndexOf("<", tailMapper);
        if(tailMapper == -1)
            return "";
        String tailString = sourceString.substring(tailMapper, sourceString.length());
        if(tailString.matches("^<\\s*\\/\\s*mapper\\s*>$")){
            sourceString = sourceString.substring(0, tailMapper);
        }else{
            sourceString = "";
        }
        return sourceString;
    }
    
        private void checkUiMasks_var(String uiModuleName, String tvarName) {
        env.put(tvarName, smodel.isFieldMasked("ui", uiModuleName) ? false : true);
    }
        
        

}
