/**
 * created by: luoqingming
 * Date: 2013-6-26
 */
var OPEN_BAIDU_VIDEO_APP = function(el,opts){
    var ua = navigator.userAgent,android = ua.match(/(Android).*?([\d.]+)/) || /HTC/.test(ua),
		ipad = ua.match(/(iPad).*OS\s([\d_]+)/),
		iphone = ua.match(/(iPhone\sOS)\s([\d_]+)/),
        ios = ipad || iphone,
        withinApp = /VideoAndroid/.test(ua);
    if(!android&&!ios || withinApp)return;
	if(!opts){
		opts = el
		el = null
	}
    if(typeof(el)==='string'){
        el = document.querySelector(el);
    }
    if(android){
        opts.url = encodeURIComponent(encodeURIComponent(opts.url));
        opts.title = encodeURIComponent(encodeURIComponent(opts.title));
        if(el===null){
            location.href = 'http://m.baidu.com/static/video/proxy.html?'+ new Date().getTime() + '#' +JSON.stringify(opts);
        }
        else{
            el.addEventListener('click',function(){
                location.href = 'http://m.baidu.com/static/video/proxy.html?'+ new Date().getTime() + '#' +JSON.stringify(opts);
            });
        }
    }
    if(ios){
        var frame = document.getElementById('open_baiduvideo_iframe');
        if(!frame){
            frame = document.createElement('iframe');
            frame.id = 'open_baiduvideo_iframe';
            frame.style.display = 'none';
            (document.body || document.documentElement).appendChild(frame);
        }
		var urlRoot =  ipad ? 'baiduvideoipadapp://?' : 'baiduvideoiphoneapp://?';
        if(el===null){
            frame.src =
            urlRoot + 'url='+opts.url+'+title='+opts.title+'+type=1+site_url=+return_url='+opts.return_url+'+isBdhd=1';
            setTimeout(function() {
				if(ipad)
					location = 'https://itunes.apple.com/cn/app/bai-du-shi-pinhd/id573885698?mt=8';
				else
					location = 'itms-apps://itunes.apple.com/WebObjects/MZStore.woa/wa/viewSoftware?id=588287777';
				
            }, 100);
        }
        else{
            el.addEventListener('click',function(){
                frame.src =
                urlRoot + 'url='+opts.url+'+title='+opts.title+'+type=1+site_url=+return_url='+opts.return_url+'+isBdhd=1';
                setTimeout(function() {
					if(ipad)
						location = 'https://itunes.apple.com/cn/app/bai-du-shi-pinhd/id573885698?mt=8';
					else
						location = 'itms-apps://itunes.apple.com/WebObjects/MZStore.woa/wa/viewSoftware?id=588287777';
                }, 100);
            });
        }
    }
}

