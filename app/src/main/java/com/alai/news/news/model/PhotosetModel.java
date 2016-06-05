package com.alai.news.news.model;

import android.util.Log;

import com.alai.news.MyApplication;
import com.alai.news.beans.PhotoBean;
import com.alai.news.commons.Urls;
import com.alai.news.utils.JsonUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/8.
 */
public class PhotosetModel {
    private static final String TAG = "PhotosetModel";
    public void loadPhotoset(int newsNo, final OnLoadPhotosetFinishListener listener) {
        String url = Urls.HOST1 + "photo?newsno=" + newsNo;
        Log.e(TAG, url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                List<PhotoBean> beans = JsonUtil.deserialize(s,  new TypeToken<List<PhotoBean>>() {}.getType());
                listener.onSuccess(beans);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }
    public interface OnLoadPhotosetFinishListener {
        void onSuccess(List<PhotoBean> photoBeans);
        void onFailure();
    }
}
