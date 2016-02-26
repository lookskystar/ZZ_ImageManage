<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ftpPath = "ftp://"+session.getAttribute("session_ftp_url")+":"+session.getAttribute("session_ftp_port")+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
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
	<script type="text/javascript" src="js/pic/jomino.js"></script>
	<script type="text/javascript" src="js/highslide/highslide-with-gallery.js"></script>
	<link rel="stylesheet" type="text/css" href="js/highslide/highslide.css" />
	<script type="text/javascript">
		$(document).ready(function(){
			var height = window.innerHeight
			|| document.documentElement.clientHeight
			|| document.body.clientHeight;
			var offsetheight = document.getElementById("scrollContent").offsetHeight; 
			$("#blank").css("height", height - offsetheight);
			$("#scrollContent").css("height", height);
			$("#scrollContent").attr("panelHeight", height);
		});
	
		//图片弹窗
		hs.graphicsDir = 'js/highslide/graphics/';
		hs.align = 'center';
		hs.transitions = ['expand', 'crossfade'];
		hs.outlineType = 'rounded-white';
		hs.fadeInOut = true;
	
		hs.addSlideshow({
			interval: 2000,
			repeat: true,
			useControls: true,
			fixedControls: 'fit',
			overlayOptions: {
				opacity: .75,
				position: 'bottom center',
				hideOnMouseOut: true
			}
		});	
	
		//标题特效
		$(function(){	
			//入场特效
			//$("#imgList .picItem2").jomino();
		})
        	
		function videoInput(url){
			top.Dialog.open({URL:"<%=basePath%>queryAction!videoInputNew.do?jcRecId=${jcRecId}&url="+url,Width:900,Height:480,Title:"视频播放"});
		}
	</script>
</head>
<body>
	<div id="scrollContent" style="overflow: auto;">
		<c:forEach items="${dataMap }" var="dataMap">
				<c:forEach items="${dataMap.value}" var="map">
					<div class="box2"  style="overflow: auto;" id="imgList" panelTitle="${dataMap.key.workType}-${map.key.procedureInfo.proName}-标准图片" showStatus="false">
						<c:forEach items="${map.value.standar}" var="stdRec">
							<div style="float: left;margin-left: 5px;margin-top: 5px;">
								<a href="<%=ftpPath%>${stdRec.prestepImage }" class="highslide" onclick="return hs.expand(this)">
									<img src="<%=ftpPath%>${stdRec.prestepImage }" width="210px" height="130px"/>
								</a>
							</div>	
						</c:forEach>
					</div>
					<div class="box2"  style="overflow: auto;" id="imgList" panelTitle="${dataMap.key.workType}-${map.key.procedureInfo.proName}-${map.key.procedureRank}-图片" showStatus="false">
						<c:forEach items="${map.value.image}" var="picRec">
							<div style="float: left;margin-left: 5px;margin-top: 5px;">
								<a href="<%=ftpPath%>${picRec.url }" class="highslide" onclick="return hs.expand(this)">
								    <c:if test="${empty picRec.preurl}">
								      <img src="<%=basePath%>images/pre.jpg"/>
								    </c:if>
								    <c:if test="${!empty picRec.preurl}">
										<img src="<%=ftpPath%>${picRec.preurl }" width="210px" height="130px"/>
									</c:if>
								</a>
								<div>工&nbsp;&nbsp;作&nbsp;&nbsp;者：${picRec.takeName }</div>
								<div>上传时间：${picRec.uploadTime }</div>
								<div>拍摄时间：${picRec.takeTime }</div>
							</div>
						</c:forEach>
					</div>
					<div class="box2"  style="overflow: auto;" id="imgList" panelTitle="${dataMap.key.workType}-${map.key.procedureInfo.proName}-${map.key.procedureRank}-视频" showStatus="false">
						<c:forEach items="${map.value.video}" var="videoRec">
							<div style="float: left;margin-left: 5px;margin-top: 5px;">
								<a href="javascript:void(0);" class="highslide" onclick="videoInput('${videoRec.url }');">
									<img src="<%=basePath%>images/video.jpg" width="210px" height="130px"/>
								</a>
								<div>工&nbsp;&nbsp;作&nbsp;&nbsp;者：${videoRec.takeName }&nbsp;&nbsp;&nbsp;&nbsp;视频时长：${videoRec.videoTime }分钟</div>
								<div>上传时间：${videoRec.uploadTime }</div>
								<div>拍摄时间：${videoRec.takeTime }</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
		</c:forEach>
		<div id="blank"></div>
	</div>
</body>
</html>