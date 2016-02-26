
var  andrPlayer={
    'Referer':parent.document.URL,
    'Width': BdPlayer['width'],
    'Height':BdPlayer['height'],
    'Url': BdPlayer['url']
}
document.write('<div id="playertip" style="display: none;"></div>');
OPEN_BAIDU_VIDEO_APP(null, {
    //bdhd开头的地址
    url: andrPlayer.Url,
    //视频名称
    title: '',
    //调用方式，action=0为播放，action=1为下载,当前版本暂不支持下载
    action: 0,
    //播放视频的页面地址
    refer: andrPlayer.Referer,
    //在客户端返回时，需要返回的页面地址
    return_url:andrPlayer.Referer
});
