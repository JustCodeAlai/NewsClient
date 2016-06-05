package com.alai.news.news.model;

import android.util.Log;

import com.alai.news.MyApplication;
import com.alai.news.beans.NewsBean;
import com.alai.news.beans.NewsDetailBean;
import com.alai.news.commons.Urls;
import com.alai.news.utils.JsonUtil;
import com.alai.news.utils.NewsJsonUtil;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2016/2/12 0012.
 */
public class NewsModelImpl implements NewsModel {

    private static final String TAG = "---NewsModel---";
    private String kk = "http://c.m.163.com/nc/article/headline/T1350383429665/15-10.html";
    private String detail = "http://c.m.163.com/nc/article/BH7LBDHF0001121M/full.html";

    @Override
    public void loadNews(String url, final OnLoadNewsListFinishListener listener) {

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

    /*@Override
    public void loadNewsDetail(final String postId, final OnLoadNewsDetailFinishListener listener) {
        String url = Urls.DETAIL_STATR + postId + Urls.DETAIL_END;
        Log.e(TAG, url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                NewsDetailBean bean = NewsJsonUtil.stringToNewsDetailBean(postId, s);
                listener.onSuccess(bean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }*/

    public void loadNews() {
        String url = "http://c.m.163.com/nc/article/headline/T1348649145984/15-10.html";
        //"http://10.0.2.2:8080/news/news?categoryno=1&index=20"
        StringRequest request = new StringRequest(
                url ,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "load news fail");
                Log.e(TAG, "shibai");
            }
        });
        MyApplication.getRequestQueue().add(request);
    }
}
