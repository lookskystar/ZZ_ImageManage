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
<script type="text/javascript">
	function sub(){
		$("#subForm").submit();
	}
</script>
</head>
<body>
  <form action="countJcAction!CountJc.do" method="post" id="subForm">
  	<div class="box2" panelTitle="车组统计" roller="false">
		<table>
			<tr>
				<td>查询日期:</td>
				<td><input type="text" id="date" value="${date}" class="Wdate" onclick="WdatePicker(({dateFmt:'yyyy-MM-dd'}))"  name="date"/></td>	
				<td><button onclick="sub();"><span class="icon_find" style="height: 22px;">查询</span></button></td>
			</tr>		
		</table>
	 </div>
	 <div id="scrollContent">
		<table class="tableStyle"  useMultColor="true" >
			<tr>
			  <th width="3%" align="center"></th>
			  <th width="30%" align="center"><span>动车所</span></th>	
		      <th width="20%" align="center"><span>车组总数</span></th>
		      <th width="20%" align="center"><span>异常车组数</span></th>
		      <th width="27%" align="center">操作</th>
		    </tr>
			<c:if test="${!empty countJc}">
			   	<c:forEach items="${countJc}" var="jc" >
			   		<tr>
			   			<td align="center"><input id="areaid" name="" type="checkbox" width="30px" value="${jc.area_id}"/></td>
			   			<td align="center"><span>${jc.area_name} </span></td>
						<td align="center"><span>${jc.totalNum}</span></td>
						<td align="center"><span>${jc.badNum}</span></td>
						<td align="center">
							<a onclick="view('${jc.area_id}');">查看详情</a>
						</td>
					</tr>
			   	</c:forEach>
			 </c:if>
			 <c:if test="${empty countJc}">
			 	<tr> 
			 		<td class="ver01" align="center" colspan="5">没有相应的数据记录!</td>
			 	</tr>
			 </c:if>							
		</table>
	</div>
  </form>	
</body>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
		//查看上传详情
		function view(areaid){
		top.Dialog.open({URL:"<%=basePath%>queryAction!queryUpload.do?taskDate=${date}&areaId="+areaid,Width:1400,Height:700,Title:"机车影像详情"});
    
		}
</script>
</html>