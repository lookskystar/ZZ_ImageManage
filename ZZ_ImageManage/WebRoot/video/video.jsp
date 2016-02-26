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
    <title>视频播放</title>
    
	<!--框架必需start-->
	<script type="text/javascript" src="js/jquery-1.4.js"></script>
	<script type="text/javascript" src="js/framework.js"></script>
	<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
	<!--框架必需end-->
	<!--截取文字start-->
	<script type="text/javascript" src="js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<!-- ztree -->
	<link rel="stylesheet" href="ztree/css/demo.css" type="text/css" />
	<link rel="stylesheet" href="ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="ztree/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
		function multiPlay(index){
		   var urls=$("#s_"+index).text();
		   var i=1;
		   if(urls!=""){
			   var urlArray=urls.split(",");
			   singlePlay(urlArray[0]);
			   setInterval(function(){
			      var state=BaiduPlayer.IsStop();
				  if(state==1&&i<urlArray.length){
				     singlePlay(urlArray[i]);
					 i++;
				  }
		   	   },1000);
		   }
		 }
	
		function singlePlay(path){
			BaiduPlayer['url'] ="${video_path}"+path;
		    BaiduPlayer.Play();
		 }
	
		 var setting = {
			//check: {enable: true},
			data: {simpleData: {enable: true}}
		};

		 var dataMaker = function(count) {
			var nodes = [];
			var n;
			<c:forEach var="first" items="${maps}" varStatus="fidx">
				var n={id:"f_${fidx.index}",pId:-1,name:'${first.key}'};
				nodes.push(n);
				<c:forEach var="second" items="${first.value}" varStatus="sidx">
				    var urls="";
					var n={id:"s_${fidx.index}_${sidx.index}",pId:"f_${fidx.index}",name:"${second.key}",click:"multiPlay(${sidx.index});"};
					nodes.push(n);
					<c:forEach var="item" items="${second.value}" varStatus="idx">
						var n={id:"c_${idx.index}",pId:"s_${fidx.index}_${sidx.index}",name:"${item.videoName}",click:"singlePlay('${item.videoUrl}');"};
						nodes.push(n);
						urls+="${item.videoUrl},";
					</c:forEach>
					var str="<span id='s_${sidx.index}'>"+urls+"</span>";
					$("#urls").append(str);
				</c:forEach>
			</c:forEach>
			return nodes;
		}

		var ruler = {
			doc: null,
			ruler: null,
			cursor: null,
			minCount: 5000,
			count: 5000,
			stepCount:500,
			init: function() {
				ruler.doc = $(document);
				ruler.ruler = $("#ruler");
				ruler.cursor = $("#cursor");
				if (ruler.ruler) {
					ruler.ruler.bind("mousedown", ruler.onMouseDown);
					
				}
			},
			onMouseDown: function(e) {
				ruler.drawRuler(e, true);
				ruler.doc.bind("mousemove", ruler.onMouseMove);
				ruler.doc.bind("mouseup", ruler.onMouseUp);
				ruler.doc.bind("selectstart", ruler.onSelect);
				$("body").css("cursor", "pointer");
			},
			onMouseMove: function(e) {
				ruler.drawRuler(e);
				return false;
			},
			onMouseUp: function(e) {
				$("body").css("cursor", "auto");
				ruler.doc.unbind("mousemove", ruler.onMouseMove);
				ruler.doc.unbind("mouseup", ruler.onMouseUp);
				ruler.doc.unbind("selectstart", ruler.onSelect);
				ruler.drawRuler(e);
			},
			onSelect: function (e) {
				return false;
			},
			getCount: function(end) {
				var start = ruler.ruler.offset().left+1;
				var c = Math.max((end - start), ruler.minWidth);
				c = Math.min(c, ruler.maxWidth);
				return {width:c, count:(c - ruler.minWidth)*ruler.stepCount + ruler.minCount};
			},
			drawRuler: function(e, animate) {
				var c = ruler.getCount(e.clientX);
				ruler.cursor.stop();
				if ($.browser.msie || !animate) {
					ruler.cursor.css({width:c.width});
				} else {
					ruler.cursor.animate({width:c.width}, {duration: "fast",easing: "swing", complete:null});
				}
				ruler.count = c.count;
				ruler.cursor.text(c.count);
			}
		}

		function createTree () {
			var zNodes = dataMaker(ruler.count);
			showNodeCount = 0;
			$("#tree").empty();
			$.fn.zTree.init($("#tree"), setting, zNodes);
		}


		$(document).ready(function(){
			ruler.init("ruler");
			createTree();
		});
		
		function zTreeeEpendAll(type){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if(type==0){
				zTree.expandAll(true);
			}else{
				zTree.expandAll(false);
			}
		}
	</script>
	<style type="text/css">
	</style>
  </head>
  
 <body>
 	<table width="100%" align="left">
 		<tr>
 		    <td align="left">
		 		<div panelHeight="450" panelTitle="视频播放" overflow="auto">
		 			<font style="font-size: 14px;font-weight: bolder;">当前播放文件：</font>
		 			<span id="nowOpen" style="color:red;"></span>
		 			<div style="margin-top: 10px;">	
						  <script language="javascript">
								var BdPlayer = new Array();
								BdPlayer['time'] = 8;//缓冲广告展示时间(如果设为0,则根据缓冲进度自动控制广告展示时间)
								BdPlayer['buffer'] = '<%=basePath%>/video/buffer.html';//贴片广告网页地址
								BdPlayer['pause'] = '<%=basePath%>/video/pause.html';//暂停广告网页地址
								BdPlayer['end'] = '<%=basePath%>/video/end.html';//影片播放完成后加载的广告
								BdPlayer['tn'] = '12345678';//播放器下载地址渠道号
								BdPlayer['width'] = 560;//播放器宽度(只能为数字)
								BdPlayer['height'] = 420;//播放器高度(只能为数字)
								BdPlayer['showclient'] = 1;//是否显示拉起拖盘按钮(1为显示 0为隐藏)
								BdPlayer['url'] = '';//当前播放任务播放地址
								BdPlayer['nextcacheurl'] = '';//下一集播放地址(没有请留空)
								BdPlayer['lastwebpage'] = '';//上一集网页地址(没有请留空)
								BdPlayer['nextwebpage'] = '';//下一集网页地址(没有请留空)
  						</script>
						<script language="javascript" src="video/js/player.js" charset="utf-8"></script>
	                </div>
		 		</div>
		 		<div id="urls" style="display: none;"></div>
		 	</td>
 			<td width="320px">
 			        <a href="javascript:void(0);" onclick="zTreeeEpendAll(0);" style="color:blue;">全部展开</a>&nbsp;&nbsp;&nbsp;
 			        <a href="javascript:void(0);" onclick="zTreeeEpendAll(1);" style="color:blue;">全部收缩</a>
		 		    <div id="tree" class="ztree" style="overflow: auto;"></div>
	 		</td>
		</tr>
 	</table>	
</body>
</html>