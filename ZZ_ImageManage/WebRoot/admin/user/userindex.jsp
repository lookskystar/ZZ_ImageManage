<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	<script type="text/javascript" src="js/tree/dtree/dtree.js"></script>
	<link href="js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
	
	<!--引入组件start-->
	<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<script type="text/javascript">
	$(function(){
		if(top.Dialog!=null){
    		top.Dialog.close();
    	}
		<c:if test="${!empty message}">
			top.Dialog.alert('${message}');
		</c:if>
	});

	//增加班组
	function toAdd(){
	   	top.Dialog.open({URL:"<%=basePath%>usersAction!addDictTeamsInput.do",Width:400,Height:250,Title:"添加班组"});
	}

	//修改班组
	function toEdit() {
		var teamId=document.getElementById("userIframe").contentWindow.document.getElementById("teamId").value;
		if(teamId!=null && teamId!=undefined && teamId!=''){
			top.Dialog.open({URL:"<%=basePath%>usersAction!editDictTeamsInput.do?teamId="+teamId,Width:400,Height:250,Title:"修改班组"});
		}else{
			top.Dialog.alert("请选择班组!");
		}	
	}

	//删除班组
	function toDelete(){
	    var teamId=document.getElementById("userIframe").contentWindow.document.getElementById("teamId").value;
		if(teamId!=null && teamId!=undefined && teamId!=''){
		    window.location.href="<%=basePath%>usersAction!delDictTeams.action?teamId="+teamId; 
		}else{
			top.Dialog.alert("请选择班组!");
		}	    
	}
	</script>
  </head>
  
 <body>
	 <div id="scrollContent">
	 	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	 	  	<tr>
		 		<td width="22%" class="ver01">
			 		<div class="box2"  panelTitle="班组目录" panelHeight="480" overflow="auto" showStatus="false">
			 			<div>
				 		   <button onclick="toAdd()"><span class="icon_add">新增</span></button>&nbsp;&nbsp;&nbsp;
				 		   <button  onclick="toEdit()"><span class="icon_edit">修改</span></button>&nbsp;&nbsp;&nbsp;
				 		   <button  onclick="toDelete()"><span class="icon_delete">删除</span></button>
			     		</div>
				 		<script type="text/javascript">
				 				var d = new dTree('d');
								d.add(0,-1,'全部','<%=basePath%>usersAction!listUsers.do','全部人员','userIframe');
								d.add('level_1',0,'段级部门',null,'','');
								d.add('level_2',0,'所级部门',null,'','');
								<c:forEach var="ite" items="${dictTeamsList}">
								   d.add(${ite.teamId},'level_${ite.teamLevel}','${ite.teamName}','<%=basePath%>usersAction!listUsers.do?teamId=${ite.teamId}','${ite.teamName}','userIframe');
								</c:forEach>
								document.write(d);
				 		</script>
			 		</div>
		 	    </td>
		 		<td class="ver01">
		 			<iframe scrolling="no" width="100%" frameBorder=0 id="userIframe" name="userIframe" onload="iframeHeight('userIframe')" src="<%=basePath%>usersAction!listUsers.do"  allowTransparency="true"></iframe>
		 		</td>
			</tr>
	 	</table>
	</div>
</body>
</html>