package com.alai.news.comment.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alai.news.R;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.comment.presenter.CommentPresenter;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/8.
 */
public class CommentActivity extends AppCompatActivity {
    private final static String TAG = "CommentActivity";

    private LinearLayout mLoadingLayout;
    private LinearLayout mLoadfailLayout;
    private RecyclerView mRecyclerView;
    private TextView mNoCommentTv;

    private LinearLayoutManager mLinearLayoutManager;
    private CommentAdapter mAdapter;
    private NewsBean mNewsInfo;
    private CommentPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mNewsInfo = (NewsBean) getIntent().getSerializableExtra("data");
        initResources();
        mPresenter = new CommentPresenter(this);
        mPresenter.loadComments(mNewsInfo);
    }

    private void initResources() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("评论");
        mLoadingLayout = (LinearLayout) findViewById(R.id.layout_loading);
        mLoadfailLayout = (LinearLayout) findViewById(R.id.layout_load_fail);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNoCommentTv = (TextView) findViewById(R.id.tvNoComment);
        mAdapter = new CommentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onShowComments(List<CommentBean> comments) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setData(comments);
        mAdapter.notifyDataSetChanged();
    }
    public void onShowLoadFailMsg() {
        mLoadfailLayout.setVisibility(View.VISIBLE);
    }

    public void onShowLoadingProgress() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }
    public void onHideLoadingProgress() {
        mLoadingLayout.setVisibility(View.GONE);
    }
    public void onShowNoCommentMsg() {
        mNoCommentTv.setVisibility(View.VISIBLE);
    }
}
