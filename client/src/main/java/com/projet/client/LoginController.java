package com.projet.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;


    @FXML
    private void Initialize() {
        System.out.println("LoginController.Initialize");

    }

    // login method
    @FXML
    private void login() {
        System.out.println("LoginController.login");
        System.out.println("username: " + username.getText());
        System.out.println("password: " + password.getText());

    }



}
