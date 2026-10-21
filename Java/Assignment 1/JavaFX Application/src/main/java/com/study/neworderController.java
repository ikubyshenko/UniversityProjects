package com.study;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import static com.almasb.fxgl.dsl.FXGL.showMessage;

public class neworderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<product> cartField;

    @FXML
    private Button neworderButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button payButton;


    @FXML
    private TableColumn<product, Integer> productidColumn;

    @FXML
    private TableColumn<product, String> productnameColumn;

    @FXML
    private TableColumn<product, String> productpriceColumn;

    @FXML
    private Button productsButton;

    @FXML
    private TableView<product> productsTable;

    @FXML
    private TableColumn<product, String> productstockColumn;

    @FXML
    private ComboBox<customer> selectcustomerCombobox;

    private final ObservableList<product> cart = FXCollections.observableArrayList();
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
        try {
            Parent productsScreenRoot = FXMLLoader.load(getClass().getResource("products.fxml"));
            Scene productsScene = new Scene(productsScreenRoot);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(productsScene);
            currentStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Cannot load Products FXML file");
        }
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
    void addtocartOrder(ActionEvent event) {
        product selectedProduct =
                productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showMessage(Alert.AlertType.WARNING,
                    "Product not selected",
                    "Select a product from the table.");
            return;
        }

        if (selectedProduct.getStockQuantity() <= 0) {
            showMessage(Alert.AlertType.WARNING,
                    "Out of stock",
                    "This product is not available.");
            return;
        }

        if (cart.contains(selectedProduct)) {
            showMessage(Alert.AlertType.WARNING,
                    "Already in cart",
                    "This product is already in the cart.");
            return;
        }

        cart.add(selectedProduct);
    }


    @FXML
    void deletefromcartOrder(ActionEvent event) {
        product selectedProduct =
                cartField.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showMessage(Alert.AlertType.WARNING,
                    "Product not selected",
                    "Select a product in the cart first.");
            return;
        }

        cart.remove(selectedProduct);
    }

    @FXML
    void actionPay(ActionEvent event) {
        customer selectedCustomer =
                selectcustomerCombobox.getValue();

        if (selectedCustomer == null) {
            showMessage(Alert.AlertType.WARNING,
                    "Customer not selected",
                    "Select a customer first.");
            return;
        }

        if (cart.isEmpty()) {
            showMessage(Alert.AlertType.WARNING,
                    "Empty cart",
                    "Add products to the cart first.");
            return;
        }

        // Проверяем остатки перед оформлением заказа
        for (product currentProduct : cart) {
            if (currentProduct.getStockQuantity() <= 0) {
                showMessage(Alert.AlertType.WARNING,
                        "Out of stock",
                        currentProduct.getName()
                                + " is no longer available.");
                return;
            }
        }

        // Считаем стоимость заказа
        double totalAmount = 0;

        for (product currentProduct : cart) {
            totalAmount += currentProduct.getPrice();
        }

        int orderId = appData.orders.size() + 1;

        // Конструктор должен совпадать с твоим классом order
        order newOrder = new order(
                orderId,
                selectedCustomer,
                new ArrayList<>(cart),
                totalAmount
        );

        // Уменьшаем остатки на складе
        for (product currentProduct : cart) {
            currentProduct.setStockQuantity(
                    currentProduct.getStockQuantity() - 1);
        }

        // Сохраняем заказ в общем списке
        appData.orders.add(newOrder);

        productsTable.refresh();
        cart.clear();

        showMessage(Alert.AlertType.INFORMATION,
                "Payment successful",
                "Order #" + orderId
                        + " created successfully.\nTotal: "
                        + String.format("%.2f", totalAmount));
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

    @FXML
    void initialize() {
        // Настройка колонок таблицы товаров
        productidColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        productnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        productpriceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        productstockColumn.setCellValueFactory(
                new PropertyValueFactory<>("stockQuantity"));

        // Загружаем товары
        productsTable.setItems(appData.products);

        // Заполняем список покупателей
        ObservableList<customer> customers =
                FXCollections.observableArrayList();

        for (user currentUser : appData.users) {
            if (currentUser instanceof customer) {
                customers.add((customer) currentUser);
            }
        }

        selectcustomerCombobox.setItems(customers);

        // Показываем имя покупателя
        selectcustomerCombobox.setConverter(
                new StringConverter<customer>() {
                    @Override
                    public String toString(customer currentCustomer) {
                        return currentCustomer == null
                                ? ""
                                : currentCustomer.getName();
                    }

                    @Override
                    public customer fromString(String value) {
                        return null;
                    }
                });

        selectcustomerCombobox.setCellFactory(list ->
                new ListCell<customer>() {
                    @Override
                    protected void updateItem(
                            customer currentCustomer, boolean empty) {
                        super.updateItem(currentCustomer, empty);

                        if (empty || currentCustomer == null) {
                            setText(null);
                        } else {
                            setText(currentCustomer.getName());
                        }
                    }
                });

        // Настройка корзины
        cartField.setItems(cart);

        cartField.setCellFactory(list ->
                new ListCell<product>() {
                    @Override
                    protected void updateItem(
                            product currentProduct, boolean empty) {
                        super.updateItem(currentProduct, empty);

                        if (empty || currentProduct == null) {
                            setText(null);
                        } else {
                            setText(currentProduct.getName()
                                    + " - "
                                    + String.format("%.2f",
                                    currentProduct.getPrice()));
                        }
                    }
                });
    }
}
