package com.alai.news.login.presenter;

import com.alai.news.beans.UserBean;
import com.alai.news.login.model.LoginModel;
import com.alai.news.login.model.LoginModelImpl;
import com.alai.news.login.view.LoginView;
import com.alai.news.utils.JsonUtil;

/**
 * Created by chenguochao on 2016/4/18.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginFinishedListener {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override public void validateCredentials(String username, String password) {
        mLoginModel.login(username, password, this);
    }

    @Override public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onError() {
        if (mLoginView != null) {
            mLoginView.setError();
        }
    }

    @Override
    public void onSuccess(String s) {
        if (mLoginView != null) {
            if (s.equals("failure")) {
                mLoginView.setError();
                return;
            }
            mLoginView.loginSuccess(JsonUtil.deserialize(s, UserBean.class));
        }
    }
}
