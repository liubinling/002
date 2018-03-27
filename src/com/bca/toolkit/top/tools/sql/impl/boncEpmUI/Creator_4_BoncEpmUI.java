/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.boncEpmUI;

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
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiMemBean.UiMemMapMode;
import com.bca.toolkit.top.tools.sql.impl.util.w.WebUiTemplUnit;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import com.bca.toolkit.top.tools.sql.qb.QbSceneModel;
import com.bca.toolkit.top.tools.sql.qb.QbTableBean;
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
 * @author pxz
 */
public class Creator_4_BoncEpmUI implements I_SqlCreator {

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
        return new String[]{"jsp", "sql"};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_BoncEpmUI.class.getName();
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

        public final static String boncEpmUI_FRAME = "boncEpmUI.Frame";
        public final static String boncEpmUI_UTIL = "boncEpmUI.util";  

    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public Creator_4_BoncEpmUI() {
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
        boolean databaseChange = Numtool.parseBoolean(pojoAttr.getProperty("databaseChange"));
        
        env.clear();
        env.put("databaseChange",databaseChange);
        initModelState();
        sourceUnits.clear();
        projNameList.clear();
        
        if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "ExtendTable";
            env.put("isExtendTable", true);
        }else if("分页表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "PagiTable";
            env.put("isPagiTable", true);
        }else if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "TableBar";
            env.put("isTableBar", true);
        }else if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Bar";
            env.put("isBar", true);
        }else if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
            modelType = "Pie";
            env.put("isPie", true);
        }else if("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Line";
            env.put("isLine", true);
        }else if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "CRUD";
            env.put("isCRUD", true);
        }else if("散点图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Scatter";
            env.put("isScatter", true);
        }else if("雷达图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Radar";
            env.put("isRadar", true);
        }else if("仪表盘图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "Gauge";
            env.put("isGauge", true);
        }else if("后端分页增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            modelType = "BackCRUD";
             env.put("isBackCRUD", true);
        }else{
            modelType = "PagiTable";
            env.put("isPagiTable", true);
        }
        env.put("modelType", modelType);
        
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
        String childPath = pojoAttr.getProperty("childFolder").replace('.', '\\');
        String fatherPath =  pojoAttr.getProperty("fatherFolder").replace('.', '\\');
        String projNameJxsPrefix, projNameMapperPrefix;
        if(!modelType.equals("ExtendTable")){
             projNameJxsPrefix = pojoAttr.getProperty("projHomeDir") + "\\src\\main\\frontend\\views\\" + fatherPath;

             projNameMapperPrefix =  pojoAttr.getProperty("projHomeDir") + "\\src\\main\\resources\\mybatisMapper\\" + fatherPath;
        }else{
             projNameJxsPrefix = pojoAttr.getProperty("projHomeDir") + "\\src\\main\\frontend\\views\\" + fatherPath+File.separator+childPath;

             projNameMapperPrefix =  pojoAttr.getProperty("projHomeDir") + "\\src\\main\\resources\\mybatisMapper\\" + fatherPath+File.separator+childPath;
        }
        String projNameJavaPrefix = pojoAttr.getProperty("projHomeDir") + "\\src\\main\\java\\" + pojoAttr.getProperty("pkgHome").replace('.', '\\');
        String projNameMybatiesPrefix =  pojoAttr.getProperty("projHomeDir") + "\\src\\main\\resources";
        String ss =  pojoAttr.className + modelType;
        createPojoSourceCreatorUnit(String.format("%s.jsx", modelType), String.format("%s.jsx", ss), pojoAttr.getProperty("codeCharset"),projNameJxsPrefix);
        projNameList.add(projNameJxsPrefix +  File.separator + String.format("%s.jsx", ss));
        //createPojoSourceCreatorUnit(String.format("%sController.java", modelType), String.format("%sController.java", ss), pojoAttr.getProperty("codeCharset"));
        //createPojoSourceCreatorUnit(String.format("%sService.java", modelType), String.format("%sService.java", ss), pojoAttr.getProperty("codeCharset"));
        //createPojoSourceCreatorUnit(String.format("%sServiceImpl.java", modelType), String.format("%sServiceImpl.java", ss), pojoAttr.getProperty("codeCharset"));
        //createPojoSourceCreatorUnit(String.format("%sMapper.java",  modelType),String.format("%sMapper.java", ss), pojoAttr.getProperty("codeCharset"));
        createPojoSourceCreatorUnit(String.format("%sMapper.xml",  modelType),String.format("%sMapper.xml", ss), pojoAttr.getProperty("codeCharset"),projNameMapperPrefix);
        projNameList.add(projNameMapperPrefix +  File.separator + String.format("%sMapper.xml",  ss));
        createPojoSourceCreatorUnit("mybatisXml", "mybatis-config.xml", pojoAttr.getProperty("codeCharset"),projNameMybatiesPrefix);
        projNameList.add(projNameMybatiesPrefix +  File.separator + "mybatis-config.xml");
        createPojoSourceCreatorUnit("url.txt", "url.bat", pojoAttr.getProperty("codeCharset"),null);
        String controllerPath, mapperPath;
        if(!modelType.equals("ExtendTable")){
            controllerPath = projNameJavaPrefix +  File.separator + "controller" + File.separator + fatherPath;
            mapperPath = projNameJavaPrefix  +  File.separator + "mapper" + File.separator + fatherPath;
        }
        else{
            controllerPath = projNameJavaPrefix +  File.separator + "controller" + File.separator + fatherPath+File.separator+childPath;
            mapperPath = projNameJavaPrefix  +  File.separator + "mapper" + File.separator + fatherPath+File.separator+childPath;
        }
        String servicePath = projNameJavaPrefix +  File.separator + "service";
        String serviceImplPath = projNameJavaPrefix  +  File.separator + "service\\impl";

        
        String pagerPath = projNameJavaPrefix +  File.separator + "pojo";
        String pojoPath = projNameJavaPrefix +  File.separator+ "pojo" + File.separator + fatherPath;
        projNameList.add(controllerPath+ File.separator + String.format("%sController.java", ss));
        projNameList.add(servicePath + File.separator + String.format("%sService.java", ss));
        projNameList.add(serviceImplPath + File.separator + String.format("%sServiceImpl.java", ss));
        projNameList.add(mapperPath + File.separator + String.format("%sMapper.java", ss));
                        
        if (modelType == "BackCRUD") {
            createPojoSourceCreatorUnit("Pager","Pager.java", pojoAttr.getProperty("codeCharset"),pagerPath);
            projNameList.add(pagerPath + File.separator + "Pager.java");
            createPojoSourceCreatorUnit("PojoFrame", String.format("%s.java", pojoAttr.className), pojoAttr.getProperty("codeCharset"),pojoPath);
            projNameList.add(pojoPath + File.separator + String.format("%s.java", pojoAttr.className));
            createPojoSourceCreatorUnit("BackCRUDController.java", String.format("%sController.java", ss), pojoAttr.getProperty("codeCharset"),controllerPath);
            createPojoSourceCreatorUnit("BackCRUDService.java", String.format("%sService.java", ss), pojoAttr.getProperty("codeCharset"),servicePath);
            createPojoSourceCreatorUnit("BackCRUDServiceImpl.java", String.format("%sServiceImpl.java", ss), pojoAttr.getProperty("codeCharset"),serviceImplPath);
            createPojoSourceCreatorUnit("BackCRUDMapper.java", String.format("%sMapper.java", ss), pojoAttr.getProperty("codeCharset"),mapperPath);
            if (!smodel.isFieldMasked("ui", "add")) {
                createPojoSourceCreatorUnit("BackCRUDAdd.jsx", String.format("%sAdd.jsx", ss), pojoAttr.getProperty("codeCharset"),projNameJxsPrefix);
                projNameList.add(projNameJxsPrefix +  File.separator + String.format("%sAdd.jsx", ss));
            }
            if (!smodel.isFieldMasked("ui", "update")) {
                createPojoSourceCreatorUnit("BackCRUDUpdate.jsx", String.format("%sUpdate.jsx", ss), pojoAttr.getProperty("codeCharset"),projNameJxsPrefix);
                projNameList.add(projNameJxsPrefix +  File.separator + String.format("%sUpdate.jsx",  ss));
            }
        
        } else if (modelType == "CRUD") {
            createPojoSourceCreatorUnit("Pager", String.format("Pager.java", ss), pojoAttr.getProperty("codeCharset"),pagerPath);
            projNameList.add(pagerPath + File.separator + "Pager.java");
            createPojoSourceCreatorUnit("PojoFrame", String.format("%s.java", ss), pojoAttr.getProperty("codeCharset"),pojoPath);
            projNameList.add(pojoPath + File.separator + String.format("%s.java", pojoAttr.className));
            createPojoSourceCreatorUnit("CRUDController.java", String.format("%sController.java", ss), pojoAttr.getProperty("codeCharset"),controllerPath);
            createPojoSourceCreatorUnit("CRUDService.java", String.format("%sService.java", ss), pojoAttr.getProperty("codeCharset"),servicePath);
            createPojoSourceCreatorUnit("CRUDServiceImpl.java", String.format("%sServiceImpl.java", ss), pojoAttr.getProperty("codeCharset"),serviceImplPath);
            createPojoSourceCreatorUnit("CRUDMapper.java", String.format("%sMapper.java", ss), pojoAttr.getProperty("codeCharset"),mapperPath);
        }else {
                createPojoSourceCreatorUnit("Controller.java", String.format("%sController.java", ss), pojoAttr.getProperty("codeCharset"),controllerPath);
                createPojoSourceCreatorUnit("Service.java", String.format("%sService.java", ss), pojoAttr.getProperty("codeCharset"),servicePath);
                createPojoSourceCreatorUnit("ServiceImpl.java", String.format("%sServiceImpl.java", ss), pojoAttr.getProperty("codeCharset"),serviceImplPath);
                createPojoSourceCreatorUnit("Mapper.java", String.format("%sMapper.java", ss), pojoAttr.getProperty("codeCharset"),mapperPath);
                }
        createPojoSourceCreatorUnit("createProj.bat", "createProj.bat", pojoAttr.getProperty("dosScriptCharset"),null);
        createPojoSourceCreatorUnit("ModifyCompare.bat", "ModifyCompare.bat", pojoAttr.getProperty("dosScriptCharset"),null);
        createPojoSourceCreatorUnit("deleteProj.bat", "deleteProj.bat", pojoAttr.getProperty("dosScriptCharset"),null);
    }

    private void createPojoSourceCreatorUnit(String creatorId, String fileName, String charset, String targetPath) {
        PojoSourceCreatorUnit srcUnit = new PojoSourceCreatorUnit(creatorId);
        srcUnit.setFileName(fileName);
        srcUnit.setTargetPath(targetPath);
        srcUnit.setCharset(charset);
        sourceUnits.put(srcUnit.getCreatorId(), srcUnit);
    }

    private void initModelState(){
        env.put("isExtendTable", false);
        env.put("isPagiTable", false);
        env.put("isBar", false);
        env.put("isPie", false);
        env.put("isTableBar", false);
        env.put("isLine", false);
        env.put("isCRUD", false);
        env.put("isScatter", false);
        env.put("isRadar", false);
        env.put("isGauge", false);
        env.put("isBackCRUD", false);
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
        
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%s.jsx",modelType), true, errInfo) ? 0 : 1;
        //absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%sController.java",modelType), true, errInfo) ? 0 : 1;     
        //absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%sMapper.java",modelType), true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%sMapper.xml",modelType), true, errInfo) ? 0 : 1;
        //absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%sService.java",modelType), true, errInfo) ? 0 : 1;
        //absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, String.format("%sServiceImpl.java",modelType), true, errInfo) ? 0 : 1;
       
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "Controller.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "Service.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "ServiceImpl.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "Mapper.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "PojoFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "Pager", true, errInfo) ? 0 : 1;
        
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUDController.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUDService.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUDServiceImpl.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUDMapper.xml", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUDMapper.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "CRUD.jsx", true, errInfo) ? 0 : 1;
        
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDController.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDService.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDServiceImpl.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDMapper.xml", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDMapper.java", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUD.jsx", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDAdd.jsx", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BackCRUDUpdate.jsx", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_UTIL, String.format("sumUnit",modelType), true, errInfo) ? 0 : 1;
    

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
//            if (isVarNumeberType(f.varType)) {
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
        env.put("varNameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
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
    
    //获取mybatis配置xml文件内容
    private String getMybatisXmlStr(){
        StringBuilder sb = new StringBuilder();
        
        //String FileName="/Users/eveliu/Documents/workspace/microservice-ui/src/main/resources/mybatis-config.xml";
        String FileName=pojoAttr.getProperty("projHomeDir") + "\\src\\main\\resources\\mybatis-config.xml";
        
      //  String FileName=pojoAttr.getProperty("pkgHome").replace('.', '\\') + "/src/main/resources/mybatis-config.xml";
        File myFile=new File(FileName);
        if(!myFile.exists())
        { 
            System.err.println("Can't Find " + FileName);
        }

        try 
        {
            
            //BufferedReader in = new BufferedReader(new FileReader(myFile));
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile),"utf-8"));
            String str = "";
            while ((str = in.readLine()) != null) 
            {
                  System.out.println(str);
                  sb.append(str);
                  sb.append("\r\n");
            }
            in.close();
        } 
        catch (IOException e) 
        {
            e.getStackTrace();
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
        // 
        
       
        PojoSourceCreatorUnit jsxUnit = sourceUnits.get(String.format("%s.jsx",modelType));
        //PojoSourceCreatorUnit controllerSrcUnit = sourceUnits.get(String.format("%sController.java",modelType));
        //PojoSourceCreatorUnit  mapperSrcUnit = sourceUnits.get(String.format("%sMapper.java",modelType));
        PojoSourceCreatorUnit mapperXmlUnit = sourceUnits.get(String.format("%sMapper.xml",modelType));
        //PojoSourceCreatorUnit serviceSrcUnit = sourceUnits.get(String.format("%sService.java",modelType));
        //PojoSourceCreatorUnit serviceSrcImplUnit = sourceUnits.get(String.format("%sServiceImpl.java",modelType));
        PojoSourceCreatorUnit mybatisXmlUnit = sourceUnits.get("mybatisXml");
        PojoSourceCreatorUnit urlTxtUnit = sourceUnits.get("url.txt");
        PojoSourceCreatorUnit pagerUnit = null;
        PojoSourceCreatorUnit pojoUnit = null;
        PojoSourceCreatorUnit controllerUnit = null;
        PojoSourceCreatorUnit serviceUnit = null;
        PojoSourceCreatorUnit serviceImplUnit = null;
        PojoSourceCreatorUnit mapperUnit = null;
        PojoSourceCreatorUnit jsxAddUnit = null;
        PojoSourceCreatorUnit jsxUpdateUnit = null;
        if(modelType=="BackCRUD") {
            pagerUnit = sourceUnits.get("Pager");
            pojoUnit = sourceUnits.get("PojoFrame");
            controllerUnit = sourceUnits.get("BackCRUDController.java");
            serviceUnit = sourceUnits.get("BackCRUDService.java");
            serviceImplUnit = sourceUnits.get("BackCRUDServiceImpl.java");
            mapperUnit = sourceUnits.get("BackCRUDMapper.java");
            if (!smodel.isFieldMasked("ui", "add")) {
                jsxAddUnit = sourceUnits.get("BackCRUDAdd.jsx");
            }
            if (!smodel.isFieldMasked("ui", "update")) {
                jsxUpdateUnit = sourceUnits.get("BackCRUDUpdate.jsx");
            }
        }else if(modelType=="CRUD") {
            pojoUnit = sourceUnits.get("PojoFrame");
            controllerUnit = sourceUnits.get("CRUDController.java");
            serviceUnit = sourceUnits.get("CRUDService.java");
            serviceImplUnit = sourceUnits.get("CRUDServiceImpl.java");
            mapperUnit = sourceUnits.get("CRUDMapper.java");
        }else {
            controllerUnit = sourceUnits.get("Controller.java");
            serviceUnit = sourceUnits.get("Service.java");
            serviceImplUnit = sourceUnits.get("ServiceImpl.java");
            mapperUnit = sourceUnits.get("Mapper.java");
        }
        PojoSourceCreatorUnit batSrcUnit = sourceUnits.get("createProj.bat");
        PojoSourceCreatorUnit ModifyCompareSrcUnit = sourceUnits.get("ModifyCompare.bat");
        PojoSourceCreatorUnit deleteBatSrcUnit = sourceUnits.get("deleteProj.bat");
        
        
        assert jsxUnit != null;
        //assert controllerSrcUnit != null;
        //assert mapperSrcUnit != null;
        assert mapperXmlUnit != null;
        //assert serviceSrcUnit != null;
       // assert serviceSrcImplUnit != null;
        
        assert controllerUnit != null;
        assert serviceUnit != null;
        assert serviceImplUnit != null;
        assert mapperUnit != null;
        assert pojoUnit != null;
        assert pagerUnit != null;
        assert jsxAddUnit != null;
        assert jsxUpdateUnit != null;
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
        
        //boncEpmUI
        StringBuilder bodySet = new StringBuilder();
        StringBuilder stateSet = new StringBuilder();
        StringBuilder selectSet = new StringBuilder();
        StringBuilder whereSet = new StringBuilder();
        StringBuilder columnSet = new StringBuilder();
        StringBuilder columnTitleSet = new StringBuilder();
        StringBuilder columnTitleSet1 = new StringBuilder();
        StringBuilder columnTitlePagiSet = new StringBuilder();
        StringBuilder tableTitleSet = new StringBuilder();
        StringBuilder tableMapSet = new StringBuilder();
        StringBuilder sumSet = new StringBuilder();
        StringBuilder linkTableMapSet = new StringBuilder();
        StringBuilder linkColumnSet = new StringBuilder();
        StringBuilder paramSet  = new StringBuilder();
        
        //CRUD新增
        StringBuilder sqlIfSet = new StringBuilder();
        StringBuilder sqlValuesSet = new StringBuilder();
        StringBuilder sqlIfSetSet = new StringBuilder();
        StringBuilder jsxTrimSet = new StringBuilder(); 
        StringBuilder whereSearchSet = new StringBuilder();
        StringBuilder whereOnlySet = new StringBuilder();
        StringBuilder formDataSet = new StringBuilder();
        StringBuilder formDataSet1 = new StringBuilder();
        StringBuilder formItemSet = new StringBuilder();
        StringBuilder formItemSearchSet = new StringBuilder();
        StringBuilder selectSet2 = new StringBuilder();
        StringBuilder insertSet = new StringBuilder();
        StringBuilder updateSet = new StringBuilder();
        StringBuilder pojoFieldSet = new StringBuilder();
        StringBuilder pojoGetSetMethodSet = new StringBuilder();
        
        StringBuilder controllerParamSet = new StringBuilder();
        StringBuilder controllerIfSet = new StringBuilder();
        StringBuilder searchFormSet = new StringBuilder();
        StringBuilder paramAppendSet = new StringBuilder();
        StringBuilder formDataSet2 = new StringBuilder();
        StringBuilder beforeSubmitSet = new StringBuilder();
        StringBuilder formItemSet2 = new StringBuilder();
        StringBuilder whereSet1 = new StringBuilder();
        StringBuilder letSet = new StringBuilder();
        StringBuilder bodySet1 = new StringBuilder();

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
        env.put("primaryIdCap", Stringtool.changeLeadingCharCase((String)env.get("primaryIdLowerCase"), TargetCase.UpperCase, 1));
        
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
        int fieldScanIndex = 0;
        // expSetHeadSrc.append(pojoAttr.tableComment).append(',');
        
        //后端分页增删改查模块
        if(modelType.equals("BackCRUD")) {
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
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
                s = s.substring(0, s.length() -2);
                selectSet2.append(s);
                
                
                //插入 sql模块
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "sqlIfUnit", env, true, errInfo);
                sqlIfSet.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "sqlIfValuesUnit", env, true, errInfo);
                sqlValuesSet.append(s);
                //更新 sql模块
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "sqlIfSetUnit", env, true, errInfo);
                sqlIfSetSet.append(s);
                
                if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName())) {
                    //模糊查询where集合or 初始化where
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereSearchUnit", env, true, errInfo);
                    whereSearchSet.append(s);
                }
                
                if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName()) || !smodel.isFieldMasked("init_0", f.col.getSqlName())) {
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "controllerParamUnit", env, true, errInfo);
                    controllerParamSet.append(s);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "controllerIfUnit", env, true, errInfo);
                    controllerIfSet.append(s);
                }
                           
                if(!smodel.isFieldMasked("kpiDesc_0", f.col.getSqlName())){
                    env.put("where_only",true);
                    //唯一性查询where集合
                    env.put("onlyCap", env.get("varNameCap"));
                    env.put("only", env.get("varName"));
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereOnlyUnit", env, true, errInfo);
                    whereOnlySet.append(s);
                }
                if(!smodel.isFieldMasked("desc_0", f.col.getSqlName())){
                    env.put("desc", env.get("fieldName"));
                }
                if(smodel.isGroupField("where_0", f.col.getSqlName())) {
                    env.put("table_where", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "whereUnit1", env, true, errInfo);
                    whereSet1.append(s);
                }
                
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "pojoFieldUnit", env, true, errInfo);
                pojoFieldSet.append(s);
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "pojoGetSetMethodUnit", env, true, errInfo);
                pojoGetSetMethodSet.append(s); 
//                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
//                selectSet2.append(s);
                
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
                p.incValue(1);
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
                     
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formDataUnit2", env, true, errInfo);
                        formDataSet2.append(s);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formDataUnit1", env, true, errInfo);
                    formDataSet1.append(s);
                    if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "columnTitleUnit1", env, true, errInfo);
                        columnTitleSet1.append(s);
                    }
                    
                    if (!smodel.isFieldMasked("param_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formItemUnit2", env, true, errInfo);
                        formItemSet2.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "beforeSubmitUnit", env, true, errInfo);
                        beforeSubmitSet.append(s);
                    }
                    
                    if(!smodel.isFieldMasked("parentId_0", f.col.getSqlName())){
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "searchFormUnit", env, true, errInfo);
                        searchFormSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "paramAppendUnit", env, true, errInfo);
                        paramAppendSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formItemSeatchUnit", env, true, errInfo);
                        formItemSearchSet.append(s);
                    }
                    if(!smodel.isFieldMasked("kpiId_0", f.col.getSqlName())) {
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "formItemUnit", env, true, errInfo);
                        formItemSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "jsxTrimUnit", env, true, errInfo);
                        jsxTrimSet.append(s);
                    }
                    
                    if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                        env.put("init_query", true);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                        stateSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "letUnit", env, true, errInfo);
                        letSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit1", env, true, errInfo);
                        bodySet1.append(s);
                    }

                }
            }
        }
        
        
        if("增删改查表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
            env.put("table_where", false);
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
                s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "updateUnit", env, true, errInfo);
                updateSet.append(s);
                if(!env.get("varName").equals(env.get("primaryIdLowerCase"))) {
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "insertUnit", env, true, errInfo);
                    insertSet.append(s);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "selectUnit2", env, true, errInfo);
                    selectSet2.append(s);
                }
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");
                
                p.incValue(1);
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
                     
                     String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                     if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
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
                env.put("table_where", false);
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
                
                p.incValue(1);
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
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
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
                env.put("table_where", false);
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
                
                p.incValue(1);
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
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
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
                env.put("table_where", false);
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
                
                p.incValue(1);
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
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
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
        
        if("线型图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())) {
                env.put("table_where", false);
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
                
                p.incValue(1);
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
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
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
        
        if("树状表格".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                env.put("table_where", false);
            int accountNum1 = 0;
            int accountNum2 = 0;
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
                    accountNum1++;
                    env.put("kpiId",f.col.getSqlName());
                }
                if (!smodel.isFieldMasked("parentId_0", f.col.getSqlName())) { // parentId_0  没有屏蔽，则输出
                    accountNum2++;
                    env.put("parentId",f.col.getSqlName());
                }
                
                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

                p.incValue(1);
            
            }
            if (accountNum1 != 1 || accountNum2 != 1) {
                Wftool.messageDialog(false, "只允许有且只有1个kpiId和parentId");
                return null;
            }
            int colunmNum = 0;
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
                env.put("fieldLabel", colName.getValue().equals("") ? colName.getKey() : colName.getValue());  
                env.put("tr_LineFeed", false);
                
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    colunmNum++;
                    if( !smodel.isFieldMasked("kpiId_0", f.col.getSqlName())){
                         env.put("fieldName", "kpiId"); 
                    }
                    if( !smodel.isFieldMasked("parentId_0", f.col.getSqlName())){
                         env.put("fieldName", "parentId"); 
                    }
                    if(colunmNum == 1){
                        env.put("dataIndex",f.col.getSqlName());
                        env.put("dataIndexLable",colName.getValue() == null ? colName.getKey() : colName.getValue());
                        if( !smodel.isFieldMasked("kpiId_0", f.col.getSqlName())){ 
                            env.put("dataIndex","kpiId");
                        }
                        if( !smodel.isFieldMasked("parentId_0", f.col.getSqlName())){ 
                            env.put("dataIndex","parentId");
                        }
                    }else{
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "columnUnit", env, true, errInfo);
                        columnSet.append(s);
                        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "columnTitleUnit", env, true, errInfo);
                        columnTitleSet.append(s);
                    }
                    
                }
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
                env.put("table_where", false);
            int chartIndex;
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

                p.incValue(1);
                
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
            
            
            int countNum = 0;
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
                String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName()) ) { // select  没有屏蔽，则输出
                    env.put("orderNum",countNum++);
                    env.put("orderNumAdd",countNum);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "columnTitlePagiUnit", env, true, errInfo);
                    columnTitlePagiSet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "tableTitleUnit", env, true, errInfo);
                    //解决拼接字符串换行问题
                    chartIndex = s.indexOf(",");
                    s = s.substring(0, chartIndex+1);
                    tableTitleSet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "tableMapUnit", env, true, errInfo);
                    chartIndex = s.indexOf(",");
                    s = s.substring(0, chartIndex+1);
                    tableMapSet.append(s);
                   
                }
                if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) {
                    env.put("init_query", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                    stateSet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                    s = s.substring(0, s.length() - 1);
                    bodySet.append(s);

                }
                if(!smodel.isFieldMasked("desc_0", f.col.getSqlName())){
                    env.put("desc", env.get("fieldLabel"));
                }

                }
            }
            env.put("tableSize",countNum);
            
        }
        
        
        if("普通表格联动柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                env.put("table_where", false);
            
            for (PojoField f : pojoFields.values()) {
                fieldScanIndex++;
                boolean isLastField = fieldScanIndex == pojoFields.size();
                putEnvAtPojoField(f);
                putEStypeEnvAtField(f);

//                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
//                
//                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "sumUnit", env, true, errInfo);
//                    sumSet.append(s);
//                    
//                }
                
                // 存储过程：add
                boolean bindingSeqCol = (Boolean) env.get("bindingSeqCol");
                env.put("douHao", isLastField ? "" : ",");

                p.incValue(1);
                
            }
            
            int countNum = 1;
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
               String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
               if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("init_query", true);
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "bodyUnit", env, true, errInfo);
                    s = s.substring(0, s.length() - 1);
                    bodySet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "stateUnit", env, true, errInfo);
                    stateSet.append(s);
                    
                }
                if (!smodel.isFieldMasked("kpiId_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    
                   env.put("kpiName",f.col.getSqlName());
                }
                if (!smodel.isFieldMasked("kpiDesc_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    
                   env.put("descLable", colName.getValue() == null ? "" : colName.getValue());
                }
                if (!smodel.isFieldMasked("select_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("columnNum",countNum++);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "tableTitleUnit", env, true, errInfo);
                    tableTitleSet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "linkTableMap", env, true, errInfo);
                    linkTableMapSet.append(s);
                    
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "linkColumnUnit", env, true, errInfo);
                    linkColumnSet.append(s);
                }
                
                if (!smodel.isFieldMasked("param_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "paramUnit", env, true, errInfo);
                    paramSet.append(s);
                    
                }
                
                }
            }
           
            
        }
        
        if("柱图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                env.put("table_where", false);
           
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

                p.incValue(1);
                
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
                String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                if (!smodel.isFieldMasked("init_0", f.col.getSqlName())) { // select  没有屏蔽，则输出
                    env.put("init_query", true);
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
        
        if("饼图".equals(Cf_BoncEpmUIOptionDialog.getModelCategory())){
                env.put("table_where", false);
            
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

                p.incValue(1);
                
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
               String nodeKey = "";  //临时变量，用于储存SQL画板上的节点对应的key值
                     if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("ManuallyTypeSQL"))  //如果是手输SQL
                             nodeKey = "_sql_";
                     else if(SqlSourceCreator_4_Query.getGetMetaDataFrom().equals("DragTable")) {  //如果是拖动单张表
                    if (dbTable.getSchema() != null) {
                        nodeKey = dbTable.getSchema()+"."+this.dbTable.getName();
                    } else {
                        nodeKey = dbTable.getTableElement().getCatalog() + "." + this.dbTable.getName();
                    }
                }
               env.put("fieldLabel1", this.smodel.sceneModel.getNode(nodeKey.toLowerCase()).getTableModel().getFieldLabel().get(fieldLabelNum++).get(f.col.getSqlName()));
                     
                
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
//         
//        
//       log.debug("--estypeTableLevelSrc_map--.size:%d", estypeTableLevelSrc_map.size());
        for (Map.Entry<String, StringBuffer> e : estypeTableLevelSrc_map.entrySet()) {
            log.debug("--estypeTableLevelSrc_map--:k=<%s>,c=<%s>", e.getKey(), e.getValue());
            env.put(e.getKey(), e.getValue().toString());
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

//        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "pkFillBySeq", env, true, errInfo);
//        p.incValue(10);
//        s = s == null ? "" : Stringtool.removeTrailingChars(s, '\n', '\r');
//
//        env.put("pkFillBySeqText", s);
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
        
        
        //boncEpmUI
        Stringtool.removeLastChars(formDataSet, '\r', '\n', ',', ' ');
        env.put("formDataSet", formDataSet.toString());
        Stringtool.removeLastChars(formDataSet1, '\r', '\n', ',', ' ');
        env.put("formDataSet1", formDataSet1.toString());
        Stringtool.removeLastChars(formItemSet, '\r', '\n', ',', ' ');
        env.put("formItemSet", formItemSet.toString());
        Stringtool.removeLastChars(formItemSearchSet, '\r', '\n', ',', ' ');
        env.put("formItemSearchSet", formItemSearchSet.toString());
        Stringtool.removeLastChars(updateSet, '\r', '\n', ',', ' ');
        env.put("updateSet", updateSet.toString());
        Stringtool.removeLastChars(selectSet2, '\r', '\n', ',', ' ');
        env.put("selectSet2", selectSet2.toString());
        Stringtool.removeLastChars(insertSet, '\r', '\n', ',', ' ');
        env.put("insertSet", insertSet.toString());
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
        
        Stringtool.removeLastChars(sqlIfSet, '\r', '\n', ',', ' ');
        env.put("sqlIfSet", sqlIfSet.toString());
        Stringtool.removeLastChars(sqlValuesSet, '\r', '\n', ',', ' ');
        env.put("sqlValuesSet", sqlValuesSet.toString());
        Stringtool.removeLastChars(sqlIfSetSet, '\r', '\n', ',', ' ');
        env.put("sqlIfSetSet", sqlIfSetSet.toString());
        Stringtool.removeLastChars(whereSearchSet, '\r', '\n', ',', ' ');
        env.put("whereSearchSet", whereSearchSet.toString());
        if(whereOnlySet.length()> 3){
            String whereOnlySetStr = whereOnlySet.toString().substring(0, whereOnlySet.length() - 4);
            env.put("whereOnlySet", whereOnlySetStr);
        }
        
        env.put("pojoFieldSet", pojoFieldSet.toString());
        env.put("pojoGetSetMethodSet", pojoGetSetMethodSet.toString());
        env.put("stateSet", stateSet.toString());
        Stringtool.removeLastChars(bodySet, '\r', '\n', ',', ' ','+');
        env.put("bodySet", bodySet.toString());
        env.put("bodySet1", bodySet1.toString());
        Stringtool.removeLastChars(letSet, '\r', '\n', ',', ' ','+');
        env.put("letSet", letSet.toString());
        env.put("whereSet1", whereSet1.toString());
        if(whereSet.length()> 3){
            String whereSetStr = whereSet.toString().substring(0, whereSet.length() - 5);
        env.put("whereSet", whereSetStr);
        }
        Stringtool.removeLastChars(selectSet, '\r', '\n', ',', ' ');
        env.put("selectSet", selectSet.toString());
        Stringtool.removeLastChars(columnSet, '\r', '\n', ',', ' ');
        env.put("columnSet", columnSet.toString());
        env.put("columnTitleSet", columnTitleSet.toString());
        env.put("columnTitleSet1", columnTitleSet1.toString());
        env.put("columnTitlePagiSet", columnTitlePagiSet.toString());
        Stringtool.removeLastChars(tableTitleSet, '\r', '\n', ',', ' ');
        env.put("tableTitleSet", tableTitleSet.toString());
       
        env.put("linkColumnSet", linkColumnSet.toString());
        Stringtool.removeLastChars(linkTableMapSet, '\r', '\n', ',', ' ');
        env.put("linkTableMapSet", linkTableMapSet.toString());
       
        env.put("paramSet", paramSet.toString());
        Stringtool.removeLastChars(tableMapSet, '\r', '\n', ',', ' ');
        env.put("tableMapSet", tableMapSet.toString());
        Stringtool.removeLastChars(sumSet, '\r', '\n', ',', ' ');
        env.put("sumSet", sumSet.toString());
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
        
        //数据库切换代码片段
        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "databaseChangeFront", env, true, errInfo);
        env.put("databaseChangeFront",s);
        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "databaseChangeBack", env, true, errInfo);
        env.put("databaseChangeBack",s);


        //mybatisXml 文件内容注入
        String str = new String();
        str = getMybatisXmlStr();
        s = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_UTIL, "mybatiesUnit", env, true, errInfo);
        //判断mybaties-config是否已经包含目标配置
        int chartIndex = s.indexOf("/>");
        String s1 = s.substring(0, chartIndex+2);
        boolean bl = str.contains(s1);
        if(!bl){
             str = str.replaceAll("<!-- insert mapperInfo here -->", s);
        }
       
        mybatisXmlUnit.setCode(str);
        urlTxtUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "url.txt", env, true, errInfo));
        jsxUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%s.jsx",modelType), env, true, errInfo));
        //controllerSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%sController.java",modelType), env, true, errInfo));
       // mapperSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%sMapper.java",modelType), env, true, errInfo));
        if(smodel.sqlPreview == null || smodel.sqlPreview == ""){
            mapperXmlUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%sMapper.xml",modelType), env, true, errInfo));
         
            }else{
                mapperXmlUnit.setCode(smodel.sqlPreview);
            }
        //serviceSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%sService.java",modelType), env, true, errInfo));
        //serviceSrcImplUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, String.format("%sServiceImpl.java",modelType), env, true, errInfo));


        batSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "createProj.bat", env, true, errInfo));
        p.incValue(10);
        deleteBatSrcUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "deleteProj.bat", env, true, errInfo));
        p.incValue(10);
        String compareBat = sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "ModifyCompare.bat", env, true, errInfo);
        ModifyCompareSrcUnit.setCode(compareBat);
        
        p.incValue(10);
        
        if(modelType == "BackCRUD") {
            pagerUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "Pager", env, true, errInfo));
            pojoUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "PojoFrame", env, true, errInfo));
            controllerUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDController.java", env, true, errInfo)); 
            serviceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDService.java", env, true, errInfo));
            serviceImplUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDServiceImpl.java", env, true, errInfo));
            mapperUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDMapper.java", env, true, errInfo));
            if (!smodel.isFieldMasked("ui", "add")) {
                jsxAddUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDAdd.jsx", env, true, errInfo));
            }
            if (!smodel.isFieldMasked("ui", "update")) {
                jsxUpdateUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "BackCRUDUpdate.jsx", env, true, errInfo));
            }
        } else if(modelType == "CRUD") {
            pojoUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "PojoFrame", env, true, errInfo));
            controllerUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "CRUDController.java", env, true, errInfo)); 
            serviceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "CRUDService.java", env, true, errInfo));
            serviceImplUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "CRUDServiceImpl.java", env, true, errInfo));
            mapperUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "CRUDMapper.java", env, true, errInfo));
        } else {
            controllerUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "Controller.java", env, true, errInfo)); 
            serviceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "Service.java", env, true, errInfo));
            serviceImplUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "ServiceImpl.java", env, true, errInfo));
            mapperUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.boncEpmUI_FRAME, "Mapper.java", env, true, errInfo));
        }
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
