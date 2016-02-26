<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/common/common.jsp" %>
<%@include file="checkLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图像信息管理系统</title>
<!--框架必需start-->
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="skins/sky/import_skin.css" rel="stylesheet" type="text/css" id="skin" themeColor="blue"/>
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/bsFormat.js"></script>
<!--框架必需end-->

<script type="text/javascript" src="js/comet4j.js"></script> 

<!--引入组件start-->
<script type="text/javascript" src="js/attention/floatPanel.js"></script>

<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<script type="text/javascript" src="js/attention/messager.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!-- 
<script>
	$(function(){
		setTimeout("openMsg()",1000)
	})
	function openMsg(){
	$.messager.lays(250, 140);
	$.messager.show(0,'<ul><li><a href="javascript:openWin()"><span class="icon_lightOn">系统消息：3条</span></a></li><div class="clear"></div>'+
	'<li><a href="javascript:openWin()"><span class="icon_lightOn">公共消息：10条</span></a></li><div class="clear"></div>'+
	'<li><a href="javascript:openWin()"><span class="icon_lightOn">私人消息：5条</span></a></li><div class="clear"></div>'+
	'<li><a href="javascript:openWin()"><span class="icon_lightOn">未读消息：15条</span></a></li><div class="clear"></div></ul>','stay');
	}
	function openWin(){
		top.Dialog.open({URL:"templete/msgBox.html",Title:"信件箱"});
	}
</script>
 -->
<!--弹出式提示框end-->
</head>
<body>
<div id="floatPanel-1"></div>	
<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter">
	<div id="bs_bannerleft">
	<div id="bs_bannerright">
		<div class="bs_banner_logo"></div>
		<div class="bs_banner_title"></div>
		<div class="bs_nav">
			<div class="bs_navleft">
				<li>
					欢迎<font color="red">${sessionScope.session_user.name }</font>用户，今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>
				</li>
				<li class="fontTitle">&nbsp;&nbsp;字号:</li>
				<li class="fontChange"><span><a href="javascript:;" setFont="16">大</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="14">中</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="12">小</a></span></li>
				<div class="clear"></div>
			</div>
			<div class="bs_navright">
				<span class="icon_back hand" onclick="window.history.back();">返回</span>
			    <span class="icon_edit hand"><a href="<%=basePath%>update_pwd.jsp" target="frmright">修改密码</a></span>
				<span class="icon_no hand" onclick='top.Dialog.confirm("确定要退出系统吗",function(){window.location="login.jsp"});'>退出系统</span>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->

<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
								<div id="lbox_topleft">
								<div id="lbox_topright">
									<div class="lbox_title">操作菜单</div>
								</div>
								</div>
								</div>
								<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
								<div id="lbox_middleright">
										<div id="bs_left">
										<IFRAME scrolling="no" width="100%"  frameBorder=0 id=frmleft name=frmleft src="function!getUserFuncs.do"  allowTransparency="true"></IFRAME>
										</div>
										<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
								</div>
								</div>
								<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
								</div>
								</div>
							</div>
		</td>
		<!--左侧区域end-->
		
		<!--中间栏区域start-->
		<td class="main_shutiao">
			<div class="bs_leftArr" id="bs_center" title="展开面板"></div>
		</td>
		<!--中间栏区域end-->
		
		<!--右侧区域start-->
		<td class="ali01 ver01"  width="100%">
							<div id="rbox">
								<div id="rbox_topcenter">
								<div id="rbox_topleft">
								<div id="rbox_topright">
									<div class="rbox_title">
										操作内容
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_middlecenter">
								<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right">
									       <IFRAME scrolling="no" width="100%" frameBorder=0 id=frmright name=frmright src="templete/open.html"  allowTransparency="true"></IFRAME>
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_bottomcenter" >
								<div id="rbox_bottomleft">
								<div id="rbox_bottomright">

								</div>
								</div>
								</div>
							</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>
<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->
</body>
</html>
