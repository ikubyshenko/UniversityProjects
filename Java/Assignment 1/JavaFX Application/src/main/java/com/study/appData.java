
package com.study;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class appData {
    public static ArrayList<order> orders = new ArrayList<>();

    public static ObservableList<user> users =
            FXCollections.observableArrayList();

    public static ObservableList<product> products =
            FXCollections.observableArrayList();


    private static boolean initialized = false;

    public static void loadSampleData() {

        if (initialized) {
            return;
        }

        initialized = true;

        users.add(new customer(
                "Ilya",
                "ilya@gmail.com",
                "12345"
        ));

        users.add(new employee(
                "John",
                "john@gmail.com",
                "admin123"
        ));
        products.add(new book(
                1,
                "Java Programming",
                25.99,
                10,
                "John Smith"
        ));

        products.add(new electronic(
                2,
                "Laptop",
                800.00,
                5,
                "Lenovo"
        ));

        products.add(new clothing(
                3,
                "T-Shirt",
                20.00,
                15,
                "M"
        ));
    }
}
