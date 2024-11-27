package com.example.sad;

public class FoodItem {
    private String name;
    private String price;
    private int imageResId; // Drawable resource ID for the image

    public FoodItem(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}