package com.alai.news.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.alai.news.MyApplication;
import com.alai.news.beans.CommentBean;
import com.alai.news.commons.Urls;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/11.
 */
public class CommentPostUtil {
    public final static String TAG = "CommentPostUtil";
    public static void postComment(final Activity context, final CommentBean comment) {
        String url = Urls.HOST1 + "comment";
        Log.e(TAG, url);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);
                Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "提交失败！", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("comment", JsonUtil.serialize(comment));
                return map;
            }
        };
        MyApplication.getRequestQueue().add(request);
    }
}
