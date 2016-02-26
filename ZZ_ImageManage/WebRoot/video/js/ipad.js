var  iosPlayer={
    'Referer':document.URL,
    'Width': BdPlayer['width'],
    'Height':BdPlayer['height'],
    'Download':BdPlayer['download'],
    'Allowsite':['www.qire123.com'],
    'install':function(){
        $z("#PadBdInstall").show();
        $z("#PadBdInstall").html('<iframe src="http://player.baidu.com/lib/setupax/ipad.html" scrolling="no"' +
            ' width="'+this.Width+'px" height="'+this.Height+'px" frameborder="0" marginheight="0" marginwidth="0">' +
            '</iframe>');
    },
    'cominstall':function(){
        $z("#PadBdInstall").show();
        $z("#PadBdInstall").html('<iframe src="http://player.baidu.com/lib/setupax/xbdyy.html?u='+this.Download+'&v=20120425" scrolling="no"' +
            ' width="'+this.Width+'px" height="'+this.Height+'px" frameborder="0" marginheight="0" marginwidth="0">' +
            '</iframe>');
    },
    'setUp':function(){
      //  if(this.Referer.indexOf('fanhuai.com')>-1 ||this.Referer.indexOf('qire123.com')>-1){
            if(this.isHD()==false){
                this.install();
            }else{
                this.cominstall();
            }
        //}
    },
    'isHD':function(){//如何判断是不是BaiduHD
        if(navigator.userAgent.toLowerCase().indexOf("baiduhd") > 0){
            return true;
        }
        return false;
    }
}

iosPlayer.setUp();