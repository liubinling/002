<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="b" uri="/bonc-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="form" border="0">
</table>
<br />
<h1>详细信息</h1>

	<!-- 隐藏 -->
	<input type="hidden" id="path" value="${pageContext.request.contextPath}">
	<input type="hidden" name="bo.po.partId" value="${bo.po.partId }"/>
	<table class="form" style="border: 0px;">
	<tbody>
		<tr>
      <td width="20%" align="right">零件ID:</td>
      <td >
        ${bo.po.partId}
      </td>
      <td width="20%" align="right">零件名称:</td>
      <td >
        ${bo.po.partName}
      </td>
    </tr>
    <tr>
      <td width="20%" align="right">零件描述:</td>
      <td >
        ${bo.po.partDiscrible}
      </td>
      <td width="20%" align="right">零件单价:</td>
      <td >
        ${bo.po.partPrice}
      </td>
    </tr>
    <tr>
      <td width="20%" align="right">IS_USED:</td>
      <td >
        ${bo.po.isUsed}
      </td>
      <td width="20%" align="right">SORT_NO:</td>
      <td >
        ${bo.po.sortNo}
      </td>

		</tr>
	</tbody>
	</table>
	
