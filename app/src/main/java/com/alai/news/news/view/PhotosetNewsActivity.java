package com.alai.news.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.beans.PhotoBean;
import com.alai.news.comment.presenter.CommentPresenter;
import com.alai.news.comment.view.CommentActivity;
import com.alai.news.login.view.LoginActivity;
import com.alai.news.news.presenter.PhotosetPresenterImpl;
import com.alai.news.utils.CommentPostUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguochao on 2016/5/7.
 */
public class PhotosetNewsActivity extends AppCompatActivity implements PhotosetView {
    private static final String TAG = "PhotosetNewsActivity";
    private PhotosetPresenterImpl mPhotosetPresenter;
    private ViewPager mViewPager;
    private LinearLayout mLoadingLayout;
    private LinearLayout mLoadfailLayout;
    private LinearLayout mCommentLayout;
    private EditText mCommentEditText;
    private Button mCommentButton;
    private PhotoItemPagerAdapter mPagerAdapter;
    private NewsBean mNewsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoset);
        mNewsInfo = (NewsBean) getIntent().getSerializableExtra("newsInfo");
        initResources();
        mPhotosetPresenter = new PhotosetPresenterImpl(this);
        mPhotosetPresenter.loadPhotoset(mNewsInfo.getNo());
    }

    private void initResources() {
        mPagerAdapter = new PhotoItemPagerAdapter(getSupportFragmentManager());
        mLoadingLayout = (LinearLayout) findViewById(R.id.layout_loading);
        mLoadfailLayout = (LinearLayout) findViewById(R.id.layout_load_fail);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mCommentLayout = (LinearLayout) findViewById(R.id.layout_comment);
        mCommentEditText = (EditText) findViewById(R.id.edtComment);
        mCommentButton = (Button) findViewById(R.id.btn_comment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getLogin()){
                    CommentBean comment = new CommentBean();
                    comment.setUser(MyApplication.getsUserInfo());
                    comment.setNews(mNewsInfo);
                    comment.setContent(mCommentEditText.getText().toString());
                    CommentPostUtil.postComment(PhotosetNewsActivity.this, comment);
                    mCommentEditText.setText("");
                } else {
                    new MaterialDialog.Builder(PhotosetNewsActivity.this)
                            .content(R.string.need_login_tip)
                            .positiveText(R.string.login)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    Intent intent = new Intent(PhotosetNewsActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .negativeText(R.string.cancel)
                            .show();
                }
            }
        });
        mCommentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.toString().trim().length() != 0) {
                    mCommentButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onShowPhotoset(List<PhotoBean> beans) {
        mCommentLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
        Log.e(TAG, "this size of beans is " + beans.size());
        for(PhotoBean bean : beans) {
            mPagerAdapter.addFragment(PhotosetItemFragment.newInstance(bean));
        }
        mViewPager.setAdapter(mPagerAdapter);
        Log.e(TAG, "the end of onShowPhotoset");
    }

    @Override
    public void onShowLoadFailMsg() {
        mLoadfailLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowLoadingProgress() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoadingProgress() {
        mLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("data", mNewsInfo);
                startActivity(intent);
                break;
        }
        return true;
    }



    private static class PhotoItemPagerAdapter extends FragmentPagerAdapter {

        private List<PhotosetItemFragment> mFragments = new ArrayList<>();

        public PhotoItemPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(PhotosetItemFragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
