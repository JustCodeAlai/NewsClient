package com.alai.news.register.model;

import com.alai.news.MyApplication;
import com.alai.news.commons.Urls;
import com.alai.news.utils.JsonUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenguochao on 2016/4/20.
 */
public class RegisterModel {
    public void register(final String name, final String password, final OnRegisterFinishListener listener) {
        String url = Urls.HOST1 + "register";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("existence") || s.equals("failure")) {
                    listener.onFailure(s);
                    return;
                }
                listener.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("username", name);
                map.put("password", password);
                return map;
            }
        };
        MyApplication.getRequestQueue().add(request);
    }
    public interface OnRegisterFinishListener {
        void onSuccess(String userInfo);
        void onFailure(String msg);
    }
}
