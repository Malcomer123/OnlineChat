package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegisterController  implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    private NetworkManager networkManager;
    private HashMap<String, String> data = new HashMap<>();





    @FXML
    private void register(ActionEvent event) throws IOException {
        System.out.println("username: " + username.getText());
        System.out.println("password: " + password.getText());
        System.out.println("email: " + email.getText());
        data.put("username", username.getText());
        data.put("password", password.getText());
        data.put("email", email.getText());
        data.put("type", "register");
        System.out.println("data: " + data);
        networkManager.sendObject(data);

     //  gotoChat(event);
    }


    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        Navigation navigation = new Navigation();
        navigation.navigate(event, "login.fxml");
    }
    @FXML
    private void gotoChat(ActionEvent event) throws IOException {

        Navigation navigation = new Navigation();
        navigation.navigate(event, "main_view.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init chatApp
        networkManager = NetworkManager.getInstance();
    }
}
