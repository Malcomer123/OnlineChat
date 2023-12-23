package com.projet.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;

public class ServerListController {
    @FXML
    private VBox serverContainer;


    public void initialize() {
        for (int i = 0; i < 15; i++) {
            addServer("enset user 1","assets/icons8-search-64.png" );
        }
        // Example servers
        addServer("Server 1","assets/icons8-account-50.png" );
        addServer("Server 2", "assets/icons8-account-50.png");
        // Add more servers as needed
    }

    private void addServer(String serverName, String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("serverCell.fxml"));
            Node serverNode = loader.load();

            // Access the controller of the loaded FXML
            ServerController serverController = loader.getController();
            // Set server details
            serverController.setServerDetails(serverName, imageUrl);

            // Add the server component to the VBox
            serverContainer.getChildren().add(serverNode);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
            throw new RuntimeException(e);
        }
    }
}
