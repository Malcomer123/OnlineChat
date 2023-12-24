package org.project.repos;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.project.Models.Unicast;

@Transactional
public class UnicastRepo {
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();

    // Create (Save) Unicast MEssage
    public void saveUnicast(Unicast message) {
        entityManager.persist(message);
    }

    // Delete Unicast Message by ID
    public void deleteUnicastById(Long unicastId) {
        Unicast unicast = entityManager.find(Unicast.class, unicastId);
        if (unicast != null) {
            entityManager.remove(unicast);
        }
    }


}
