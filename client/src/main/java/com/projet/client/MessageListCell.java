package com.projet.client;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MessageListCell {
    private final ImageView imageView;
    private final Label textLabel;



    public MessageListCell(Image image, String messageText) {
          this.imageView = new ImageView(image);
        imageView.setFitHeight(30);  // Adjust the desired height
        imageView.setFitWidth(30);
        this.imageView.getStyleClass().add("message-image-view");
        this.textLabel = new Label(messageText);
        this.textLabel.getStyleClass().add("message-text");
        HBox container = new HBox(imageView, textLabel);
        container.getStyleClass().add("message-list-cell");
        }


    public ImageView getImageView() {
        return imageView;
    }

    public String getMessageText() {
        return textLabel.getText();
    }
}

