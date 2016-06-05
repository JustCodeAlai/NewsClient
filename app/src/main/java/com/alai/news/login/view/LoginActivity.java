package com.alai.news.login.view;

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
import android.widget.TextView;
import android.widget.Toast;

import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.UserBean;
import com.alai.news.login.presenter.LoginPresenter;
import com.alai.news.login.presenter.LoginPresenterImpl;
import com.alai.news.register.view.RegisterActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by chenguochao on 2016/4/18.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    public final static int REGISTER_REQUEST = 2;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mRegisterTextView;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenterImpl(this);
        initResources();
    }

    private void initResources() {
        mRegisterTextView = (TextView) findViewById(R.id.login_tv_register);
        mUsernameEditText = (EditText) findViewById(R.id.login_edt_user_name);
        mPasswordEditText = (EditText) findViewById(R.id.login_edt_password);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        mRegisterTextView = (TextView) findViewById(R.id.login_tv_register);
        getSupportActionBar().setTitle("登录");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsernameEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.validateCredentials(name, password);
            }
        });

        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_REQUEST);
                finish();
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
        return true;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTER_REQUEST) {
            if (resultCode == RESULT_OK) {

                String username = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                mUsernameEditText.setText(username);
                mPasswordEditText.setText(password);
                mPresenter.validateCredentials(username, password);
            }
        }
    }*/

    @Override
    public void setError() {
        Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(UserBean userInfo) {
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
}
