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
	    //关闭此页面
		function toclose(){
			top.Dialog.close();
		}
    </script>
  </head>
<body>
    <form action="usersAction!userInfoList.do" method="post" target="frmright"> 
  	    <input type="hidden" value="${user.userId }" name="userId"/>
		<table class="tableStyle" formMode="true">
		<tr>
				<td>角&nbsp;&nbsp;&nbsp;&nbsp;色: </td>
				<td>
					  <c:forEach items="${rolePrivsList}" var="entry">
    	                  <c:if test="${user.roleId==entry.roleId}">${entry.roleName}</c:if>
    	              </c:forEach>
				</td>
				<td>地&nbsp;&nbsp;&nbsp;&nbsp;区: </td>
				<td>
					 <c:forEach items="${dictAreaList}" var="entry">
    	                <c:if test="${user.areaId==entry.areaId}">${entry.areaName}</c:if>
    	             </c:forEach>
				</td>
			</tr>
	 		<tr>
				<td>班&nbsp;&nbsp;&nbsp;&nbsp;组: </td>
				<td>
					<c:forEach items="${dictTeamsList}" var="entry">
    	               <c:if test="${user.teamId==entry.teamId}">${entry.teamName}</c:if>
    	            </c:forEach>
				</td>
				<td>工号: </td>
				<td>
					${user.gonghao }
				</td>
			</tr>
			<tr>	
				<td>用户姓名: </td>
				<td>
					${user.name }
				</td>
				<td>拼音缩写: </td>
				<td>
					${user.py }
				</td>
			</tr>
			<tr>	
				<td>登陆名称: </td>
				<td>
					${user.username }
				</td>
				<td>登录密码: </td>
				<td>
					${user.password }
				</td>
			</tr>
			<tr>
				<td>用户ID: </td>
				<td>
				   ${user.userId }
				</td>
				<td>IDK卡号: </td>
				<td>
					${user.idnum }
				</td>
			</tr>
			<tr>
			    <td colspan="4" align="center"><input type="button" value="确定" onclick="toclose();"/></td>
			</tr>
			
		</table>
	</form>
</body>
</html>