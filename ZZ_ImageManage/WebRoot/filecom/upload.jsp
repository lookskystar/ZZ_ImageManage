<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>图像信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css" />
	<script type="text/javascript" src="uploadify/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#file_upload').uploadify({
				'swf'      : 'uploadify/uploadify.swf',
				'uploader' : 'repairJcAction!testFile.do',
				'cancelImg' : 'uploadify/cancel.png',
				'auto'	   : false,
				'multi':true,
				'buttonText':'选择文件',
				'fileObjName':'file',
				//'queueSizeLimit':4,
				'queueID':'fileQueue',
				'fileExt':'*.jpg;*.png;*.gif;',
				'method'   : 'post',
				//'uploadLimit':3,
    			'onUploadSuccess' : function(file, data, response) {
					if(data=="success"){
						$('#result').html(data.filesUploaded +'个图片上传成功');
					}else{
						alert("图片上传失败!");
					}
    			},
    			'onUploadError' : function(){
    				alert("图片上传失败!");
    			}
    			
				
			});
		});
	</script>
<style type="text/css">
.inputcss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	border:solid 1px #CCCCCC;
}
.buttoncss
{
	color:#333333;
	font-family: "Tahoma"; 
	font-size: 12px; 
	background-color:#FFFFFF;
	border:solid 1px #CCCCCC;
}
</style>
</head>
<body>
<table style="width: 90%;">
		<tr>
			<td style="width: 100px;">
				<input type="file" name="file_upload" id="file_upload" />
			</td>
			<td align="left">
				<a href="javascript: $('#file_upload').uploadify('upload','*');">开始上传</a>|
			<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>
			</td>
		</tr>
	</table>
		<div id="fileQueue" style="width: 400px;height: 300px; border: 2px solid green;"></div>
</body>
</html>
