package com.alai.news.news.presenter;

import com.alai.news.MyApplication;
import com.alai.news.beans.NewsDetailBean;
import com.alai.news.news.model.NewsModel;
import com.alai.news.news.model.NewsModelImpl;
import com.alai.news.news.view.NewsDetailView;

/**
 * Created by Administrator on 2016/2/22.
 */
public class NewsDetailPresenterImpl {

    /*private final static String TAG = "NewsDetailPresenterImpl";

    private NewsModel mModel;
    private NewsDetailView mView;

    public NewsDetailPresenterImpl(NewsDetailView view) {
        mView = view;
        mModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String postId) {
        mView.onShowLoadingProgress();
        mModel.loadNewsDetail(postId, this);
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onSuccess(NewsDetailBean bean) {
        mView.onHideLoadingProgress();
        mView.onShowNewsDetail(bean);
    }

    @Override
    public void onFailure() {
        mView.onHideLoadingProgress();
        mView.onShowLoadFailMsg();
    }*/
}
