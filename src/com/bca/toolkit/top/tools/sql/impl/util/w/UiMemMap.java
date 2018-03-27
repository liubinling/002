package com.bca.toolkit.top.tools.sql.impl.util.w;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author: �������ĺ��ƿƼ����޹�˾-���������.
 *           http://www.hxhk.com.cn
 */
/** �־��ඨ��
 *  �ࣺcom.bca.toolkit.top.tools.sql.impl.util.w.UiMemMap
 *  ӳ�䵽��test01.UI_MEM_MAPS
 * ����ʱ�䣺2013-02-14 13:13:42.925
 */
public class UiMemMap implements java.io.Serializable {
    private    java.lang.String  value;		// field VALUE : VARCHAR2(100)
    private    java.lang.String  label;		// field LABEL : VARCHAR2(100)


  public UiMemMap() {
  }

  public UiMemMap(java.lang.String value) {
    this.value = value;
  }

  public UiMemMap(java.lang.String value, java.lang.String label) {
    this.value = value;
    this.label = label;
  }

  public void setValue(java.lang.String value) {
    this.value=value;
  }
  public java.lang.String getValue() {
    return this.value;
  }
  public void setLabel(java.lang.String label) {
    this.label=label;
  }
  public java.lang.String getLabel() {
    return this.label;
  }


  public String toString() {
    return new ToStringBuilder(this)
        .append("value", value)
        .append("label", label)
        .toString();
  }

  public boolean equals(Object other) {
    if (! (other instanceof UiMemMap)) {
      return false;
    }
    UiMemMap castOther = (UiMemMap) other;
    return new EqualsBuilder()
        .append(this.value, castOther.value)
        .append(this.label, castOther.label)
        .isEquals();
  }

  public int hashCode() {
    return new HashCodeBuilder()
        .append(value)
        .append(label)
        .toHashCode();
  }
}
