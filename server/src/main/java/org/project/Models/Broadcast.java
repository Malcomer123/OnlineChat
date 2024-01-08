package org.project.Models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "broadcasts")
public class Broadcast extends Message{

    @ManyToOne
    @JoinColumn(name = "id_server")
    private Server serverBroadcast;

    public Broadcast() {
        super();
    }

    public Broadcast(Long id, String message, User userOwner,Server serverBroadcast) {
        this.serverBroadcast = serverBroadcast;
    }

    public Server getServerBroadcast() {
        return serverBroadcast;
    }

    public void setServerBroadcast(Server serverBroadcast) {
        this.serverBroadcast = serverBroadcast;
    }
}
