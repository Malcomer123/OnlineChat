package org.project.repos;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.project.Models.Message;

@Transactional
public class MessageRepo {
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();

    // Create (Save) Message
    public void saveMessage(Message message) {
        entityManager.persist(message);
    }
}
