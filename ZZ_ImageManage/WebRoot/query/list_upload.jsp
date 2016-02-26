<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript">
	
</script>
</head>
<body>
	<form action="<%=basePath%>queryAction!queryUploadRec.do" method="post">
	<div class="box1" roller="false">
		<table>
			<tr>
				<td>车型:</td>
				<td>
					<select id="jcType" name="jcType">
						<option value="">请选择</option>
						<c:forEach items="${jcTypeList }" var="type">
							<option value="${type }"<c:if test="${jcType==type}">selected="selected"</c:if>>${type }</option>
						</c:forEach>
					</select>
				</td>
				<td>车号:</td>
				<td><input type="text" name="jcNum" value="${jcNum }"/></td>
				<td>日期</td>
				<td><input type="text" class="Wdate" onclick="WdatePicker(({dateFmt:'yyyy-MM-dd'}))" name="taskDate" value="${taskDate }"/></td>
				<td><input type="submit" value="查询" class="icon_find"/></td>
			</tr>
		</table>
	</div>
	<div>
		<table class="tableStyle" >
			<c:set var="workTypeOne"></c:set>
			<c:set var="workTypeTwo"></c:set>
			<c:set var="workTypeThr"></c:set>
			<c:set var="workTypeFour"></c:set>
			<tr align="center">
				<td rowspan="2" width="10%">编号</td>
				<td rowspan="2" width="10%">车型</td>
				<td rowspan="2" width="10%">车号</td>
				<c:forEach items="${workTypeList}" var="workType" varStatus="s">
					<td colspan="2" width="15%">${workType.workType}</td>
					<c:if test="${s.index+1 == 1}">
						<c:set var="workTypeOne" value="${workType.workId}"></c:set> 
					</c:if>
					<c:if test="${s.index+1 == 2}">
						<c:set var="workTypeTwo" value="${workType.workId}"></c:set> 
					</c:if>
					<c:if test="${s.index+1 == 3}">
						<c:set var="workTypeThr" value="${workType.workId}"></c:set> 
					</c:if>
					<c:if test="${s.index+1 == 4}">
						<c:set var="workTypeFour" value="${workType.workId}"></c:set> 
					</c:if>
				</c:forEach>
				<td rowspan="2" width="10%">操作</td>
			 </tr>
			 <tr align="center">
				<td width="7%">图片数量</td>
				<td width="7%">视频数量</td>
				<td width="7%">图片数量</td>
				<td width="7%">视频数量</td>
				<td width="7%">图片数量</td>
				<td width="7%">视频数量</td>
				<td width="7%">图片数量</td>
				<td width="7%">视频数量</td>
			 </tr>
			<c:forEach items="${uploadRecList}" var="map" varStatus="s">
				 <tr align="center">	
				 	<td align="center">${map.jcRecId }</td>
				 	<td align="center">${map.jcType }</td>
				 	<td align="center">${map.jcNum }</td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeOne}&type=0">${map.onePic }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeOne}&type=1">${map.oneVid }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeTwo}&type=0">${map.twoPic }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeTwo}&type=1">${map.twoVid }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeThr}&type=0">${map.thrPic }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeThr}&type=1">${map.thrVid }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeFour}&type=0">${map.fourPic }</a></td>
				 	<td align="center"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeFour}&type=1">${map.fourVid }</a></td>
					<td align="center"><a href="<%=basePath%>queryAction!constractTreeOfUploadRecOnPlan.do?jcRecId=${map.jcRecId }">详情</a></td> 
				</tr>
			</c:forEach>
			<c:if test="${empty uploadRecList}">
				<tr align="center">	
				 	<td align="center" colspan="12">当前没有记录!</td>
				</tr>
			</c:if>
		</table>
	</div>
	</form>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</body>
</html>