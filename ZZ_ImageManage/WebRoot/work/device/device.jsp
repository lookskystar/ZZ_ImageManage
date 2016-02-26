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
	
	<!--引入组件start-->
	<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
	<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
	<!--引入弹窗组件end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--修正IE6支持透明PNG图start-->
    <script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->

</head>
<body>
<div class="box4" panelTitle="操作面板">
	<table>
		 <form action="deviceAction!deviceInput.do" method="post">
			<tr>
				<td>状态:</td>
				<td><select name="deviceStatus" class="default" style="width:80px;">
					<option value="">全部</option>
					<option value="0" <c:if test="${0==deviceStatus}">selected="selected"</c:if>>库存</option>
					<option value="1" <c:if test="${1==deviceStatus}">selected="selected"</c:if>>使用</option>
					<option value="2" <c:if test="${2==deviceStatus}">selected="selected"</c:if>>维修</option>
					<option value="3" <c:if test="${3==deviceStatus}">selected="selected"</c:if>>报废</option>
				</select></td>
                <td>班组:</td>
				<td>
				    <select name="teamId" class="default" style="width:80px;" onchange="getReceiverName();" id="teamId">
					  <option value="">全部</option>
					  <c:forEach items="${dictteamslist}" var="entry">
    	                 <option value="${entry.teamId}" <c:if test="${entry.teamId==teamId}">selected="selected"</c:if>>${entry.teamName}</option>
    	              </c:forEach>
				    </select>
				</td>
				<td>
				   <select id="receiverId" class="default" onchange="choiceName();">
				     <option value="">选择领取人</option>
				   </select>
				</td>
				<td>领取人:</td>
				<td><input type="text"  name="receiverName" value="${receiverName }" style="width:80px;" id="receiverName"/></td>				
				<td>设备编码:</td>
				<td><input type="text"  name="deviceCode" value="${deviceCode }" style="width:80px;"/></td>
				<td><input type="submit" value="查询"/></td>
				<td>
					<button onclick="toAdd()"><span class="icon_add">设备入库</span></button>
				</td>
			</tr>
		</form>		
	</table>
	</div>
<div id="scrollContent">
	<table class="tableStyle"  headFixMode="true" useMultColor="true" useCheckBox="true">
		<tr>
			<th width="3%" align="center"></th>
			<th width="8%" align="center"><span>型号</span></th>
			<th width="6%" align="center"><span>编码</span></th>
			<th width="6%" align="center"><span>设备状态</span></th>
			<th width="8%" align="center"><span>班组</span></th>
			<th width="8%" align="center"><span>领取人</span></th>
			<th width="10%" align="center"><span>领取时间</span></th>
			<th width="8%" align="center"><span>归还人</span></th>
			<th width="10%" align="center"><span>归还时间</span></th>
			<th width="17%" align="center"><span>备注</span></th>
			<th width="16%" align="center">操作</th>
		</tr>

	<c:if test="${!empty pm.datas}">
		<c:forEach items="${pm.datas}" var="device">
		<tr>
			<td align="center"><input id="devicesId" name="rec" type="checkbox" width="30px" value="${device.devicesId }"/></td>
			<td align="center"><span>${device.devicesType }</span></td>
			<td align="center"><span>${device.deviceCode }</span></td>
			<td align="center">
				<c:if test="${device.deviceStatus==0}">库存</c:if>
				<c:if test="${device.deviceStatus==1}">使用</c:if>
				<c:if test="${device.deviceStatus==2}">维修</c:if>
				<c:if test="${device.deviceStatus==3}"><font style="color: #f00">报废</font></c:if>
			</td>
			<td align="center"><span>${device.teamName }</span></td>
			<td align="center"><span>${device.receiverName }</span></td>
			<td align="center"><span>${device.receiverTime }</span></td>
			<td align="center"><span>${device.returnName }</span></td>
			<td align="center"><span>${device.returnTime }</span></td>
			<td align="center"><span>${device.devicesNote }</span></td>
			<td align="left">
			<c:if test="${device.deviceStatus==0}">
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="find(${device.devicesId });" style="color:blue;">查看</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="receive(${device.devicesId });" style="color:blue;">领取</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="repair(${device.devicesId });" style="color:blue;">送修</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="discard(${device.devicesId });" style="color:blue;">报废</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="resume(${device.devicesId });" style="color:blue;">履历</a>
			</c:if>
			<c:if test="${device.deviceStatus==1}">
			    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="find(${device.devicesId });" style="color:blue;">查看</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="returns(${device.devicesId });" style="color:blue;">归还</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="discard(${device.devicesId });" style="color:blue;">报废</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="resume(${device.devicesId });" style="color:blue;">履历</a>
			</c:if>
			<c:if test="${device.deviceStatus==2}">
			    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="find(${device.devicesId });" style="color:blue;">查看</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="back(${device.devicesId });" style="color:blue;">修后返回</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="discard(${device.devicesId });" style="color:blue;">报废</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="resume(${device.devicesId });" style="color:blue;">履历</a>
			</c:if>
			<c:if test="${device.deviceStatus==3}">
			    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="find(${device.devicesId });" style="color:blue;">查看</a>&nbsp;&nbsp;
			    <a href="javascript:void(0);" onclick="resume(${device.devicesId });" style="color:blue;">履历</a>
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr> 
		    <td class="ver01" align="center" colspan="10">没有相应的数据记录!</td>
		</tr>
	</c:if>
	</table>
   		<div style="height:35px;">
           <div class="float_left padding5">共有${pm.count}条信息</div>
        </div>
		<!-- 处理分页 -->
		<div style="height: 35px;">
			<div class="float_right padding5 paging">
				<div class="float_left padding_top4">
					<pg:pager maxPageItems="${pageSize }"
						url="deviceAction!deviceInput.do" items="${pm.count}"
						export="page1=pageNumber">
						<pg:param name="deviceStatus" value="${deviceStatus}"/>
						<pg:param name="teamId" value="${teamId}"/>
						<pg:param name="receiverName" value="${receiverName}"/>
				        <pg:param name="deviceCode" value="${deviceCode}"/>
						<pg:first>
							<span><a href="${pageUrl  }" id="first">首页</a>
							</span>
						</pg:first>
						<pg:prev>
							<span><a href="${pageUrl  }">上一页</a>
							</span>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${page1==pageNumber}">
									<span class="paging_current"><a href="javascript:;">${pageNumber
											}</a>
									</span>
								</c:when>
								<c:otherwise>
									<span><a href="${pageUrl  }">${pageNumber }</a>
									</span>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<span><a href="${pageUrl }">下一页</a>
							</span>
						</pg:next>
						<pg:last>
							<span><a href="${pageUrl }">末页</a>
							</span>
						</pg:last>
					</pg:pager>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 处理分页end -->

</body>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty message}">
	    top.Dialog.close();
		top.Dialog.alert('${message}');
	</c:if>
});

//新增设备
function toAdd(){
   	     top.Dialog.open({URL:"<%=basePath%>deviceAction!addDeviceInput.do",Width:400,Height:320,Title:"设备入库"});
}

//查看
function find(devicesId){
	top.Dialog.open({URL:"<%=basePath%>deviceAction!deviceInfoInput.do?devicesId="+devicesId,Width:500,Height:320,Title:"查看设备信息"});
    
}

//设备返修回来，状态变为库存
function back(devicesId){
	top.Dialog.confirm("确认返修回来，入库吗？",function(){ 
	$.ajax({
		type:"post",
		async:false,
		url:"deviceAction!backDevicesInfo.do",
		data:{"device":devicesId},
		success:function(result){
			if(result=="success"){
    			top.Dialog.alert("设备成功入库",function(){
	    			window.location.href="<%=basePath%>deviceAction!deviceInput.do";
    			});
    		}else{
    			top.Dialog.alert("设备入库失败");
    		}
		}
	});
	});
}

//设备报废
function discard(devicesId){
	top.Dialog.confirm("确认报废，修不好了？",function(){ 
	$.ajax({
		type:"post",
		async:false,
		url:"deviceAction!discardDevicesInfo.do",
		data:{"device":devicesId},
		success:function(result){
			if(result=="success"){
    			top.Dialog.alert("设备报废成功",function(){
	    			window.location.href="<%=basePath%>deviceAction!deviceInput.do";
    			});
    		}else{
    			top.Dialog.alert("设备报废失败");
    		}
		}
	});
	});
}

//设备送修
function repair(devicesId){
	top.Dialog.confirm("确认送去维修？",function(){ 
	$.ajax({
		type:"post",
		async:false,
		url:"deviceAction!repairDevicesInfo.do",
		data:{"device":devicesId},
		success:function(result){
			if(result=="success"){
    			top.Dialog.alert("设备送修成功",function(){
	    			window.location.href="<%=basePath%>deviceAction!deviceInput.do";
    			});
    		}else{
    			top.Dialog.alert("设备送修失败");
    		}
		}
	});
	});
}

//设备领取
function receive(devicesId){
   	     top.Dialog.open({URL:"<%=basePath%>deviceAction!receiveDeviceInput.do?devicesId="+devicesId,Width:400,Height:320,Title:"设备领取"});
}

//设备归还
function returns(devicesId){
	top.Dialog.confirm("归还前,请确认手电影像文件已经上传！",function(){
   	     top.Dialog.open({URL:"<%=basePath%>deviceAction!returnDeviceInput.do?devicesId="+devicesId,Width:400,Height:320,Title:"设备归还"});
	});
}

//查看履历
function resume(devicesId){
	top.Dialog.open({URL:"<%=basePath%>deviceAction!resumeInput.do?devicesId="+devicesId,Width:1400,Height:700,Title:"查看设备履历"});
    
}

function getReceiverName(){
	var teamId=$("#teamId").val();
	if(teamId!=""){
		$.post("deviceAction!ajaxGetTeamUser.do",{"teamId":teamId},function(data){
			var datas=eval("("+data+")");
			var result=datas.users;
			var str="<option value=''>选择领取人</option>";
			if(result.length!=0){
				for(var i=0;i<result.length;i++){
					str+="<option value="+result[i].userId+">"+result[i].userName+"</option>";
				}
				$("#receiverId").children().remove();
				$("#receiverId").append(str);
			}else{
				$("#receiverId").children().remove();
				$("#receiverId").append(str);
			}
		});
	}
}

function choiceName(){
	var receiverName=$("#receiverId").find("option:selected").text();
	if(receiverName!=""){
		$("#receiverName").val(receiverName);
	}
}
</script>
</html>