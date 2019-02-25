package com.hababk.delivery.service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.hababk.delivery.R;
import com.hababk.delivery.network.ApiUtils;
import com.hababk.delivery.network.ChefService;
import com.hababk.delivery.network.request.DeliveryProfileUpdateRequest;
import com.hababk.delivery.network.request.LocationUpdateRequest;
import com.hababk.delivery.network.response.DeliveryProfile;
import com.hababk.delivery.utils.Constants;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationService extends Service {
    private static final String CHANNEL_ID_MAIN = "delivery_channel_01";

    private Double latitude, longitude;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private SharedPreferenceUtil sharedPreferenceUtil;

    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LocationService", "onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LocationService", "onCreate");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_MAIN, "Delivery location service", NotificationManager.IMPORTANCE_LOW);
            channel.setSound(null, null);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_MAIN)
                .setSmallIcon(R.drawable.outline_my_location_white)
                .setContentTitle("Fetching Location")
                .setContentText("Fetching current location")
                .setSound(null)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocationService", "onStartCommand");
        sharedPreferenceUtil = new SharedPreferenceUtil(this);
        setupLocation();
        checkLocationPermission();
        return super.onStartCommand(intent, flags, startId);
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGpsEnabled()) {
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            } else {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }
    }

    private void setupLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    mLastLocation = location;
                }
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                updateLocation(new DeliveryProfileUpdateRequest(latitude, longitude, true));
            }
        };
    }

    private void updateLocation(DeliveryProfileUpdateRequest deliveryProfileUpdateRequest) {
        Helper.setLastLocation(sharedPreferenceUtil, new LatLng(deliveryProfileUpdateRequest.getLatitude(), deliveryProfileUpdateRequest.getLongitude()));
        broadcastLocation();
        ApiUtils.getClient().create(ChefService.class).updateProfile(Helper.getApiToken(sharedPreferenceUtil), deliveryProfileUpdateRequest).enqueue(new Callback<DeliveryProfile>() {
            @Override
            public void onResponse(Call<DeliveryProfile> call, Response<DeliveryProfile> response) {
                if (response.isSuccessful())
                    Helper.setDeliveryDetails(sharedPreferenceUtil, response.body());
            }

            @Override
            public void onFailure(Call<DeliveryProfile> call, Throwable t) {

            }
        });

        DeliveryProfile deliveryProfile = Helper.getDeliveryDetails(sharedPreferenceUtil);
        if (deliveryProfile != null && deliveryProfile.getId() != null) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference().child("cookfu").child(deliveryProfile.getId().toString()).setValue(new LocationUpdateRequest(deliveryProfileUpdateRequest.getLatitude(), deliveryProfileUpdateRequest.getLongitude())).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    stopForeground(true);
                    stopSelf();
                }
            });
        } else {
            stopForeground(true);
            stopSelf();
            Log.d("LocationService", "destroying");
        }
    }

    private void broadcastLocation() {
        Intent intent = new Intent(Constants.BROADCAST_LOCATION);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);
    }

    private boolean isGpsEnabled() {
        String locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        return !TextUtils.isEmpty(locationProviders);
    }
}
