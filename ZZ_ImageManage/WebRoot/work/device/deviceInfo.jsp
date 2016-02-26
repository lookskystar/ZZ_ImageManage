<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
	<!--截取文字start-->
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<!--修正IE6支持透明PNG图start-->
    <script type="text/javapersle" src="js/method/pngFix/supersleight.js"></script>
    <!--修正IE6支持透明PNG图end-->
	<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
	<script>
	    //关闭此页面
		function toclose(){
			top.Dialog.close();
		}
    </script>
  </head>
<body>
    <form action="deviceAction!deviceInfoInput.do" method="post" target="frmright"> 
  	    <input type="hidden" value="${device.devicesId }" name="devicesId"/>
		<table class="tableStyle" formMode="true">
		<tr>
			<th colspan="4">设备基本信息</th>
		</tr>
		<tr>
				<td width="25%">设备ID： </td>
				<td width="25%">
				   ${device.devicesId }
				</td>
			    <td width="25%">设备型号： </td>
				<td width="25%">
				   ${device.devicesType }
				</td>   	
		</tr>
		<tr>	
				<td>设备编码： </td>
				<td>
				   ${device.deviceCode }
				</td>
				<td>设备状态：</td>
				<td>
					<c:if test="${device.deviceStatus==0}">库存</c:if>
				    <c:if test="${device.deviceStatus==1}">使用</c:if>
				    <c:if test="${device.deviceStatus==2}">维修</c:if>
				    <c:if test="${device.deviceStatus==3}">报废</c:if>
				</td>
		</tr>
		<tr>	
				<td>领取人姓名：</td>
				<td>
					${device.receiverName }
				</td>
				<td>领取时间：</td>
				<td>
					${device.receiverTime }
				</td>
		 </tr>
		 <tr>	
				<td>归还人姓名：</td>
				<td>
					${device.returnName }
				</td>
				<td>归还时间：</td>
				<td>
					${device.returnTime }
				</td>
		 </tr>
		 <tr>
				<td>地区：</td>
				<td>
					  <c:forEach items="${dictareaslist}" var="entry"> 
    	                  <c:if test="${device.areaId==entry.areaId}">${entry.areaName}</c:if>
    	              </c:forEach>
				</td>
				<td>所属班组：</td>
				<td>
					${device.teamName }
				</td>
		 </tr>
		 <tr>
				<td>备注：</td>
				<td colspan="3">
					<textarea style="width:280px;height:70px;" class="default">${device.devicesNote }</textarea>
				</td>
		 </tr>
			<tr>
			    <td colspan="4" align="center"><input type="button" value="确定" onclick="toclose();"/></td>
			</tr>
			
		</table>
	</form>
</body>
</html>