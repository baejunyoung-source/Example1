package com.example.myapplication_mof;

public class AlarmItem {
    private int imageResId;
    private String title;
    private String date;

    public AlarmItem(int imageResId, String title, String date) {
        this.imageResId = imageResId;
        this.title = title;
        this.date = date;
    }

    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
}
