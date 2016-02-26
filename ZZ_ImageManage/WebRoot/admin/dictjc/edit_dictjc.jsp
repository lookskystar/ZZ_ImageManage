<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
	<link href="js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
	<!--截取文字start-->
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<!--修正IE6支持透明PNG图start-->
    <script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
  </head>
<body>
	<input type="hidden" value="${dictJcstype.id }"  id="dictJcId"/>
	<table class="tableStyle" transMode="true">
		<tr>
			<td>地&nbsp;&nbsp;&nbsp;&nbsp;区: </td>
			<td>
			   <select id="areaId" name="dictJcstype.areaId">
				     <option value="">--请选择--</option>
				     <c:forEach items="${dictAreaList}" var="entry">
   	                     <option value="${entry.areaId}" <c:if test="${dictJcstype.areaId == entry.areaId}">selected="selected"</c:if>>${entry.areaName}</option>
   	                 </c:forEach>
				</select><font color="red">&nbsp;&nbsp;*</font>
			</td>
		</tr>
		<tr>
			<td>车型:</td>
			<td>
				<select id="jcType" name="dictJcstype.jcType">
					<option value="">--请选择--</option>
					<c:forEach items="${jcTypeList }" var="type">
						<option value="${type.jcType }"<c:if test="${dictJcstype.jcStype==type.jcType}">selected="selected"</c:if>>${type.jcType }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>	
			<td>机&nbsp;&nbsp;车&nbsp;&nbsp;号: </td>
			<td>
				<input  type="text" name="dictJcstype.jcNum" id="jcNum" value="${dictJcstype.jcNum }"></input><font color="red">&nbsp;&nbsp;*</font>
			</td>
		</tr>
		<tr>	
			<td>采集仪标识号: </td>
			<td>
				<input  type="text" name="dictJcstype.jcIdentify" id="jcIdentify" value="${dictJcstype.jcIdentify }"></input><font color="red">&nbsp;&nbsp;*</font>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td colspan="4">
				<input type="button" value=" 提 交 " id="subEnter"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
<script type="text/javascript">
	$(document).ready(function(){
		$("#subEnter").bind("click",function(){
			var areaId = $("#areaId").val();
			var jcType = $("#jcType").val();
			var jcNum = $("#jcNum").val();
			var jcIdentify = $("#jcIdentify").val();
			var dictJcId = $("#dictJcId").val();
			if(areaId == "" || areaId == null || areaId == undefined){
				top.Dialog.alert("请选择地区！");
			}else if(jcType == "" || jcType == null ||jcType == undefined){
				top.Dialog.alert("请选择机车类型！");
			}else if(jcNum == "" || jcNum == null || jcNum == undefined){
				top.Dialog.alert("请输入机车号！");
			}else {
				$.post("dictJcAction!addDictJc.do",{"dictJcId":dictJcId, "areaId":areaId, "jcType":jcType, "jcNum":jcNum, "jcIdentify":jcIdentify},function(result){
					if(result == "success"){
						top.Dialog.alert("编辑成功！", function(){
							top.window.frmright.location.href="<%=basePath%>dictJcAction!queryDictJcByCondition.do";
						});
					}else {
						top.Dialog.alert("编辑失败！");
					}
				
				})
			}
		})
	})
</script>
</body>
</html>