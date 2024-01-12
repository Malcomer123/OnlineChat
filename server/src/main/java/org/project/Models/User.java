package org.project.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idUser;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "userOwner")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "userUnicast", cascade = ALL, orphanRemoval = true)
    private List<Message> receivedMessages = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Server> servers = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String password, String email) {
        this.idUser = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }

    @Override
    public String toString() {
        return idUser +
                "|" + username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }
}
