package com.alai.news.news.view;

import com.alai.news.beans.PhotoBean;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/8.
 */
public interface PhotosetView {
    void onShowPhotoset(List<PhotoBean> beans);
    void onShowLoadFailMsg();
    void onShowLoadingProgress();
    void onHideLoadingProgress();
}
