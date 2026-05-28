package com.example.myapplication_mof;

import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private String price;
    private int priceValue;
    private int imageResId;
    private String description;

    public Product(String title, String author, String publisher, String price, int priceValue, int imageResId) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.priceValue = priceValue;
        this.imageResId = imageResId;
        this.description = "";
    }

    public Product(String title, String author, String publisher, String price, int priceValue, int imageResId, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.priceValue = priceValue;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getPrice() { return price; }
    public int getPriceValue() { return priceValue; }
    public int getImageResId() { return imageResId; }
    public String getDescription() { return description; }
}
