package com.projet.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import com.projet.client.utils.User;

public class UserCell extends ListCell<User> {

    @FXML
    private HBox userBox;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label userNameLabel;

    // handle userClick

    public UserCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userCell.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setGraphic(null);
        } else {
            String imageU = getClass().getResource("assets/icons8-account-50.png").toExternalForm();
            Image image = new Image(imageU);
            userImageView.setImage(image); // Set the user's image
            userNameLabel.setText(user.getUsername());
            setGraphic(userBox);
        }
    }

    @FXML
    private void userClick(MouseEvent event) {
        System.out.println("user clicked");
    }
}