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
			    if($("#teamName").val()==''){
					alert("班组名称不能为空！");
					return false;
				}
			}
		</script>
  </head>
<body>
  	<form action="usersAction!editDictTeams.do" method="post" id="subForm" target="frmright" onsubmit="return checkform()">   
  		<input type="hidden" value="${dictteams.teamId }" name="teamId"/>
		<table class="tableStyle" transMode="true">
		    <tr>
				<td>班组名称: </td>
				<td>
					<input type="text" name="dictteams.teamName" id="teamName" value="${dictteams.teamName }"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>
				<td>班组所属: </td>
				<td>
					<select name="dictteams.teamLevel">
						<option value="1" <c:if test="${dictteams.teamLevel==1}">selected="selected"</c:if>>段级部门</option>
						<option value="2" <c:if test="${dictteams.teamLevel==2}">selected="selected"</c:if>>所级部门</option>
					</select>
				</td>
			</tr>
            <tr>
				<td colspan="2">
					<input type="submit" value="修 改"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重 置 "/>
				</td>
			</tr>      
		</table>
  	</form>  
</body>

</html>