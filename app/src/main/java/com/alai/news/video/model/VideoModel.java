package com.alai.news.video.model;

import com.alai.news.MyApplication;
import com.alai.news.beans.NewsBean;
import com.alai.news.utils.JsonUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/5.
 */
public class VideoModel {
    public void loadVideo(String url, final OnLoadVideoFinishListener listener) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                List<NewsBean> list = JsonUtil.deserialize(s, new TypeToken<List<NewsBean>>() {}.getType());
                listener.onSuccess(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }
    public interface OnLoadVideoFinishListener {
        void onSuccess(List<NewsBean> list);
        void onFailure();
    }
}
