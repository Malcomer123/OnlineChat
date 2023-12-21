package org.project.Models;

public class Message {
    private Long id;
    private String message;
    private User userOwner;

    public Message() {
    }

    public Message(Long id, String message, User userOwner) {
        this.id = id;
        this.message = message;
        this.userOwner = userOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
