/*
 * PojoSourceCreator_4_Hibernate.java
 *
 * Created on 2007年8月3日, 下午9:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm.creator;

import com.bca.api.pub.Ret;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.tools.Stringtool;
import com.bca.pub.tools.Stringtool.TargetCase;
import com.bca.pub.tools.Timetool;
import com.bca.studio.BStudioConfigBean;
import com.bca.toolkit.top.tools.orm.I_PojoSourceCreator;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.toolkit.app.BcaToolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pxz
 */
public class PojoSourceCreator_4_iRapid implements I_PojoSourceCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;

    @Override
    public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
        return sourceUnits;
    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String IRAPID_FRAME = "irapid.orm.Frame";
        public final static String IRAPID_FIELD = "irapid.orm.Field";
//        public final static String HBM_FRAME = "ETL.FRAME";
//        public final static String ETL_ITEM = "ETL.ITEM";
//        public final static String ETL_SQL = "ETL.SQL";
//        public final static String ETL_FUNC = "ETL.FUNC";
    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public PojoSourceCreator_4_iRapid() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();

    }
    String charsetName = "GBK";

    @Override
    public void setCharset(String charsetName) {
        if (!Stringtool.isStringEmpty(charsetName)) {
            this.charsetName = charsetName;
        }
    }

    @Override
    public void init(String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable) {
        this.alias = alias;
        this.pojoAttr = pojoAttr;
        this.pojoFields = pojoFields;
        this.dbTable = dbTable;

        PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
        pojoSourceUnit.setFileName(String.format("%s.java", pojoAttr.className));

        PojoSourceCreatorUnit mapSourceUnit = new PojoSourceCreatorUnit("map");
        mapSourceUnit.setFileName(String.format("%s.Mapper.xml", pojoAttr.className));

        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
        sourceUnits.put(mapSourceUnit.getCreatorId(), mapSourceUnit);
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

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FRAME, "PojoFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FRAME, "MapFrame", true, errInfo) ? 0 : 1;

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "DeclareField", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "hashCode", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "equals", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "toString", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "setOperator", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "toStringFunction", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "getOperator", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "isOperator", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.IRAPID_FIELD, "pkMapString", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.IRAPID_FRAME, getClass(), "sj");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.IRAPID_FIELD, getClass(), "sj");

        if (absentContents == 0) {
            return Ret.getSuccessRet();
        }
//        String s = String.format("缺少模板，无法生成ETL模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
//        JOptionPane.showMessageDialog(dwws.app.getMainFrame(), s, "错误信息", JOptionPane.ERROR_MESSAGE);
        return Ret.getFailureRet("缺少模板，无法生成ORM模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
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
        s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "namedQueryByAlllDeclare", env, true, errInfo);
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

            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "DeclareField", env, true, errInfo);
            fieldDeclareSource.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "namedQueryDeclareField", env, true, errInfo);
            namedQueryDeclareSource.append(s);
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "setOperator", env, true, errInfo));
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "getOperator", env, true, errInfo));

            // irapid...
            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "insert_FieldName", env, true, errInfo);
            insert_FieldList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "insert_ValueExpr", env, true, errInfo);
            insert_ValuesList.append(s);

            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "update_Expr", env, true, errInfo);
            update_Body.append(s);
            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "select_Field", env, true, errInfo);
            select_FieldList.append(s);
            // ...

            if (f.col.isPk()) {
                pkConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
                pkConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

                // <key-property name="${varname}" type="${vartype}" column="${colname}" length="${length}"/>
                pkHbmXml.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "pkMapString", env, true, errInfo));

                //
                s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "pkCriteriaItem", env, true, errInfo);
                pkCriteria.append(s);

                s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "pkVarItem", env, true, errInfo);
                pkVarsList.append(s);
            }
            s = sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "fieldMapString", env, true, errInfo);
            fieldsHbmXml.append(s);

            wholeConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
            wholeConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

            if (f.inToString) {
                toStringAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "toString", env, true, errInfo));
            }
            if (f.inEquals) {
                equalsAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "equals", env, true, errInfo));
            }
            if (f.inHashCode) {
                hashAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FIELD, "hashCode", env, true, errInfo));
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

        pojoSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FRAME, "PojoFrame", env, true, errInfo));

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

        mapSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.IRAPID_FRAME, "MapFrame", env, true, errInfo));

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
}
