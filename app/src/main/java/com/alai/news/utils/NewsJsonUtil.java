package com.alai.news.utils;

import android.util.Log;

import com.alai.news.beans.NewsBean;
import com.alai.news.beans.NewsDetailBean;
import com.alai.news.commons.Urls;
import com.alai.news.news.view.NewsFragment;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/13 0013.
 */
public class NewsJsonUtil {

    /*private static final String TAG = "NewsJsonUtil";

    public static List<NewsBean> stringToNewsBeanList(int type, String res) {
        String id = getID(type);
        List<NewsBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(res).getAsJsonObject();
            JsonElement element = object.get(id);
            if (element == null) {
                return null;
            }
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject obj = array.get(i).getAsJsonObject();
                if (obj.has("TAG") || obj.has("TAGS") || obj.has("skipType")) {
                    continue;
                }
                if (!obj.has("imgextra")) {
                    NewsBean bean = JsonUtil.deserialize(obj, NewsBean.class);
                    beans.add(bean);
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Json parse error!");
        }
        return beans;
    }

    public static NewsDetailBean stringToNewsDetailBean(final String postId, final String json) {
        NewsDetailBean bean = new NewsDetailBean();
        try {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(json).getAsJsonObject().get(postId).getAsJsonObject();
            bean = JsonUtil.deserialize(object, NewsDetailBean.class);

            String imgsrc = object.get("img").getAsJsonArray().get(0).getAsJsonObject().get("src").getAsString();
            bean.setImgUrl(imgsrc);
            Log.e(TAG, bean.getImgUrl());
        } catch (Exception e) {
            Log.e(TAG, "Json parse error!");
        }
        return bean;
    }

    private static String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }*/
}
