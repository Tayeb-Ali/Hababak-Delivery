package com.hababk.delivery.network.request;

public class LocationUpdateRequest {
    public Double lat, lang;

    public LocationUpdateRequest(Double lat, Double lang) {
        this.lat = lat;
        this.lang = lang;
    }

    public LocationUpdateRequest() {
    }
}