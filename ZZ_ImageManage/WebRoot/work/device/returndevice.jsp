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


 </head>
<body>
<form action="deviceAction!returnDevice.do" method="post" id="subForm" target="frmright" onsubmit="return checkform()">
<input type="hidden" value="${device.devicesId }" name="devicesId"/>
<input type="hidden" id="teamName" name="devicesInfo.teamName" value=""/>   
<input type="hidden" id="returnName" name="devicesInfo.returnName" value=""/>
		<table class="tableStyle" transMode="true">
		
	 	<tr>
				<td>班组: </td>
				<td>
					<select id="teamId"  name="devicesInfo.teamId" class="default" onchange="getReturnName();">
					     <option value="">请选择班组</option>
					     <c:forEach items="${dictteamslist}" var="entry">
    	                     <option value="${entry.teamId}" <c:if test="${device.teamId==entry.teamId}">selected="selected"</c:if>>${entry.teamName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
		 	<%--<tr>
				<td>归还地区: </td>
				<td>
					<select id="areaId"  name="devicesInfo.areaId" class="default">
					     <option value="">请选择地区</option>
					     <c:forEach items="${dictareaslist}" var="entry">
    	                     <option value="${entry.areaId}">${entry.areaName}</option>
    	                 </c:forEach>
					</select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>   --%>
			<tr>
				<td>归还人: </td>
				<td>
				    <select id="returnId" name="devicesInfo.returnId" class="default" onchange="setReturnName(this);">
				     <option value="">选择归还人</option>
				   </select><font color="red">&nbsp;&nbsp;*</font>
				</td>
			</tr>
			<tr>
				<td>归还时间: </td>
				<td>
				   <input type="text" name="devicesInfo.returnTime" id="returnTime" class="Wdate" onclick="WdatePicker(({dateFmt:'yyyy-MM-dd HH:mm'}))" value="${ntime }"></input><font color="red">&nbsp;&nbsp;*</font>
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
  	   <link href="<%=basePath %>My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
  	  <script type="text/javascript" src="<%=basePath %>My97DatePicker/WdatePicker.js"></script> 
</body>
     <script>
		function checkform(){
		   if($("#teamId").val()==''){
				    alert("班组不能为空");
				    return false;
			    }else{
				    var teamName=$("#teamId").find("option:selected").text();
				    $("#teamName").val(teamName);
			    }
		   <%--    if($("#areaId").val()==''){
				    alert("归还地区不能为空");
				    return false;
			    }
			    --%> 
			    var returnName=$("#returnId").find("option:selected").text();
			    $("#returnName").val(returnName);
			    if($("#returnTime").val()==''){
				    alert("归还时间不能为空");
				    return false;
			    }
			    
		}
		
		function getReturnName(){
			var teamId=$("#teamId").val();
			var receiverId = '${device.receiverId}';
			if(teamId!=""){
				$.post("deviceAction!ajaxGetTeamUser.do",{"teamId":teamId},function(data){
					var datas=eval("("+data+")");
					var result=datas.users;
					var str="<option value=''>选择归还人</option>";
					if(result.length!=0){
						for(var i=0;i<result.length;i++){
							if(receiverId==result[i].userId){
								str+="<option value="+result[i].userId+" selected='selected'>"+result[i].userName+"</option>";
							}else{
								str+="<option value="+result[i].userId+">"+result[i].userName+"</option>";
							}
						}
						$("#returnId").children().remove();
						$("#returnId").append(str);
					}else{
						$("#returnId").children().remove();
						$("#returnId").append(str);
					}
				});
			}
		}
	    
		function setReturnName(obj){
			var v=$(obj).val();
			if(v!=""){
				var userName=$("#returnId").find("option:selected").text();
				$("#returnName").val(userName);
			}
			
		}
		
		$(function(){
			getReturnName();
		});
	</script>

</html>