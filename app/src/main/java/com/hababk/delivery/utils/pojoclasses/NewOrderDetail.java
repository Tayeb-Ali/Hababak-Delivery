package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by user on 2/1/2018.
 */

public class NewOrderDetail {

    private String mPersonName="";
    private String mPrice="";
    private String mOrderNumber="";
    private String mOrderDate="";
    private String mPaymentMethod="";
    private int mOrderStatusFlag=0;
    private String mAddress="";
    private OrderItemDetail orderItemDetail;

    public NewOrderDetail(String mPersonName, String mPrice, String mOrderNumber, String mOrderDate, String mPaymentMethod, int mOrderStatusFlag) {
        this.mPersonName = mPersonName;
        this.mPrice = mPrice;
        this.mOrderNumber = mOrderNumber;
        this.mOrderDate = mOrderDate;
        this.mPaymentMethod = mPaymentMethod;
        this.mOrderStatusFlag = mOrderStatusFlag;
        orderItemDetail=new OrderItemDetail();
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public OrderItemDetail getOrderItemDetail() {
        return orderItemDetail;
    }

    public void setOrderItemDetail(OrderItemDetail orderItemDetail) {
        this.orderItemDetail = orderItemDetail;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmOrderNumber() {
        return mOrderNumber;
    }

    public void setmOrderNumber(String mOrderNumber) {
        this.mOrderNumber = mOrderNumber;
    }

    public String getmOrderDate() {
        return mOrderDate;
    }

    public void setmOrderDate(String mOrderDate) {
        this.mOrderDate = mOrderDate;
    }

    public String getmPaymentMethod() {
        return mPaymentMethod;
    }

    public void setmPaymentMethod(String mPaymentMethod) {
        this.mPaymentMethod = mPaymentMethod;
    }

    public int getmOrderStatusFlag() {
        return mOrderStatusFlag;
    }

    public void setmOrderStatusFlag(int mOrderStatusFlag) {
        this.mOrderStatusFlag = mOrderStatusFlag;
    }
}
