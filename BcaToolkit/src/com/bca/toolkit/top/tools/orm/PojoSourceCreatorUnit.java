/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.orm;

/**
 *
 * @author pxz
 */
public class PojoSourceCreatorUnit {

    private final String creatorId;
    private String fileName;
    private String targetPath;
//    private String saveDir;
    private String code;
    private String charset = "GBK";

    public PojoSourceCreatorUnit(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String GetTargetPath() {
        return targetPath;
    }
    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

//    public String getSaveDir() {
//        return saveDir;
//    }
//
//    public void setSaveDir(String saveDir) {
//        this.saveDir = saveDir;
//    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        restoreSpecialSymbolText();
    }

    public void restoreSpecialSymbolText() {
        if (code != null) {
            code = code.replace("_SH_DOLLAR_", "$");
            code = code.replace("__HXHK_ZEROSTRING_SYM__", "");   // 某些特殊符号，vocity会转意义。
        }
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }
}
