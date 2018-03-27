<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="b" uri="/bonc-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<b:doctype/>
<html>
  <head>
    <b:head meddle="false"/>
    <s:head theme="xframe"/>
    <style type="text/css">
     form label{
       width:80px;
       text-align: right;
       display:inline-block;
     }
    </style>
    <script type="text/javascript" src="/demo1/resources/dmc/js/codemanage/codemapping/CarPartCodeInfo.js"></script>
    <script type='text/javascript' src='/demo1/resources/dmc/js/common/AjaxSubmit.js' ></script>
  </head>
  <body style="padding:2px;">
    <b:appletBox title="零件信息表管理" showMM="false"/>
    <s:form action="" id="carPartCodeForm">
    <input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
      <table class="form">
        <tbody>
        <tr>
          <td><s:textfield name="bo.partId" label="零件ID"/></td>
          <td><s:textfield name="bo.partName" label="零件名称"/></td>

          <td>
          &nbsp;&nbsp;
          <input type="button" class="button2"  value="查询" id="searchCarPartCode">
          &nbsp;&nbsp;
          <input type="button" class="button2"  value="新建" id="addCarPartCode">
          &nbsp;&nbsp;
          <input type="button" class="button2"  value="删除" id="deleteCarPartCode">
          &nbsp;&nbsp;
          <input type="button" class="button2"  value="修改" id="updateCarPartCode">
          &nbsp;&nbsp;
          <input type="button" class="button2"  value="导出" id="exportCarPartCode">
          </s:form>
          </td><td>
          
          <s:form action="CarPartCodeInfo!uploadFile.action" method="post" enctype="multipart/form-data" theme="simple">
      
          &nbsp;   <s:file
             name="bo.uploadFile" size="6" class="button2"/><input type="submit" value="导入" class="button2"/>

        </s:form>
        
          </td>
        </tr>
        </tbody>
      </table>

    <!-- 局部刷新查询页面 -->
    <div id="carPartCodeListDiv" >
      <jsp:include page="CarPartCodeInfo-listdiv.jsp"></jsp:include>
    </div>
    <!-- 添加新零件信息表设置div -->
    <div id="inputDiv" style="display:none;">
      
    </div>
    
    <!--  修改零件信息表信息设置div -->
    <div id="updateCarPartCodeDiv" style="display:none;">
    
    </div>
    <!--  显示零件信息表信息设置div -->
    <div id="detailCarPartCodeDiv" style="display:none;">
      
    </div>
    
    <!-- 结果的span -->
    <div id="resultDiv" style="display:none;"></div>
  </body>
</html>
