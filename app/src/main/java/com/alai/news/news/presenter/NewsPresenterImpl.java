package com.alai.news.news.presenter;

import com.alai.news.MyApplication;
import com.alai.news.beans.NewsBean;
import com.alai.news.commons.Urls;
import com.alai.news.news.model.NewsModel;
import com.alai.news.news.model.NewsModelImpl;
import com.alai.news.news.view.NewsFragment;
import com.alai.news.news.view.NewsListView;

import java.util.List;

/**
 * Created by Administrator on 2016/2/9 0009.
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModel.OnLoadNewsListFinishListener {

    private static final String TAG = "NewsPresenterImpl";

    private NewsListView mView;
    private NewsModel mModel;
    public NewsPresenterImpl(NewsListView view) {
        mView = view;
        mModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, int pageIndex) {
        //http://user-20160510sb:8080/news/news?categoryno=1&index=10
        String url = Urls.HOST1 + "news?categoryno=" + type + "&index=" + pageIndex;
        if (pageIndex == 0) {
            mView.onShowProgress();
        }
        mModel.loadNews(url, this);
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onSuccess(List<NewsBean> newsBeanList) {
        mView.onHideProgress();
        mView.onLoadMoreNews(newsBeanList);
    }

    @Override
    public void onFailure() {
        mView.onHideProgress();
        mView.onShowLoadFailMsg();
    }

    /*private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_LIST_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_LIST_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_LIST_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_LIST_URL).append(Urls.JOKE_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END);
        return sb.toString();
    }*/
}
