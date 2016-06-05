package com.alai.news.video.presenter;

import com.alai.news.beans.NewsBean;
import com.alai.news.video.model.VideoModel;
import com.alai.news.video.view.VideoDetailActivity;
import com.alai.news.video.view.VideoListFragment;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/5.
 */
public class VideoPresenter implements VideoModel.OnLoadVideoFinishListener {
    private VideoListFragment mVideoListFragment;
    private VideoModel mModel;
    private VideoDetailActivity mVideoDetailActivity;

    public VideoPresenter(VideoListFragment view) {
        mVideoListFragment = view;
        mModel = new VideoModel();
    }

    public VideoPresenter(VideoDetailActivity view) {
        mVideoDetailActivity = view;
        mModel = new VideoModel();
    }

    public void loadVideos(String url) {
        mVideoListFragment.onShowProgress();
        mModel.loadVideo(url, this);
    }

    public void destroy() {
        mVideoListFragment = null;
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mVideoListFragment.onHideProgress();
        mVideoListFragment.onLoadMoreVideo(list);
    }

    @Override
    public void onFailure() {
        mVideoListFragment.onHideProgress();
        mVideoListFragment.onShowLoadFailMsg();
    }
}
