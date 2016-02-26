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
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/framework.js"></script>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	
	<!--引入组件start-->
	<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
	<script type="text/javascript" src="js/nav/jquery.layout.js"></script>
	<script type="text/javascript" src="js/nav/layout.js"></script>
	<!--引入弹窗组件end-->
	<link rel="stylesheet" href="ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="ztree/js/jquery.ztree.core-3.5.js"></script>
	
	<script type="text/javascript" src="js/upload-image.js"></script>
	<script type="text/javascript">
		//上传图片
//<span class="icon_no hand" onclick='top.Dialog.open({URL:"filecom/filecom.jsp",Width:900,Height:600,Title:"图像上传"});'>图像上传</span>
		function upload(){
			var imgUrls=[];
			$("input[name='checkb']:checked").each(function(){
				imgUrls.push($(this).val());
			});
			if(imgUrls.length==0){
				top.Dialog.alert("请选择图片");
			}else{
				alert(imgUrls.join(","));
			}
			//showProgressBar("正在上传中...");
			//top.Dialog.close();
			//top.Dialog.alert("上传成功!");
		}
	</script>
</head>
<body rel="layout">
	 <div class="ui-layout-west">
        <div class="header">图像采集仪</div>
	    <div class="content">
	    	<div id="tree" class="ztree"></div>
        </div>
    </div>
    <div class="ui-layout-center">
        <div class="header">
        	<table><tr>
        		<td width="50px"><input type="checkbox" name="all" onclick="checkAll(this)"/>全选</td>
        		<td><button onclick="upload();"><span class="icon_add">开始上传</span></button></td>
        	</tr></table>
        </div>
        <div class="content" id="content">
        </div>
   </div>
</body>
</html>
