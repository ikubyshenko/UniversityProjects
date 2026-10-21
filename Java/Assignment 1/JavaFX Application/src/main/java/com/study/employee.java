package com.study;

public class employee extends user {

    // Constructor
    public employee(String name, String email, String password) {
        super(name, email, password, "Employee");
    }

    // Employee-specific methods
    public void addProduct() {
        System.out.println("Employee can add products.");
    }

    public void removeProduct() {
        System.out.println("Employee can remove products.");
    }

    public void updateProduct() {
        System.out.println("Employee can update products.");
    }
}