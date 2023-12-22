package com.projet.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UsersListController {
    @FXML
    private VBox usersContainer;

    public void initialize() {
        // Example servers
        for (int i = 0; i < 15; i++) {
            addUser("enset user 1","assets/icons8-search-64.png" );
        }


        addUser("enset user 2", "assets/icons8-account-50.png");
        // Add more servers as needed
    }

    private void addUser(String userName, String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userCell.fxml"));
            Node serverNode = loader.load();

            // Access the controller of the loaded FXML
            UserController userController = loader.getController();
            // Set server details
            userController.setUserDetails(userName, imageUrl);

            // Add the server component to the VBox
            usersContainer.getChildren().add(serverNode);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
            throw new RuntimeException(e);
        }
    }
}
