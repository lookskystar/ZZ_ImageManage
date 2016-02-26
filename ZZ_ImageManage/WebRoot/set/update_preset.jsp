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
   function check(num){
	 if(num!=""){
	  $.ajax({
	   type: "POST",
	   url: "presetImage!checkNum.do",
	   data: "proNum="+num,
	   success: function(msg){
	     if("success" == msg){
		     alert("该工序编码已经存在，请重新输入！");	
		     $("#num").val("");
		     $("#num").focus();		     
		 }
	   }
	}); 
	}
   }
</script>
</head>
<body>
	<form action="presetImage!updatePreset.do" method="post" target="frmright" onsubmit="return checkform()">
		<input type="hidden" value="${procedureInfo.proId }" name="id"/>
		<table class="tableStyle" transMode="true">
			<tr>
				<td>工序标准名称：</td>
				<td>
					<input class="easyui-validatebox" type="text" name="procedureInfo.proName" value="${procedureInfo.proName }"></input>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>车型：</td>
				<td>
					<select id="" name="procedureInfo.jcType" class="default" style="width:120px;">
					   <c:forEach var="jcStypes" items="${jcStypes}">
					      <option value="${jcStypes }" <c:if test="${jcStypes==procedureInfo.jcType}">selected="selected"</c:if>>${jcStypes }</option>
					   </c:forEach>
					</select>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>工序编号：</td>
				<td>
					<input class="easyui-validatebox" id="num" type="text" maxlength="2" onblur="check(this.value);" name="procedureInfo.proNum" value="${procedureInfo.proNum }" onkeyup="this.value=this.value.replace(/\D/g,'')"></input>
					<font color="red">*必须为数字</font>
				</td> 
			</tr>
			<tr>
				<td>工序顺序：</td>
				<td>
					<input class="easyui-validatebox" type="text" name="procedureInfo.proSn" value="${procedureInfo.proSn }" onkeyup="this.value=this.value.replace(/\D/g,'')"></input>
					<font color="red">*必须为数字</font>
				</td> 
			</tr>
			<tr>
				<td>标准作业时间（分钟）：</td>
				<td>
					<input class="easyui-validatebox" type="text" name="procedureInfo.imageTimeDifference" value="${procedureInfo.imageTimeDifference }" onkeyup="this.value=this.value.replace(/\D/g,'')">&nbsp;&nbsp;分钟</input>
					<font color="red">*</font>
				</td>
			</tr>
		    <tr>
				<td>标准视频时间（分钟）：</td>
				<td>
					<input class="easyui-validatebox" type="text" name="procedureInfo.videoTime" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${procedureInfo.videoTime }">&nbsp;&nbsp;分钟</input>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>拍照数量：</td>
				<td>
				    <input class="easyui-validatebox" type="text" name="procedureInfo.imageNum" value="${procedureInfo.imageNum }" onkeyup="this.value=this.value.replace(/\D/g,'')"></input>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>工序作业时间间隔：</td>
				<td>
					<input class="easyui-validatebox" type="text" name="procedureInfo.proTimeDifference" value="${procedureInfo.proTimeDifference }" onkeyup="this.value=this.value.replace(/\D/g,'')">&nbsp;&nbsp;分钟</input>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>工序大类：</td>
				<td>
					<select name="procedureInfo.dictWorktype.workId" class="default" style="width:120px;">
					   <c:forEach var="workers" items="${workers}">
					      <option value="${workers.workId }" <c:if test="${workers.workType==procedureInfo.dictWorktype.workType}">selected="selected"</c:if>>${workers.workType }</option>
					   </c:forEach>
					</select>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
				    <textarea rows="" cols="" name="procedureInfo.proNote">${procedureInfo.proNote }</textarea>
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
</html>