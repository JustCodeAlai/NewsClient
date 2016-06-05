package com.alai.news.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alai.news.R;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class NewsFragment extends Fragment {

    public static final int NEWS_TYPE_TOP = 1;
    public static final int NEWS_TYPE_NBA = 2;
    public static final int NEWS_TYPE_SOCIETY =  3;
    public static final int NEWS_TYPE_ENTERTAINMENT = 4;
    public static final int NEWS_TYPE_FINANCE = 5;
    public static final int NEWS_TYPE_SPORT = 6;
    public static final int NEWS_TYPE_CARS = 7;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_top));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_nba));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_society));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_entertainment));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_finance));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_sport));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.news_car));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager() {
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(getString(R.string.news_top), NewsListFragment.newInstance(NEWS_TYPE_TOP));
        adapter.addFragment(getString(R.string.news_nba), NewsListFragment.newInstance(NEWS_TYPE_NBA));
        adapter.addFragment(getString(R.string.news_society), NewsListFragment.newInstance(NEWS_TYPE_SOCIETY));
        adapter.addFragment(getString(R.string.news_entertainment), NewsListFragment.newInstance(NEWS_TYPE_ENTERTAINMENT));
        adapter.addFragment(getString(R.string.news_finance), NewsListFragment.newInstance(NEWS_TYPE_FINANCE));
        adapter.addFragment(getString(R.string.news_sport), NewsListFragment.newInstance(NEWS_TYPE_SPORT));
        adapter.addFragment(getString(R.string.news_car), NewsListFragment.newInstance(NEWS_TYPE_CARS));
        mViewPager.setAdapter(adapter);
    }
}
