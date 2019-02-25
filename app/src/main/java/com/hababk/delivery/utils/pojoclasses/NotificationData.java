package com.hababk.delivery.utils.pojoclasses;

/**
 * Created by a_man on 19-02-2018.
 */

public class NotificationData {
    private String title, description, time;
    private boolean read;

    public NotificationData(String title, String description, String time, boolean read) {
        this.title = title;
        this.description = description;
        this.read = read;
        this.time = time;
    }

    public boolean isRead() {
        return read;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}
