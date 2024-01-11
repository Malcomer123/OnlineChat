package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ChatController {
    @FXML
    private VBox chatContainer;

    Preferences pref = Preferences.userRoot();
    public void updateChat() throws IOException {

        if (chatContainer == null) {
            System.out.println("chatContainer is null. FXML injection might not have occurred yet.");
            return;
        }

        String activeUser = pref.get("activeUser", null);
        System.out.println("active user: " + activeUser);
        //get active user messages
        try {
            String messagesString = pref.get("messages", null);
            System.out.println("string received = "+  messagesString);
            String[] messages = messagesString.split(",");
          //  for (String message : messages) {
            String message = messages[0];

                System.out.println("message: " + message);
                String[] messageDetails = message.split("\\|");
                //print the array
                for(String s : messageDetails) {
                    System.out.println( " *" + s);
                }
                if(messageDetails.length == 4)
                addMessage(messageDetails[0], messageDetails[1], messageDetails[2], "assets/icons8-search-64.png");
                else {
                    System.out.println("message details length = " + messageDetails.length);
                }


        } catch (Exception e) {
            System.out.println("no messages");
            e.printStackTrace();
        }

    }


    public void initialize() {
        System.out.println(chatContainer);
        try {
            updateChat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
      /*
        addMessage("leoo user 1","Le Lorem Ipsum est simp","19/12/2029:12.31","assets/icons8-search-64.png" );
       */

        /*
        for (int i = 0; i < 5; i++) {
            addMessage("leoo user 1","Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker","19/12/2029:12.31","assets/icons8-search-64.png" );
        }
        */
    }

    private void addMessage(String senderName, String content, String date, String imageUrl) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("messageCell.fxml"));
            Node messageNode = loader.load();
            MessageController messageController = loader.getController();
            messageController.setMessageDetails(senderName, content, date, imageUrl);
            chatContainer.getChildren().add(messageNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
