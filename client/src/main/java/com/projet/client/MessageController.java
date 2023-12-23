package com.projet.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MessageController {
    @FXML
    private Label senderNameLabel;
    @FXML
    private Label contentLabel;

    @FXML
    private ImageView senderImageView;

    public void setMessageDetails(String senderName,String content, String imageUrl) {
        // Set server name
        senderNameLabel.setText(senderName);
        contentLabel.setText(content);

        // Set server image
        try {
            String imageU = getClass().getResource(imageUrl).toExternalForm();
            Image image = new Image(imageU);
            senderImageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle image loading error (e.g., set a default image)
        }
    }
}

