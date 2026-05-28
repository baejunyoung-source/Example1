package com.example.myapplication_mof;

public class ChatItem {
    private String name;
    private String message;
    private String time;
    private int imageResId;

    public ChatItem(String name, String message, String time, int imageResId) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getMessage() { return message; }
    public String getTime() { return time; }
    public int getImageResId() { return imageResId; }
}
