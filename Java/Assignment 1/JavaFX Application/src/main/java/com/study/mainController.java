package com.study;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink linkCommunitydocs;

    @FXML
    private Hyperlink linkHowusecss;

    @FXML
    private Hyperlink linkIntroduction;

    @FXML
    private Hyperlink linkOfficialdocs;

    @FXML
    private Button neworderButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button usersButton;

    @FXML
    void actionMain(ActionEvent event) {

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
    void openlinkCommunitydocs(ActionEvent event) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://fxdocs.github.io/docs/html5/"));
    }

    @FXML
    void openlinkHowusecss(ActionEvent event) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://openjfx.io/javadoc/27/javafx.graphics/javafx/scene/doc-files/cssref.html"));
    }

    @FXML
    void openlinkIntroduction(ActionEvent event) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://openjfx.io/javadoc/27/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html"));
    }

    @FXML
    void openlinkOfficialdocs(ActionEvent event) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://openjfx.io/javadoc/27/"));
    }

    @FXML
    void initialize() {

    }

}
