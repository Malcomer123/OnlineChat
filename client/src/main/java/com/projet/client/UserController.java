package com.projet.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class UserController implements Initializable {
    @FXML
    private Label userNameLabel;

    @FXML
    private HBox userBox;

    @FXML
    private ImageView userImageView;

    private final HashMap<String, String> data = new HashMap<>();
    String userID;


    NetworkManager networkManager;

    public void setUserDetails(String id , String userName, String imageUrl) {
        System.out.println(id + " idd ");
        // Set server name
        userNameLabel.setText(userName);
        this.userID = id;

        // Set server image
        try {
            String imageU = getClass().getResource(imageUrl).toExternalForm();
            Image image = new Image(imageU);
            userImageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle image loading error (e.g., set a default image)
        }
    }

    @FXML

    public void showMessages(MouseEvent event) {
        try {
            System.out.println("ddddd");
            Preferences pref = Preferences.userRoot();
            data.put("type", "getMessages");
            String id = userID.trim();
            data.put("userID", id);
            networkManager.sendObject(data);

            Object receivedObject = networkManager.receiveObject();

            if (receivedObject instanceof String) {
                String response = (String) receivedObject;
                System.out.println("messsages : " + response);
                pref.put("messages", response);
            } else if (receivedObject instanceof ArrayList) {
                // Handle the received ArrayList appropriately
                ArrayList<?> receivedList = (ArrayList<?>) receivedObject;
                System.out.println("receivedList: " + receivedList);

                // Process the list elements
            } else {
                // Handle other types if needed
            }

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init chatApp
        networkManager = NetworkManager.getInstance();
        userBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Handle the click event
                showMessages(event);
            }
        });

    }
}