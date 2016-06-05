package com.alai.news.register.presenter;

import com.alai.news.beans.UserBean;
import com.alai.news.register.model.RegisterModel;
import com.alai.news.register.view.RegisterActivity;
import com.alai.news.utils.JsonUtil;

/**
 * Created by chenguochao on 2016/4/20.
 */
public class RegisterPresenter implements RegisterModel.OnRegisterFinishListener {
    private RegisterModel mRegisterModel;
    private RegisterActivity mRegisterView;

    public RegisterPresenter(RegisterActivity view) {
        mRegisterModel = new RegisterModel();
        mRegisterView = view;
    }

    public void register(String username, String password) {
        mRegisterModel.register(username, password, this);
    }

    @Override
    public void onSuccess(String userInfo) {
        mRegisterView.sendLoginBroadcast(JsonUtil.deserialize(userInfo, UserBean.class));
    }

    @Override
    public void onFailure(String msg) {
        if (msg.equals("existence")) {
            mRegisterView.exist();
        } else if (msg.equals("failure")) {
            mRegisterView.fail();
        }
    }
}
