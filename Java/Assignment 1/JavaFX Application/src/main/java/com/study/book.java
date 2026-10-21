package com.study;

public class book extends product {

    private String author;

    // Constructor
    public book(int id, String name, double price, int stockQuantity, String author) {
        super(id, name, price, stockQuantity);
        this.author = author;
    }

    // Getter
    public String getAuthor() {
        return author;
    }

    // Setter
    public void setAuthor(String author) {
        this.author = author;
    }

    // Display book information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Author: " + author);
    }
}
