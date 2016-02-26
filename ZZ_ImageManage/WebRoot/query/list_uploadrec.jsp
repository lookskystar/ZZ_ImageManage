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
</head>
<body>
	<div class="box2" panelTitle="机车图像指标列表" roller="false">
		<form action="<%=basePath%>queryAction!queryUploadRec.do" method="post">
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
				<td><input type="text" class="Wdate" onclick="WdatePicker(({dateFmt:'yyyy-MM-dd'}))" id="taskDate" name="taskDate" value="${taskDate }"/></td>
				<td><input type="submit" value="查询" class="icon_find"/></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="scrollContent">
		<table class="tableStyle" useMultColor="true" useCheckBox="true">
			<c:set var="workTypeOne"></c:set>
			<c:set var="workTypeTwo"></c:set>
			<c:set var="workTypeThr"></c:set>
			<c:set var="workTypeFour"></c:set>
			<tr align="center">
				<td rowspan="2" width="5%">编号</td>
				<td rowspan="2" width="8%">车型车号</td>
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
				<td rowspan="2" width="5%">异常</td>
				<td rowspan="2" width="20%">操作</td>
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
		</table>
		<table class="tableStyle" useMultColor="true" useCheckBox="true">
			<c:forEach items="${uploadRecList}" var="map" varStatus="s">
				 <tr align="center">	
				 	<td align="center" width="5%">${map.jcRecId }</td>
				 	<td align="center" width="8%">${map.jcType }-${map.jcNum }</td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeOne}&type=0">${map.onePic }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeOne}&type=1">${map.oneVid }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeTwo}&type=0">${map.twoPic }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeTwo}&type=1">${map.twoVid }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeThr}&type=0">${map.thrPic }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeThr}&type=1">${map.thrVid }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeFour}&type=0">${map.fourPic }</a></td>
				 	<td align="center" width="7%"><a href="<%=basePath%>queryAction!queryDetailUploadRecOnPlan.do?jcRecId=${map.jcRecId }&workId=${workTypeFour}&type=1">${map.fourVid }</a></td>
				 	<td align="center" width="5%">
				 		<c:choose>
				 			<c:when test="${map.status > 0}">
				 				<font style="color: red;">异常</font>
				 			</c:when>
				 			<c:otherwise>
				 				正常
				 			</c:otherwise>
				 		</c:choose>
				 	</td>
					<td align="center" width="20%">
						<a href="<%=basePath%>queryAction!constractTreeOfUploadRecOnPlan.do?jcRecId=${map.jcRecId }" style="color:blue;">详情</a>&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="videoInput('${map.jcRecId }');" style="color:blue;">视频播放</a>&nbsp;&nbsp;
						<a href="<%=basePath%>queryAction!reportInput.do?jcRecId=${map.jcRecId }" style="color:blue;">报告</a>
						<a href="ftp://10.172.2.239/" style="color:blue;" target="_blank" name="ftpUrl">影像地址</a>
					</td> 
				</tr>
			</c:forEach>
			<c:if test="${empty uploadRecList}">
				<tr align="center">	
				 	<td align="center" colspan="12">当前没有记录!</td>
				</tr>
			</c:if>
		</table>
	</div>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var ftpUrlDateArray = $("#taskDate").val().split("-");
			var ftpUrlDate = "";
			for(var i = 0; i < ftpUrlDateArray.length; i++){
				ftpUrlDate += ftpUrlDateArray[i];
			}
			$("a[name='ftpUrl']").each(function(){
				$(this).attr("href","ftp://10.172.2.239/" +ftpUrlDate);
			})

		});
	
		function videoInput(jcRecId){
			top.Dialog.open({URL:"<%=basePath%>queryAction!videoInputNew.do?jcRecId="+jcRecId,Width:900,Height:480,Title:"视频播放"});
		}

		function ajaxExplorerFtp(){
			var taskDate = $("#taskDate").val();
			$.ajax({
			   	type: "POST",
			   	url: "<%=basePath%>queryAction!ajaxExplorerFtp.do",
			   	data: {"taskDate": taskDate},
			   	success: function(msg){
			     	if(msg == "failure"){
			     		top.Dialog.alert("请输入正确的FTP地址！")
				    }
			   	}
			});
		}
	
	</script>
</body>
</html>