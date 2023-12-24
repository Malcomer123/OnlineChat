package org.project.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name="server")
public class Server {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idServer;
    private String name;
    private int port;
    private String status;
    @ManyToMany
    @JoinTable(
            name = "user_servers",
            joinColumns = @JoinColumn(name="id_server"),
            inverseJoinColumns = @JoinColumn(name="id_user")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "serverBroadcast", cascade = ALL, orphanRemoval = true)
    private List<Broadcast> broadcasts = new ArrayList<>();

    public Server() {
    }

    public Server(Long id, String name, int port, String status) {
        this.idServer = id;
        this.name = name;
        this.port = port;
        this.status = status;
    }

    public Long getIdServer() {
        return idServer;
    }

    public void setIdServer(Long id) {
        this.idServer = id;
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
