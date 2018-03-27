package com.bca.toolkit.top.tools.sql.impl.estyle;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author: 北京华夏翰科科技有限公司-工具软件部.
 *           http://www.hxhk.com.cn
 */
/** 持久类定义
 *  类：pxz.demo.pojo.EditStyleCode
 *  映射到表：test01.EDIT_STYLE_CODE
 * 创建时间：2012-10-02 23:07:06.921
 */
public class EditStyleCode implements java.io.Serializable {
    private    java.lang.String  itemCode;		// field ITEM_CODE : VARCHAR2(100)
    private    java.lang.String  compLabel;		// field COMP_LABEL : VARCHAR2(100)
    private    int  sortNo;		// field SORT_NO : NUMBER(22)


  public EditStyleCode() {
  }

  public EditStyleCode(java.lang.String itemCode) {
    this.itemCode = itemCode;
  }

  public EditStyleCode(java.lang.String itemCode, java.lang.String compLabel, int sortNo) {
    this.itemCode = itemCode;
    this.compLabel = compLabel;
    this.sortNo = sortNo;
  }

  public void setItemCode(java.lang.String itemCode) {
    this.itemCode=itemCode;
  }
  public java.lang.String getItemCode() {
    return this.itemCode;
  }
  public void setCompLabel(java.lang.String compLabel) {
    this.compLabel=compLabel;
  }
  public java.lang.String getCompLabel() {
    return this.compLabel;
  }
  public void setSortNo(int sortNo) {
    this.sortNo=sortNo;
  }
  public int getSortNo() {
    return this.sortNo;
  }


  public String toString() {
    return new ToStringBuilder(this)
        .append("itemCode", itemCode)
        .append("compLabel", compLabel)
        .append("sortNo", sortNo)
        .toString();
  }

  public boolean equals(Object other) {
    if (! (other instanceof EditStyleCode)) {
      return false;
    }
    EditStyleCode castOther = (EditStyleCode) other;
    return new EqualsBuilder()
        .append(this.itemCode, castOther.itemCode)
        .append(this.compLabel, castOther.compLabel)
        .append(this.sortNo, castOther.sortNo)
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder()
        .append(itemCode)
        .append(compLabel)
        .append(sortNo)
        .toHashCode();
  }
}
