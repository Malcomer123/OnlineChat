package com.projet.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    @FXML
    private Label senderNameLabel;
    @FXML
    private Text contentText;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView senderImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void setMessageDetails(String senderName,String content,String messageDate, String imageUrl) {
        // Set server name
        senderNameLabel.setText(senderName);
        contentText.setText(content);
        dateLabel.setText(messageDate);

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

