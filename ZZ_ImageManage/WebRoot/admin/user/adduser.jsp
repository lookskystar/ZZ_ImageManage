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
	<script>
		function checkform(){
			    if($("#roleId").val()==''){
				    alert("角色不能为空！");
				    return false;
			    }
			    if($("#teamId").val()==''){
				    alert("班组不能为空！");
				    return false;
			    }
				if($("#name").val()==''){
					alert("用户姓名不能为空！");
					return false;
				}
				if($("#username").val()==''){
					alert("登陆帐号不能为空！");
					return false;
				}
				if($("#password").val()==''){
					alert("登录密码不能为空！");
					return false;
				}
				if($("#areaId").val()==''){
					alert("地区不能为空！");
					return false;
				}
				if($("#gonghao").val()==''){
					alert("工号不能为空！");
					return false;
				}
				if($("#py").val()==''){
					alert("拼音不能为空！");
					return false;
				}
			}
		</script>
  </head>
<body>
  	<form action="usersAction!addUserPrivs.do" method="post" id="subForm" target="frmright" onsubmit="return checkform()">   
		<table class="tableStyle" transMode="true">
			<tr>
				<td>角&nbsp;&nbsp;&nbsp;&nbsp;色: </td>
				<td>
					<select id="roleId" name="dictUsers.roleId" class="default">
					     <option value="">请选择角色</option>
					     <c:forEach items="${rolePrivsList}" var="entry">
    	                      <option value="${entry.roleId}">${entry.roleName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			<!-- 	<td>地&nbsp;&nbsp;&nbsp;&nbsp;区: </td>
				<td>
				   <select id="areaId" name="dictUsers.areaId" class="default" >
					     <option value="">请选择地区</option>
					     <c:forEach items="${dictAreaList}" var="entry">
    	                     <option value="${entry.areaId}">${entry.areaName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>    -->
				
				<td>班&nbsp;&nbsp;&nbsp;&nbsp;组: </td>
				<td>
					<select id="teamId"  name="dictUsers.teamId" class="default">
					     <option value="">请选择班组</option>
					     <c:forEach items="${dictTeamsList}" var="entry">
    	                     <option value="${entry.teamId}">${entry.teamName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
	 		<tr>
				<td>用户姓名: </td>
				<td>
					<input  type="text" name="dictUsers.name" id="name"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
				
				<td>工&nbsp;&nbsp;&nbsp;&nbsp;号: </td>
				<td>
					<input  type="text" name="dictUsers.gonghao" id="gonghao"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>	
				<td>登陆名称: </td>
				<td>
					<input  type="text" name="dictUsers.username" id="username"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
				<td>登录密码: </td>
				<td>
					<input  type="text" name="dictUsers.password" id="password"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>	
			    <td>拼音缩写: </td>
				<td>
					<input  type="text" name="dictUsers.py" id="py"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
				<td>IDK卡号: </td>
				<td>
					<input  type="text" name="dictUsers.idnum"></input>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="4">
					<input type="submit" value=" 提 交 "/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 重 置 "/>
				</td>
			</tr>
		</table>
  	</form>  
</body>
</html>