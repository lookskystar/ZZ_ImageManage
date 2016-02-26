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
	function add(){		
		top.Dialog.open({URL:"<%=basePath%>presetImage!inputAddProcedure.do",Width:550,Height:450,Title:"添加工序标准"});
	}
	
	function update(id){
		top.Dialog.open({URL:"<%=basePath%>presetImage!updatePresetInput.do?id="+id,Width:550,Height:450,Title:"修改工序标准"});
	}
	
	function del(id){
		top.Dialog.confirm("确定要删除此节点信息吗?",function(){
			window.location.href="<%=basePath%>presetImage!deletePresetNode.do?id="+id;
		});
	}
	
	$(function(){
    	<c:if test="${!empty message}">
    	    top.Dialog.close();
			top.Dialog.alert('${message}');
	    </c:if>
    });
</script>
</head>
<body>
<div>
  <form action="presetImage!presetListByName.do" method="post">
		<table>
			<tr>
				<td>
				    <button onclick="add();"><span class="icon_add">添加工序标准</span></button>
				</td>
				<td>车型：</td>
				<td><select name="jcStypes" class="default" style="width:110px;">
						   <option value="">全部</option>	
						   <c:forEach var="jc" items="${jcStypes}">
						      <option value="${jc }" <c:if test="${jc==jcStype}">selected="selected"</c:if>>${jc }</option>
						   </c:forEach>
				</select></td>
				<td>工序名称：</td>
				<td><select name="procedure" class="default" style="width:110px;">
					   <option value="">全部</option>	 	
					   <c:forEach var="procedure" items="${procedure}">
					      <option value="${procedure}" <c:if test="${procedure==proce}">selected="selected"</c:if>>${procedure}</option>
					   </c:forEach>
					</select>
				</td>				
				<td><input type="submit" value="查   询"/></td>
			</tr>		
		</table></form>
	</div>
<div id="scrollContent">
	<table class="tableStyle">
		<tr">
			<th width="50px">序号</th>
			<th width="8%">工序名称</th>
			<th width="5%">车型</th>
			<th width="6%">工序编号</th>
			<th width="8%">工序大类</th>
			<th width="6%">工序顺序</th>
			<th width="11%">标准作业时间(min)</th>
			<th width="11%">标准摄像时间(min)</th>
			<th width="5%">拍照数量</th>
			<th width="5%">工序间隔</th>
			<th width="10%">备注</th>
			<th width="20%">操作</th>
		</tr>
		<c:forEach items="${procedures.datas}" var="gx">
		    <tr align="center">
				<td >${gx.proId }</td>
				<td >${gx.proName }</td>
				<td >${gx.jcType }</td>
				<td >${gx.proNum }</td>
				<td >${gx.dictWorktype.workType }</td>
				<td >${gx.proSn }</td>
				<td >${gx.imageTimeDifference }</td>	
				<td >${gx.videoTime }</td>		
				<td >${gx.imageNum }</td>
				<td >${gx.proTimeDifference }</td>		
				<td >${gx.proNote }</td>
				<td >
				     <a href="javascript:void(0)" onclick='javascript:top.Dialog.open({URL:"<%=basePath%>presetImage!presetImageUplodInput.do?id=${gx.proId}",Width:"100",Height:"100",Title:"标准图片"});' style="color:blue;">
				     	查看详情/上传标准图片</a>&nbsp;&nbsp;&nbsp;
				     <a href="javascript:void(0);" onclick="update(${gx.proId});" style="color:blue;">修改</a>&nbsp;&nbsp;&nbsp;
				  <a href="javascript:void(0);" onclick="del(${gx.proId});" style="color:blue;">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

	<!-- 处理分页 -->
	共有信息${procedures.count }条,一页可显示${pageSize}条记录。
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
			<pg:pager maxPageItems="${pageSize }" url="presetImage!presetList.do" items="${procedures.count }" export="page1=pageNumber">		
			    <pg:param name="jcStypes" value="${jcStypes}"/>
			    <pg:param name="procedure" value="${procedure}"/>
		  		<pg:first>
					 <span><a href="${pageUrl }" id="first">首页</a></span>
		 		</pg:first>
		 		<pg:prev>
					  <span><a href="${pageUrl }">上一页</a></span>
		  		</pg:prev>
	  	  		<pg:pages>
					<c:choose>
						<c:when test="${page1==pageNumber }">
							<span class="paging_current"><a href="javascript:;">${pageNumber }</a></span>
						</c:when>
						<c:otherwise>
							<span><a href="${pageUrl }">${pageNumber }</a></span>
						</c:otherwise>
					</c:choose>
		  		</pg:pages>
		  		<pg:next>
				    <span><a href="${pageUrl }">下一页</a></span>
		  		</pg:next>
		  		<pg:last>
				  	<span><a href="${pageUrl }">末页</a></span>
		 		</pg:last>
		 	</pg:pager>
		</div>
		<div class="clear"></div>
	</div>		
</body>
</html>