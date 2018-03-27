package com.bca.toolkit.top.tools.sql.impl.util.w;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author: 北京华夏翰科科技有限公司-工具软件部. http://www.hxhk.com.cn
 */
/**
 * 持久类定义 类：pxz.demo.pojo.WebUiTemplUnit 映射到表：test01.WEB_UI_TEMPL_UNIT
 * 创建时间：2013-04-09 14:28:22.207
 */
public class WebUiTemplUnit implements java.io.Serializable {

    private static final long serialVersionUID = com.bca.kernel.BcaSerialData.serialVersionUID;
    private java.lang.String estyleArgName;		// field ESTYLE_ARG_NAME : VARCHAR2(100)
    private java.lang.String estyleContent;		// field ESTYLE_CONTENT : VARCHAR2(1000)
    private char scope = 't';                           // true: 表级别的（需要字符累加）。 false：字段级别的（每个字段单独生成)
    private int idx = 0;
    String note = "";
//    StringBuffer runtimeSrc = new StringBuffer();

    public WebUiTemplUnit() {
    }

    public WebUiTemplUnit(java.lang.String estyleArgName) {
        this.estyleArgName = estyleArgName;
    }

    public WebUiTemplUnit(java.lang.String estyleArgName, java.lang.String estyleContent) {
        this.estyleArgName = estyleArgName;
        this.estyleContent = estyleContent;
    }

    public void setEstyleArgName(java.lang.String estyleArgName) {
        this.estyleArgName = estyleArgName;
    }

    public java.lang.String getEstyleArgName() {
        return this.estyleArgName;
    }

    public void setEstyleContent(java.lang.String estyleContent) {
        this.estyleContent = estyleContent;
    }

    public java.lang.String getEstyleContent() {
        return this.estyleContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("estyleArgName", estyleArgName)
                .append("estyleContent", estyleContent)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WebUiTemplUnit)) {
            return false;
        }
        WebUiTemplUnit castOther = (WebUiTemplUnit) other;
        return new EqualsBuilder()
                .append(this.estyleArgName, castOther.estyleArgName)
                .append(this.estyleContent, castOther.estyleContent)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(estyleArgName)
                .append(estyleContent)
                .toHashCode();
    }

    /**
     * @return the idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * @return the scope
     */
    public char getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(char scope) {
        this.scope = scope;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
}
