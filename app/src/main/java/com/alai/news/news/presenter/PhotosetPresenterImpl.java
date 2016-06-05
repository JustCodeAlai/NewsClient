package com.alai.news.news.presenter;

import android.app.Activity;
import android.util.Log;

import com.alai.news.MyApplication;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.beans.PhotoBean;
import com.alai.news.beans.UserBean;
import com.alai.news.commons.Urls;
import com.alai.news.news.model.PhotosetModel;
import com.alai.news.news.view.NewsDetailActivity;
import com.alai.news.news.view.PhotosetNewsActivity;
import com.alai.news.news.view.PhotosetView;
import com.alai.news.utils.JsonUtil;
import com.alai.news.video.model.VideoModel;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/8.
 */
public class PhotosetPresenterImpl implements PhotosetModel.OnLoadPhotosetFinishListener {

    private static final String TAG = "PhotosetPresentImpl";
    private PhotosetView mView;
    private PhotosetModel mModel;

    public PhotosetPresenterImpl(PhotosetView view) {
        mView = view;
        mModel = new PhotosetModel();
    }

    public PhotosetPresenterImpl() {};

    public void loadPhotoset(int newsNo) {
        mView.onShowLoadingProgress();
        mModel.loadPhotoset(newsNo, this);
    }

    public void loadPhotoset(final NewsDetailActivity activity, int newsNo) {
        String url = Urls.HOST1 + "photo?newsno=" + newsNo;
        Log.e(TAG, url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                List<PhotoBean> beans = JsonUtil.deserialize(s, new TypeToken<List<PhotoBean>>() {
                }.getType());
                activity.showPhoto(beans);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        MyApplication.getRequestQueue().add(request);
    }

    @Override
    public void onSuccess(List<PhotoBean> photoBeans) {
        mView.onHideLoadingProgress();
        mView.onShowPhotoset(photoBeans);
    }

    @Override
    public void onFailure() {
        mView.onHideLoadingProgress();
        mView.onShowLoadFailMsg();
    }
}
