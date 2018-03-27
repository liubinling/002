/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.impl.qry;

import com.bca.api.pub.Ret;
import com.bca.db.meta.I_WfColumn;
import com.bca.db.meta.unit.Meta_Table;
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
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Cf_BoncEpmUIOptionDialog;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.Creator_4_BoncEpmUI.CodeTempIds;
import com.bca.toolkit.top.tools.sql.impl.boncEpmUI.SqlPreview_EpmUI;
import com.bca.toolkit.top.tools.sql.impl.boncLink.SqlPreview_BoncLink;
import com.bca.toolkit.top.tools.sql.qb.QbOutputFieldUnit;
import java.awt.Component;
import java.io.File;
import java.util.HashMap;
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
public class SqlSourceCreator_4_Query implements I_SqlCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;
    SqlCreateModel smodel;
    String frameId;
    private List<Map<String, String>> uiColSort;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;
    private static String getMetaDataFrom = "";    //获取元数据的方式：表格/手输SQL
    public static boolean isFirstRefresh = false;   //标识是否创建出表格后首次生成SQL预览

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

        public final static String iSQL_FRAME = "isql.builder.Frame";
        public final static String iSQL_FIELD = "isql.builder.Field";
        
        public final static String boncEpmUI_FRAME = "boncEpmUI.Frame";
        public final static String boncEpmUI_UTIL = "boncEpmUI.util"; 
        
        public final static String boncLink_FRAME = "boncLink.Frame";
        public final static String boncLink_UTIL = "boncLink.util";
//        public final static String HBM_FRAME = "ETL.FRAME";
//        public final static String ETL_ITEM = "ETL.ITEM";
//        public final static String ETL_SQL = "ETL.SQL";
//        public final static String ETL_FUNC = "ETL.FUNC";
    }

    /** Creates a new instance of PojoSourceCreator_4_Hibernate */
    public SqlSourceCreator_4_Query() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();

    }

    @Override
    public void init(SqlCreateModel smodel, String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable, List<Map<String, String>> uiColSort) {
        this.alias = alias;
        this.pojoAttr = pojoAttr;
        this.pojoFields = pojoFields;
        this.dbTable = dbTable;
        this.smodel = smodel;
        this.uiColSort = uiColSort;
        frameId = (String)this.smodel.getSqlBuilderPanel().getPojoFactoryBox().getSelectedItem();
        //初始化pojoFields
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
            
            pojoFields.put(f.col.getSqlName(), f);
        }
        /*PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
        pojoSourceUnit.setFileName(String.format("%s.java", pojoAttr.className));

        PojoSourceCreatorUnit mapSourceUnit = new PojoSourceCreatorUnit("map");
        mapSourceUnit.setFileName(String.format("%s.Mapper.xml", pojoAttr.className));

        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
        sourceUnits.put(mapSourceUnit.getCreatorId(), mapSourceUnit);*/
    }

    @Override
    public Ret createAllPojoSource() {
//    public Ret createAllPojoSource(PojoSourceCreatorUnit pojoSourceUnit, PojoSourceCreatorUnit mapSourceUnit) {
//        public Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource) {
        if (sourceUnits == null) {
            return Ret.getFailureRet("sourceUnits is null");
        }
        PojoSourceCreatorUnit pojoSourceUnit = sourceUnits.get("pojo");
        PojoSourceCreatorUnit mapSourceUnit = sourceUnits.get("map");

        assert pojoSourceUnit != null;
        assert mapSourceUnit != null;

        Ret r0 = checkAllEtlCodeTemplatesExist();
        if (r0.isRetSuccess()) {
            return make(pojoSourceUnit, mapSourceUnit);
        }
        return r0;
//        ret.copyFrom(r0);
//        return null;

    }

    private Ret checkAllEtlCodeTemplatesExist() {
        int absentContents = 0;
        errInfo.setLength(0);

        /*absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FRAME, "select", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FRAME, "update", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FRAME, "delete", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FRAME, "insert", true, errInfo) ? 0 : 1;

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FIELD, "q_field", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.iSQL_FIELD, "q_tables", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.iSQL_FRAME, getClass(), "sj");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.iSQL_FIELD, getClass(), "sj");*/
      //检出模版是否存在，若不存在则在log打出
        //树状表格
        if(frameId.equals("BONCEpmUI")){
	        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "ExtendTableMapper.xml", true, errInfo) ? 0 : 1;
	        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "PagiTableMapper.xml", true, errInfo) ? 0 : 1;
	        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_UTIL, "selectUnit", true, errInfo) ? 0 : 1;
                absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "PieMapper.xml", true, errInfo) ? 0 : 1;
                absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "BarMapper.xml", true, errInfo) ? 0 : 1;
                absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "TableLinkBarMapper.xml", true, errInfo) ? 0 : 1;
                absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncEpmUI_FRAME, "LineMapper.xml", true, errInfo) ? 0 : 1;
        }
        if(frameId.equals("BONCLink")){
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_FRAME, "Mapper.xml", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "sumUnitItem", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "whereUnit", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "orderUnit", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "groupUnit", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectChart1", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectChart1", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectTable", true, errInfo) ? 0 : 1;
            absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.boncLink_UTIL, "selectUnit", true, errInfo) ? 0 : 1;
        }
        

        if (absentContents == 0) {
            return Ret.getSuccessRet();
        }
//        String s = String.format("缺少模板，无法生成ETL模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
//        JOptionPane.showMessageDialog(dwws.app.getMainFrame(), s, "错误信息", JOptionPane.ERROR_MESSAGE);
        return Ret.getFailureRet("缺少模板，无法生成SQL代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }

    private Ret make(PojoSourceCreatorUnit pojoSourceUnit, PojoSourceCreatorUnit mapSourceUnit) {
        StringBuffer fieldDeclareSource = new StringBuffer();
//        com.bca.templ.po.TlTemplateTextReg@1c047f0[templDomainId=1024,templId=1234,contentId=9,syntaxTypeId=0,contentName=namedQueryDeclareField,contentTxtName=namedQueryDeclareField,syntaxTagEnable=false,helpInfo=]
        StringBuffer namedQueryDeclareByAllSource = new StringBuffer();
        StringBuffer namedQueryDeclareSource = new StringBuffer();
        StringBuffer varsOperatorSource = new StringBuffer();

        StringBuffer pkConstructor_para_Source = new StringBuffer();
        StringBuffer pkConstructor_body_Source = new StringBuffer();
        StringBuffer wholeConstructor_para_Source = new StringBuffer();
        StringBuffer wholeConstructor_body_Source = new StringBuffer();

        StringBuffer toStringAppendSource = new StringBuffer();
        StringBuffer equalsAppendSource = new StringBuffer();
        StringBuffer hashAppendSource = new StringBuffer();

        StringBuffer pkHbmXml = new StringBuffer();
        StringBuffer fieldsHbmXml = new StringBuffer();

        // irapid...
        StringBuffer insert_FieldList = new StringBuffer();
        StringBuffer insert_ValuesList = new StringBuffer();

        StringBuffer update_Body = new StringBuffer();
        StringBuffer select_FieldList = new StringBuffer();

        StringBuffer pkCriteria = new StringBuffer();
        StringBuffer pkVarsList = new StringBuffer();

        String s = "";

        env.put("packageName", pojoAttr.packageName);
        env.put("className", pojoAttr.className);
        env.put("author", BStudioConfigBean.getInst().getCodeFactory_author());
        env.put("orgnization", BStudioConfigBean.getInst().getCodeFactory_orgnization());
        env.put("encoding", BStudioConfigBean.getInst().getCodeFactory_encoding());

        //
        s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "namedQueryByAlllDeclare", env, true, errInfo);
        namedQueryDeclareByAllSource.append(s);
        env.put("namedQueryDeclareByAllSource", namedQueryDeclareByAllSource.toString());
        for (PojoField f : pojoFields.values()) {
            if (f.varName == null || f.varName.length() == 0) {
                continue;
            }
            //     ${scope}    ${vartype}  ${varname};		// field ${colname} : ${colDataType}
            env.put("varname", f.varName);
            env.put("vartype", f.varType);
            env.put("scope", f.scope);
            env.put("colname", f.col.getSqlName());
            env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
            env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
            env.put("length", f.col.getSize());
            env.put("jdbcType", f.col.getSqlTypeName());
            env.put("colRemark", f.col.getColComment() == null ? "" : f.col.getColComment());   // 字段备注。暂用字段名称。

            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "DeclareField", env, true, errInfo);
            fieldDeclareSource.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "namedQueryDeclareField", env, true, errInfo);
            namedQueryDeclareSource.append(s);
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "setOperator", env, true, errInfo));
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "getOperator", env, true, errInfo));

            // irapid...
            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "insert_FieldName", env, true, errInfo);
            insert_FieldList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "insert_ValueExpr", env, true, errInfo);
            insert_ValuesList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "update_Expr", env, true, errInfo);
            update_Body.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "select_Field", env, true, errInfo);
            select_FieldList.append(s);
            // ...

            if (f.col.isPk()) {
                pkConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
                pkConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

                // <key-property name="${varname}" type="${vartype}" column="${colname}" length="${length}"/>
                pkHbmXml.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "pkMapString", env, true, errInfo));

                //
                s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "pkCriteriaItem", env, true, errInfo);
                pkCriteria.append(s);

                s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "pkVarItem", env, true, errInfo);
                pkVarsList.append(s);
            }
            s = sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "fieldMapString", env, true, errInfo);
            fieldsHbmXml.append(s);

            wholeConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
            wholeConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

            if (f.inToString) {
                toStringAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "toString", env, true, errInfo));
            }
            if (f.inEquals) {
                equalsAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "equals", env, true, errInfo));
            }
            if (f.inHashCode) {
                hashAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FIELD, "hashCode", env, true, errInfo));
            }
        }
        Stringtool.reduceLength(pkConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(pkConstructor_body_Source, "\r\n".length());
        Stringtool.reduceLength(wholeConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(wholeConstructor_body_Source, "\r\n".length());

        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        env.put("tableRemark", this.pojoAttr.tableComment == null ? "" : pojoAttr.tableComment);
        env.put("createTime", Timetool.getSysTimestamp().toString());

        env.put("fieldDeclareSource", fieldDeclareSource.toString());
        //
        Stringtool.trimStringBufferA(namedQueryDeclareSource, (char) 0x0a);
        Stringtool.trimStringBufferA(namedQueryDeclareSource, (char) 0x0d);
        Stringtool.trimStringBufferA(namedQueryDeclareSource, ',');
        env.put("namedQueryDeclareSource", namedQueryDeclareSource.toString());
        //
        env.put("varOperatorsSource", varsOperatorSource.toString());

        env.put("pkConstructor_parameters", pkConstructor_para_Source.toString());
        env.put("pkConstructor_body", pkConstructor_body_Source.toString());
        env.put("wholeConstructor_parameters", wholeConstructor_para_Source.toString());
        env.put("wholeConstructor_body", wholeConstructor_body_Source.toString());

        env.put("toStringAppendString", toStringAppendSource.toString());
        env.put("equalsAppendString", equalsAppendSource.toString());
        env.put("hashAppendString", hashAppendSource.toString());

        pojoSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FRAME, "PojoFrame", env, true, errInfo));

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

        mapSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.iSQL_FRAME, "MapFrame", env, true, errInfo));

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
//创建SQL预览的方法
    @Override
    public String createSelectSQL(TreeMap<Integer, QbOutputFieldUnit> outputFields) {
       
        Ret r0 = checkAllEtlCodeTemplatesExist();
       /* if(!r0.isRetSuccess()){
        	return "";
        }*/
        String s = "";
      //boncEpmUI
        //判断框架类型，决定调用哪个框架下的类创建Sql预览内容
        if(frameId.equals("BONCEpmUI")){
        	SqlPreview_EpmUI sqlPreviewCreator = SqlPreview_EpmUI.getInst();
        	sqlPreviewCreator.init(this.smodel, this.alias, this.pojoAttr, this.pojoFields, this.dbTable, this.uiColSort);
                sqlPreviewCreator.setMetaDataFrom(getGetMetaDataFrom());
        	s = sqlPreviewCreator.createSqlPreview();
                smodel.sqlPreview = s;      //将生成的SQL预览内容存入SqlCreateModel中，用于生成代码
        	return s;
        }else if(frameId.equals("BONCLink")){
        	SqlPreview_BoncLink sqlPreviewCreator = SqlPreview_BoncLink.getInst();
        	sqlPreviewCreator.init(this.smodel, this.alias, this.pojoAttr, this.pojoFields, this.dbTable, this.uiColSort);
        	s = sqlPreviewCreator.createSqlPreview();
        	return s;
        }
        return "";
        
    }

    

	@Override
    public String[] getSubPaths() {
        return new String[]{};
    }

    @Override
    public String getColBatchDlgClass() {
        return QbTablePopDialog_SQL.class.getName();
    }

    @Override
    public TableCellRenderer getCellRenderer_sw(I_WfColumn col) {
//        boolean listdev = smodel.isFieldMasked("listdev", col.getSqlName());
//        boolean add = smodel.isFieldMasked("add", col.getSqlName());
//        boolean update = smodel.isFieldMasked("update", col.getSqlName());
//        boolean view = smodel.isFieldMasked("view", col.getSqlName());
//        boolean exp = smodel.isFieldMasked("exp", col.getSqlName());
//        boolean imp = smodel.isFieldMasked("imp", col.getSqlName());
//        // 
//        final StringBuilder sb = new StringBuilder();
//        sb.append(listdev ? 'L' : ' ');
//        sb.append(add ? 'a' : ' ');
//        sb.append(update ? 'u' : ' ');
//        sb.append(view ? 'v' : ' ');
//        sb.append(exp ? 'x' : ' ');
//        sb.append(imp ? 'm' : ' ');
        return new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JLabel("o...");  // sb.toString());
            }
        };
    }

    @Override
    public void setSModel(SqlCreateModel smodel) {
//        this.smodel = smodel;
    }

    /**
     * @return the getMetaDataFrom
     */
    public static String getGetMetaDataFrom() {
        return getMetaDataFrom;
    }

    /**
     * @param aGetMetaDataFrom the getMetaDataFrom to set
     */
    public static void setGetMetaDataFrom(String aGetMetaDataFrom) {
        getMetaDataFrom = aGetMetaDataFrom;
    }
}

