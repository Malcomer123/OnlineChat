package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;
public class UsersListController implements Initializable {
    @FXML
    private VBox usersContainer;
    private final HashMap<String, String> data = new HashMap<>();

   @FXML
    private TextField search;

    NetworkManager networkManager ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        /*
         networkManager = NetworkManager.getInstance();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            data.clear();
            data.put("type", "search");
            StringBuffer aaa = new StringBuffer("");

            aaa.append(newValue);

            data.put("filter", String.valueOf(aaa));
            System.out.println("data: " + data.get("filter"));
            // send newValue to server
            try {
                networkManager.sendObject(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
*/


        addUser("enset user 2", "assets/icons8-account-50.png");
        // Add more servers as needed
    }
    public void addUsers(List<String> userList) {
        for (String user : userList) {
            String[] userInfo = user.split("\\|");
            // Assuming the name is the second field in your toString method
            String name = userInfo.length >= 2 ? userInfo[1] : "Unknown";
            addUser(name, "assets/icons8-account-50.png");
        }
    }



    private void addUser(String userName, String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userCell.fxml"));
            Node serverNode = loader.load();

            // Access the controller of the loaded FXML
            UserController userController = loader.getController();
            // Set server details
            userController.setUserDetails(userName, imageUrl);

            // Add the server component to the VBox
            usersContainer.getChildren().add(serverNode);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle error loading FXML
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void filter(ActionEvent event){
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            data.put("type", "search");
            data.put("search", newValue);
            // send newValue to server
            try {
                networkManager.sendObject(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



}
