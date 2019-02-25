package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by user on 2/5/2018.
 */

public class OrderItemDetail {

    private String mName="";
    private String mSubTotal="";
    private String mPrice="";
    private String mQuantity="";

    public OrderItemDetail(String mName, String mPrice, String mQuantity) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mQuantity = mQuantity;
    }

    public OrderItemDetail() {}

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSubTotal() {
        return mSubTotal;
    }

    public void setmSubTotal(String mSubTotal) {
        this.mSubTotal = mSubTotal;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }
}
