package com.alai.news.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.beans.NewsDetailBean;
import com.alai.news.beans.PhotoBean;
import com.alai.news.comment.view.CommentActivity;
import com.alai.news.login.view.LoginActivity;
import com.alai.news.news.presenter.NewsDetailPresenter;
import com.alai.news.news.presenter.NewsDetailPresenterImpl;
import com.alai.news.news.presenter.PhotosetPresenterImpl;
import com.alai.news.utils.CommentPostUtil;
import com.bumptech.glide.Glide;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

/**
 * Created by Administrator on 2016/2/22.
 */
public class NewsDetailActivity extends AppCompatActivity {
    private final static String TAG = "NewsDetailActivity";

    /*private LinearLayout mLoadingLayout;
    private LinearLayout mLoadfailLayout;*/
    private LinearLayout mCommentLayout;
    private EditText mCommentEditText;
    private Button mCommentButton;
    //private ScrollView mContentLayout;
    private TextView mTitle;
    private TextView mSource;
    private TextView mTime;
    private ImageView mImage;
    private HtmlTextView mBody;

    private NewsBean mNewsInfo;
    //private NewsDetailPresenter mPresenter;
    private int mType;
    private String mPostId;
    //如果没有图片就用列表的图片
    private String mImgurl;

    private PhotosetPresenterImpl mPhotosetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initResource();
        mNewsInfo = (NewsBean) getIntent().getSerializableExtra("newsInfo");
        mPhotosetPresenter = new PhotosetPresenterImpl();
        mPhotosetPresenter.loadPhotoset(this, mNewsInfo.getNo());
        showNewsDetail();
        //mPresenter = new NewsDetailPresenterImpl(this);
        //mPresenter.loadNewsDetail(mPostId);
    }

    private void initResource() {
        /*mLoadingLayout = (LinearLayout) findViewById(R.id.layout_loading);
        mLoadfailLayout = (LinearLayout) findViewById(R.id.layout_load_fail);*/
        mCommentLayout = (LinearLayout) findViewById(R.id.layout_comment);
        mCommentEditText = (EditText) findViewById(R.id.edtComment);
        mCommentButton = (Button) findViewById(R.id.btn_comment);
        //mContentLayout = (ScrollView) findViewById(R.id.news_detail_layout_content);
        mTitle = (TextView) findViewById(R.id.tvTitle);
        mSource = (TextView) findViewById(R.id.tvSource);
        mTime = (TextView) findViewById(R.id.tvTime);
        mImage = (ImageView) findViewById(R.id.ivImage);
        mBody = (HtmlTextView) findViewById(R.id.htvBody);

        /*mType = getIntent().getIntExtra("type", 0);
        mPostId = getIntent().getStringExtra("postid");
        mImgurl = getIntent().getStringExtra("imgsrc");
        getSupportActionBar().setTitle(getType());*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getLogin()) {
                    CommentBean comment = new CommentBean();
                    comment.setUser(MyApplication.getsUserInfo());
                    comment.setNews(mNewsInfo);
                    comment.setContent(mCommentEditText.getText().toString());
                    CommentPostUtil.postComment(NewsDetailActivity.this, comment);
                    mCommentEditText.setText("");
                } else {
                    new MaterialDialog.Builder(NewsDetailActivity.this)
                            .content(R.string.need_login_tip)
                            .positiveText(R.string.login)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    Intent intent = new Intent(NewsDetailActivity.this, LoginActivity.class);
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

    private void showNewsDetail() {
        mTitle.setText(mNewsInfo.getTitle());
        mSource.setText(mNewsInfo.getSource());
        mTime.setText(mNewsInfo.getPtime());
        mBody.setHtmlFromString(mNewsInfo.getBody(), false);
    }

    public void showPhoto(List<PhotoBean> list) {
        if (list == null || list.size() == 0) {
            Glide.with(this).load(mNewsInfo.getImgsrc()).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(mImage);
            return;
        }
        Glide.with(this).load(list.get(0).getImgsrc()).
                placeholder(R.mipmap.ic_image_loading).
                error(R.mipmap.ic_image_loadfail).
                crossFade().into(mImage);
    }

    @Override
    protected void onDestroy() {
        //mPresenter.destroy();
        super.onDestroy();
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

    /*@Override
    public void onShowNewsDetail(NewsDetailBean bean) {
        mTitle.setText(bean.getTitle());
        mSource.setText(bean.getSource());
        mTime.setText(bean.getTime());

        //如果没有图片就用列表的图片
        if (bean.getImgUrl() != null) {
            Glide.with(this).load(bean.getImgUrl()).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(mImage);
        } else {
            Glide.with(this).load(mImgurl).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(mImage);
        }
        mBody.setHtmlFromString(bean.getBody(), false);
        mContentLayout.setVisibility(View.VISIBLE);
        mCommentLayout.setVisibility(View.VISIBLE);
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
    }*/

    /*private int getType() {
        int resource = 0;
        switch (mType) {
            case NewsFragment.NEWS_TYPE_TOP:
                resource = R.string.news_top;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                resource = R.string.news_nba;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                resource = R.string.news_joke;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                resource = R.string.news_car;
                break;
        }
        return resource;
    }*/
}
