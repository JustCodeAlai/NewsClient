package com.alai.news.main.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.about.view.AboutFragment;
import com.alai.news.funnyimage.view.FunImageFragment;
import com.alai.news.login.view.LoginActivity;
import com.alai.news.main.presenter.MainPresenter;
import com.alai.news.main.presenter.MainPresenterImpl;
import com.alai.news.news.view.NewsFragment;
import com.alai.news.video.view.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/1/25 0025.
 */
public class MainActivity extends AppCompatActivity implements MainView {

    public final static int LOGIN_REQUEST = 1;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private View mHeaderView;

    private CircleImageView mHeaderImage;
    private TextView mHeaderName;

    private Fragment[] mFragments = new Fragment[4];
    private Fragment mCurrentFragment;
    private MainPresenter mPresenter;

    private BroadcastReceiver mLoginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mHeaderName.setText(MyApplication.getsUserInfo().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.alai.news.ACTION_LOGIN");
        registerReceiver(mLoginReceiver, filter);
    }

    private void initResources() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mHeaderView = mNavigationView.getHeaderView(0);
        mHeaderImage = (CircleImageView) mHeaderView.findViewById(R.id.profile_image);
        mHeaderName = (TextView) mHeaderView.findViewById(R.id.tvHeaderName);
        mPresenter = new MainPresenterImpl(this);

        mFragments[0] = new NewsFragment();
        mCurrentFragment = mFragments[0];
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mFragments[0])
                .commit();
        mToolbar.setTitle(R.string.navigation_news);

        if (MyApplication.getLogin()) {
            mHeaderName.setText(MyApplication.getsUserInfo().getName());
        }
        mHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mPresenter.switch2(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        unregisterReceiver(mLoginReceiver);
        super.onDestroy();
    }

    @Override
    public void onSwitch2News() {
        if (mFragments[0] == null) {
            mFragments[0] = new NewsFragment();
        }
        if (mFragments[0] != mCurrentFragment) {
            if (!mFragments[0].isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .add(R.id.fragment_container, mFragments[0])
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .show(mFragments[0])
                        .commit();
            }
            mCurrentFragment = mFragments[0];
            mToolbar.setTitle(R.string.navigation_news);
        }
    }

    @Override
    public void onSwitch2Image() {
        if (mFragments[1] == null) {
            mFragments[1] = new FunImageFragment();
        }
        if (mFragments[1] != mCurrentFragment) {
            if (!mFragments[1].isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .add(R.id.fragment_container, mFragments[1])
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .show(mFragments[1])
                        .commit();
            }
            mCurrentFragment = mFragments[1];
            mToolbar.setTitle(R.string.navigation_images);
        }
    }

    @Override
    public void onSwitch2Video() {
        if (mFragments[2] == null) {
            mFragments[2] = new VideoListFragment();
        }
        if (mFragments[2] != mCurrentFragment) {
            if (!mFragments[2].isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .add(R.id.fragment_container, mFragments[2])
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .show(mFragments[2])
                        .commit();
            }
            mCurrentFragment = mFragments[2];
            mToolbar.setTitle(R.string.navigation_video);
        }
    }

    @Override
    public void onSwitch2About() {
        if (mFragments[3] == null) {
            mFragments[3] = new AboutFragment();
        }
        if (mFragments[3] != mCurrentFragment) {
            if (!mFragments[3].isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .add(R.id.fragment_container, mFragments[3])
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .show(mFragments[3])
                        .commit();
            }
            mCurrentFragment = mFragments[3];
            mToolbar.setTitle(R.string.navigation_about);
        }
    }

    @Override
    public void onSwitch2Setting() {

    }

    @Override
    public void onSwitchTheme() {

    }
}
