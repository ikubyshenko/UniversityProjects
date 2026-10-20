package com.study;

public class Customer extends User {
    public Customer(String name, String email, String password) {
        super(name, email, password, "Customer");
    }

    public void placeOrder() {
        IO.println("Customer can place orders");
    }

    public void viewOrder() {
        IO.println("Customer can view orders");
    }
}
