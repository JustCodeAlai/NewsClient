package com.alai.news;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.alai.news.news.model.NewsModelImpl;
import com.alai.news.utils.HttpUtil;

/**
 * Created by Administrator on 2016/2/14 0014.
 */
public class TestNewsModel extends InstrumentationTestCase {

    public void testLoadNews() {
        NewsModelImpl model = new NewsModelImpl();
        model.loadNews();
        /*try {
            String s = HttpUtil.sendRequest("http://10.0.2.2:8080/news/news?categoryno=1&index=20");
            Log.e("===success==", s);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("===Test===", "Exception");
        }*/
        Log.e("======Test=====", "hkdgajsgljsajl");
	Log.e("======Test=====", "hkdgajsgljsajl");
	Log.e("======Test=====", "hkdgajsgljsajl");
	Log.e("======Test=====", "hkdgajsgljsajl");
    }
}
