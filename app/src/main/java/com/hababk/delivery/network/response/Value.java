package com.hababk.delivery.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private String value;

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
