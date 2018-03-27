package com.bca.toolkit.top.tools.sql.impl.boncLink;

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
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Cf_BoncEpmUIOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.SqlPreview_EpmUI;
import com.bca.toolkit.top.tools.sql.impl.qry.SqlSourceCreator_4_Query.CodeTempIds;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;

public class SqlPreview_BoncLink implements I_SqlCreator{
	private static SqlCreateModel smodel;
	final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final static CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final static Map env = new HashMap();
    final static StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;
    //boncLink 需要联动的图的数目
    private int chartNum;
            
	private List<Map<String, String>> uiColSort;
    private static SqlPreview_BoncLink inst;
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable, List<Map<String, String>> uiColSort){
    	this.smodel = smodel;
        this.pojoAttr = pojoAttr;
        this.dbTable = dbTable;
        this.pojoFields = pojoFields;
        this.uiColSort = uiColSort;
        chartNum = QbTablePopDialog_BoncLink.PicNum;
        boolean numberPackFlag = Numtool.parseBoolean(pojoAttr.getProperty("numberPackFlag"));
	}
	private SqlPreview_BoncLink(){};
	public static SqlPreview_BoncLink getInst(){
		if(inst == null)
			inst = new SqlPreview_BoncLink();
		return inst;
	}
	@SuppressWarnings("unchecked")
	public String createSqlPreview() {
		StringBuilder selectSet = new StringBuilder();
        String s = "", sqlPreview = "";
        int fieldScanIndex = 0;
        
        StringBuilder mapperSql = new StringBuilder();
        StringBuilder sqlSumAddSrc = new StringBuilder();
        StringBuilder tableSelectSet = new StringBuilder();
        StringBuilder tableGroupSet = new StringBuilder();
        StringBuilder tableWhereSet = new StringBuilder();
        StringBuilder tableOrderSet = new StringBuilder();



       
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
       // viewByColFlag = false;
       // env.put("viewByColFlag", viewByColFlag);

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
        /*for (I_WfColumn col : dbTable.getPrimaryKeyColumns()) {
            pkColNameList_Source.append(col.getSqlName()).append(',');
        }
        p.incValue(10);
        Stringtool.reduceLength(pkColNameList_Source, 1);
        env.put("pkFieldName", pkColNameList_Source.toString());
        env.put("pkFieldName_lowerCase", pkColNameList_Source.toString().toLowerCase());*/
        env.put("pkField_vartype", "String");
        
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

      //Env初始化结束
        
        for (int orderNum = 1; orderNum <= chartNum; orderNum++){
            env.put("orderNum", orderNum);
            env.put("orderNumAdd", orderNum + 1);
           
            if(orderNum < 3){
                s = sourceTemplatePool.makeSource(CodeTempIds.boncLink_UTIL, "selectChart" + orderNum, env, true, errInfo);
                mapperSql.append(s); 
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
        sqlPreview = mapperSql.append(s).toString();
        
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
            env.put("pkField_vartype", f.varType);
        }
       
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
        colSW = !smodel.isFieldMasked("bindingSeqCol", f.col.getSqlName());   // 列与seq绑定....
        env.put("bindingSeqCol", colSW);
        if (colSW) {
            env.put("seqBindVarNameCap", env.get("varnameCap"));    // 绑定seq的成员变量名称，变成了首字母大写...
            env.put("bindingSeqOnTable", true);
        }
	}
        
        @Override
        public List<String> projNameList() {
           return  null;
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

}
