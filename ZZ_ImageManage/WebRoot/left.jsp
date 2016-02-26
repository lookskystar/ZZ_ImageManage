<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin"
	prePath="<%=basePath%>" />
<!--框架必需end-->

<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<style>
a {
	behavior:url(../js/method/focus.htc)
}
.categoryitems span{
	width:160px;
}
</style>
</head>

<body leftFrame="true">
<div id="scrollContent">
	<div class="arrowlistmenu">
  		<c:forEach var="map" items="${funMap}" varStatus="idx">
    		<div class="menuheader expandable"><span class="normal_icon${idx.index+1}"></span>${map.key }</div>
    			<ul class="categoryitems">
       				<c:forEach var="it" items="${map.value}">
	    				<li><a href="${it.funcUrl}" target="frmright"><span class="text_slice">${it.funcName}</span></a></li>
	   				</c:forEach>
				</ul>
  		</c:forEach>
	 </div>
</div>
</body>
</html>