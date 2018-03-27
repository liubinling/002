<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="b" uri="/bonc-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<b:form action="" id="from2">
<table width="100%" id="carPartCodeList" class="grid0" style="margin-top:5px;">
<thead>
  <b:gHead action="CarPartCodeInfo.action">
    <tr>
          <th><input type="checkbox" name="checkAll" id="checkAll" value="on"/></th>
        <th><b:gh col="part_id">���ID</b:gh></th>
        <th><b:gh col="part_name">�������</b:gh></th>
        <th><b:gh col="part_discrible">�������</b:gh></th>
        <th><b:gh col="part_price">�������</b:gh></th>
        <th><b:gh col="is_used">IS_USED</b:gh></th>
        <th><b:gh col="sort_no">SORT_NO</b:gh></th>

    </tr>
  </b:gHead>
</thead>
<tbody>
  <s:iterator value="bo.poList">
    <tr>
      <td align="center" width="4%">
        <input type="checkbox" name="selectMap" value="${partId }" class="ext"/> 
      </td>
            <td>${partId}</td>
            <td>${partName}</td>
            <td>${partDiscrible}</td>
            <td>${partPrice}</td>
            <td>${isUsed}</td>
            <td>${sortNo}</td>

            <td><a href="javascript:;" onclick="showDetail(${partId});">�鿴����</a></td>
    </tr>
  </s:iterator>
</tbody>
</table>
</b:form>
<b:pagiLink action="CarPartCodeInfo.action"/>
