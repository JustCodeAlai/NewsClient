package com.alai.news.news.presenter;

/**
 * Created by Administrator on 2016/1/28 0028.
 */
public interface NewsPresenter {

    /**
     * @param type 类型 如NBA、头条
     * @param page 页数 15-20.html page = 15
     */
    void loadNews(int type, int page);

    void destroy();
}
