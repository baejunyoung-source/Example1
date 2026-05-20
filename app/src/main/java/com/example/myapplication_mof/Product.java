package com.example.myapplication_mof;

public class Product {
    private String title;
    private String author;
    private String publisher;
    private String price;
    private int priceValue; // 가격순 정렬용
    private int imageResId;

    public Product(String title, String author, String publisher, String price, int priceValue, int imageResId) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.priceValue = priceValue;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getPrice() { return price; }
    public int getPriceValue() { return priceValue; }
    public int getImageResId() { return imageResId; }
}
