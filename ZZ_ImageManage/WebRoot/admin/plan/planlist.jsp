<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin"
	prePath="<%=basePath%>" />
	
<!--框架必需end-->

<!--引入组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->
<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--修正IE6支持透明PNG图start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明PNG图end-->
<!-- 引入分页JS -->
<script type="text/javascript" src="js/page.js"></script>	
<script type="text/javascript">
	function del(id){
		top.Dialog.confirm("确定要删除吗?",function(){
			window.location.href="<%=basePath%>planAction!deletePlan.do?planId="+id;
		});		
	}

	function add(){
		top.Dialog.open({URL:"<%=basePath%>planAction!addPlanInput.do",Width:420,Height:300,Title:"添加应用计划"});
	}

	function sel(){
		var time = $("#btime").val();
		var areaId = $("#areaid").val();
		window.location.href="<%=basePath%>planAction!trainPlanInput.do?time="+time+"&areaId="+areaId;
	}

	function change(id){
		top.Dialog.open({URL:"<%=basePath%>planAction!updatePlanInput.do?planId="+id,Width:420,Height:300,Title:"修改应用计划"});
	}
	$(function(){
    	<c:if test="${!empty message}">
    	    top.Dialog.close();
			top.Dialog.alert('${message}');
	    </c:if>
    });
</script>

</head>
<body>	
<div class="box2" panelTitle="车组应用计划" roller="false">
	<table>
		<tr>
			<td>计划时间：<input id="btime" type="text" class="Wdate"	 value="${shijian }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:119px;"/></td>
			<td>地区：<select id="areaid" class="default">
						<option value="">请选择地区</option>
						<c:forEach items="${dictAreaList}" var="area">
							<option value="${area.areaId}">${area.areaName}</option>
						</c:forEach>
					</select>
			</td>
			<td><input type="button" value="添加" onclick="add()"/></td>
			<td><input type="button" value="查询" onclick="sel()"/></td>
		</tr>
	</table>
</div>

<div>
	<table class="tableStyle" align="center">
	<tr>
		<th width="50px;" >序号</th>
		<th >车型</th>
		<th >车号</th>
		<th >始发站</th>
		<th >终点站</th>
		<th >车次</th>
		<th >计划时间</th>
		<th >检查次数</th>
		<th >操作</th>
	</tr>
	<c:forEach var="plan" items="${trainPlan.datas}">	
	<tr align="center">
		<td >${plan.id }</td>
		<td >${plan.jcStyle }</td>
		<td >${plan.jcNum }</td>
		<td >${plan.started }</td>
		<td >${plan.ended }</td>
		<td >${plan.cjNum }</td>
		<td >${plan.time }</td>
		<td >${plan.jcCs }</td>
		<td >
			<span onclick="change(${plan.id })"><a style="color:blue;">修改</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span onclick="del(${plan.id })"><a style="color:blue;" >删除</a></span>
		</td>
	</tr>
	</c:forEach>
	</table>
</div>
<!-- 处理分页 -->
共有信息${trainPlan.count }条,一页可显示${pageSize}条记录。
<div class="float_right padding5 paging">
	<div class="float_left padding_top4">
		<pg:pager maxPageItems="${pageSize }" url="planAction!trainPlanInput.do" items="${trainPlan.count }" export="page1=pageNumber">		
		    <pg:param name="shijian" value="${shijian}"/>
		    <pg:param name="areaId" value="${areaId}"/>
	  		<pg:first>
				 <span><a href="${pageUrl }" id="first">首页</a></span>
	 		</pg:first>
	 		<pg:prev>
				  <span><a href="${pageUrl }">上一页</a></span>
	  		</pg:prev>
	 	  		<pg:pages>
				<c:choose>
					<c:when test="${page1==pageNumber }">
						<span class="paging_current"><a href="javascript:;">${pageNumber }</a></span>
					</c:when>
					<c:otherwise>
						<span><a href="${pageUrl }">${pageNumber }</a></span>
					</c:otherwise>
				</c:choose>
	  		</pg:pages>
	  		<pg:next>
			    <span><a href="${pageUrl }">下一页</a></span>
	  		</pg:next>
	  		<pg:last>
			  	<span><a href="${pageUrl }">末页</a></span>
	 		</pg:last>
	 	</pg:pager>
	</div>
	<div class="clear"></div>
</div>		
</body>
<link href="<%=basePath %>My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>My97DatePicker/WdatePicker.js"></script>
</html>