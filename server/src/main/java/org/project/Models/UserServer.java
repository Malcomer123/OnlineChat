package org.project.Models;

public class UserServer {
    private User user;
    private Server server;

    public UserServer() {
    }

    public UserServer(User user, Server server) {
        this.user = user;
        this.server = server;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
