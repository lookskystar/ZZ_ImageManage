var fso = new ActiveXObject("Scripting.FileSystemObject");
var zNodes =[];
var drivers = "";
var fileType=new Array("gif","jpg","png","jpeg","bmp");//显示文件的格式
/**
 * 获取目录列表
 */
function getForlderList(forlder){
  var f = fso.GetFolder(forlder);

  var ff = new Enumerator(f.SubFolders);
  var forlder=[];
   for (;!ff.atEnd();ff.moveNext()){
     forlder.push(ff.item());
   }
   return forlder;
}
/**
 * 显示子目录
 */
 function showForlder(level,parentId,folder){
	if(folder!=null && folder!=""){
	 	var forlder = getForlderList(folder);
		var truePath,filehz,fileName,id;
		for(var i=0;i<forlder.length;i++){
		 	if(forlder[i].attributes!=22 && forlder[i].attributes!=19){
				truePath = forlder[i]+"";
				id = level*100+i;
				fileName = truePath.substr(truePath.lastIndexOf("\\")+1,truePath.length);
				
				zNodes.push({id:id,pId:parentId,name:fileName,isParent:true,click:"showFileList('"+truePath.replace(/\\/g,"/")+"');"});
				//递归查询文件夹
				if(getForlderList(forlder[i]).length>0){
					showForlder(level+1,id,truePath);
				}
			}
		}
	}
 }
/**
 * 获取并右侧显示所有的文件列表
 */
function showFileList(forlder){
	//获取文件
	var f = fso.GetFolder(forlder);
	var ff = new Enumerator(f.Files);
	var files=[];
	for (;!ff.atEnd();ff.moveNext()){
	   files.push(ff.item());
	}
	//显示文件
	var truePath,filehz,fileName,cont="";
	for(var i=0;i<files.length;i++){
		truePath = files[i]+"";
		filehz = truePath.substr(truePath.lastIndexOf(".")+1,truePath.length);
		fileName = truePath.substr(truePath.lastIndexOf("\\")+1,truePath.length);
		filehz = filehz.toLowerCase();

		if($.inArray(filehz, fileType)!=-1){
			cont=cont+ "<div style='width:100px;height:120px;margin:14px;float:left;background:#ffffff'>"+
							"<div style='border:1px solid #f0f0f0; width:110px;height:110px; ' onclick='clickImg(this)'>"+
								"<div style='height:10px;'></div>"+
								"<div style='padding-top:5px;'>"+
									"<div style=\"height:80px; width:110px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+truePath+"', sizingMethod='scale')\"></div>"+
									"<input type='checkbox' name='checkb' style='display:none' value='"+truePath+"'/>"+
							"</div></div>"+
							"<div style='text-align:center; width:90px;height:20px;'>"+fileName+"</div>"+
						"</div>";
		}
	}
	$("#content").html(cont);
}

//获取U盘
function getUDrive(){
	var e = new Enumerator(fso.Drives) 
	for(;!e.atEnd();e.moveNext()) { 
		x=e.item(); 
		if(x.DriveType==1){ 
			if(x.Path!="A:") { 
				return x.Path+"\\"; 
			} 
		} 
	}
	return null;
}
//创建日期文件
function createDateFile(drivers){
	var file = fso.CreateTextFile(drivers+"\\TIMECFG.txt",true);
	file.Write("TIME,"+time+"/5;");
	file.close();
}
 
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	}
};
zNodes.push({id:1,pId:0,name:"图像采集仪",open:true,
	iconOpen:"ztree/css/zTreeStyle/img/diy/1_open.png", 
	iconClose:"ztree/css/zTreeStyle/img/diy/1_open.png"});
//var drivers = getUDrive();
var drivers = "D://测试文件夹//"
if(drivers==null || drivers==""){
	alert("请插入图像采集仪,点击刷新按钮!");
}else{
	showForlder(1,1,drivers);
	createDateFile(drivers);
}
$(document).ready(function(){
	$.fn.zTree.init($("#tree"), setting, zNodes);
});

/**
*全部选中文件操作
*/
function checkAll(e){
  var checkbs = document.getElementsByName("checkb")

  if(e.checked){ //如果选中
    for(var i=0;i<checkbs.length;i++){
      checkbs[i].checked=true;
      var checkimg = checkbs[i].parentNode.previousSibling;
      checkimg.innerHTML="<img style='' src='images/chose.gif'>";
    }
  }
  else{
    for(var i=0;i<checkbs.length;i++){
    checkbs[i].checked=false;
    var checkimg = checkbs[i].parentNode.previousSibling;
    checkimg.innerHTML="";
    }
  }
}
//单击选中图片
function clickImg(e){
	var target = e;
	var check = e.lastChild.lastChild;
	if(check.checked){ //如果是选中
		check.checked=false;
		e.firstChild.innerHTML="";
	}
	else{
		check.checked=true;
		e.firstChild.innerHTML="<img style='' src='images/chose.gif'>";
	}
}