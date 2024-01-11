package com.projet.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import java.io.*;
import java.net.Socket;

public class ChatApp extends Application implements Initializable {
    private Stage stage;
    RegisterController registerController;
    LoginController loginController;

    private NetworkManager networkManager;




    //init controllers and set the chatApp


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.networkManager = NetworkManager.getInstance();
        Preferences pref = Preferences.userRoot();


        networkManager.connectToServer("localhost", 9090);
        //setupSocketCommunication();
        laodScene("login.fxml");
    }


    private void laodScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApp.class.getResource(path));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("WhatsApp ?!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init chatApp
        networkManager = NetworkManager.getInstance();

    }




}



