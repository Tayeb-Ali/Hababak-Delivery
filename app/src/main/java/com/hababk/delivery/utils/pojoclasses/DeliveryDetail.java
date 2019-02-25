package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by user on 1/30/2018.
 */

public class DeliveryDetail {
    private String mPersonName = "";
    private String mOrderNumber = "";
    private String addressFrom, addressTo;
    private String mDispatchDate = "";
    private boolean accepted;

    public DeliveryDetail(String mPersonName, String addressFrom, String addressTo, String mOrderNumber, String mDispatchDate, boolean accepted) {
        this.mPersonName = mPersonName;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.mOrderNumber = mOrderNumber;
        this.mDispatchDate = mDispatchDate;
        this.accepted = accepted;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public String getmOrderNumber() {
        return mOrderNumber;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public String getmDispatchDate() {
        return mDispatchDate;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
