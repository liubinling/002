<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="b" uri="/bonc-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="form" border="0">
</table>
<br />
<h1>基本信息</h1>
<s:form id="addForm" action="CarPartCodeInfo!addCarPartCode">
	<!-- 隐藏 -->
	<input type="hidden" id="path" value="${pageContext.request.contextPath}">
	
	<table class="form" style="border: 0px;">
	<tbody>
		<tr>
      <td width="20%" align="right">零件ID:</td>
      <td >
        <input type="text" name="bo.po.partId" style="width: 200px" id="descColName" check_str="零件ID" maxlength="13" check_type="string">
      </td>
      <td width="20%" align="right">零件名称:</td>
      <td >
        <input type="text" name="bo.po.partName" style="width: 200px" id="descColName" check_str="零件名称" maxlength="13" check_type="string">
      </td>
    </tr>
    <tr>
      <td width="20%" align="right">零件描述:</td>
      <td >
        <input type="text" name="bo.po.partDiscrible" style="width: 200px" id="descColName" check_str="零件描述" maxlength="13" check_type="string">
      </td>
      <td width="20%" align="right">零件单价:</td>
      <td >
        <input type="text" name="bo.po.partPrice" style="width: 200px" id="descColName" check_str="零件单价" maxlength="13" check_type="string">
      </td>
    </tr>
    <tr>
      <td width="20%" align="right">IS_USED:</td>
      <td >
        <input type="text" name="bo.po.isUsed" style="width: 200px" id="descColName" check_str="IS_USED" maxlength="13" check_type="string">
      </td>
      <td width="20%" align="right">SORT_NO:</td>
      <td >
        <input type="text" name="bo.po.sortNo" style="width: 200px" id="descColName" check_str="SORT_NO" maxlength="13" check_type="string">
      </td>

		</tr>
	</tbody>
	
	</table>
	 <s:submit value="保存" align="right"/>
</s:form>
