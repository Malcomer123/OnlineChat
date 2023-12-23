package com.projet.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatController {
    @FXML
    private VBox chatContainer;


    public void initialize() {
        for (int i = 0; i < 15; i++) {
            addMessage("leoo user 1","bellingoalll","assets/icons8-search-64.png" );
        }
    }
    private void addMessage(String senderName,String content,  String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("messageCell.fxml"));
            Node messageNode = loader.load();
            // Access the controller of the loaded FXML
            MessageController messageController = loader.getController();
            // Set server details
            messageController.setMessageDetails(senderName,content, imageUrl);
            // Add the server component to the VBox
            chatContainer.getChildren().add(messageNode);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
            throw new RuntimeException(e);
        }
    }

}
