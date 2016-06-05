package com.alai.news.utils;

import android.util.Log;

import com.alai.news.beans.FunImageBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FunImageUtil {
    private static final String TAG = "FunImageUtil";
    public static List<FunImageBean> stringToFunImageBeanList(String json) {
        Log.e(TAG, json);
        List<FunImageBean> beans = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        for (int i = 1; i < jsonArray.size(); i++) {
            JsonObject jo = jsonArray.get(i).getAsJsonObject();
            FunImageBean bean = JsonUtil.deserialize(jo, FunImageBean.class);
            beans.add(bean);
        }
        /*try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(json).getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                FunImageBean bean = JsonUtil.deserialize(jo, FunImageBean.class);
                beans.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "json parse error");
        }*/
        return beans;
    }
}
