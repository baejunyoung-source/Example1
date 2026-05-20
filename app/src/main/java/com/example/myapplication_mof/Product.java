package com.example.myapplication_mof;

public class Product {
    private String title;
    private String seller;
    private String price;
    private int imageResId;

    public Product(String title, String seller, String price, int imageResId) {
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getSeller() { return seller; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
