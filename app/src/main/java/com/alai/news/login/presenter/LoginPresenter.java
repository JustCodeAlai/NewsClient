package com.alai.news.login.presenter;

/**
 * Created by chenguochao on 2016/4/18.
 */
public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}
