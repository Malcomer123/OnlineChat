package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;
    private NetworkManager networkManager;
    private HashMap<String, String> data = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init chatApp
        networkManager = NetworkManager.getInstance();

    }

    // login method
    @FXML
    private void login(ActionEvent event) throws Exception {
        System.out.println("LoginController.login");
        System.out.println("username: " + username.getText());
        System.out.println("password: " + password.getText());
        // send username and password to server as HashMap
        data.put("username", username.getText());
        data.put("password", password.getText());
        data.put("type", "login");
        System.out.println("data: " + data);
        networkManager.sendObject(data);
        //check if the response is true or false
        String response = (String) networkManager.receiveObject();
        System.out.println("response: " + response);
        if (response.equals("true")) {
            gotoChat(event);
        } else {
            System.out.println("login failed");
            // show error message
        }

    }

    // register method
    @FXML
    private void gotoRegister(ActionEvent event) throws IOException {

        Navigation navigation = new Navigation();
        navigation.navigate(event, "register.fxml");
    }
    @FXML
    private void gotoChat(ActionEvent event) throws IOException {
        System.out.println("LoginController.gotoChat");
        Navigation navigation = new Navigation();
        navigation.navigate(event ,"main_view.fxml");

    }





}
