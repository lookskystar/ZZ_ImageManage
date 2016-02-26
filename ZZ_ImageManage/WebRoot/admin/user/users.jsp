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
	
  </head>
  
 <body>
    
	    <form action="usersAction!listUsers.do" method="post">
	    <input type="hidden" id="teamId" value="${teamId }"/>
 		<div class="box2" showStatus="false">
 		    <table>
 			<tr>
 			  <!--  <td>地区：<select name="areaId" class="default" style="width:90px;">
					  <option value="">全部</option>
					  <c:forEach items="${dictAreaList}" var="entry">
    	                 <option value="${entry.areaId}" <c:if test="${entry.areaId==areaId}">selected="selected"</c:if>>${entry.areaName}</option>
    	              </c:forEach>
				    </select>
				</td>   --> 
				<td>姓名：<input type="text" watermark="输入姓名"  name="name" value="${name }"/></td>
				<td><input type="submit" value="查询"/></td>
			</tr>
			<tr>
 			    <td colspan="2">
	                <input type="button" value="新增" onclick="toAdduser()" />&nbsp;&nbsp;
					<input type="button" value="删除" onclick="toDeleteuser();"/>&nbsp;&nbsp;
					<input type="button" value="转移" onclick="toRemove()"/>&nbsp;&nbsp;
	            </td>
	        </tr>
	        </table>
        </div>
	
   	<div id="scrollContent">
		 <table class="tableStyle"  useMultColor="true"  useCheckBox="true" id="dictTable">
		 	<tr>
		      <th class="ver01" width="4%" align="center"></th>
		      <th class="ver01" width="6%" align="center"><span>用户ID</span></th>
		      <th class="ver01" width="13%" align="center"><span>所属地区</span></th>
		      <th class="ver01" width="10%" align="center"><span>用户名称</span></th>
		      <th class="ver01" width="9%" align="center"><span>工号</span></th>
		      <th class="ver01" width="9%" align="center"><span>角色</span></th>
		      <th class="ver01" width="13%" align="center"><span>登录帐号</span></th>
		      <th class="ver01" width="13%" align="center"><span>登录密码</span></th>
		      <th class="ver01" width="23%" align="center">操作</th>
		    </tr>
			<c:if test="${!empty pm.datas}">
			   	<c:forEach items="${pm.datas}" var="usersPrivs">
			   		<tr>
						<td class="ver01"  align="center"><input id="userId" name="user" type="checkbox" value="${usersPrivs.userId }"/></td>
						<td class="ver01"  align="center"><span>${usersPrivs.userId }</span></td>
						<td class="ver01"  align="center"><span>
						<c:forEach items="${dictAreaList}" var="entry">
    	                     <c:if test="${usersPrivs.areaId==entry.areaId}">${entry.areaName}</c:if>
    	                 </c:forEach>
						</span></td>
						<td class="ver01"  align="center"><span>${usersPrivs.name }</span></td>
						<td class="ver01"  align="center"><span>${usersPrivs.gonghao}</span></td>
						<td class="ver01"  align="center"><span>
						<c:forEach items="${rolePrivsList}" var="entry">
    	                     <c:if test="${usersPrivs.roleId==entry.roleId}">${entry.roleName}</c:if>
    	                 </c:forEach>
						</span></td>
						<td class="ver01"  align="center"><span>${usersPrivs.username }</span></td>
						<td class="ver01"  align="center"><span>${usersPrivs.password }</span></td>
						<td width="100" align="center">
						   <a href="javascript:void(0);" onclick="find(${usersPrivs.userId });" style="color:blue;">查看</a>&nbsp;
						   <a href="javascript:void(0);" onclick="edite(${usersPrivs.userId });" style="color:blue;">修改</a>&nbsp;
						   <a href="javascript:void(0);" onclick="deletes(${usersPrivs.userId });" style="color:blue;">删除</a>
						</td>
					</tr>
			   	</c:forEach>
			  </c:if>
			  <c:if test="${empty pm.datas}">
			     <tr> <td class="ver01" align="center" colspan="10">没有相应的人员信息!</td></tr>
			  </c:if>
		 </table>
	</div>
	<!-- 处理分页 -->
	<div class="float_left padding5">  共有${pm.count}条信息</div>
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
			<pg:pager maxPageItems="${pageSize }" url="usersAction!listUsers.do" items="${pm.count }" export="page1=pageNumber">
				<pg:param name="areaId" value="${areaId }" />
				<pg:param name="name" value="${name }" />
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
		<div class="clear"></div>
	</div>
	</div>
	<!-- 处理分页end -->
	</form>
</body>
<script type="text/javascript">
    
	//增加
	function toAdduser(){
	   	     top.Dialog.open({URL:"<%=basePath%>usersAction!addUserPrivsInput.do",Width:450,Height:300,Title:"添加用户"});
	}
	//删除
	function toDeleteuser() {
		//用于保存 选中的那一条数据的ID 
		var users = [];  
		//判断是否一个未选
		var flag; 
		//遍历所有的name为dict的 checkbox 
		$("input[name='user']").each(function() {
			//判断是否选中 
			if ($(this).attr("checked")) { 
				//只要有一个被选择 设置为 true
				flag = true; 
			}
	    })
	    if (flag) {  
	    	$("input[name='user']").each(function() { 
	    		if ($(this).attr("checked")) { 
	    			//将选中的值 添加到 array中
	    			users.push($(this).val());
	    		}
	    	})
	    	top.Dialog.confirm("确认删除吗？",function(){ 
	    	$.ajax({
				type:"post",
				async:false,
				url:"usersAction!delUsersPrivs.do",
				data:{"users":users.join(",")},
				success:function(result){
					if(result=="success"){
		    			top.Dialog.alert("用户删除成功！",function(){
		    				window.location.href="<%=basePath%>usersAction!listUsers.do";
			    			});
		    		}else{
		    			top.Dialog.alert("用户删除失败！");
		    		}
				}
			});
	    	});
	    }else {
	    	top.Dialog.alert("请至少选择一个用户！");
	    }
		
	}

	//转移班组
	function toRemove(){
		var users=[];
		$("input[name='user']:checked").each(function(){
			users.push($(this).val());
		});
		if(users.length==0){
			top.Dialog.alert("请至少选择一个用户！");
		}else{
			top.Dialog.open({URL:"<%=basePath%>usersAction!updateUserBzInput.do?userIds="+users.join(","),Width:250,Height:150,Title:"修改用户班组"});
		}
	}
	
	//修改
	function edite(userId){
		
			top.Dialog.open({URL:"<%=basePath%>usersAction!editUserPrivsInput.do?userId="+userId,Width:450,Height:300,Title:"修改用户"});
	
	}

	// 查看
	function find(userId){
		
			top.Dialog.open({URL:"<%=basePath%>usersAction!userInfoListInput.do?userId="+userId,Width:450,Height:300,Title:"查看用户详情"});
	}

	//删除
    function deletes(userId){
    	top.Dialog.confirm("确认删除吗？",function(){
    	$.ajax({
			type:"post",
			async:false,
			url:"usersAction!delUsersPrivs.do",
			data:{"users":userId},
			success:function(result){
				if(result=="success"){
	    			top.Dialog.alert("用户删除成功！",function(){
	    				window.location.href="<%=basePath%>usersAction!listUsers.do";
	    			});
	    		}else{
	    			top.Dialog.alert("用户删除失败！");
	    		}
			}
		});
    	});
    }

    //绑定事件
	function addEventHandler(target, type, func) {
	    if (target.addEventListener)
	        target.addEventListener(type, func, false);
	    else if (target.attachEvent)
	        target.attachEvent("on" + type, func);
	    else target["on" + type] = func;
	}
	
	//解除事件 
	function removeEventHandler(target, type, func) {
	    if (target.removeEventListener)
	        target.removeEventListener(type, func, false);
	    else if (target.detachEvent)
	        target.detachEvent("on" + type, func);
	    else delete target["on" + type];
	}
	
	</script>
</html>