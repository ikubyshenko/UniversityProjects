package com.study;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<user> users = new ArrayList<>();
        ArrayList<product> products = new ArrayList<>();
        ArrayList<order> orders = new ArrayList<>();

        // Sample users
        customer customer1 = new customer(
                "Ilya",
                "ilya@gmail.com",
                "12345"
        );

        employee employee1 = new employee(
                "John",
                "john@gmail.com",
                "admin123"
        );

        users.add(customer1);
        users.add(employee1);

        // Sample products
        book book1 = new book(
                1,
                "Java Programming",
                25.99,
                10,
                "John Smith"
        );

        electronic laptop = new electronic(
                2,
                "Laptop",
                800.00,
                5,
                "Lenovo"
        );

        clothing shirt = new clothing(
                3,
                "T-Shirt",
                20.00,
                15,
                "M"
        );

        products.add(book1);
        products.add(laptop);
        products.add(shirt);

        int choice;

        do {
            System.out.println();
            System.out.println("===== E-COMMERCE SYSTEM =====");
            System.out.println("1. List Users");
            System.out.println("2. List Products");
            System.out.println("3. Add Product");
            System.out.println("4. Add User");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n===== USERS =====");

                    for (user currentUser : users) {
                        currentUser.displayInfo();
                        System.out.println("-------------------");
                    }

                    break;

                case 2:
                    System.out.println("\n===== PRODUCTS =====");

                    for (product currentProduct : products) {
                        currentProduct.displayInfo();
                        System.out.println("-------------------");
                    }

                    break;

                case 3:
                    System.out.println("\n===== ADD PRODUCT =====");

                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter stock quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Choose product type:");
                    System.out.println("1. Book");
                    System.out.println("2. Electronics");
                    System.out.println("3. Clothing");

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();

                        products.add(
                                new book(id, name, price, stock, author)
                        );

                    } else if (type == 2) {

                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();

                        products.add(
                                new electronic(id, name, price, stock, brand)
                        );

                    } else if (type == 3) {

                        System.out.print("Enter size: ");
                        String size = scanner.nextLine();

                        products.add(
                                new clothing(id, name, price, stock, size)
                        );

                    } else {
                        System.out.println("Invalid product type.");
                        break;
                    }

                    System.out.println("Product added successfully!");

                    break;

                case 4:
                    System.out.println("\n===== ADD USER =====");

                    System.out.print("Enter name: ");
                    String userName = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    System.out.println("Choose role:");
                    System.out.println("1. Customer");
                    System.out.println("2. Employee");

                    int role = scanner.nextInt();
                    scanner.nextLine();

                    if (role == 1) {

                        users.add(
                                new customer(userName, email, password)
                        );

                    } else if (role == 2) {

                        users.add(
                                new employee(userName, email, password)
                        );

                    } else {
                        System.out.println("Invalid role.");
                        break;
                    }

                    System.out.println("User added successfully!");

                    break;

                case 5:
                    System.out.println("\n===== PLACE ORDER =====");

                    System.out.print("Enter customer email: ");
                    String customerEmail = scanner.nextLine();

                    customer selectedCustomer = null;

                    // Find customer
                    for (user currentUser : users) {

                        if (currentUser instanceof customer &&
                                currentUser.getEmail().equals(customerEmail)) {

                            selectedCustomer = (customer) currentUser;
                            break;
                        }
                    }

                    if (selectedCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    // Create new order
                    order newOrder = new order(
                            orders.size() + 1,
                            selectedCustomer
                    );

                    int productId;

                    do {
                        System.out.println("\n===== AVAILABLE PRODUCTS =====");

                        for (product currentProduct : products) {
                            currentProduct.displayInfo();
                            System.out.println("-------------------");
                        }

                        System.out.println("Enter 0 to finish the order.");
                        System.out.print("Enter product ID: ");

                        productId = scanner.nextInt();
                        scanner.nextLine();

                        if (productId == 0) {
                            break;
                        }

                        product selectedProduct = null;

                        // Find product
                        for (product currentProduct : products) {

                            if (currentProduct.getId() == productId) {
                                selectedProduct = currentProduct;
                                break;
                            }
                        }

                        if (selectedProduct == null) {
                            System.out.println("Product not found.");
                            continue;
                        }

                        // Check stock
                        if (selectedProduct.getStockQuantity() <= 0) {
                            System.out.println("Product is out of stock.");
                            continue;
                        }

                        // Add product to order
                        newOrder.addProduct(selectedProduct);

                        // Reduce stock
                        selectedProduct.setStockQuantity(
                                selectedProduct.getStockQuantity() - 1
                        );

                        System.out.println(
                                selectedProduct.getName()
                                        + " added to the order."
                        );

                    } while (true);

                    // Check order
                    if (newOrder.getProducts().isEmpty()) {

                        System.out.println("Order was not created.");

                    } else {

                        orders.add(newOrder);

                        System.out.println("Order placed successfully!");
                        newOrder.printReceipt();
                    }

                    break;

                case 6:
                    System.out.println("\n===== ORDERS =====");

                    if (orders.isEmpty()) {

                        System.out.println("No orders found.");

                    } else {

                        for (order currentOrder : orders) {
                            currentOrder.printReceipt();
                            System.out.println();
                        }
                    }

                    break;

                case 7:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 7);

        scanner.close();
    }
}