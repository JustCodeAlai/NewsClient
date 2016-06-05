package com.alai.news.comment.model;

import android.util.Log;

import com.alai.news.MyApplication;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.commons.Urls;
import com.alai.news.utils.JsonUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/8.
 */
public class CommentModel {
    private final static String TAG = "CommentModel";
    public void loadComments(NewsBean info, final OnLoadCommentsFinishListener listener) {
        String url = Urls.HOST1 + "comment?newsno=" + info.getNo();
        Log.e(TAG, url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                List<CommentBean> beans = JsonUtil.deserialize(s, new TypeToken<List<CommentBean>>() {}.getType());
                listener.onLoadSuccess(beans);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onLoadFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }
    public interface OnLoadCommentsFinishListener {
        void onLoadSuccess(List<CommentBean> comments);
        void onLoadFailure();
    }
}
