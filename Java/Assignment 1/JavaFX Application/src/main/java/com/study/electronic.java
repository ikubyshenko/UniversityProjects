package com.study;

public class electronic extends product {

    private String brand;

    // Constructor
    public electronic(int id, String name, double price, int stockQuantity, String brand) {
        super(id, name, price, stockQuantity);
        this.brand = brand;
    }

    // Getter
    public String getBrand() {
        return brand;
    }

    // Setter
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Display electronics information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Brand: " + brand);
    }
}