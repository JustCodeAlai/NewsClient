package com.alai.news.register.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.UserBean;
import com.alai.news.register.presenter.RegisterPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by chenguochao on 2016/4/20.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mRegisterButton;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPresenter = new RegisterPresenter(this);
        initResources();
    }

    private void initResources() {
        getSupportActionBar().setTitle("注册");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mUsernameEditText = (EditText) findViewById(R.id.register_edt_user_name);
        mPasswordEditText = (EditText) findViewById(R.id.register_edt_password);
        mRegisterButton = (Button) findViewById(R.id.register_btn_register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsernameEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.register(name, password);
            }
        });
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

    /**
     * 注册成功返回用户名和密码给登录界面
     */
    public void sendLoginBroadcast(UserBean userInfo) {
        MyApplication.setLogin(true);
        MyApplication.setsUserInfo(userInfo);
        Intent intent = new Intent();
        intent.setAction("com.alai.news.ACTION_LOGIN");
        sendBroadcast(intent);
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userno", userInfo.getNo());
        editor.putString("username", userInfo.getName());
        editor.putString("password", userInfo.getPassword());
        editor.commit();
        finish();
    }

    public void exist() {
        Toast.makeText(this, "用户名已存在！", Toast.LENGTH_SHORT).show();
    }

    public void fail() {
        Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
    }

}
