package com.hababk.delivery.network.request;

/**
 * Created by a_man on 12-03-2018.
 */

public class LoginRequest {
    private String email, password, role = "delivery";

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
