package com.alai.news.login.view;

import com.alai.news.beans.UserBean;

/**
 * Created by chenguochao on 2016/4/18.
 */
public interface LoginView {
    void setError();
    void loginSuccess(UserBean userInfo);
}
