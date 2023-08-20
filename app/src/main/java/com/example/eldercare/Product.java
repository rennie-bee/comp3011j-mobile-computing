package com.example.eldercare;

// The product class symbolizes the attributes of Product items
// Each product item should own a name and an image
// Actually, each product owns an explanation but that is not mentioned here
public class Product {
    private String name;
    private int imageID;

    public Product(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }
}
