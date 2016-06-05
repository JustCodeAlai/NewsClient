package com.alai.news;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alai.news.beans.UserBean;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/2/14 0014.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static RequestQueue sQueue;
    private static boolean sIsLogin = false;
    private static UserBean sUserInfo;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sQueue = Volley.newRequestQueue(this);

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sUserInfo == null) {
            sUserInfo = new UserBean();
        }
        if (preferences.contains("userno") && preferences.contains("username")
                && preferences.contains("password")) {
            sUserInfo.setNo(preferences.getInt("userno", 0));
            sUserInfo.setName(preferences.getString("username", "未登录"));
            sUserInfo.setPassword(preferences.getString("password", "123"));
            setLogin(true);
        }
        Log.e(TAG, "the username is " + sUserInfo.getName());
    }

    public static boolean getLogin() {
        return sIsLogin;
    }

    public static void setLogin(boolean login) {
        sIsLogin = login;
    }

    public static void setsUserInfo(UserBean userBean) {
        sUserInfo = userBean;
    }

    public static UserBean getsUserInfo() {
        return sUserInfo;
    }

    public static RequestQueue getRequestQueue() {
        return sQueue;
    }

    public static Context getContext() {
        return sContext;
    }
}
