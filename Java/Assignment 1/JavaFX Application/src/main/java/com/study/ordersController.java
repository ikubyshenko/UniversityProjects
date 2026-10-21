package com.study;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ordersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button neworderButton;

    @FXML
    private TableColumn<order, String> ordercustomerColumn;

    @FXML
    private TableColumn<order, Integer> orderidColumn;

    @FXML
    private TableColumn<order, String> orderitemsColumn;

    @FXML
    private Button ordersButton;

    @FXML
    private TableView<order> ordersTable;

    @FXML
    private TableColumn<order, Double> ordertotalColumn;

    @FXML
    private Button productsButton;

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
    void initialize() {

        // ID заказа
        orderidColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getOrderId()
                )
        );

        // Имя покупателя
        ordercustomerColumn.setCellValueFactory(data -> {
            order currentOrder = data.getValue();

            if (currentOrder.getCustomer() == null) {
                return new SimpleStringProperty("Unknown customer");
            }

            return new SimpleStringProperty(
                    currentOrder.getCustomer().getName()
            );
        });

        // Названия товаров в заказе
        orderitemsColumn.setCellValueFactory(data -> {
            order currentOrder = data.getValue();

            if (currentOrder.getProducts() == null ||
                    currentOrder.getProducts().isEmpty()) {
                return new SimpleStringProperty("No products");
            }

            return new SimpleStringProperty(
                    currentOrder.getProducts()
                            .stream()
                            .filter(java.util.Objects::nonNull)
                            .map(product::getName)
                            .collect(Collectors.joining(", "))
            );
        });

        // Общая стоимость
        ordertotalColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getTotalAmount()
                )
        );

        // Загружаем заказы в таблицу
        ordersTable.setItems(
                FXCollections.observableArrayList(appData.orders)
        );
    }
}

