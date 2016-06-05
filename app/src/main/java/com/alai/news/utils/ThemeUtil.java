package com.alai.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.alai.news.storage.SettingShared;

/**
 * Created by Administrator on 2016/1/26 0026.
 */
public class ThemeUtil {

    public static boolean configThemeBeforeOnCreate(Activity activity, int light, int dark) {
        boolean isThemeDark = SettingShared.isThemeDark(activity);
        activity.setTheme(isThemeDark ? dark : light);
        return isThemeDark;
    }

    public static void recreateActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            activity.recreate();
        } else {
            Intent intent = activity.getIntent();
            intent.setClass(activity, activity.getClass());
            activity.startActivity(intent);
            activity.finish();
            activity.overridePendingTransition(0, 0);
        }
    }

}
