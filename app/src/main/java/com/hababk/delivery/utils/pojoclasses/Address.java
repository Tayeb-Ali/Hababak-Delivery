package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by a_man on 20-02-2018.
 */

public class Address {
    private String title, address;
    private boolean selected;

    public Address(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public boolean isSelected() {
        return selected;
    }
}
