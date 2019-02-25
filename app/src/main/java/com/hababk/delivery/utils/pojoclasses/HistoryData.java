package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by a_man on 20-02-2018.
 */

public class HistoryData {
    private String title, date;
    private int orderNumber, price, profit;

    public HistoryData(String title, String date, int orderNumber, int price, int profit) {
        this.title = title;
        this.date = date;
        this.orderNumber = orderNumber;
        this.price = price;
        this.profit = profit;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getProfit() {
        return profit;
    }
}
