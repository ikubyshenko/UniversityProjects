package com.study;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static com.almasb.fxgl.dsl.FXGL.showMessage;

public class productsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button neworderButton;

    @FXML
    private Button ordersButton;

    @FXML
    private TableColumn<product, String> productdetailsColumn;

    @FXML
    private TextField productdetailsField;

    @FXML
    private TableColumn<product, String> productidColumn;

    @FXML
    private TextField productidField;

    @FXML
    private TableColumn<product, String> productnameColumn;

    @FXML
    private TextField productnameField;

    @FXML
    private TableColumn<product, String> productpriceColumn;

    @FXML
    private TextField productpriceField;

    @FXML
    private Button productsButton;

    @FXML
    private TableView<product> productsTable;

    @FXML
    private TableColumn<product, String> productstockColumn;

    @FXML
    private TextField productstockField;

    @FXML
    private TableColumn<product, String> producttypeColumn;

    @FXML
    private ComboBox<String> typeCombobox;

    @FXML
    private ComboBox<String> detailsCombobox;

    @FXML
    private Button usersButton;

    @FXML
    void actionMain(ActionEvent event) {
        try {
            Parent mainScreenRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene mainScene = new Scene(mainScreenRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(mainScene);
            currentStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Cannot load Main FXML file");
        }
    }

    @FXML
    void actionNeworder(ActionEvent event) {
        try {
            Parent neworderScreenRoot = FXMLLoader.load(getClass().getResource("neworder.fxml"));
            Scene neworderScene = new Scene(neworderScreenRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(neworderScene);
            currentStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Cannot load New order FXML file");
        }
    }

    @FXML
    void actionOrders(ActionEvent event) {
        try {
            Parent ordersScreenRoot = FXMLLoader.load(getClass().getResource("orders.fxml"));
            Scene ordersScene = new Scene(ordersScreenRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(ordersScene);
            currentStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Cannot load Order FXML file");
        }
    }

    @FXML
    void actionProducts(ActionEvent event) {

    }

    @FXML
    void actionUser(ActionEvent event) throws IOException {
        try {
            Parent userScreenRoot = FXMLLoader.load(getClass().getResource("users.fxml"));
            Scene userScene = new Scene(userScreenRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(userScene);
            currentStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Cannot load User FXML file");
        }
    }

    @FXML
    void addProduct(ActionEvent event) {

        try {

            String idText = productidField.getText().trim();
            String name = productnameField.getText().trim();
            String stockText = productstockField.getText().trim();
            String priceText = productpriceField.getText().trim()
                    .replace(',', '.');

            String type = typeCombobox.getValue();
            String details = detailsCombobox.getValue();

            // Check empty fields
            if (idText.isEmpty()
                    || name.isEmpty()
                    || stockText.isEmpty()
                    || priceText.isEmpty()
                    || type == null
                    || details == null
                    || details.isBlank()) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Validation error",
                        "Please fill in all fields."
                );
                return;
            }

            // Convert values
            int id = Integer.parseInt(idText);
            int stock = Integer.parseInt(stockText);
            double price = Double.parseDouble(priceText);

            // Validate values
            if (id <= 0) {
                showMessage(
                        Alert.AlertType.WARNING,
                        "Validation error",
                        "Product ID must be greater than zero."
                );
                return;
            }

            if (stock < 0) {
                showMessage(
                        Alert.AlertType.WARNING,
                        "Validation error",
                        "Stock cannot be negative."
                );
                return;
            }

            if (!Double.isFinite(price) || price < 0) {
                showMessage(
                        Alert.AlertType.WARNING,
                        "Validation error",
                        "Price must be a valid non-negative number."
                );
                return;
            }

            if (name.isBlank()) {
                showMessage(
                        Alert.AlertType.WARNING,
                        "Validation error",
                        "Product name cannot be empty."
                );
                return;
            }

            // Check duplicate ID
            for (product item : appData.products) {

                if (item.getId() == id) {

                    showMessage(
                            Alert.AlertType.WARNING,
                            "Duplicate ID",
                            "A product with this ID already exists."
                    );
                    return;
                }
            }

            // Create product
            switch (type) {

                case "Book":
                    appData.products.add(
                            new book(id, name, price, stock, details)
                    );
                    break;

                case "Electronics":
                    appData.products.add(
                            new electronic(id, name, price, stock, details)
                    );
                    break;

                case "Clothing":
                    appData.products.add(
                            new clothing(id, name, price, stock, details)
                    );
                    break;

                default:
                    showMessage(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Unknown product type."
                    );
                    return;
            }

            // Clear fields
            clearFields();

            showMessage(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Product added successfully."
            );

        } catch (NumberFormatException e) {

            showMessage(
                    Alert.AlertType.ERROR,
                    "Invalid input",
                    "ID and stock must be integers. Price must be a valid number."
            );
        }
    }




    @FXML
    void deleteProduct(ActionEvent event) {

        product selected = productsTable.getSelectionModel()
                .getSelectedItem();

        if (selected == null) {

            showMessage(
                    Alert.AlertType.WARNING,
                    "No selection",
                    "Please select a product in the table first."
            );
            return;
        }

        appData.products.remove(selected);

        showMessage(
                Alert.AlertType.INFORMATION,
                "Success",
                "Product deleted successfully."
        );
    }

    private String getProductType(product item) {

        if (item instanceof book) {
            return "Book";
        }

        if (item instanceof electronic) {
            return "Electronics";
        }

        if (item instanceof clothing) {
            return "Clothing";
        }

        return "Product";
    }

    private String getProductDetails(product item) {

        if (item instanceof book) {
            return ((book) item).getAuthor();
        }

        if (item instanceof electronic) {
            return ((electronic) item).getBrand();
        }

        if (item instanceof clothing) {
            return ((clothing) item).getSize();
        }

        return "-";
    }


    private void clearFields() {
        productidField.clear();
        productnameField.clear();
        productstockField.clear();
        productpriceField.clear();
        productdetailsField.clear();
        typeCombobox.getSelectionModel().selectFirst();
    }


    private void showMessage(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateDetailsOptions() {

        String type = typeCombobox.getValue();

        if (type == null) {
            detailsCombobox.getItems().clear();
            detailsCombobox.setPromptText("Select type first");
            return;
        }

        switch (type) {

            case "Book":
                detailsCombobox.setItems(
                        FXCollections.observableArrayList(
                                "John Smith",
                                "Robert Martin",
                                "Joshua Bloch",
                                "Other"
                        )
                );
                detailsCombobox.setPromptText("Select author");
                break;

            case "Electronics":
                detailsCombobox.setItems(
                        FXCollections.observableArrayList(
                                "Lenovo",
                                "HP",
                                "Apple",
                                "Samsung",
                                "Other"
                        )
                );
                detailsCombobox.setPromptText("Select brand");
                break;

            case "Clothing":
                detailsCombobox.setItems(
                        FXCollections.observableArrayList(
                                "XS",
                                "S",
                                "M",
                                "L",
                                "XL",
                                "XXL"
                        )
                );
                detailsCombobox.setPromptText("Select size");
                break;
        }

        detailsCombobox.getSelectionModel().clearSelection();
        detailsCombobox.setValue(null);
    }


    @FXML
    void initialize() {

        // Table columns
        productidColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getId())
                )
        );

        productnameColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        cell.getValue().getName()
                )
        );

        producttypeColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        getProductType(cell.getValue())
                )
        );

        productdetailsColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        getProductDetails(cell.getValue())
                )
        );

        productpriceColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.format("%.2f", cell.getValue().getPrice())
                )
        );

        productstockColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cell.getValue().getStockQuantity())
                )
        );

        // Product types
        typeCombobox.setItems(
                FXCollections.observableArrayList(
                        "Book",
                        "Electronics",
                        "Clothing"
                )
        );

        typeCombobox.getSelectionModel().selectFirst();

        // Update details when product type changes
        typeCombobox.setOnAction(event -> updateDetailsOptions());

        updateDetailsOptions();

        // Load products
        productsTable.setItems(appData.products);

        System.out.println(
                "Products loaded: " + appData.products.size()
        );
    }

}
