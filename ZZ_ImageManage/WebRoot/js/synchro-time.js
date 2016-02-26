var fso = new ActiveXObject("Scripting.FileSystemObject");
window.onload = function() { 
	var drivers = getUDrive();
	if(drivers!=null){
		$.post("loginAction!getServerTime.do",{},function(text){
			if(text!=null && text!=""){
				createDateFile(drivers,text);
			}
		});
	}
}; 
//同步时间
function synchroTime(){
	var drivers = getUDrive();
	if(drivers==null){
		alert("请检查是否连接图像采集仪!");
	}else{
		$.post("loginAction!getServerTime.do",{},function(text){
			if(text!=null && text!=""){
				createDateFile(drivers,text);
				alert("时间同步成功!");
			}
		});
	}
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
function createDateFile(drivers,times){
	var file = fso.CreateTextFile(drivers+"\\TIMECFG.txt",true);
	file.Write("TIME,"+times+"/5;");
	file.close();
}
