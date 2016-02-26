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
	<!--树start-->
	<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
	<link href="js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
	<!--树end-->
	<script type="text/javascript">
		$(document).ready(function(){
			var height = window.innerHeight
			|| document.documentElement.clientHeight
			|| document.body.clientHeight;
			var offsetheight = document.getElementById("tree").offsetHeight; 
			$("#blank").css("height", height - offsetheight);
			$("#tree").css("height", height);
			$("#tree").attr("panelHeight", height);
			$("#imageIframe").css("height", height);
		});
	</script>
</head>
<body>
 	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
 		<tr>
 			<td class="ver01" width="20%" >
				<div id="tree" class="box4" showStatus="false" > 
					<div  style="margin:5px 10px 5px 10px;float: left;">
						<a href="javascript: d.openAll();">展开所有</a>
					</div>
					<div  style="margin:5px 10px 5px 10px;float: left;">
						<a href="javascript: d.closeAll();">关闭所有</a>
					</div>
					<div style="margin-top: 5px;">
						<script type="text/javascript">
								d = new dTree('d');
								<c:forEach items="${dataMap}" var="jcRec">
									d.add(0,-1,'${jcRec.key.jcType}-${jcRec.key.jcNum}','<%=basePath%>queryAction!queryUploadRecOnPlan.do?jcRecId=${jcRecId }','查看整车','imageIframe');
									<c:forEach items="${jcRec.value}" var="type" varStatus="types">
										d.add(${types.index+100 },0,'${type.key.workType }','<%=basePath%>queryAction!queryUploadRecOnPlan.do?jcRecId=${jcRecId }&workId=${type.key.workId}','${type.key.workType }','imageIframe');
										<c:forEach items="${type.value}" var="pro" varStatus="pros">
											d.add(${pros.index+1 },${types.index+100 },'${pro.proName}','<%=basePath%>queryAction!queryUploadRecOnPlan.do?jcRecId=${jcRecId }&workId=${type.key.workId}&proId=${pro.proId}','${pro.proName}','imageIframe');
										</c:forEach>
									</c:forEach>
								</c:forEach>
								document.write(d);
						</script>
					</div>
					<div id="blank"></div>
				</div>
			</td>
			<td valign="top">
				<iframe scrolling="auto" width="100%" frameBorder=0 id=imageIframe name=imageIframe onload="iframeHeight('imageIframe')" src="<%=basePath%>queryAction!queryUploadRecOnPlan.do?jcRecId=${jcRecId }"  allowTransparency="true"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>