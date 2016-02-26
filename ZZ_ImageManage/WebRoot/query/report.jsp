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
</head>
<body>
	<c:forEach items="${uploadRecList}" var="map" varStatus="s">
		<div>
			<div>
				<h2 align="center" style="margin-top: 50px;"><font color="blue">${map.jcType }&nbsp;&nbsp;${map.jcNum }</font>号动车组<font color="blue">${jcRec.taskDate }</font>日质量分析报告</h2>
					<div style="margin-left: 100px;margin-right: 100px;font-size: 14px;">
					<center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">${map.jcType }&nbsp;&nbsp;${map.jcNum }</font>号动车组，于&nbsp;&nbsp;<font color="blue">${jcRec.taskDate }</font>&nbsp;&nbsp;日，在广州动车段&nbsp;&nbsp;<font color="blue">${area.areaName }</font>&nbsp;&nbsp;动车所检修完毕。
					其中:
					<c:forEach items="${workTypeList}" var="workType" varStatus="s">
						<br/>${workType.workType}机械师共拍摄
						<c:if test="${workType.workId == 1}">
							<font color="blue">&nbsp;&nbsp;${map.onePic }&nbsp;&nbsp;</font>张照片，<font color="blue">&nbsp;&nbsp;${map.oneVid }&nbsp;&nbsp;</font>段视频;<br/>
						</c:if>
						<c:if test="${workType.workId == 2}">
							<font color="blue">&nbsp;&nbsp;${map.twoPic }&nbsp;&nbsp;</font>张照片，<font color="blue">&nbsp;&nbsp;${map.twoVid }&nbsp;&nbsp;</font>段视频;<br/>
						</c:if>
						<c:if test="${workType.workId == 3}">
							<font color="blue">&nbsp;&nbsp;${map.thrPic }&nbsp;&nbsp;</font>张照片，<font color="blue">&nbsp;&nbsp;${map.thrVid }&nbsp;&nbsp;</font>段视频;<br/>
						</c:if>
						<c:if test="${workType.workId == 4}">
							<font color="blue">&nbsp;&nbsp;${map.fourPic }&nbsp;&nbsp;</font>张照片，<font color="blue">&nbsp;&nbsp;${map.fourVid }&nbsp;&nbsp;</font>段视频。<br/>
						</c:if>
					</c:forEach></center>
					</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>