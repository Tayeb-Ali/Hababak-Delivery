package com.hababk.delivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hababk.delivery.R;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.IntentKeyConstants;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_signin_btn)
    Button signInBtn;
    @BindView(R.id.splash_register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this);
        if (Helper.isLoggedIn(sharedPreferenceUtil)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }else{
            Helper.refreshSettings(sharedPreferenceUtil);
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ImageView splashBg = findViewById(R.id.splashBg);
        Glide.with(this).load(R.drawable.background).into(splashBg);

        ImageView chef_logo = findViewById(R.id.chef_logo);
        Glide.with(this).load(R.drawable.chef_logo).into(chef_logo);
    }

    @OnClick({R.id.splash_signin_btn})
    public void onCLickSignIn() {
        openLoginSignUpActivity(0);
    }

    @OnClick({R.id.splash_register_btn})
    public void onClickRegister() {
        openLoginSignUpActivity(1);
    }

    private void openLoginSignUpActivity(int position) {
        Intent logiSignUpIntent = new Intent(this, LoginSignUpActivity.class);
        logiSignUpIntent.putExtra(IntentKeyConstants.TAB_POSITION, position);
        logiSignUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logiSignUpIntent);
        finish();
    }
}