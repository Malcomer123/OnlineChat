package org.project.Models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "unicasts")
public class Unicast extends Message{
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userUnicast;

    public Unicast() {
        super();
    }

    public Unicast(Long id, String message, User userOwner) {
        super(id, message, userOwner);
    }
}
