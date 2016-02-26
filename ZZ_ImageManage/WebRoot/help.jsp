<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图像信息管理系统</title>
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<!--框架必需end-->
</head>
<body>
	1、下载JRE1.6:<a href="<%=basePath %>jre-6u45-windows-i586.exe">点击下载</a><br/>
	2、安装JRE1.6<br/>
	<img src="<%=basePath %>images/1.jpg" alt="" width="400px" height="280px"/><br/>
	3、检查设置是否安装成功:浏览器--->工具--->Internet选项<br/>
	在"安全"选项-->点击选中"本地Intranet"-->点击"站点"-->"高级"-->添加"系统域名"-->确定关闭<br/>
	在"安全"选项-->-->"高级"-->查看Java插件是否勾上<br/>
	<img src="<%=basePath %>images/2_1.jpg" alt="" width="280px" height="380px"/>
	<img src="<%=basePath %>images/2_2.jpg" alt="" width="450px" height="380px"/>
	<img src="<%=basePath %>images/2_3.jpg" alt="" width="280px" height="380px"/><br/>
	4、关闭浏览器所有窗口，重启浏览器<br/>
	5、重新进入页面,信任浏览器插件<br/>
	<img src="<%=basePath %>images/3.jpg" alt="" width="250px" height="280px"/><br/>
</body>
</html>