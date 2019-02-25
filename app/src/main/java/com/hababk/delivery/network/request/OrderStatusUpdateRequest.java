package com.hababk.delivery.network.request;

public class OrderStatusUpdateRequest {
    private String delivery_status;

    public OrderStatusUpdateRequest(String delivery_status) {
        this.delivery_status = delivery_status;
    }
}
