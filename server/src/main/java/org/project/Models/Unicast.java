package org.project.Models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "unicasts")
public class Unicast extends Message{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long idUnicast;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userUnicast;

    public Unicast() {
        super();
    }

    public Unicast(Long id, String message, User userOwner, Long idUnicast) {
        super(id, message, userOwner);
        this.idUnicast = idUnicast;
    }

    public Long getIdUnicast() {
        return idUnicast;
    }

    public void setIdUnicast(Long idUnicast) {
        this.idUnicast = idUnicast;
    }
}
