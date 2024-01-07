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
        try {
        networkManager = NetworkManager.getInstance();
        data.put("type", "getUsers");
        networkManager.sendObject(data);


        String response = null;
            response = (String) networkManager.receiveObject();
            String[] users = response.split(",");
            for (String user : users) {
                System.out.println("user: " + user);
                String id = user.split("\\|")[0];
                if (id.startsWith("[")) {
                    id = id.substring(1);
                }
                addUser(  id, user.split("\\|")[1], "assets/icons8-account-50.png");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        // Add more servers as needed
    }
    /*
    public void addUsers(List<String> userList) {
        for (String user : userList) {
            String[] userInfo = user.split("\\|");
            // Assuming the name is the second field in your toString method
            String name = userInfo.length >= 2 ? userInfo[1] : "Unknown";
            String userID = userInfo.length >= 1 ? userInfo[0] : "Unknown";
            addUser(userID,name, "assets/icons8-account-50.png");
        }

        //set the first user as the default user
        Preferences pref = Preferences.userRoot();
        pref.put("userID",userList.get(0).split("\\|")[0]);
    }

*/

    private void addUser(String userID, String userName, String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userCell.fxml"));


            Node serverNode = loader.load();
            UserController userController = loader.getController();
            userController.setUserDetails(userID, userName, imageUrl);

            // Access the controller of the loaded FXML

            // Set user details

            // Add the user component to the VBox
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
