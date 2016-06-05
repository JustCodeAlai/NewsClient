package com.alai.news.funnyimage.model;

import android.util.Log;

import com.alai.news.MyApplication;
import com.alai.news.beans.FunImageBean;
import com.alai.news.commons.Urls;
import com.alai.news.utils.FunImageUtil;
import com.alai.news.utils.OkHttpUtils;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FunImageModelImpl implements FunImageModel {
    private static final String TAG = "FunImageModel";
    @Override
    public void loadImageList(final OnLoadImageListFinishListener listener) {
        String url = Urls.IMAGE_URL;
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.e("kafjsagasgasj", response);
                List<FunImageBean> imageBeanList = FunImageUtil.stringToFunImageBeanList(response);
                listener.onSuccess(imageBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                //listener.onFailure("load image list failure.", e);
                Log.e(TAG, "onFailure");
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);


       /* StringRequest request = new StringRequest("http://api.laifudao.com/open/tupian.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "===onResponse()===");
                Log.e(TAG, s);
                List<FunImageBean> list = FunImageUtil.stringToFunImageBeanList(s);
                listener.onSuccess(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "====onErrorResponse====");
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);*/
    }

    public interface OnLoadImageListListener {
        void onSuccess(List<FunImageBean> list);
        void onFailure(String msg, Exception e);
    }
}
