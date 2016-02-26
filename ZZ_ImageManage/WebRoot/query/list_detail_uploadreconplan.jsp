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
		
		function videoInput(url){
			top.Dialog.open({URL:"<%=basePath%>queryAction!videoInputNew.do?jcRecId=${jcRecId}&url="+url,Width:900,Height:480,Title:"视频播放"});
		}

	</script>
</head>
<body>
	<div id="scrollContent" overflow="auto">
		<c:forEach items="${dataMap }" var="map">
			<c:forEach items="${map.value}" var="proMap">
				<c:forEach items="${proMap.value}" var="proecMap">
					<div class="box2" overflow="auto" panelTitle="${map.key.workType }-${proMap.key.proName }-${proecMap.key.procedureRank}" showStatus="false">
						<c:forEach items="${proecMap.value}" var="rec">
							<c:if test="${type == 0}">
								<div style="float: left;margin-left: 5px;margin-top: 5px;">
									<a href="<%=ftpPath%>${rec.preurl }" class="highslide" onclick="return hs.expand(this)">
										<img src="<%=ftpPath%>${rec.preurl }" width="210px" height="130px"/>
									</a>
									<div>作&nbsp;&nbsp;业&nbsp;&nbsp;者：${rec.takeName }</div>
									<div>上传时间：${rec.uploadTime }</div>
									<div>拍摄时间：${rec.takeTime }</div>
								</div>
							</c:if>
							<c:if test="${type == 1}">
								<div style="float: left;margin-left: 5px;margin-top: 5px;">
									<a href="javascript:videoInput('${rec.url }');" >
										<img src="<%=basePath%>images/video.jpg" width="210px" height="130px"/>
									</a>
									<div>作&nbsp;&nbsp;业&nbsp;&nbsp;者：${rec.takeName }&nbsp;&nbsp;&nbsp;&nbsp;视频时长：${rec.videoTime }分钟</div>
									<div>上传时间：${rec.uploadTime }</div>
									<div>拍摄时间：${rec.takeTime }</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</div>
</body>
</html>