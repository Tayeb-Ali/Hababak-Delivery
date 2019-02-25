package com.hababk.delivery.network.request;

/**
 * Created by a_man on 27-03-2018.
 */

public class DeliveryProfileUpdateRequest {
    private Double longitude, latitude;
    private boolean is_online;

    public DeliveryProfileUpdateRequest(Double latitude, Double longitude, boolean is_online) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.is_online = is_online;
    }

    public DeliveryProfileUpdateRequest(boolean is_online) {
        this.is_online = is_online;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public boolean isIs_online() {
        return is_online;
    }
}
