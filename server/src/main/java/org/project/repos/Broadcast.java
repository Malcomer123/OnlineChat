package org.project.repos;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Transactional
public class Broadcast {
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();

    // Create (Save) Broadcast Message
    public void saveBroadcast(Broadcast message) {
        entityManager.persist(message);
    }

    // Delete Broadcast Message by ID
    public void deleteBroadcastById(Long broadcastId) {
        Broadcast broadcast = entityManager.find(Broadcast.class, broadcastId);
        if (broadcast != null) {
            entityManager.remove(broadcast);
        }
    }

}
