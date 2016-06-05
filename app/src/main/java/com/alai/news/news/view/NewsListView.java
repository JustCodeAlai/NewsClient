package com.alai.news.news.view;

import com.alai.news.beans.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30 0030.
 */
public interface NewsListView {
    void onShowProgress();
    void onHideProgress();
    void onShowLoadFailMsg();
    void onLoadMoreNews(List<NewsBean> list);
}
