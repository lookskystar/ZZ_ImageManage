<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jstl-c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="login/skin1/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/login.js"></script>

<!--引入弹窗组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明png图片end-->

<!--居中显示start-->
<script type="text/javascript" src="js/method/center-plugin.js"></script>
<!--弹出式提示框end-->
<script type="text/javascript" src="js/timer2.src.js"></script> 

<script>
	$(function(){
		 $('.login_main').center();
	})
	
	function checkLogin(){
		var uname = $.trim($("#_username").val());
		var pwd = $.trim($("#_password").val());
		if(uname==null || uname.length==0){
			top.Dialog.alert("用户名不能为空");
			return false;
		}
		if(pwd==null || pwd.length==0){
			top.Dialog.alert("密码不能为空");
			return false;
		}
		$(".login_info").text("");
		return true;
	}
</script>
<!-- 
<script type='text/javascript'>
var CallShowMsg = function(){
	var xx = document.getElementById("x");
	xx.Test('这是读卡数据：');
	document.all.idkid.value=xx.Msg;
	if (document.all.idkid.value!=""){
		//alert(document.all.idkid.value); 
		//alert(document.all.idkid.value); 
		document.loginForm.submit();
	}
}
//CallShowMsg()
var timer = new Timer(50); 
var globalTimer = new Timer(1000); 

function init() { 
	timer.addEventListener(TimerEvent.TIMER, calTime); 
	timer.start(); 
	globalTimer.addEventListener(TimerEvent.TIMER, controlTime); 
	globalTimer.start(); 
} 

function controlTime() { 
	if (!timer.running) { 
		timer.reset(); 
		timer.start(); 
	} 
} 

function calTime() { 
	CallShowMsg();
	var v=document.all.idkid.value;
	if (v != null) { 
		timer.stop(); 
		document.all.username.value=x.Msg; 
		CallShowMsg();
	} 
} 
window.onload = init;
</script> 
 -->

<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
</style>
</head>
<body >
	<div class="login_main">
		<div class="login_top">
			<div class="login_title"></div>
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form name="loginForm" action="loginAction!login.action" method="post" class="login_form" onsubmit="return checkLogin();">
						<input type="hidden" id="idkid" name="idkid" value=""/>
						<div class="login_user"><input type="text" id="_username" name="username"/></div>
						<div class="login_pass"><input type="password" id="_password" name="password"/></div>
						<div class="clear"></div>
						<div class="login_button">
							<div class="login_button_left"><input type="submit" value="" onfocus="this.blur()"/></div>
							<div class="login_button_right"><input type="reset" value="" onfocus="this.blur()"/></div>
							<div class="clear"></div>
						</div>
						<!-- 
						<object width="32" height="32" title="MyActiveX" id="x" name="x" classid="clsid:A4D8D89A-CF2A-49BB-B703-25E48360D2A8" codebase="ActiveX.CAB#version=1,0,0,0"></object>
						 -->
					</form>
					<div class="login_info">${loginError}</div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">广州铁路(集团)公司 COPYRIGHT 2013 </div>
		</div>
	</div>
	<!-- 
	<div class="login_skin"><span onclick='top.Dialog.open({URL:"login/useSkin.html",Title:"皮肤更换方法",Width:720,Height:490});'>皮肤更换方法</span></div>
	 -->
</body>
</html>