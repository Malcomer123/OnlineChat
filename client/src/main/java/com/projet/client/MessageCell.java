package com.projet.client;

import com.projet.client.utils.Message;
import com.projet.client.utils.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class MessageCell extends ListCell<Message> {

    @FXML
    private HBox messageBox;

    @FXML
    private ImageView senderImageView;

    @FXML
    private Label senderNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Text contentText;

    // handle userClick

    public MessageCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("messageCell.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setGraphic(null);
        } else {
            String imageU = getClass().getResource("assets/icons8-account-50.png").toExternalForm();
            Image image = new Image(imageU);
            senderImageView.setImage(image); // Set the user's image
            senderNameLabel.setText(message.getSender());
            dateLabel.setText(message.getDate());
            contentText.setText(message.getMessage());
            setGraphic(messageBox);
        }
    }

}
