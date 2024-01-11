package com.projet.client.utils;

public class User {
    private int idUser;
    private String username;
    private String imageUrl;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public User(int id, String username, String imageUrl) {

        this.idUser = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return idUser;
    }
}
