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
import com.bca.toolkit.top.tools.orm.I_PojoSourceCreator;
import com.bca.toolkit.top.tools.orm.PojoAttribute;
import com.bca.toolkit.top.tools.orm.PojoField;
import com.bca.toolkit.top.tools.orm.PojoSourceCreatorUnit;
import com.bca.templ.pool.CodeTemplatePool;
import com.bca.toolkit.app.BcaToolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author pxz
 */
public class PojoSourceCreator_4_Hibernate implements I_PojoSourceCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool();
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;
//    final Map<String, String> packedTypeMaps = new TreeMap<String, String>();

    @Override
    public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
        return sourceUnits;
    }
    String charsetName = "GBK";

    @Override
    public void setCharset(String charsetName) {
        if (!Stringtool.isStringEmpty(charsetName)) {
            this.charsetName = charsetName;
            for (PojoSourceCreatorUnit u : sourceUnits.values()) {
                u.setCharset(charsetName);
            }
        }
    }

//    private void checkFieldToPackagedType(PojoField f) {
//        String pt = packedTypeMaps.get(f.varType);
//        if (pt != null) {
//            f.varType = pt;
//        }
//    }

    public static class CodeTempIds {
        //1	6	ETL.FRAME	ETL.FRAME	//系统/数据/数据引擎/ETL
        //1	7	ETL.FUNC	ETL.FUNC	//系统/数据/数据引擎/ETL
        //1	8	ETL.ITEM	ETL.ITEM	//系统/数据/数据引擎/ETL
        //1	9	ETL.SQL	ETL.SQL	//系统/数据/数据引擎/ETL

        public final static String HIBERNATE_FRAME = "hibernate.pojo.Frame";
        public final static String HIBERNATE_FIELD = "hibernate.pojo.Field";
//        public final static String HBM_FRAME = "ETL.FRAME";
//        public final static String ETL_ITEM = "ETL.ITEM";
//        public final static String ETL_SQL = "ETL.SQL";
//        public final static String ETL_FUNC = "ETL.FUNC";
    }

    /**
     * Creates a new instance of PojoSourceCreator_4_Hibernate
     */
    public PojoSourceCreator_4_Hibernate() {
//        this.sourceUnits = sourceUnits;
        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();

    }

    @Override
    public void init(String alias, PojoAttribute pojoAttr, Map<String, PojoField> pojoFields, Meta_Table dbTable) {
        this.alias = alias;
        this.pojoAttr = pojoAttr;
        this.pojoFields = pojoFields;
        this.dbTable = dbTable;
        //
        PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
        pojoSourceUnit.setFileName(String.format("%s.java", pojoAttr.className));
        pojoSourceUnit.setCharset(charsetName);
        //
        PojoSourceCreatorUnit hbmSourceUnit = new PojoSourceCreatorUnit("hbm");
        hbmSourceUnit.setFileName(String.format("%s.hbm.xml", pojoAttr.className));
        hbmSourceUnit.setCharset(charsetName);
        //
        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
        sourceUnits.put(hbmSourceUnit.getCreatorId(), hbmSourceUnit);
        //
//        packedTypeMaps.put("int", Integer.class.getSimpleName());
//        packedTypeMaps.put("long", Long.class.getSimpleName());
//        packedTypeMaps.put("float", Float.class.getSimpleName());
//        packedTypeMaps.put("double", Double.class.getSimpleName());
//        packedTypeMaps.put("boolean", Boolean.class.getSimpleName());
    }

    @Override
    public Ret createAllPojoSource() {
//    public Ret createAllPojoSource(PojoSourceCreatorUnit pojoSourceUnit, PojoSourceCreatorUnit hbmSourceUnit) {
//        public Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource) {
        if (sourceUnits == null) {
            return Ret.getFailureRet("sourceUnits is null");
        }
        PojoSourceCreatorUnit pojoSourceUnit = sourceUnits.get("pojo");
        PojoSourceCreatorUnit hbmSourceUnit = sourceUnits.get("hbm");

        assert pojoSourceUnit != null;
        assert hbmSourceUnit != null;

        Ret r0 = checkAllEtlCodeTemplatesExist();
        if (r0.isRetSuccess()) {
            return make(pojoSourceUnit, hbmSourceUnit);
        }
        return r0;
//        ret.copyFrom(r0);
//        return null;

    }

    private Ret checkAllEtlCodeTemplatesExist() {
        int absentContents = 0;
        errInfo.setLength(0);

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FRAME, "PojoFrame", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FRAME, "HbmFrame", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FRAME, "hashFunction", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FRAME, "equalsFunction", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FRAME, "toStringFunction", true, errInfo) ? 0 : 1;

        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "DeclareField", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "hashCode", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "equals", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "toString", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "setOperator", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "toStringFunction", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "getOperator", true, errInfo) ? 0 : 1;
//        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "isOperator", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.HIBERNATE_FIELD, "pkMapString", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.HIBERNATE_FRAME, getClass(), "pxz");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.HIBERNATE_FIELD, getClass(), "pxz");

        if (absentContents == 0) {
            return Ret.getSuccessRet();
        }
//        String s = String.format("缺少模板，无法生成ETL模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
//        JOptionPane.showMessageDialog(dwws.app.getMainFrame(), s, "错误信息", JOptionPane.ERROR_MESSAGE);
        return Ret.getFailureRet("缺少模板，无法生成ORM模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }

    private Ret make(PojoSourceCreatorUnit pojoSourceUnit, PojoSourceCreatorUnit hbmSourceUnit) {
        StringBuilder fieldDeclareSource = new StringBuilder();
        StringBuilder varsOperatorSource = new StringBuilder();

        StringBuffer pkConstructor_para_Source = new StringBuffer();
        StringBuffer pkConstructor_body_Source = new StringBuffer();
        StringBuffer wholeConstructor_para_Source = new StringBuffer();
        StringBuffer wholeConstructor_body_Source = new StringBuffer();

        StringBuilder toStringAppendSource = new StringBuilder();
        StringBuilder equalsAppendSource = new StringBuilder();
        StringBuilder hashAppendSource = new StringBuilder();

        StringBuilder pkHbmXml = new StringBuilder();
        StringBuilder fieldsHbmXml = new StringBuilder();

        for (PojoField f : pojoFields.values()) {
            if (f.varName == null || f.varName.length() == 0) {
                continue;
            }
            if (this.pojoAttr.usePackageClassFlag) {
                f.changeNumberTypeToPackedMode();
            }
            //     ${scope}    ${vartype}  ${varname};		// field ${colname} : ${colDataType}
            env.put("varname", f.varName);
            env.put("vartype", f.varType);
            env.put("scope", f.scope);
            env.put("colname", f.col.getSqlName());
            env.put("colDataType", f.col.getSqlTypeNameWithLenInfo());
            env.put("varnameCap", Stringtool.changeLeadingCharCase(f.varName, TargetCase.UpperCase, 1));
            env.put("length", f.col.getSize());
            env.put("comment", f.getColComment_singleLine());

            fieldDeclareSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "DeclareField", env, true, errInfo));
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "setOperator", env, true, errInfo));
            varsOperatorSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "getOperator", env, true, errInfo));

            if (f.col.isPk()) {
                pkConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
                pkConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

                // <key-property name="${varname}" type="${vartype}" column="${colname}" length="${length}"/>
                pkHbmXml.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "pkMapString", env, true, errInfo));
            } else {
                //env.put("length", f.col.getSize());
                fieldsHbmXml.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "fieldMapString", env, true, errInfo));
            }
            wholeConstructor_para_Source.append(String.format("%s %s, ", f.varType, f.varName));
            wholeConstructor_body_Source.append(String.format("    this.%s = %s;\r\n", f.varName, f.varName));

            if (f.inToString) {
                toStringAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "toString", env, true, errInfo));
            }
            if (f.inEquals) {
                equalsAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "equals", env, true, errInfo));
            }
            if (f.inHashCode) {
                hashAppendSource.append(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FIELD, "hashCode", env, true, errInfo));
            }
        }
        Stringtool.reduceLength(pkConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(pkConstructor_body_Source, "\r\n".length());
        Stringtool.reduceLength(wholeConstructor_para_Source, ", ".length());
        Stringtool.reduceLength(wholeConstructor_body_Source, "\r\n".length());

        env.put("packageName", this.pojoAttr.packageName);
        env.put("className", this.pojoAttr.className);
        env.put("alias", this.pojoAttr.alias);
        env.put("schema", this.pojoAttr.schema);
        env.put("tableName", this.pojoAttr.tableName);
        env.put("createTime", Timetool.getSysTimestamp().toString());

        env.put("fieldDeclareSource", fieldDeclareSource.toString());
        env.put("varOperatorsSource", varsOperatorSource.toString());

        env.put("pkConstructor_parameters", pkConstructor_para_Source.toString());
        env.put("pkConstructor_body", pkConstructor_body_Source.toString());
        env.put("wholeConstructor_parameters", wholeConstructor_para_Source.toString());
        env.put("wholeConstructor_body", wholeConstructor_body_Source.toString());

        env.put("toStringAppendString", toStringAppendSource.toString());
        env.put("equalsAppendString", equalsAppendSource.toString());
        env.put("hashAppendString", hashAppendSource.toString());

        env.put("charsetName", this.charsetName);
        pojoSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FRAME, "PojoFrame", env, true, errInfo));

        env.put("pkMapString", pkHbmXml.toString());
        env.put("fieldsMapString", fieldsHbmXml.toString());
        hbmSourceUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.HIBERNATE_FRAME, "HbmFrame", env, true, errInfo));

        return Ret.getSuccessRet();
    }

    @Override
    public String getSaveDir(String homeDir) {
        return homeDir + File.separator + "src";
    }
}
