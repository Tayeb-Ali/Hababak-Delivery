package com.hababk.delivery.network;

import com.google.gson.JsonObject;
import com.hababk.delivery.model.User;
import com.hababk.delivery.network.request.BankDetailRequest;
import com.hababk.delivery.network.request.DeliveryProfileUpdateRequest;
import com.hababk.delivery.network.request.FcmTokenUpdateRequest;
import com.hababk.delivery.network.request.LoginRequest;
import com.hababk.delivery.network.request.MenuItemCreateRequest;
import com.hababk.delivery.network.request.MobileVerifiedRequest;
import com.hababk.delivery.network.request.OrderStatusUpdateRequest;
import com.hababk.delivery.network.request.RegisterRequest;
import com.hababk.delivery.network.request.SupportRequest;
import com.hababk.delivery.network.response.AuthResponse;
import com.hababk.delivery.network.response.BankDetailResponse;
import com.hababk.delivery.network.response.BaseListModel;
import com.hababk.delivery.network.response.DeliveryProfile;
import com.hababk.delivery.network.response.EarningResponse;
import com.hababk.delivery.network.response.GoogleMapsRoute;
import com.hababk.delivery.network.response.MenuItem;
import com.hababk.delivery.network.response.MenuItemCategory;
import com.hababk.delivery.network.response.Order;
import com.hababk.delivery.network.response.SettingResponse;
import com.hababk.delivery.network.response.SupportResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a_man on 05-12-2017.
 */

public interface ChefService {
    @Headers("Accept: application/json")
    @POST("api/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @Headers("Accept: application/json")
    @POST("api/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);

    @Headers("Accept: application/json")
    @POST("api/verify-mobile")
    Call<JsonObject> verifyMobile(@Body MobileVerifiedRequest mobileVerifiedRequest);

    @Headers("Accept: application/json")
    @POST("api/support")
    Call<SupportResponse> support(@Body SupportRequest supportRequest);

    @Headers("Accept: application/json")
    @PUT("api/user")
    Call<User> updateFcmToken(@Header("Authorization") String token, @Body FcmTokenUpdateRequest fcmTokenUpdateRequest);

    @Headers("Accept: application/json")
    @GET("api/bank-detail")
    Call<BankDetailResponse> getBankDetails(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @GET("api/earnings")
    Call<EarningResponse> getEarnings(@Header("Authorization") String token, @Query("page") int page);

    @Headers("Accept: application/json")
    @POST("api/bank-detail")
    Call<BankDetailResponse> setBankDetails(@Header("Authorization") String token, @Body BankDetailRequest bankDetailRequest);

    @Headers("Accept: application/json")
    @GET("api/menuitem")
    Call<BaseListModel<MenuItem>> getMenuItems(@Header("Authorization") String token, @Query("status") String status, @Query("page") Integer page);

    @Headers("Accept: application/json")
    @GET("api/delivery/order")
    Call<Order> getOrder(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @PUT("api/delivery/update-delivery-status/{id}")
    Call<Order> updateOrderStatus(@Header("Authorization") String token, @Body OrderStatusUpdateRequest orderStatusUpdateRequest, @Path("id") Integer orderId);

    @Headers("Accept: application/json")
    @POST("api/menuitem")
    Call<MenuItem> createMenuItem(@Header("Authorization") String token, @Body MenuItemCreateRequest menuItemCreateRequest);

    @Headers("Accept: application/json")
    @POST("api/menuitem/{id}")
    Call<MenuItem> updateMenuItem(@Header("Authorization") String token, @Body MenuItemCreateRequest menuItemUpdateRequest, @Path("id") Integer menuItemId);

    @Headers("Accept: application/json")
    @GET("api/category")
    Call<BaseListModel<MenuItemCategory>> getMenuItemCategories(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @PUT("api/delivery/profile/update")
    Call<DeliveryProfile> updateProfile(@Header("Authorization") String token, @Body DeliveryProfileUpdateRequest deliveryProfileUpdateRequest);

    @Headers("Accept: application/json")
    @GET("api/store")
    Call<DeliveryProfile> getProfile(@Header("Authorization") String token);

    @GET("https://maps.googleapis.com/maps/api/directions/json?units=metric")
    Call<GoogleMapsRoute> getRoute(@Query("key") String apiKey, @Query("origin") String commaSeparatedSourceLatLong, @Query("destination") String commaSeparatedDestLatLong);

    @Headers("Accept: application/json")
    @GET("api/settings")
    Call<ArrayList<SettingResponse>> getSettings();
}
