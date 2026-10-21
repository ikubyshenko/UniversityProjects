package com.study;

public class clothing extends product {

    private String size;

    // Constructor
    public clothing(int id, String name, double price, int stockQuantity, String size) {
        super(id, name, price, stockQuantity);
        this.size = size;
    }

    // Getter
    public String getSize() {
        return size;
    }

    // Setter
    public void setSize(String size) {
        this.size = size;
    }

    // Display clothing information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Size: " + size);
    }
}