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
		<applet id="upload" alt="文件上传applet小程序" width="500px" height="320px" code="com.image.applet.ui.AppletMain.class" 
		codebase="<%=basePath %>" archive="upload.jar" >
			<param name="host" value="${sessionScope.session_ftp_url}" />
			<param name="port" value="${sessionScope.session_ftp_port}" />
			<param name="userName" value="${sessionScope.session_ftp_username}" />
			<param name="password" value="${sessionScope.session_ftp_password}" />
			
			<param name="acceptAllFileFilterUsed" value="false" />
			<param name="isDirectorySelectionEnabled" value="true" />
			<param name="isFileSelectionEnabled" value="true" />
			<param name="fileNameExtension" value="AVI,JPG" />
			<param name="isFileHidingEnabled" value="true" />
			<param name="isMultiSelectionEnabled" value="true" />
			<param name="maxFileSize" value="8589934592" />
			<param name="areaid" value="${sessionScope.session_user.areaId }" />
			<param name="validateUrl" value="<%=basePath%>ftpValidate!validate.do" />
			<param name="getDateUrl" value="<%=basePath%>queryAction!ajaxAddJSONData.do" />
		</applet>
		<div>
			<a href="help.jsp" style="color:red;font-size:12px" target="_blank">显示不出来，进入帮助，下载插件</a>
		</div>
</body>
</html>