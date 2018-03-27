/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm.creator;

import com.bca.api.pub.Ret;
import com.bca.db.meta.unit.Meta_Table;
import com.bca.pub.tools.Stringtool;
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
public class PojoSourceCreator_4_ZxJspPage implements I_PojoSourceCreator {

    final static com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    final BcaToolkit app = BcaToolkit.getApp();
    final CodeTemplatePool sourceTemplatePool = CodeTemplatePool.getPool("DSS");
    final Map env = new HashMap();
    final StringBuffer errInfo = new StringBuffer();
    String alias;
    PojoAttribute pojoAttr;
    Map<String, PojoField> pojoFields;
    Meta_Table dbTable;

    @Override
    public Map<String, PojoSourceCreatorUnit> getSourceUnits() {
        return sourceUnits;
    }

    public static class CodeTempIds {

        public final static String ZX_JSP_SONG_FRAME = "ZX_JSP_SONG.FRAME";
        public final static String ZX_JSP_SONG_COLUMN = "ZX_JSP_SONG.COLUMN";
    }
    private final Map<String, PojoSourceCreatorUnit> sourceUnits;

    /** Creates a new instance of PojoSourceCreator_4_ZxJspPage */
    public PojoSourceCreator_4_ZxJspPage() {
//        this.sourceUnits = sourceUnits;
        PojoSourceCreatorUnit pojoSourceUnit = new PojoSourceCreatorUnit("pojo");
        pojoSourceUnit.setFileName(String.format("%s.java", pojoAttr.className));

        PojoSourceCreatorUnit hbmSourceUnit = new PojoSourceCreatorUnit("hbm");
        hbmSourceUnit.setFileName(String.format("%s.hbm.xml", pojoAttr.className));

        sourceUnits = new HashMap<String, PojoSourceCreatorUnit>();
        sourceUnits.put(pojoSourceUnit.getCreatorId(), pojoSourceUnit);
        sourceUnits.put(hbmSourceUnit.getCreatorId(), hbmSourceUnit);

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
    }

    @Override
    public Ret createAllPojoSource() {
//    public Ret createAllPojoSource(StringBuffer pojoSource, StringBuffer hbmSource) {
        if (sourceUnits == null) {
            return Ret.getFailureRet("sourceUnits is null");
        }
        PojoSourceCreatorUnit zxPojoJspUnit = sourceUnits.get("ZxPojoJsp");

        Ret r0 = checkAllEtlCodeTemplatesExist();
        if (r0.isRetSuccess()) {
            return make(zxPojoJspUnit);
        }
        return r0;
    }

    private Ret checkAllEtlCodeTemplatesExist() {
        int absentContents = 0;
        errInfo.setLength(0);
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.ZX_JSP_SONG_FRAME, "FRAME", true, errInfo) ? 0 : 1;
        absentContents += sourceTemplatePool.checkContentExist(CodeTempIds.ZX_JSP_SONG_COLUMN, "jspOnColumn", true, errInfo) ? 0 : 1;

        sourceTemplatePool.regTemplateUsing(CodeTempIds.ZX_JSP_SONG_FRAME, getClass(), "pxz");
        sourceTemplatePool.regTemplateUsing(CodeTempIds.ZX_JSP_SONG_COLUMN, getClass(), "pxz");

        if (absentContents == 0) {
            return Ret.getSuccessRet();
        }
        return Ret.getFailureRet("缺少模板，无法生成ORM模型代码。\n请查看日志，并检查模板数据库。\n\n%s", errInfo.toString());
    }

    private Ret make(PojoSourceCreatorUnit zxPojoJspUnit) {
        StringBuffer jspOnColSource = new StringBuffer();
        for (PojoField f : pojoFields.values()) {
            if (f.varName == null || f.varName.length() == 0) {
                continue;
            }
            //     ${scope}    ${vartype}  ${varname};		// field ${colname} : ${colDataType}
            env.put("varNameOnColumn", f.varName);
            env.put("colLabel", "标签_" + f.col.getSqlName() + "_字段");

            jspOnColSource.append(sourceTemplatePool.makeSource(CodeTempIds.ZX_JSP_SONG_COLUMN, "jspOnColumn", env, true, errInfo));
        }

        env.put("title", "标题_" + pojoAttr.tableName + "_表");
        env.put("jspOnColumn", jspOnColSource.toString());
        zxPojoJspUnit.setCode(sourceTemplatePool.makeSource(CodeTempIds.ZX_JSP_SONG_FRAME, "FRAME", env, true, errInfo));
        return Ret.getSuccessRet();
    }

    public String getSaveDir(String homeDir) {
        return homeDir + File.separator + "demo";
    }
}
