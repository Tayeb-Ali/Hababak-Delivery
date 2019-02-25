package com.hababk.delivery.activity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.hababk.delivery.R;
import com.hababk.delivery.adapter.UniversalPagerAdapter;
import com.hababk.delivery.fragment.AccountFragment;
import com.hababk.delivery.fragment.EarningsFragment;
import com.hababk.delivery.fragment.HomeFragment;
import com.hababk.delivery.model.User;
import com.hababk.delivery.network.ApiUtils;
import com.hababk.delivery.network.ChefService;
import com.hababk.delivery.network.request.DeliveryProfileUpdateRequest;
import com.hababk.delivery.network.request.FcmTokenUpdateRequest;
import com.hababk.delivery.network.response.DeliveryProfile;
import com.hababk.delivery.service.LocationService;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;
import com.hababk.delivery.view.NonSwipeableViewPager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private final String FRAG_TAG_HOME = "Home";
    private final String FRAG_TAG_EARNING = "Earnings";
    private final String FRAG_TAG_NOTIFICATION = "Notification";
    private final String FRAG_TAG_ACCOUNT = "Account";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private int[][] bottomViewRes = {{R.drawable.ic_home_24dp, R.drawable.ic_home_selected_24dp},
            {R.drawable.ic_money_24dp, R.drawable.ic_money_selected_24dp},
            {R.drawable.ic_notification_24dp, R.drawable.ic_notifications_selected_24dp},
            {R.drawable.ic_person_24dp, R.drawable.ic_person_selected_24dp}};
    private SwitchCompat onlineSwitch;
    private TextView[] bottomViews = new TextView[3];
    private NonSwipeableViewPager viewPager;

    private Handler mHandler;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private boolean checkForGps;
    private UniversalPagerAdapter adapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferenceUtil = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferenceUtil = new SharedPreferenceUtil(this);
        Helper.refreshSettings(sharedPreferenceUtil);
        initUi();
        setupViewPager();
        checkLocationPermission();
        updateFcmToken();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getStringExtra("order_id") != null && adapter != null && adapter.getItem(0) != null)
            recheck();
    }

    private void setupViewPager() {
        adapter = new UniversalPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), FRAG_TAG_HOME);
        adapter.addFrag(new EarningsFragment(), FRAG_TAG_EARNING);
        //adapter.addFrag(new NotificationFragment(), FRAG_TAG_NOTIFICATION);
        adapter.addFrag(new AccountFragment(), FRAG_TAG_ACCOUNT);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkForGps) {
            checkLocationPermission();
            checkForGps = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        checkLocationPermission();
                    }
                }
                break;
        }
    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(FRAG_TAG_HOME);
        viewPager = findViewById(R.id.viewPager);
        bottomViews[0] = findViewById(R.id.bottomNavOption1);
        bottomViews[1] = findViewById(R.id.bottomNavOption2);
//        bottomViews[2] = findViewById(R.id.bottomNavOption3);
        bottomViews[2] = findViewById(R.id.bottomNavOption4);
        for (TextView bottomView : bottomViews) {
            bottomView.setOnClickListener(this);
            bottomView.setSelected(true);
        }
        onlineSwitch = findViewById(R.id.onlineSwitch);
        onlineSwitch.setChecked(Helper.getDeliveryDetails(sharedPreferenceUtil).isIs_online());
        onlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onlineSwitch.setText(isChecked ? getString(R.string.online) : getString(R.string.offline));
                if (isChecked)
                    recheck();
                updateOnline(new DeliveryProfileUpdateRequest(isChecked));
            }
        });
    }

    private void checkLocationPermission() {
        DeliveryProfile savedDetails = Helper.getDeliveryDetails(sharedPreferenceUtil);
        if (savedDetails != null) {
            if (savedDetails.isIs_online()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        new AlertDialog.Builder(this)
                                .setTitle(R.string.location_permision)
                                .setMessage(R.string.permission_message)
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ActivityCompat.requestPermissions(HomeActivity.this,
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_LOCATION);
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                } else if (isGpsEnabled()) {
                    checkLocationUpdates(true);
                } else {
                    requestGps();
                }
            } else {
                checkLocationUpdates(false);
            }
        }
    }

    private void recheck() {
        if (adapter != null && adapter.getItem(0) != null)
            ((HomeFragment) adapter.getItem(0)).getOrder();
    }

    private void requestGps() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.location_services)
                .setMessage(R.string.gps_enable)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        checkForGps = true;
                    }
                })
                .create()
                .show();
    }

    private boolean isGpsEnabled() {
        String locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        return !TextUtils.isEmpty(locationProviders);
    }

    private void updateOnline(DeliveryProfileUpdateRequest deliveryProfileUpdateRequest) {
        Helper.setDeliveryDetails(sharedPreferenceUtil, DeliveryProfile.getFromRequest(deliveryProfileUpdateRequest));
        checkLocationPermission();

        ChefService chefService = ApiUtils.getClient().create(ChefService.class);
        chefService.updateProfile(Helper.getApiToken(sharedPreferenceUtil), deliveryProfileUpdateRequest).enqueue(new Callback<DeliveryProfile>() {
            @Override
            public void onResponse(Call<DeliveryProfile> call, Response<DeliveryProfile> response) {
                if (response.isSuccessful() && sharedPreferenceUtil != null) {
                    Helper.setDeliveryDetails(sharedPreferenceUtil, response.body());
                }
            }

            @Override
            public void onFailure(Call<DeliveryProfile> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void checkLocationUpdates(boolean online) {
        PendingIntent pendingIntent;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 99, new Intent(this, LocationService.class), 0);

        if (online) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, 2 * 60000, pendingIntent);
            Log.d("alarmManager", "Scheduled");
        } else {
            alarmManager.cancel(pendingIntent);
            Log.d("alarmManager", "Cancelled");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottomNavOption1:
                setBottomBarSelection(0);
                break;
            case R.id.bottomNavOption2:
                setBottomBarSelection(1);
                break;
//            case R.id.bottomNavOption3:
//                setBottomBarSelection(2);
//                break;
            case R.id.bottomNavOption4:
                setBottomBarSelection(2);
                break;
        }
    }

    private void setBottomBarSelection(final int selectedIndex) {
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(selectedIndex);
                getSupportActionBar().setTitle(adapter.getPageTitle(selectedIndex));
                onlineSwitch.setVisibility(selectedIndex == 0 ? View.VISIBLE : View.GONE);
            }
        });
        for (int i = 0; i < 3; i++) {
            bottomViews[i].setTextColor(ContextCompat.getColor(this, i == selectedIndex ? R.color.colorAccent : R.color.grayDark));
            bottomViews[i].setBackgroundResource(i == selectedIndex ? R.drawable.bottom_bar_item_selected : android.R.color.white);
            bottomViews[i].setCompoundDrawablesWithIntrinsicBounds(0, bottomViewRes[i][i == selectedIndex ? 1 : 0], 0, 0);
        }
    }

    private void updateFcmToken() {
        ApiUtils.getClient().create(ChefService.class).updateFcmToken(Helper.getApiToken(sharedPreferenceUtil), new FcmTokenUpdateRequest(FirebaseInstanceId.getInstance().getToken())).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("FcmTokenUpdate", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("FcmTokenUpdate", t.getMessage());
            }
        });
    }

    public static Intent newIntent(Context context, String order_id) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("order_id", order_id);
        return intent;
    }
}
