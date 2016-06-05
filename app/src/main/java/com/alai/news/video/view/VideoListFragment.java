package com.alai.news.video.view;

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
import com.alai.news.video.presenter.VideoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguochao on 2016/5/5.
 */
public class VideoListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = "NewsListFragment";

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private VideoAdapter mVideoAdapter;
    private VideoPresenter mPresenter;
    private List<NewsBean> mNewsBeanList;
    private LinearLayoutManager mLinearLayoutManager;
    private int mPageIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new VideoPresenter(this);
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
                        mLinearLayoutManager.findLastVisibleItemPosition() == mVideoAdapter.getItemCount() - 1) {
                    mPresenter.loadVideos(Urls.VIDEO_URL + mPageIndex);
                }
            }
        });

        mVideoAdapter = new VideoAdapter();
        mVideoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                NewsBean data = mVideoAdapter.getItem(position);
                Intent intent = new Intent();
                intent.setClass(getActivity(), VideoDetailActivity.class);
                intent.putExtra("newsInfo", data);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mVideoAdapter);
    }

    public void onShowProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void onHideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onShowLoadFailMsg() {
        View view = mRecyclerView.getRootView();
        Snackbar.make(view, "加载数据失败！", Snackbar.LENGTH_SHORT).show();
    }

    public void onLoadMoreVideo(List<NewsBean> list) {
        if (list == null) {
            return;
        }
        //第一次加载
        if (mNewsBeanList == null) {
            mNewsBeanList = new ArrayList<>();
        }
        mNewsBeanList.addAll(list);
        if (mPageIndex == 0) {
            mVideoAdapter.setData(mNewsBeanList);
        }
        mVideoAdapter.notifyDataSetChanged();
        //如果网络中断而请求数据为空，如果不做判断下次请求时就会漏掉数据
        mPageIndex = mPageIndex + 10;
    }

    @Override
    public void onRefresh() {
        mPageIndex = 0;
        mPresenter.loadVideos(Urls.VIDEO_URL + mPageIndex);
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
