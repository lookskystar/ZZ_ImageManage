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
	<form action="dictJcAction!queryDictJcByCondition.do" method="post">
 		<div class="box2">
 		    <table>
 			<tr>
				<td>车型:
					<select id="jcType" name="jcType">
						<option value="">--请选择--</option>
						<c:forEach items="${jcTypeList }" var="type">
							<option value="${type.jcType }"<c:if test="${jcType==type.jcType}">selected="selected"</c:if>>${type.jcType }</option>
						</c:forEach>
					</select>
				</td>
				<td>车号:</td>
				<td><input type="text" name="jcNum" value="${jcNum }"/></td>
				<td><input type="submit" value="查询"/></td>
			</tr>
			<tr>
 			    <td>
	                <input type="button" value="新增" onclick="toAdd()" />&nbsp;&nbsp;
					<input type="button" value="删除" onclick="toDelete();"/>&nbsp;&nbsp;
	            </td>
	        </tr>
	        </table>
        </div>
	   	<div id="scrollContent">
			 <table class="tableStyle"  useMultColor="true"  useCheckBox="true" id="dictJcTable">
			 	<tr>
			      <th class="ver01" width="4%" align="center"></th>
			      <th class="ver01" width="15%" align="center"><span>所属地区</span></th>
			      <th class="ver01" width="15%" align="center"><span>机车类型</span></th>
			      <th class="ver01" width="15%" align="center"><span>机车号</span></th>
			      <th class="ver01" width="20%" align="center">操作</th>
			    </tr>
				<c:if test="${!empty dictJcPm.datas}">
				   	<c:forEach items="${dictJcPm.datas}" var="dictJc">
				   		<tr>
							<td class="ver01"  align="center"><input id="dictJcId" name="dictJc" type="checkbox" value="${dictJc.id }"/></td>
							<td class="ver01"  align="center">
								<span>
									<c:forEach items="${dictAreaList}" var="entry">
			    	                     <c:if test="${dictJc.areaId==entry.areaId}">${entry.areaName}</c:if>
			    	                </c:forEach>
								</span>
							</td>
							<td class="ver01"  align="center"><span>${dictJc.jcStype }</span></td>
							<td class="ver01"  align="center"><span>${dictJc.jcNum }</span></td>
							<td width="20%" align="center">
							   <a href="javascript:void(0);" onclick="edite(${dictJc.id });" style="color:blue;">修改</a>&nbsp;
							   <a href="javascript:void(0);" onclick="deletes(${dictJc.id });" style="color:blue;">删除</a>
							</td>
						</tr>
				   	</c:forEach>
				 </c:if>
				 <c:if test="${empty dictJcPm.datas}">
				    <tr> <td class="ver01" align="center" colspan="10">没有相应的机车信息!</td></tr>
				 </c:if>
			 </table>
		</div>
		<!-- 处理分页 -->
		<div class="float_left padding5">  共有${dictJcPm.count}条信息</div>
		<div class="float_right padding5 paging">
			<div class="float_left padding_top4">
				<pg:pager maxPageItems="${pageSize }" url="usersAction!listUsers.do" items="${dictJcPm.count }" export="page1=pageNumber">
					<pg:param name="areaId" value="${areaId }" />
					<pg:param name="jcNum" value="${jcNum }" />
					<pg:param name="jcType" value="${jcType }" />
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
<script type="text/javascript">
	//增加
	function toAdd(){
		top.Dialog.open({URL:"<%=basePath%>dictJcAction!addDictJcInput.do",Width:350,Height:250,Title:"添加机车"});
	}
	
	//删除
	function toDelete() {
		//用于保存 选中的那一条数据的ID 
		var dictJcs = [];  
		//判断是否一个未选
		var flag; 
		//遍历所有的name为dictJc的 checkbox 
		$("input[name='dictJc']").each(function() {
			//判断是否选中 
			if ($(this).attr("checked")) { 
				//只要有一个被选择 设置为 true
				flag = true; 
			}
	    })
	    if (flag) {  
	    	$("input[name='dictJc']").each(function() { 
	    		if ($(this).attr("checked")) { 
	    			//将选中的值 添加到 array中
	    			dictJcs.push($(this).val());
	    		}
	    	})
	    	top.Dialog.confirm("确认删除吗？",function(){ 
	    	$.ajax({
				type:"post",
				async:false,
				url:"dictJcAction!deleteDictJc.do",
				data:{"dictJcs":dictJcs.join(",")},
				success:function(result){
					if(result=="success"){
		    			top.Dialog.alert("机车记录删除成功！",function(){
		    				window.location.href="<%=basePath%>dictJcAction!queryDictJcByCondition.do";
			    			});
		    		}else{
		    			top.Dialog.alert("机车记录删除失败！");
		    		}
				}
			});
	    	});
	    }else {
	    	top.Dialog.alert("请至少选择一条机车信息！");
	    }
		
	}

	//删除
    function deletes(dictJcId){
    	top.Dialog.confirm("确认删除吗？",function(){
    	$.ajax({
			type:"post",
			async:false,
			url:"dictJcAction!deleteDictJc.do",
			data:{"dictJcs":dictJcId},
			success:function(result){
				if(result=="success"){
	    			top.Dialog.alert("机车记录删除成功！",function(){
	    				window.location.href="<%=basePath%>dictJcAction!queryDictJcByCondition.do";
	    			});
	    		}else{
	    			top.Dialog.alert("机车记录删除失败！");
	    		}
			}
		});
    	});
    }
    
    
	//修改
	function edite(dictJcId){
		top.Dialog.open({URL:"<%=basePath%>dictJcAction!updateDictJcInput.do?dictJcId="+dictJcId,Width:450,Height:300,Title:"修改机车信息"});
	
	}
	
</script>
</body>
</html>