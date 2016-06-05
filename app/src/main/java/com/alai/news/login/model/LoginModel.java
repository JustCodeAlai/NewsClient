package com.alai.news.login.model;

/**
 * Created by chenguochao on 2016/4/18.
 */
public interface LoginModel {
    interface OnLoginFinishedListener {

        void onError();

        void onSuccess(String userNo);
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
