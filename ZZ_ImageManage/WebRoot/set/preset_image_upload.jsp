<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.image.common.util.PropertyUtil"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ftpPath = "ftp://"+session.getAttribute("session_ftp_url")+":"+session.getAttribute("session_ftp_port")+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>

<script type="text/javascript" src="js/pic/jomino.js"></script>
<script type="text/javascript" src="js/highslide/highslide-with-gallery.js"></script>
<link rel="stylesheet" type="text/css" href="js/highslide/highslide.css" />
<!--框架必需end-->

<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--截取文字end-->
<script>
	//图片弹窗
	hs.graphicsDir = 'js/highslide/graphics/';
	hs.align = 'center';
	hs.transitions = ['expand', 'crossfade'];
	hs.outlineType = 'rounded-white';
	hs.fadeInOut = true;
	
	hs.addSlideshow({
		interval: 2000,
		repeat: false,
		useControls: true,
		fixedControls: 'fit',
		overlayOptions: {
			opacity: .75,
			position: 'bottom center',
			hideOnMouseOut: true
		}
	});	

	function add(){
		top.Dialog.open({URL:"set/step_add.jsp?nodeId=${procedure.proId}",Width:380,Height:440,Title:"新增工序标准图片"});
	}
	//编辑
	function editImage(imageId){
		top.Dialog.open({URL:"presetImage!updateNodeImageInput.do?imageId="+imageId,Width:380,Height:440,Title:"更新图片"});
	}
	//删除
	function deleteImage(imageId){
		top.Dialog.confirm("确定要删除吗？",function(){
			$.post("presetImage!deleteNodeImage.do",{"imageId":imageId},function(text){
				if(text=="success"){
					top.Dialog.alert("操作成功！",function(){
						top.frames[2].location.href="<%=basePath%>presetImage!presetImageUplodInput.do?id=${procedure.proId}";
					});
				}
			});
		});
	}
</script>
</head>
<body>
<div class="box1" id="imgList">
	<button onclick="add()"><span class="icon_add">新增标准图片</span></button><br/>
	工序名称：${procedure.proName}&nbsp;&nbsp;工序编号：${procedure.proNum }&nbsp;&nbsp;工序大类：${procedure.dictWorktype.workType }&nbsp;&nbsp;标准作业时间（分钟）：${procedure.imageTimeDifference}
	&nbsp;&nbsp;标准摄像时间（分钟）：${procedure.videoTime }&nbsp;&nbsp;拍照数量：${procedure.imageNum}<br/>备注：${procedure.proNote }
	
	<c:forEach var="procedureStep" items="${procedureStep}">
		<div class="box1">
			<div class="picItem2">
				<a href="<%=ftpPath%>${procedureStep.stepImage}" class="highslide" onclick="return hs.expand(this)">
					<img src="<%=ftpPath%>${procedureStep.prestepImage}"/>
				</a>
				<div>				
					<a href="javascript:deleteImage(${procedureStep.stepId});" style="color:red;">删除</a>
					<a href="javascript:editImage(${procedureStep.stepId});" style="color:red;">编辑</a>
					<br/>标题：${procedureStep.stepName }<br/>
					顺序号：${procedureStep.proSn}<br/>
				</div>
			</div>		
		</div>
	</c:forEach>
	<div class="clear"></div>
</div>
</body>
</html>