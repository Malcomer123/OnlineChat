package com.projet.client;

import com.projet.client.utils.Message;
import com.projet.client.utils.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.crypto.MacSpi;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.prefs.Preferences;

public class mainController implements Initializable {

    @FXML
    private VBox usersContainer;
    private HashMap<String, String> data = new HashMap<>();
    private HashMap<String, String> messagesMap = new HashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();



    @FXML
    private TextField search;

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<User> usersList;
    @FXML
    private ListView<Message> messagesList;
    @FXML
    private TextField messageInput;
    @FXML
    private ImageView logoutButton;
    NetworkManager networkManager;

    @FXML
    private Label activeUser;

    private int userID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUsers();
        initMessages();
        refreshMessages();
    }

    // refresh messages every 5 seconds
    public void refreshMessages() {
        scheduler.scheduleAtFixedRate(() -> {
            //   System.out.println("refreshing messages");
            refreshMessages(usersList.getSelectionModel().getSelectedItem().getId());
        }, 0, 2, java.util.concurrent.TimeUnit.SECONDS);
    }
    @FXML
    private void logout(Event event) {
        try {
            //  System.out.println("logout");
            networkManager = NetworkManager.getInstance();
            Preferences pref = Preferences.userRoot();
            HashMap<String, String> data = new HashMap<>();
            // change the active user
            // System.out.println("active user: " + pref.get("activeUser", null));
            pref.put("activeUser", "");
            HashMap<String, String> sendData = new HashMap<>();
            sendData.put("type", "logout");
            networkManager.sendObject(sendData);
            Object receivedObject = networkManager.receiveObject();
            //  System.out.println("logout : " + receivedObject);
            Navigation navigation = new Navigation();
            navigation.navigate(event, "login.fxml");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUsers(){
        try {
            networkManager = NetworkManager.getInstance();
            Preferences pref = Preferences.userRoot();
            data.put("type", "getUsers");

            networkManager.sendObject(data);
            String response = null;
            response = (String) networkManager.receiveObject();
            String[] usersString = response.split(",");
            String id  = (usersString[0].split("\\|")[0]);
            if (id.startsWith("[")) {
                id = id.substring(1);
            }
            userID = Integer.parseInt(id.trim());


            List<User> users = new ArrayList<>();

            for (String user : usersString) {
                // System.out.println(user.toString());
                id = user.split("\\|")[0];
                if (id.startsWith("[")) {
                    id = id.substring(1);
                }
                users.add(new User(Integer.parseInt(id.trim()), user.split("\\|")[1], "assets/icons8-account-50.png"));

            }
            // set the first user as the default user
            pref.put("activeUser", usersString[0]);
            activeUser.setText(usersString[0].split("\\|")[1]);


            ObservableList<User> items = FXCollections.observableArrayList(users);
            usersList.setItems(items);
            usersList.setCellFactory(param -> new UserCell());

            //set the first user as the default user
            usersList.getSelectionModel().select(0);
            refreshMessages(usersList.getSelectionModel().getSelectedItem().getId());


            // Add event handler for user selection change
            usersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    // Handle user selection change, e.g., refresh messages based on the selected user
                    refreshMessages(newValue.getId());
                    pref.put("activeUser", newValue.toString());
                    //  System.out.println(newValue.getUsername());
                    activeUser.setText(newValue.getUsername());
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshMessages(int id) {
        try {
            //   System.out.println("refreshing .. ");
            userID = id;
            networkManager = NetworkManager.getInstance();
            Preferences pref = Preferences.userRoot();
            HashMap<String, String> messagesMap = new HashMap<>();
            messagesMap.put("type", "getMessages");
            messagesMap.put("targetId", String.valueOf(id));
            networkManager.sendObject(messagesMap);

            String response = (String) networkManager.receiveObject() ;
            //check if no messages
            if (response.equals("[]")) {
                //    System.out.println("no messages");
                //empty messagesList
                ObservableList<Message> items = FXCollections.observableArrayList(new ArrayList<>());
                Platform.runLater(() -> {
                    messagesList.setItems(items);
                    messagesList.setCellFactory(param -> new MessageCell());
                });
                return;
            }
            String[] messagesString = response.split(",");
            List<Message> messages = new ArrayList<Message>();

            for (String message : messagesString) {
                String[] messageParams  = message.split("\\|");
                //   System.out.println(message.toString());

                messages.add(new Message(messageParams[1], messageParams[0], messageParams[2]));
                // set the first user as the default user
            }
            //  ObservableList<Message> items = FXCollections.observableArrayList(messages);
            // messagesList.setItems(items);
            //  messagesList.setCellFactory(param -> new MessageCell());

            ObservableList<Message> items = FXCollections.observableArrayList(messages);
            Platform.runLater(() -> {
                messagesList.setItems(items);
                messagesList.setCellFactory(param -> new MessageCell());
            });

            //     String receivedObject = (String) networkManager.receiveObject();

            //    System.out.println("messages received after clicking user : " +
            id +" =  "  + response);
            // Update your messagesList based on the receivedObject

        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initMessages() {
        try {
            //get active user from getItems
            // User activeUser = usersList.getSelectionModel().getSelectedItem();
//System.out.println("active user: " + activeUser);
            //   userID = activeUser.getId();
            networkManager = NetworkManager.getInstance();
            Preferences pref = Preferences.userRoot();
            // change the active user
            pref.get("activeUser", null);
            data.put("type", "getMessages");
            String id = userID + "";
            data.put("targetId", id);
            networkManager.sendObject(data);

            Object receivedObject = networkManager.receiveObject();


        } catch (IOException  | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }



    }


    @FXML
    private void sendMessage() {
        try {
            if (messageInput.getText().isEmpty()) {
                return;
            }
            networkManager = NetworkManager.getInstance();
            Preferences pref = Preferences.userRoot();
            // change the active user
            HashMap<String, String> sendData = new HashMap<>();
            pref.get("activeUser", null);
            sendData.put("type", "sendMessage");
            String id = userID + "";
            sendData.put("targetId", id);
            sendData.put("content", messageInput.getText());
            networkManager.sendObject(sendData);

            Object receivedObject = networkManager.receiveObject();
            // System.out.println("message sent : " + receivedObject);
            messageInput.setText("");
            // Update your messagesList based on the receivedObject
            refreshMessages(usersList.getSelectionModel().getSelectedItem().getId());

        } catch (IOException  | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }


}