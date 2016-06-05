package com.alai.news.news.model;

import com.alai.news.beans.NewsBean;
import com.alai.news.beans.NewsDetailBean;
import com.android.volley.RequestQueue;

import java.util.List;

/**
 * Created by Administrator on 2016/1/28 0028.
 */
public interface NewsModel {

    /**
     * 加载新闻列表
     * @param url 新闻列表的url，例如：http://c.m.163.com/nc/article/headline/T1348649145984/15-20.html
     * @param listener 由NewsPresenterImp实现，执行回调函数
     */
    void loadNews(String url, OnLoadNewsListFinishListener listener);

    interface OnLoadNewsListFinishListener {
        void onSuccess(List<NewsBean> newsBeanList);
        void onFailure();
    }

    //void loadNewsDetail(String url, OnLoadNewsDetailFinishListener listener);

    /*interface OnLoadNewsDetailFinishListener {
        void onSuccess(NewsDetailBean bean);
        void onFailure();
    }*/
}
