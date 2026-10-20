package com.study;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ArrayLists (Memory in RAM)
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        //Sample user
        Customer ilya = new Customer(
                "Ilya",
                "ikubyshenko@arizona.edu",
                "123"
        );
        Customer egor = new Customer(
                "Egor",
                "roxxa@gmail.com",
                "223"
        );

        users.add(ilya);
        users.add(egor);

        //Sample product
        Product java = new Book(
                1,
                "Java Learning",
                5.0,
                12,
                "Smart Man with glasses"
        );

        Product macbook = new Electronic(
                2,
                "MacBook Neo",
                600.0,
                8,
                "Apple"
        );

        products.add(java);
        products.add(macbook);

        int choice;
        do {
            IO.println("===== E-COMMERCE SYSTEM =====");
            IO.println("1. List Users");
            IO.println("2. List Products");
            IO.println("3. Add Product");
            IO.println("4. Add User");
            IO.println("5. Place Order");
            IO.println("6. View Orders");
            IO.println("7. Exit");
            IO.println("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                //Show all Users
                case 1:
                    IO.println("\n===== USERS =====");
                    for (User currentUser : users) {
                        currentUser.displayInfo();
                        IO.println("-------------------");
                    }
                    break;

                //Show all Products
                case 2:
                    IO.println("\n===== PRODUCTS =====");
                    for (Product currentProduct : products) {
                        currentProduct.displayInfo();
                        System.out.println("-------------------");
                    }
                    break;

                //Add new Product
                case 3:
                    IO.println("\n===== ADD PRODUCT =====");

                    IO.println("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    IO.println("Enter product name: ");
                    String name = scanner.nextLine();

                    IO.println("Enter price: ");
                    double price = scanner.nextDouble();

                    IO.println("Enter stock quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    IO.println("Choose product type:");
                    IO.println("1. Book");
                    IO.println("2. Electronics");
                    IO.println("3. Clothing");

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {
                        IO.println("Enter author: ");
                        String author = scanner.nextLine();
                        products.add(new Book(id, name, price, stock, author));

                    } else if (type == 2) {
                        IO.println("Enter brand: ");
                        String brand = scanner.nextLine();
                        products.add(new Electronic(id, name, price, stock, brand));

                    } else if (type == 3) {
                        IO.println("Enter size: ");
                        String size = scanner.nextLine();
                        products.add(new Clothing(id, name, price, size, stock));

                    } else {
                        IO.println("Invalid product type:");
                        break;
                    }

                    IO.println("Product added successfully!");
                    break;

                //Add new User
                case 4:
                    IO.println("\n===== ADD USER =====");

                    IO.println("Enter name: ");
                    String userName = scanner.nextLine();

                    IO.println("Enter email: ");
                    String email = scanner.nextLine();

                    IO.println("Enter password: ");
                    String password = scanner.nextLine();

                    IO.println("Choose role:");
                    IO.println("1. Customer");
                    IO.println("2. Employee");

                    int role = scanner.nextInt();
                    scanner.nextLine();

                    if (role == 1) {
                        users.add(new Customer(userName, email, password));
                    } else if (role == 2) {
                        users.add(new Employee(userName, email, password));
                    } else {
                        IO.println("Invalid role.");
                        break;
                    }
                    IO.println("User added successfully!");
                    break;

                case 5:
                    IO.println("\n===== PLACE ORDER =====");

                    IO.println("Enter customer email: ");
                    String customerEmail = scanner.nextLine();

                    Customer selectedCustomer = null;

                    // Find customer
                    for (User currentUser : users) {

                        if (currentUser instanceof Customer &&
                                currentUser.getEmail().equals(customerEmail)) {

                            selectedCustomer = (Customer) currentUser;
                            break;
                        }
                    }

                    if (selectedCustomer == null) {
                        IO.println("Customer not found.");
                        break;
                    }

                    // Create new order
                    Order newOrder = new Order(
                            orders.size() + 1,
                            selectedCustomer
                    );

                    int productId;

                    do {
                        IO.println("\n===== AVAILABLE PRODUCTS =====");

                        for (Product currentProduct : products) {
                            currentProduct.displayInfo();
                            IO.println("-------------------");
                        }

                        IO.println("Enter 0 to finish the order.");
                        IO.println("Enter product ID: ");

                        productId = scanner.nextInt();
                        scanner.nextLine();

                        if (productId == 0) {
                            break;
                        }

                        Product selectedProduct = null;

                        // Find product
                        for (Product currentProduct : products) {

                            if (currentProduct.getId() == productId) {
                                selectedProduct = currentProduct;
                                break;
                            }
                        }

                        if (selectedProduct == null) {
                            IO.println("Product not found.");
                            continue;
                        }

                        // Check stock
                        if (selectedProduct.getStockQuantity() <= 0) {
                            IO.println("Product is out of stock.");
                            continue;
                        }

                        // Add product to order
                        newOrder.addProduct(selectedProduct);

                        // Reduce stock
                        selectedProduct.setStockQuantity(
                                selectedProduct.getStockQuantity() - 1
                        );

                        IO.println(
                                selectedProduct.getName()
                                        + " added to the order."
                        );

                    } while (true);

                    // Check order
                    if (newOrder.getProducts().isEmpty()) {

                        IO.println("Order was not created.");

                    } else {

                        orders.add(newOrder);

                        IO.println("Order placed successfully!");
                        newOrder.printReceipt();
                    }

                    break;

                case 6:
                        IO.println("\n===== ORDERS =====");

                    if (orders.isEmpty()) {

                        IO.println("No orders found.");

                    } else {

                        for (Order currentOrder : orders) {
                            currentOrder.printReceipt();
                            IO.println();
                        }
                    }

                    break;

                case 7:
                    IO.println("Goodbye!");
                    break;

                default:
                    IO.println("Invalid option.");
            }

        } while (choice != 7);
        scanner.close();

    }
}
