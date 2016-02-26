var unique = (function () {
    var time= (new Date()).getTime()+'-', i=0;
    return function () {
        return time + (i++);
    }
})();

var imgLog = function (url) {
    var data = window['imgLogData'] || (window['imgLogData'] = {});
    var img = new Image();
    var uid = unique();
    img.onload = img.onerror = function () {//销毁一些对象
        img.onload = img.onerror = null;
        img = null;
        delete data[uid];
    }
    img.src = url + '&_uid=' + uid;
};

/**
 *
 * @param type
 * type webshow 页面展示; butdownclick 下载按钮点击;wddownclick 文字下载点击;
 */
var platform=function(type){
    var url64='http://fsreport.p2sp.baidu.com/stat?key=platform&type='+type;
    imgLog(url64);
}

var headtip=function(type){
    var tipurl='http://fsreport.p2sp.baidu.com/stat?key=headtips&type='+type;
    imgLog(tipurl);
}

