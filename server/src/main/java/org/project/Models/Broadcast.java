package org.project.Models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "broadcasts")
public class Broadcast extends Message{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idBroadcast;

    @ManyToOne
    @JoinColumn(name = "id_server")
    private Server serverBroadcast;

    public Broadcast() {
        super();
    }

    public Broadcast(Long id, String message, User userOwner, Long idBroadcast, Server serverBroadcast) {
        super(id, message, userOwner);
        this.idBroadcast = idBroadcast;
        this.serverBroadcast = serverBroadcast;
    }

    public Long getIdBroadcast() {
        return idBroadcast;
    }

    public void setIdBroadcast(Long idBroadcast) {
        this.idBroadcast = idBroadcast;
    }

    public Server getServerBroadcast() {
        return serverBroadcast;
    }

    public void setServerBroadcast(Server serverBroadcast) {
        this.serverBroadcast = serverBroadcast;
    }
}
