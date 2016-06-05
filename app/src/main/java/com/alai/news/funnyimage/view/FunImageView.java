package com.alai.news.funnyimage.view;

import com.alai.news.beans.FunImageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface FunImageView {
    void onShowLoadingProgress();
    void onHideLoadingProgress();
    void onShowLoadfailMsg();
    void onShowFunImageListData(List<FunImageBean> list);
}
