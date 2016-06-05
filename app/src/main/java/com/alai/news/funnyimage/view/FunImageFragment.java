package com.alai.news.funnyimage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alai.news.R;
import com.alai.news.beans.FunImageBean;
import com.alai.news.funnyimage.presenter.FunImagePresenter;
import com.alai.news.funnyimage.presenter.FunImagePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FunImageFragment extends Fragment implements FunImageView, SwipeRefreshLayout.OnRefreshListener {

    private final static String TAG = "FunImageFragment";

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FunImageAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LinearLayout mLoadingLayout;
    private LinearLayout mLoadFailLayout;

    private FunImagePresenter mPresenter;
    private List<FunImageBean> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FunImagePresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, null);
        mLoadingLayout = (LinearLayout) view.findViewById(R.id.layout_loading);
        mLoadFailLayout = (LinearLayout) view.findViewById(R.id.layout_load_fail);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new FunImageAdapter(getActivity().getApplicationContext());

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        onRefresh();
        return view;
    }

    @Override
    public void onShowLoadingProgress() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoadingProgress() {
        mLoadingLayout.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowLoadfailMsg() {
        mLoadFailLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowFunImageListData(List<FunImageBean> list) {
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        mAdapter.setData(mData);
    }

    @Override
    public void onRefresh() {
        if (mData != null) {
            mData.clear();
        }
        mPresenter.loadImageList();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
