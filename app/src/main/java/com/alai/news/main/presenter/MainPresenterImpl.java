package com.alai.news.main.presenter;

import com.alai.news.R;
import com.alai.news.main.view.MainView;

/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switch2(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mMainView.onSwitch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.onSwitch2Image();
                break;
            case R.id.navigation_item_video:
                mMainView.onSwitch2Video();
                break;
            case R.id.navigation_item_about:
                mMainView.onSwitch2About();
                break;
           /* case R.id.navigation_theme_switch:
                mMainView.onSwitchTheme();
                break;*/
            default:
                break;
        }
    }

    @Override
    public void destroy() {
        mMainView = null;
    }
}
