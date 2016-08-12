package com.example.administrator.mynews.constants;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class APIUrl {
    /*
    天气api
     */
    //eg: http://wthrcdn.etouch.cn/weather_mini?city=广州
    public static final String WEATHER_URL="http://wthrcdn.etouch.cn/weather_mini";
    /*
    地址API
     */
    //http://ip.taobao.com/service/getIpInfo.php?ip=myip   自动获取外网ip以及地址
    //http://ip.taobao.com/service/getIpInfo.php?ip=121.35.211.41   根据Ip获取地址
    public static final String LOCATION_URL ="http://ip.taobao.com/service/getIpInfo.php";
    /*
    段子APi
     */
    //http://c.3g.163.com/recommend/getChanListNews?channel=T1419316284722&size=20
    public static final String JOKE_URL="http://c.3g.163.com/recommend/getChanListNews";
    /*
    头条Api
     */
    //http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html
    public static final String HEAD_LINE_URL="http://c.3g.163.com/nc/article/headline/T1348647909107/";
     /*
    轻松一刻Api
     */
    //http://3g.163.com/touch/reconstruct/article/list/BD21K0DLwangning/0-10.html
    public static final String RELAX_URL="http://3g.163.com/touch/reconstruct/article/list/BD21K0DLwangning/";
    /*
    美图Api
     */
    public static final String PHOTOS_URL="http://api.laifudao.com/open/tupian.json";

    /*
    某一新闻
     */
    //http://3g.163.com/touch/article/BTQ4VLSK00964LQ9/full.html
    public static final String DETAIL_URL="http://3g.163.com/touch/article/";

}
