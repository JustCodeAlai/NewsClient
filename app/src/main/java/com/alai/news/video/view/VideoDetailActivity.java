package com.alai.news.video.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.comment.view.CommentActivity;
import com.alai.news.login.view.LoginActivity;
import com.alai.news.utils.CommentPostUtil;
import com.alai.news.video.presenter.VideoPresenter;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

/**
 * Created by chenguochao on 2016/5/5.
 */
public class VideoDetailActivity extends AppCompatActivity {
    private static final String TAG = "VideoDetailActivity";

    private EditText mCommentEditText;
    private Button mCommentButton;
    private TextView mTitle;
    private TextView mTime;
    private TextView mDigest;
    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;
    private NewsBean mNewsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        mNewsInfo = (NewsBean) getIntent().getSerializableExtra("newsInfo");
        initResources();
    }

    private void initResources() {
        getSupportActionBar().setTitle("视频");
        mCommentEditText = (EditText) findViewById(R.id.edtComment);
        mCommentButton = (Button) findViewById(R.id.btn_comment);
        mTitle = (TextView) findViewById(R.id.tvTitle);
        mTime = (TextView) findViewById(R.id.tvTime);
        mDigest = (TextView) findViewById(R.id.tvDigest);
        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);

        mTitle.setText(mNewsInfo.getTitle());
        mTime.setText(mNewsInfo.getPtime());
        mDigest.setText(mNewsInfo.getDigest());
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoURI(Uri.parse(mNewsInfo.getVideosrc()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getLogin()) {
                    CommentBean comment = new CommentBean();
                    comment.setUser(MyApplication.getsUserInfo());
                    comment.setNews(mNewsInfo);
                    comment.setContent(mCommentEditText.getText().toString());
                    CommentPostUtil.postComment(VideoDetailActivity.this, comment);
                    mCommentEditText.setText("");
                } else {
                    new MaterialDialog.Builder(VideoDetailActivity.this)
                            .content(R.string.need_login_tip)
                            .positiveText(R.string.login)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    Intent intent = new Intent(VideoDetailActivity.this, LoginActivity.class);
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
}
