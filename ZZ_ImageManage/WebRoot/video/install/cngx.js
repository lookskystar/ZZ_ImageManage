var Setup = {
	'UserAgent': function() {//判断浏览器类型及版本号
        var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/msie ([\d.]+)/)){
			return 'IE';
		}else if(ua.match(/firefox\/([\d.]+)/)){
			return 'Firefox';
		}else if(ua.match(/bidubrowser\/([\d.]+)/)){
            return '百度';
        }else if(ua.match(/chrome\/([\d.]+)/)){
			return 'Chrome';
		}else if(ua.match(/opera.([\d.]+)/)){
			return 'Opera';
		}else if(ua.match(/version\/([\d.]+)/)){
			return 'Safari';
		}else{
			return '';
		}
	},
	'DownUrl':function(){//获取渠道包地址
		var url = document.URL;
		var defaulturl = 'http://dl.p2sp.baidu.com/BaiduPlayer/un2/BaiduPlayerNetSetup_bdplayer.exe';
		if(url.indexOf("&v=")>1){
			downurl = url.split("&v=")[0].split("?u=")[1];
		}else if(url.indexOf("#")>1){
			downurl = url.split("#")[1];
		}else{
			downurl = defaulturl;
		}
		//获取域名
		domain = downurl.substring(7, 7+downurl.replace('http://','').indexOf('/'));//lastIndexOf
		if(domain == 'union.gxcms.com' || domain == 'dl.client.baidu.com' || domain == 'dl.p2sp.baidu.com'){
			return downurl;
		}else{
			return defaulturl;
		}		
	},
	'Log': function(subtype){//写入日志
		window['sendLog'] = new Image();
		window['sendLog'].src = 'http://php.player.baidu.com/cab/cab.php?type=9&src=0&subtype='+subtype+'&err=0';
	}
}
var downurl = baidu.string.filterFormat.escapeString(Setup.DownUrl());
var userAgent = Setup.UserAgent();
var tips = '<font color=red>安装完成后请刷新页面或按F5</font>';