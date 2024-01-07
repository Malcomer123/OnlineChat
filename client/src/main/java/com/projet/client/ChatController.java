package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatController {
    @FXML
    private VBox chatContainer;


    public void initialize() {



        addMessage("leoo user 1","Le Lorem Ipsum est simp","19/12/2029:12.31","assets/icons8-search-64.png" );
        for (int i = 0; i < 5; i++) {
            addMessage("leoo user 1","Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker","19/12/2029:12.31","assets/icons8-search-64.png" );
        }
    }
    private void addMessage(String senderName,String content,String date,  String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("messageCell.fxml"));
            Node messageNode = loader.load();
            // Access the controller of the loaded FXML
            MessageController messageController = loader.getController();
            // Set server details
            messageController.setMessageDetails(senderName,content,date, imageUrl);
            // Add the server component to the VBox
            chatContainer.getChildren().add(messageNode);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
            throw new RuntimeException(e);
        }
    }


}
