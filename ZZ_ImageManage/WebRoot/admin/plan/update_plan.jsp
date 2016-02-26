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
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>

<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--截取文字end-->
<script type="text/javascript">
   function checkform(){
	   if($("#proName").val()==""){
		   top.Dialog.alert("工序标准名称不能为空!");
		   return false;
	   }
	   if($("#proNum").val()==""){
		   top.Dialog.alert("工序编号不能为空!");
		   return false;
	   }
	   if($("#proSn").val()==""){
		   top.Dialog.alert("工序顺序不能为空!");
		   return false;
	   }
	   if($("#imageTimeDifference").val()==""){
		   top.Dialog.alert("标准作业时间不能为空!");
		   return false;
	   }
	   if($("#videoTime").val()==""){
		   top.Dialog.alert("标准视频时间不能为空!");
		   return false;
	   }
	   if($("#imageNum").val()==""){
		   top.Dialog.alert("拍照数量不能为空!");
		   return false;
	   }
	   if($("#proTimeDifference").val()==""){
		   top.Dialog.alert("工序作业时间不能为空!");
		   return false;
	   }
	   return true;
   }

   function check(num){
	   if(num!=""){
		   $.ajax({
			   type: "POST",
			   url: "presetImage!checkNum.do",
			   data: "proNum="+num,
			   success: function(msg){
			     if("success" == msg){
				     alert("该工序编码已经存在，请重新输入！");	
				     $("#proNum").val("");
				     $("#proNum").focus();			     
				 }
			   }
			}); 
	   }
   }
</script>
</head>
<body>
	<form action="planAction!updatePlan.do" method="post" target="frmright" onsubmit="return checkform()">
			<input type="hidden" name="planId" value="${trainPlan.id }"/>
			<table class="tableStyle" transMode="true">
				<tr>
					<td>车型：</td>
					<td>
						<select name="trainPlan.jcStyle" class="default" style="width:120px;">
						   <c:forEach var="jcStypes" items="${jcStypes}">
						      <option value="${jcStypes }" <c:if test="${jcStypes == trainPlan.jcStyle}">selected="selected"</c:if>>${jcStypes }</option>
						   </c:forEach>
						</select>						
					</td>
				</tr>
				<tr>
					<td>车号：</td>
					<td>
						<input class="easyui-validatebox" type="text" id="" value="${trainPlan.jcNum }" name="trainPlan.jcNum" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
					</td> 
				</tr>				
				<tr>
					<td>始发站：</td>
					<td>
						<input class="easyui-validatebox" type="text" id="" name="trainPlan.started" value="${trainPlan.started }"/>
					</td> 
				</tr>
				<tr>
					<td>终点站：</td>
					<td>
						<input class="easyui-validatebox" id="" type="text" name="trainPlan.ended" value="${trainPlan.ended }"/>
					</td>
				</tr>
			    <tr>
					<td>车次：</td>
					<td>
						<input class="easyui-validatebox" type="text" id="" name="trainPlan.cjNum" value="${trainPlan.cjNum }"/>
					</td>
				</tr>
				<tr>
					<td>计划时间：</td>
					<td>
					    <input type="text" class="Wdate" name="trainPlan.time" value="${trainPlan.time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:150px;"/>
					</td>
				</tr>
				<tr>
					<td>检查次数：</td>
					<td>
						<input class="easyui-validatebox" type="text" id="" name="trainPlan.jcCs" value="${trainPlan.jcCs }" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;次
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value=" 提 交 " />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 重 置 " />
					</td>
				</tr>
			</table>
	</form>
</body>
<link href="<%=basePath %>My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>My97DatePicker/WdatePicker.js"></script>
</html>