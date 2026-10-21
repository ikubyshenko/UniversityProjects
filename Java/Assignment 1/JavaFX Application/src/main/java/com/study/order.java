package com.study;

import java.util.ArrayList;

public class order {

    private int orderId;
    private customer customer;
    private ArrayList<product> products;
    private double totalAmount;

    // Constructor
    public order(int orderId, customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public order(int orderId, customer selectedCustomer,
                 ArrayList<product> products, double totalAmount) {
        this.orderId = orderId;
        this.customer = selectedCustomer;
        this.products = products == null
                ? new ArrayList<>()
                : new ArrayList<>(products);
        this.totalAmount = totalAmount;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public customer getCustomer() {
        return customer;
    }

    public ArrayList<product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Add product to order
    public void addProduct(product product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    // Print receipt
    public void printReceipt() {
        System.out.println("===== RECEIPT =====");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());

        System.out.println("-------------------");

        for (product product : products) {
            System.out.println(
                    product.getName() + " - $" + product.getPrice()
            );
        }

        System.out.println("-------------------");
        System.out.println("Total: $" + totalAmount);
        System.out.println("===================");
    }
}
