package com.alai.news.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/1/26 0026.
 * 设置共享  如主题，无图模式
 */
public class SettingShared {

    public static final String SHARED_PREFERENCES_NAME = "sp_name";

    public static final String KEY_THEME_DARK = "theme_dark";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isThemeDark(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_THEME_DARK, false);
    }

    public static void setThemeDark(Context context, boolean enable) {
        getSharedPreferences(context).edit().putBoolean(KEY_THEME_DARK, enable).apply();
    }
}
