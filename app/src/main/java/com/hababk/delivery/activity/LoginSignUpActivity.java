package com.hababk.delivery.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hababk.delivery.R;
import com.hababk.delivery.fragment.LoginSignUpTabFragment;
import com.hababk.delivery.utils.IntentKeyConstants;

import butterknife.ButterKnife;

public class LoginSignUpActivity extends AppCompatActivity {
    private int tabPosition;
    private static final String TAG = LoginSignUpActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        ButterKnife.bind(this);

        ImageView splashBg = findViewById(R.id.splashBg);
        Glide.with(this).load(R.drawable.background).into(splashBg);

        ImageView chef_logo = findViewById(R.id.chef_logo);
        Glide.with(this).load(R.drawable.chef_logo).into(chef_logo);

        tabPosition = getIntent().getIntExtra(IntentKeyConstants.TAB_POSITION, 0);
        openLoginSIgnUpTabFragment();
    }

    private void openLoginSIgnUpTabFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.splashFrame, LoginSignUpTabFragment.newInstance(tabPosition), TAG);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}