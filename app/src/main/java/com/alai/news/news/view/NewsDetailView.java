package com.alai.news.news.view;

import com.alai.news.beans.NewsDetailBean;

/**
 * Created by Administrator on 2016/2/22.
 */
public interface NewsDetailView {
    void onShowNewsDetail(NewsDetailBean bean);
    void onShowLoadFailMsg();

    void onShowLoadingProgress();
    void onHideLoadingProgress();
}
