package com.projet.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserController {
    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView userImageView;

    public void setUserDetails(String userName, String imageUrl) {
        // Set server name
        userNameLabel.setText(userName);

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
}