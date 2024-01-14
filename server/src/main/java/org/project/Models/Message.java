package org.project.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "message")
@Inheritance(strategy = JOINED)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idMessage;
    private String message;
    @ManyToOne
    @JoinColumn(name = "idOwner", nullable = false)
    private User userOwner;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "idReceiver")
    private User userUnicast;

    public Message() {
    }

    public Message(Long id, String message, Date date, User userOwner) {
        this.idMessage = id;
        this.message = message;
        this.userOwner = userOwner;
        this.date = date;
    }

    public Message(String message, User userOwner, User userUnicast) {
        this.message = message;
        this.userOwner = userOwner;
        this.date = new Date();
        this.userUnicast = userUnicast;
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public User getUserUnicast() {
        return userUnicast;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return userOwner.getUsername() + "|" + message +
                "|" + date;
    }
}
