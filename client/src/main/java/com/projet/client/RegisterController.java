package com.projet.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegisterController  implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;
    @FXML
    private Label emailError;

    private NetworkManager networkManager;
    //private HashMap<String, String> data = new HashMap<>();

    @FXML
    private void register(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("username: " + username.getText());
        System.out.println("password: " + password.getText());
        System.out.println("email: " + email.getText());
        int temp =  checkFields();
        if(temp == 0){
            return;
        }
        HashMap<String, String> data = new HashMap<>();
        data.put("username", username.getText());
        data.put("password", password.getText());
        data.put("email", email.getText());
        data.put("type", "register");
        System.out.println("data: " + data);
        networkManager.sendObject(data);
        //check response
        String response = (String) networkManager.receiveObject();
        System.out.println("response: " + response);
        if(response.equals("true")){
            clearFields();
            gotoLogin(event);
        }else{
            usernameError.setText("username already exists");
        }

        //  gotoChat(event);
    }

    private void clearFields() {
        username.setText("");
        password.setText("");
        email.setText("");
    }

    private int checkFields(){
        //clear error labels
        usernameError.setText("");
        passwordError.setText("");
        emailError.setText("");
        //check if the username and password are empty
        if(email.getText().isEmpty()){
            emailError.setText("please enter your email");
            return 0;
        }
        if(username.getText().isEmpty()){
            usernameError.setText("please enter your username");
            return 0;
        }if(password.getText().isEmpty()){
            passwordError.setText("please enter your password");
            return 0;
        }
        // check if the email is valid using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if(!email.getText().matches(emailRegex)){
            emailError.setText("please enter a valid email");
            return 0;
        }
        if (password.getText().length() < 8) {
            passwordError.setText("password must be at least 8 characters");
            return 0;
        }
        if(username.getText().length() < 4){
            usernameError.setText("username must be at least 4 characters");
            return 0;
        }

        return 1;

    }


    @FXML
    private void gotoLogin(ActionEvent event) throws IOException {
        Navigation navigation = new Navigation();
        navigation.navigate(event, "login.fxml");
    }
    @FXML
    private void gotoChat(ActionEvent event) throws IOException {

        Navigation navigation = new Navigation();
        navigation.navigate(event, "main_view.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init chatApp
        networkManager = NetworkManager.getInstance();
    }
}
