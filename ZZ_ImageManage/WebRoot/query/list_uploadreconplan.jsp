<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>" />
<!--框架必需end-->
<!--截取文字start-->
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--截取文字end-->
</head>
<body>
	<div id="scrollContent" class="box4" style="overflow: auto;">
		<table class="tableStyle">
			<tr align="center">
				<th width="5%">编号</th>
				<th width="10%">车型</th>
				<th width="10%">车号</th>
				<th width="5%">工序大类</th>
				<th width="10%">工序</th>
				<th width="5%">工序次数</th>
				<th width="5%">工作者</th>
				<th width="15%">标准</th>
				<th width="5%">实拍数量</th>
				<th width="5%">实拍时长</th>
				<th width="10%">异常情况</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${uploadRecListOnPlan }" var="rec" varStatus="s">
				<tr align="center">
					<td>${s.index+1 }</td>
					<td>${rec.jcType }</td>
					<td>${rec.jcNum }</td>
					<td>${rec.workType }</td>
					<td>${rec.proName }</td>
					<td>${rec.proRank }</td>
					<td>${rec.takeName }</td>
					<td>照片数量：${rec.imageNumStd }<br />视频时长：${rec.videoTimeStd }分钟<br />
					</td>
					<td>${rec.imageNumAct }</td>
					<td>${rec.videoTimeAct }</td>
					<td><c:choose>
							<c:when test="${rec.status == 1 }">
								<font style="color: red;">数量异常</font>
							</c:when>
							<c:when test="${rec.status == 2 }">
								<font style="color: green;">时间异常</font>
							</c:when>
							<c:otherwise>
								正常
							</c:otherwise>
						</c:choose></td>
					<td><a
						href="<%=basePath%>queryAction!queryTakeDetailRecOnProec.do?proecId=${rec.proecId }&workId=${rec.workId }&jcRecId=${jcRecId}"
						style="color:blue;">查看详情</a>
					</td>
				</tr>
			</c:forEach>
			
			
		
			<c:if test="${empty uploadRecListOnPlan}">
				<tr align="center">
					<td colspan="12">当前没有数据!</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>