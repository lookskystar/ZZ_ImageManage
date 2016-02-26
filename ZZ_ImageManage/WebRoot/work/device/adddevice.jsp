<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	
	<!--引入组件start-->
	<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--修正IE6支持透明PNG图start-->
    <script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->
    <script type="text/javascript">
    	$(function(){
			$("#deviceCode").focus();
        });
    </script>


 </head>
<body>
<form action="deviceAction!addDevice.do" method="post" id="subForm" target="frmright" onsubmit="return checkform()">   
		<table class="tableStyle" transMode="true">
		    <tr>
				<td>地区: </td>
				<td>
					<select name="devicesInfo.areaId" class="default" value="${areaId }">
					     <c:forEach items="${dictareaslist}" var="entry">
    	                     <option value="${entry.areaId}" <c:if test="${entry.areaId==areaId}">selected="selected"</c:if>>${entry.areaName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
	 		<tr>
				<td>设备型号: </td>
                <td>
					<input  type="text" name="devicesInfo.devicesType" id="devicesType" value="XR-11plf"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>
				<td>设备编码: </td>
				<td>
				   <input  type="text" name="devicesInfo.deviceCode" id="deviceCode"></input><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>	
				<td>备注: </td>
				<td>
				   <textarea style="width:180px;height:90px;" name="devicesInfo.devicesNote" ></textarea>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="2">
					<input type="submit" value=" 提 交 "/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 重 置 "/>
				</td>
			</tr>
		</table>
		
  	</form>
  	  
</body>
     <script type="text/javascript">
		function checkform(){
			    if($("#devicesType").val()==''){
				    top.Dialog.alert("设备型号不能为空");
				    return false;
			    }
			    if($("#deviceCode").val()==''){
				    top.Dialog.alert("设备编码不能为空");
				    return false;
			    }
		}
		
		$(function(){
			$("#deviceCode").blur(function(){
				var deviceType=$("#devicesType").val();
				var deviceCode=$(this).val();
				if(deviceCode!=""){
					$.post("deviceAction!ajaxDeviceExist.do",{"deviceType":deviceType,"deviceCode":deviceCode},function(data){
						if(data=="success"){
							top.Dialog.alert("该编码设备已存在!",function(){
								$("#deviceCode").val("");
								$("#deviceCode").focus();
							});
						}
					});
				}
			});
		});
	</script>

</html>