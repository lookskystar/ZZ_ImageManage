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
    <script type="text/javapersle" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
  </head>
<body>
 <form action="deviceAction!resumeInput.do" method="post">
	<div id="scrollContent">
		<table class="tableStyle" headFixMode="true" useMultColor="true" useCheckBox="false">
			<tr>
				<th width="10%" align="center">设备型号</th>
				<th width="10%" align="center">设备编码</th>
				<th width="15%" align="center">操作人</th>
				<th width="15%" align="center">班组</th>
				<th width="15%" align="center">操作类型</th>
				<th width="15%" align="center">操作时间</th>
				<th width="20%" align="center">备注</th>	
			</tr>
			<c:if test="${!empty resume}">
				<c:forEach items="${resume}" var="resume">
					<tr>
						<td class="ver01" align="center">${resume.device_type}</td>
						<td class="ver01" align="center">${resume.device_code}</td>
						<td class="ver01" align="center">${resume.user}</td>
						<td class="ver01" align="center">${resume.team}</td>
						<td class="ver01" align="center">
					      <c:if test="${resume.use_type==0}">领取</c:if>
					      <c:if test="${resume.use_type==1}">归还</c:if>
					      <c:if test="${resume.use_type==2}">送修</c:if>
					      <c:if test="${resume.use_type==3}">返修回来</c:if>
					      <c:if test="${resume.use_type==4}">报废</c:if>
						</td>
						<td class="ver01" align="center">${resume.use_time}</td>
						<td class="ver01" align="center">${resume.rec_note}</td>
					</tr>
				 
			    </c:forEach>			
			</c:if>
			<c:if test="${empty resume}">
				<tr> 
				    <td class="ver01" align="center" colspan="10">没有此设备的操作记录!</td>
				</tr>
			</c:if>
		</table>
	</div>
 </form>
</body>
</html>