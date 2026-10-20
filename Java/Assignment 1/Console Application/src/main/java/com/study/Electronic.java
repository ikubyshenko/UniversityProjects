package com.study;

public class Electronic extends Product {

    private String brand;

    public Electronic(int id, String name, double price, int stockQuantity, String brand) {
        super(id, name, price, stockQuantity);
        this.brand = brand;
    }

    //getter
    public String getBrand() {
        return brand;
    }

    //setter
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Parent Polymorphism method in /Product
        IO.println("Electronic: " + brand);
    }
}
