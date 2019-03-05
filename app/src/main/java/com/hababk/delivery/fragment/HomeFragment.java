package com.hababk.delivery.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hababk.delivery.R;
import com.hababk.delivery.network.ApiUtils;
import com.hababk.delivery.network.ChefService;
import com.hababk.delivery.network.request.OrderStatusUpdateRequest;
import com.hababk.delivery.network.response.GoogleMapsRoute;
import com.hababk.delivery.network.response.Order;
import com.hababk.delivery.utils.Constants;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private SharedPreferenceUtil sharedPreferenceUtil;
    private ChefService chefService;
    private Order order;
    private GoogleMap googleMap;
    private TextView distanceText, durationText, chefName, orderNumber, orderTime, statusDelivery, actionDeliveryText, addressStore, addressCustomer;
    private ImageView chefImage;
    private FrameLayout actionDelivery;
    private ProgressBar progressBar;

    private Marker myLocation, toGoLocation;

    public HomeFragment() {
        // Required empty public constructor
    }

    BroadcastReceiver newLocationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (googleMap != null) {
                setMapDetails(getLatLngNavTo());
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(newLocationReceiver, new IntentFilter(Constants.BROADCAST_LOCATION));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(newLocationReceiver);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceUtil = new SharedPreferenceUtil(getContext());
        chefService = ApiUtils.getClient().create(ChefService.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);
        view.findViewById(R.id.toolbar).setVisibility(View.GONE);
        distanceText = view.findViewById(R.id.distance);
        durationText = view.findViewById(R.id.duration);
        chefImage = view.findViewById(R.id.chefImage);
        chefName = view.findViewById(R.id.chefName);
        orderNumber = view.findViewById(R.id.orderNumber);
        orderTime = view.findViewById(R.id.orderTime);
        statusDelivery = view.findViewById(R.id.statusDelivery);
        actionDelivery = view.findViewById(R.id.actionDelivery);
        actionDeliveryText = view.findViewById(R.id.actionDeliveryText);
        addressStore = view.findViewById(R.id.addressStore);
        addressCustomer = view.findViewById(R.id.addressCustomer);
        progressBar = view.findViewById(R.id.progressBar);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        actionDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order != null && !order.getDelivery_status().equals("complete")) {
                    toggleOrderStatus();
                } else {
                    Toast.makeText(getContext(), "Looking up for orders.", Toast.LENGTH_SHORT).show();
                    getOrder();
                }
            }
        });
        view.findViewById(R.id.distanceContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });
        view.findViewById(R.id.durationContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOrder();
    }

    public void getOrder() {
        progressBar.setVisibility(View.VISIBLE);
        chefService.getOrder(Helper.getApiToken(sharedPreferenceUtil)).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    HomeFragment.this.order = response.body();
                    setDetails();
                } else {
                    if (chefName != null) chefName.setText(getString(R.string.no_order));
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                if (chefName != null) chefName.setText(getString(R.string.no_order));
                Log.e("CHECK", t.getMessage());
            }
        });
    }

    private void toggleOrderStatus() {
        OrderStatusUpdateRequest orderStatusUpdateRequest = null;
        switch (order.getDelivery_status()) {
            case "allotted":
                if (order.getStatus().equals("dispatched"))
                    orderStatusUpdateRequest = new OrderStatusUpdateRequest("started");
                else
                    getOrder();
                break;
            case "started":
                orderStatusUpdateRequest = new OrderStatusUpdateRequest("complete");
                break;
        }
        if (orderStatusUpdateRequest != null) {
            progressBar.setVisibility(View.VISIBLE);
            chefService.updateOrderStatus(Helper.getApiToken(sharedPreferenceUtil), orderStatusUpdateRequest, order.getId()).enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    progressBar.setVisibility(View.INVISIBLE);
                    if (response.isSuccessful()) {
                        HomeFragment.this.order = response.body();
                        setDetails();
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setDetails() {
        setMapDetails(getLatLngNavTo());
        Glide.with(getContext()).load(order.getStore().getImage_url()).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher)).into(chefImage);
        chefName.setText(order.getStore().getName());
        addressStore.setText(order.getStore().getAddress());
        orderNumber.setText("Order #" + order.getId());
        orderTime.setText(Helper.timeDiff(order.getUpdated_at()));
        addressCustomer.setText(order.getAddress().getAddress());
        setOrderStatus();
    }

    private void setOrderStatus() {
        statusDelivery.setText(order.getStatus().substring(0, 1).toUpperCase() + order.getStatus().substring(1));
        statusDelivery.setSelected(true);
        switch (order.getDelivery_status()) {
            case "allotted":
                actionDeliveryText.setText("Start delivery");
                actionDelivery.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                Toast.makeText(getContext(), order.getStatus().equals("dispatched") ? "Order dispatched" : "Order not yet dispatched", Toast.LENGTH_SHORT).show();
                break;
            case "started":
                actionDeliveryText.setText("Mark Complete");
                actionDelivery.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                break;
            case "complete":
                Toast.makeText(getContext(), "Order completed.", Toast.LENGTH_SHORT).show();
                actionDeliveryText.setText("Refresh order");
                actionDelivery.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

                durationText.setText("-- min");
                distanceText.setText("-- km");
                chefName.setText(getString(R.string.loading));
                String Order_number = getString(R.string.order);
                orderNumber.setText(Order_number);
//                orderNumber.setText("Order #--");
                orderTime.setText("-- ago");
                statusDelivery.setText(getString(R.string.loading));
                addressStore.setText(getString(R.string.loading));
                addressCustomer.setText(getString(R.string.loading));
                break;
        }
    }

    private void navigate() {
        LatLng lastCurrent = Helper.getLastLocation(sharedPreferenceUtil);
        LatLng toGoTo = getLatLngNavTo();
        if (lastCurrent != null && toGoTo != null) {
            final String BASE_URI = "https://www.google.com/maps/dir/?api=1";
            String uri = BASE_URI + "&origin=" + lastCurrent.latitude + "," + lastCurrent.longitude;
            uri += "&destination=" + toGoTo.latitude + "," + toGoTo.longitude;

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (intent.resolveActivity(getContext().getPackageManager()) != null)
                startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setMapDetails(getLatLngNavTo());
    }

    private void setMapDetails(LatLng latLng) {
        if (googleMap == null)
            return;
        LatLng lastCurrent = Helper.getLastLocation(sharedPreferenceUtil);
        if (lastCurrent != null) {
            if (myLocation == null) {
                myLocation = googleMap.addMarker(new MarkerOptions().position(lastCurrent).title("You are here!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            } else {
                myLocation.setPosition(lastCurrent);
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastCurrent, 14));
        }

        if (latLng != null) {
            if (toGoLocation == null) {
                toGoLocation = googleMap.addMarker(new MarkerOptions().position(latLng).title("Go here!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            } else {
                toGoLocation.setPosition(latLng);
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        }

        if (lastCurrent != null && latLng != null && !order.getStatus().equals("complete")) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(lastCurrent);
            builder.include(latLng);
            LatLngBounds bounds = builder.build();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 14));

            chefService.getRoute(getString(R.string.directions_api_key),lastCurrent.latitude + "," + lastCurrent.longitude, latLng.latitude + "," + latLng.longitude).enqueue(new Callback<GoogleMapsRoute>() {
                @Override
                public void onResponse(Call<GoogleMapsRoute> call, Response<GoogleMapsRoute> response) {
                    if (response.isSuccessful() && !response.body().getRoutes().isEmpty() && !response.body().getRoutes().get(0).getLegs().isEmpty()) {
                        if (distanceText != null || durationText != null) {
                            distanceText.setText(response.body().getRoutes().get(0).getLegs().get(0).getDistance().getText());
                            durationText.setText(response.body().getRoutes().get(0).getLegs().get(0).getDuration().getText());
                        }
//                        if (response.body().getRoutes().get(0).getOverview_polyline() != null)
//                            plotPolyline(response.body().getRoutes().get(0).getOverview_polyline().decodePoly());
                    }
                }

                @Override
                public void onFailure(Call<GoogleMapsRoute> call, Throwable t) {

                }
            });
        }
    }

    private void plotPolyline(ArrayList<LatLng> latLngs) {
        googleMap.addPolyline(new PolylineOptions().addAll(latLngs).color(Color.BLACK));
        zoomLatLngs(latLngs);
    }

    private void zoomLatLngs(ArrayList<LatLng> toPlot) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : toPlot)
            builder.include(latLng);
        LatLngBounds bounds = builder.build();
        if (areBoundsTooSmall(bounds, 300)) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), 17));
        } else {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
        }
    }

    private boolean areBoundsTooSmall(LatLngBounds bounds, int minDistanceInMeter) {
        float[] result = new float[1];
        Location.distanceBetween(bounds.southwest.latitude, bounds.southwest.longitude, bounds.northeast.latitude, bounds.northeast.longitude, result);
        return result[0] < minDistanceInMeter;
    }

    private LatLng getLatLngNavTo() {
        LatLng latLng = null;
        if (order != null) {
            if (order.getStatus().equals("started") && order.getAddress() != null && order.getAddress().getLatitude() != null && order.getAddress().getLongitude() != null) {
                latLng = new LatLng(order.getAddress().getLatitude(), order.getAddress().getLongitude());
            } else if (order.getStore() != null && order.getStore().getLatitude() != null && order.getStore().getLongitude() != null) {
                latLng = new LatLng(order.getStore().getLatitude(), order.getStore().getLongitude());
            }
        }
        return latLng;
    }
}
