package com.study;

public class Employee extends User {
    public Employee(String name, String email, String password) {
        super(name, email, password, "Employee");
    }

    public void addProduct() {
        IO.println("Employee can add products");
    }

    public void updateProduct() {
        IO.println("Employee can update products");
    }

    public void deleteProduct() {
        IO.println("Employee can delete products");
    }
}
