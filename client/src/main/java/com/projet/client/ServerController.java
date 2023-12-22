package com.projet.client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerController {

    @FXML
    private Label serverNameLabel;

    @FXML
    private ImageView serverImageView;

    public void setServerDetails(String serverName, String imageUrl) {
        // Set server name
        serverNameLabel.setText(serverName);

        // Set server image
        try {
            String imageU = getClass().getResource(imageUrl).toExternalForm();
            Image image = new Image(imageU);
            serverImageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle image loading error (e.g., set a default image)
        }
    }
}