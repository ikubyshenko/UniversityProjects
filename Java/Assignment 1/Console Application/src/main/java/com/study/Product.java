package com.study;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    //constructor
    public Product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    //DisplayInfo method (Polymorphism)
    public void displayInfo() {
        IO.println("ID: " + id);
        IO.println("Name: " + name);
        IO.println("Price: " + price);
        IO.println("Stock Quantity: " + stockQuantity);
    }
}
