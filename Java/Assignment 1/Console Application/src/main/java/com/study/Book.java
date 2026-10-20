package com.study;

public class Book extends Product {

    private String author;

    public Book(int id, String name, double price, int stockQuantity, String author) {
        super(id, name, price, stockQuantity);
        this.author = author;
    }

    //getter
    public String getAuthor() {
        return author;
    }

    //setter
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();// Parent Polymorphism method in /Product
        IO.println("Author: " + author);
    }
}
