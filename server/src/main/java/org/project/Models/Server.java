package org.project.Models;

import java.util.List;

public class Server {
    private Long id;
    private String name;
    private int port;
    private String status;
    private List<UserServer> users;

    public Server() {
    }

    public Server(Long id, String name, int port, String status) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
