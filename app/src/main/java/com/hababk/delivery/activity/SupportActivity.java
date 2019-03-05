package com.hababk.delivery.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hababk.delivery.R;
import com.hababk.delivery.model.User;
import com.hababk.delivery.network.ApiUtils;
import com.hababk.delivery.network.ChefService;
import com.hababk.delivery.network.request.SupportRequest;
import com.hababk.delivery.network.response.SupportResponse;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {
    private EditText name, email, message;
    private ProgressBar progressBar;
    private TextView submitSupport,supportPhone, supportEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle("الدعم الفني");
        }

        supportEmail = findViewById(R.id.supportEmail);
        supportPhone = findViewById(R.id.supportPhone);
        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        message = findViewById(R.id.etMessage);
        progressBar = findViewById(R.id.progressBar);
        submitSupport = findViewById(R.id.submitSupport);
        submitSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText())) {
                    Toast.makeText(SupportActivity.this, "Please provide your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    Toast.makeText(SupportActivity.this, "Enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(message.getText().toString().trim())) {
                    Toast.makeText(SupportActivity.this, "Please provide message for support", Toast.LENGTH_SHORT).show();
                    return;
                }
                Helper.closeKeyboard(SupportActivity.this, submitSupport);
                submitSupportRequest(new SupportRequest(name.getText().toString(), email.getText().toString(), message.getText().toString().trim()));
            }
        });

        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this);
        User user = Helper.getLoggedInUser(sharedPreferenceUtil);
        if (user != null) {
            name.setText(user.getName());
            email.setText(user.getEmail());
        }
        final String emailSetting = Helper.getSetting(sharedPreferenceUtil, "support_email");
        if (!TextUtils.isEmpty(emailSetting)) {
            supportEmail.setText(emailSetting);
            supportEmail.setSelected(true);
            findViewById(R.id.supportEmailContainer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailSetting, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(emailIntent, "Send email"));
                }
            });
        }
        final String phoneSetting = Helper.getSetting(sharedPreferenceUtil, "support_phone");
        if (!TextUtils.isEmpty(phoneSetting)) {
            supportPhone.setText(phoneSetting);
            supportPhone.setSelected(true);
            findViewById(R.id.supportPhoneContainer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneSetting, null)));
                }
            });
        }
    }

    private void submitSupportRequest(SupportRequest supportRequest) {
        setSubmitProgress(true);
        ChefService service = ApiUtils.getClient().create(ChefService.class);
        service.support(supportRequest).enqueue(new Callback<SupportResponse>() {
            @Override
            public void onResponse(Call<SupportResponse> call, Response<SupportResponse> response) {
                setSubmitProgress(false);
                if (response.isSuccessful()) {
                    Toast.makeText(SupportActivity.this, "Request submitted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SupportActivity.this, "Something went wrong!#101", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SupportResponse> call, Throwable t) {
                setSubmitProgress(false);
                Toast.makeText(SupportActivity.this, "Something went wrong!#102", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSubmitProgress(boolean b) {
        progressBar.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        submitSupport.setClickable(!b);
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
