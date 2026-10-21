package com.study;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static com.almasb.fxgl.dsl.FXGL.showMessage;


public class usersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<user, String> emailColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<user, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private Button neworderButton;

    @FXML
    private Button ordersButton;

    @FXML
    private TableColumn<user, String> passwordColumn;

    @FXML
    private TextField passwordField;

    @FXML
    private Button productsButton;

    @FXML
    private RadioButton radiobuttonCustomer;

    @FXML
    private RadioButton radiobuttonEmployee;

    @FXML
    private TableColumn<user, String> roleColumn;

    @FXML
    private ToggleGroup selectRole;

    @FXML
    private Button usersButton;

    @FXML
    private TableView<user> usersTable;

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
    void actionUser(ActionEvent event) {

    }

    @FXML
    private void addUser() {
        System.out.println("Add user clicked");

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showMessage(Alert.AlertType.WARNING,
                    "Validation", "Fill in all fields.");
            return;
        }

        for (user existingUser : appData.users) {
            if (existingUser.getEmail().equalsIgnoreCase(email)) {
                showMessage(Alert.AlertType.WARNING,
                        "Validation", "This email already exists.");
                return;
            }
        }

        user newUser;

        if (radiobuttonEmployee.isSelected()) {
            newUser = new employee(name, email, password);
        } else {
            newUser = new customer(name, email, password);
        }

        appData.users.add(newUser);

        usersTable.refresh();

        nameField.clear();
        emailField.clear();
        passwordField.clear();

        radiobuttonCustomer.setSelected(true);

        showMessage(Alert.AlertType.INFORMATION,
                "Success", "User added successfully.");
    }


    private void showMessage(Alert.AlertType alertType, String validation, String s) {
    }


    @FXML
    private void deleteUser() {
        System.out.println("Delete user clicked");

        user selectedUser = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            showMessage(Alert.AlertType.WARNING,
                    "Selection", "Select a user in the table first.");
            return;
        }

        appData.users.remove(selectedUser);

        usersTable.refresh();

        showMessage(Alert.AlertType.INFORMATION,
                "Success", "User deleted successfully.");
    }

    @FXML
    void initialize() {

        // Load sample data before displaying the table
        appData.loadSampleData();

        // Connect columns to user fields
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        passwordColumn.setCellValueFactory(
                new PropertyValueFactory<>("password")
        );

        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );

        // Create a group for the radio buttons
        selectRole = new ToggleGroup();

        radiobuttonCustomer.setToggleGroup(selectRole);
        radiobuttonEmployee.setToggleGroup(selectRole);

        radiobuttonCustomer.setSelected(true);

        // Connect the table to the shared list
        usersTable.setItems(appData.users);

        System.out.println(
                "Users loaded: " + appData.users.size()
        );
    }
}
