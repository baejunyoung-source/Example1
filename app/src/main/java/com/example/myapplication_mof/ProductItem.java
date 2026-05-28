package com.example.myapplication_mof;

public class ProductItem {
    private int imageResId;
    private boolean isSoldOut;
    private String title;
    private String author;
    private String publisher;
    private String price;

    public ProductItem(int imageResId, boolean isSoldOut, String title, String author, String publisher, String price) {
        this.imageResId = imageResId;
        this.isSoldOut = isSoldOut;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getImageResId() { return imageResId; }
    public boolean isSoldOut() { return isSoldOut; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public String getPrice() { return price; }
}
