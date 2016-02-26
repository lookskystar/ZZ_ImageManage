<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" src="log.js"></script>
<title>你的浏览器不支持</title>
<style> 
body{margin:0px;background-color:black;font-size:12px;color:#99B6D6;line-height:17px;}*{margin:0px;padding:0px;}#outer{position:absolute;top:50%;width:100%;float:left;}#outer2{position:absolute;top:-50px;width:100%;}#wrapper{position:relative;margin:auto;padding-left:30px;width:420px;height:320px;}#left{width:173px;float:left;margin:0 10px 0 0;}#right{width:110px;float:left;margin-top:3px;}#btn{width:173px;height:55px;background-image:url(first_btn.jpg);}#btn>a{width:173px;height:55px;display:block;}a{color:#CBDEF0;}a:hover{text-decoration:none;}#ver{width:173px;height:55px;cursor:hand;}#text{position:relative;top:33px;left:20px;font-size:12px;}#shadow{margin-top:12px;width:173px;height:45px;background-image:url(first_shadow.jpg);clear:both;}#bottom{position:absolute;bottom:0px;background:red;height:29px;width:100%;background:url(first_bg.gif)}#player_left{float:left;width:204px;height:29px;background:url(first_left.gif)}#player_right{float:right;width:38px;height:29px;background:url(first_right.gif);}.clear{clear:both;}
.tips{margin-bottom:8px;font-size:14px;color:#FF0000;}
.wrp2{width:300px;margin:auto;}
</style>
</head>
<body>
<div id="outer"><div id="outer2">
<div id="wrapper">
   <div class="tips">未安装该播放器，请下载安装百度影音后刷新本页面！ </div>
    <div class="wrp2">
    <div id="left">
        <div id="btn">
            <a href="<%=basePath%>video/OfflineBaiduPlayer_151.exe"><div id="ver"><div id="text">点击进入下载</div></div></a>
        </div>
        <div id="shadow"></div>
    </div>
    <div id="right">
        <p>湖南骁睿铁路科技</p>
        <p>支持XP/Vista/win7</p>
        <p>图像信息管理系统</p>
    </div>
    </div>
</div>
</div></div>
<div class="clear"></div>
<div id="bottom">
	<div id="player_left"></div>
	<div id="player_right"></div>
</div>
</body>
</html>
