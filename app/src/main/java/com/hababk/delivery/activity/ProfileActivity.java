package com.hababk.delivery.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.hababk.delivery.R;
import com.hababk.delivery.model.User;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

public class ProfileActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPhone;
    private SharedPreferenceUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferencesUtil = new SharedPreferenceUtil(this);
        initUi();
        User user = Helper.getLoggedInUser(sharedPreferencesUtil);
        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etPhone.setText(user.getMobile_number());
    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle("Profile");
        }

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
