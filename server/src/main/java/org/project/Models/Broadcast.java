package org.project.Models;

public class Broadcast extends Message{
    private User userSent;

    public Broadcast(User userSent) {
        this.userSent = userSent;
    }

    public Broadcast(Long id, String message, User userOwner, User userSent) {
        super(id, message, userOwner);
        this.userSent = userSent;
    }

    public User getUserSent() {
        return userSent;
    }

    public void setUserSent(User userSent) {
        this.userSent = userSent;
    }
}
