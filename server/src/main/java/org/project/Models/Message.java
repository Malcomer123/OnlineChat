package org.project.Models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "message")
@Inheritance(strategy = JOINED)
public class Message {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idMessage;
    private String message;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User userOwner;

    public Message() {
    }

    public Message(Long id, String message, User userOwner) {
        this.idMessage = id;
        this.message = message;
        this.userOwner = userOwner;
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
}
