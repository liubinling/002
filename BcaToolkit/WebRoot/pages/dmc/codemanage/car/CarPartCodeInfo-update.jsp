<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="b" uri="/bonc-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="form" border="0">
</table>
<br />
<h1>基本信息</h1>
<s:form id="updateForm" action="CarPartCodeInfo!updateCarPartCode">
  <!-- 隐藏 -->
  <input type="hidden" id="path" value="${pageContext.request.contextPath}">
  <input type="hidden" name="bo.po.partId" value="${bo.po.partId}"/>
  <table class="form" style="border: 0px;">
  <tbody>
    <tr>
      <td width="20%" align="right">零件ID:</td>
      <td>
        ${bo.po.partId}
      </td>
      <td width="20%" align="right">零件名称:</td>
      <td>
         <input type="text" name="bo.po.partName" style="width: 200px" id="partName" value="${bo.po.partName}" >
       </td>
    </tr>
    <tr>
      <td width="20%" align="right">零件描述:</td>
      <td>
         <input type="text" name="bo.po.partDiscrible" style="width: 200px" id="partDiscrible" value="${bo.po.partDiscrible}" >
       </td>
      <td width="20%" align="right">零件单价:</td>
      <td>
         <input type="text" name="bo.po.partPrice" style="width: 200px" id="partPrice" value="${bo.po.partPrice}" >
       </td>
    </tr>
    <tr>
      <td width="20%" align="right">IS_USED:</td>
      <td>
         <input type="text" name="bo.po.isUsed" style="width: 200px" id="isUsed" value="${bo.po.isUsed}" >
       </td>
      <td width="20%" align="right">SORT_NO:</td>
      <td>
         <input type="text" name="bo.po.sortNo" style="width: 200px" id="sortNo" value="${bo.po.sortNo}" >
       </td>

    </tr>
  </tbody>
  </table>
  <s:submit value="提交" align="right"></s:submit>
</s:form>
