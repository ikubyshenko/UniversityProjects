package com.study;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalAmount;

    //constructor
    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    //getters
    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    //add products to order
    public void addProduct(Product product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    //Print Receipt in console
    public void printReceipt() {
        IO.println("===RECEIPT===");
        IO.println("Order ID: " + orderId);
        IO.println("Customer: " + customer.getName());
        IO.println("-------------");

        for (Product product : products)
            IO.println(product.getName() + " - $" + product.getPrice());

        IO.println("-------------");
        IO.println("Total: $" + totalAmount);
        IO.println("=============");
    }
}
