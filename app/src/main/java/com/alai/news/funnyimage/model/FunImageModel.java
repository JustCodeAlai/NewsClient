package com.alai.news.funnyimage.model;

import com.alai.news.beans.FunImageBean;
import com.android.volley.RequestQueue;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface FunImageModel {
    void loadImageList(OnLoadImageListFinishListener listener);
    interface OnLoadImageListFinishListener {
        void onSuccess(List<FunImageBean> list);
        void onFailure();
    }
}
