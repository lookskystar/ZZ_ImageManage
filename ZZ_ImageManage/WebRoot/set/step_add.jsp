<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("nodeId",request.getParameter("nodeId"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>

<script type="text/javascript" src="js/attention/prettyLoader.js"></script>
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<!--截取文字end-->
<script type="text/javascript">
   //预览图片
   function previeImage(obj){
		if( !obj.value.match( /.jpg|.gif|.png|.bmp/i ) ){
			obj.value="";   
		    $("#preImage").html('<img src="images/pre.jpg"/>');  
		    top.Dialog.alert('图片格式无效！',function(){
			   $("#fileInput").html('<input type="file" onchange="previeImage(this)" id="file" name="file" class="default"/>');  
		    });
		}else{
			$("#preImage").html("<div style=\"height:170px; width:170px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+obj.value+"', sizingMethod='scale')\"></div>");
		}
   }
   
   //提交表单
   function submitForm(){
   		var title = $("#title").val();
   		var stepNum = $("#stepNum").val();
   		var imgurl = $("#file").val();
   		var qita = $("#qita").val();
   		if(title==""){
   			top.Dialog.alert('标题不能为空！');
		    return ;
   		}
   		if(stepNum=="" || isNaN(stepNum)){
   			top.Dialog.alert('顺序号不能为空,且只能输入数字！');
		    return ;
   		}
   		if(imgurl==""){
   			top.Dialog.alert('上传图片不能为空！');
		    return ;
   		}
   		if(!imgurl.match( /.jpg|.gif|.png|.bmp/i ) ){
		    top.Dialog.alert('图片格式无效！');
		    return ;
		}
		$.prettyLoader.show();
		$.ajaxFileUpload ({ 
			url :'presetImage!nodeImageUpload.do?nodeId=${nodeId}&title='+title+"&stepNum="+stepNum+"&qita="+qita, 
			secureuri :false, 	
			fileElementId :'file', 
			dataType : 'text', 
			success : function (data, status){ 
				top.Dialog.alert("操作成功！",function(){
					top.frames[2].location.href="<%=basePath%>presetImage!presetImageUplodInput.do?id=${nodeId}";
					top.Dialog.close();
				});
				$.prettyLoader.hide();
			}
		}) ;
   }
</script>
</head>
<body>
	<form action="" method="post" id="form" name="form" enctype="multipart/form-data">
			<table class="tableStyle" transMode="true">
				<tr>
					<td>标题：</td>
					<td>
						<input class="easyui-validatebox" type="text" name="title" id="title"></input><span class="star">*</span>
					</td>
				</tr>
				<tr>
					<td>顺序号：</td>
					<td>
						<input class="easyui-validatebox" type="text" name="stepNum" id="stepNum" onkeyup="this.value=this.value.replace(/\D/g,'')"></input><span class="star">*请输入数字</span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
					<div id="preImage" style="border:1px solid #f0f0f0;width:170px;height:170px;">
						<img src="images/pre.jpg"/>
					</div>
					</td>
				</tr>
				<tr>
					<td>上传图片：</td>
					<td>
						<span id="fileInput">
							<input type="file" onchange="previeImage(this)" id="file" name="file" class="default"/><span class="star">*</span>
						</span>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<span class="float_left">
					    	<textarea rows="" cols="" name="qita" id="qita"></textarea>
					    </span>
					    <span class="img_light" style="height:80px;" title="限制在200字以内"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value=" 提 交 " onclick="submitForm();"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 重 置 " />
					</td>
				</tr>
			</table>
	</form>
</body>
</html>