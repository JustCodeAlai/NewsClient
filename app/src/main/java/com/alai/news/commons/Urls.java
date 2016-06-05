package com.alai.news.commons;

/**
 * Created by Administrator on 2016/1/21 0021.
 */
public class Urls {

    /**
     * 从15条开始获取，一共获取20条   也就是获取15-35条新闻
     */
    //http://c.m.163.com/nc/article/headline/T1348649145984/15-20.html
    //http://10.0.2.2:8080/news/
    //"http://c.m.163.com/nc"
    public static final String VIDEO_URL = "http://10.0.2.2:8080/news/news?videoIndex=";
    public static final String HOST1 = "http://10.0.2.2:8080/news/";
    /**
     * 每页显示多少条新闻
     */
    public static final int PAGE_SIZE = 20;

    public static final String HOST = "http://c.m.163.com/nc";
    public static final String END = "-" + PAGE_SIZE + ".html";
    public static final String DETAIL_STATR = HOST + "/article/";
    public static final String DETAIL_END = "/full.html";

    //新闻列表的host url后面再加id就可获得列表
    public static final String TOP_LIST_URL = HOST + "/article/headline/";

    public static final String COMMON_LIST_URL = HOST + "/article/list/";

    //头条
    public static final String TOP_ID = "T1348647909107";

    //NBA
    public static final String NBA_ID = "T1348649145984";

    //财经
    public static final String JOKE_ID = "T1348648756099";

    //汽车
    public static final String CAR_ID = "T1348654060988";

    // 图片
    public static final String IMAGE_URL = "http://api.laifudao.com/open/tupian.json";

    // 天气预报
    public static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?city=";

    //百度定位
    public static final String BAIDU_LOCATION = "http://api.map.baidu.com/geocoder";

}
