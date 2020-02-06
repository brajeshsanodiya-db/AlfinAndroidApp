package com.alfinapp.data.db.entity;

public class NotificationInfo {
    private String content;
    private String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NotificationInfo(String content, String time) {
        this.content = content;
        this.time = time;
    }
}
