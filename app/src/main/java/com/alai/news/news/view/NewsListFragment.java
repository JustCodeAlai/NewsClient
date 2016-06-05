package com.alai.news.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alai.news.R;
import com.alai.news.beans.NewsBean;
import com.alai.news.commons.Urls;
import com.alai.news.news.presenter.NewsPresenter;
import com.alai.news.news.presenter.NewsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1 0001.
 */
public class NewsListFragment extends Fragment implements NewsListView, SwipeRefreshLayout.OnRefreshListener{

    private final static String TAG = "NewsListFragment";

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mNewsAdapter;
    private NewsPresenter mPresenter;
    private List<NewsBean> mNewsBeanList;
    private LinearLayoutManager mLinearLayoutManager;
    private int mPageIndex = 0;
    private int mType;

    public static NewsListFragment newInstance(int type) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
        mPresenter = new NewsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, null);
        initView(view);
        onRefresh();
        return view;
    }

    private void initView(View view) {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.red_light, R.color.green_light, R.color.blue_light, R.color.orange_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        mLinearLayoutManager.findLastVisibleItemPosition() == mNewsAdapter.getItemCount() - 1) {
                    mPresenter.loadNews(mType, mPageIndex);
                }
            }
        });

        mNewsAdapter = new NewsAdapter();
        mNewsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                NewsBean data = mNewsAdapter.getItem(position);
                Intent intent = new Intent();
                if (data.getSkipType() == null) {
                    intent.setClass(getActivity(), NewsDetailActivity.class);
                } else {
                    if (data.getSkipType().equals("photoset")) {
                        intent.setClass(getActivity(), PhotosetNewsActivity.class);
                    }
                }
                intent.putExtra("newsInfo", data);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mNewsAdapter);
    }

    @Override
    public void onShowProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onHideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowLoadFailMsg() {
        View view = mRecyclerView.getRootView();
        Snackbar.make(view, "加载数据失败！", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreNews(List<NewsBean> list) {
        if (list == null) {
            return;
        }
        //第一次加载
        if (mNewsBeanList == null) {
            mNewsBeanList = new ArrayList<>();
        }
        mNewsBeanList.addAll(list);
        if (mPageIndex == 0) {
            mNewsAdapter.setData(mNewsBeanList);
        }
        mNewsAdapter.notifyDataSetChanged();
        //如果网络中断而请求数据为空，如果不做判断下次请求时就会漏掉数据
        int videoCount = 0;
        for (int i = 0; i<list.size(); i++) {
            NewsBean bean = list.get(i);
            if (bean.getSkipType() != null) {
                if (bean.getSkipType().equals("video")) {
                    videoCount++;
                }
            }
        }
        mPageIndex = mPageIndex + Urls.PAGE_SIZE + ++videoCount;
    }

    @Override
    public void onRefresh() {
        mPageIndex = 0;
        mPresenter.loadNews(mType, mPageIndex);
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
