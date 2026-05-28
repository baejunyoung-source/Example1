package com.example.myapplication_mof;

public class SearchResultItem {
    private String title;
    private String location;
    private String price;
    private int chatCount;
    private int heartCount;
    private int imageResId;

    public SearchResultItem(String title, String location, String price, int chatCount, int heartCount, int imageResId) {
        this.title = title;
        this.location = location;
        this.price = price;
        this.chatCount = chatCount;
        this.heartCount = heartCount;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getPrice() { return price; }
    public int getChatCount() { return chatCount; }
    public int getHeartCount() { return heartCount; }
    public int getImageResId() { return imageResId; }
}
