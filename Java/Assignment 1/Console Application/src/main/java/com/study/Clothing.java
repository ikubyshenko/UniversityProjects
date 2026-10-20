package com.study;

public class Clothing extends Product {

    private String size;

    public Clothing(int id, String name, double price, String size, int stockQuantity) {
        super(id, name, price, stockQuantity);
        this.size = size;
    }

    //getter
    public String getSize() {
        return size;
    }

    //setter
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();// Parent Polymorphism method in /Product
        IO.println("Size: " + size);
    }
}
