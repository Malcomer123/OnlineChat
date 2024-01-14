package org.project.repos;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.project.Models.Message;
import org.project.Models.User;

import java.util.List;

@Transactional
public class MessageRepo {
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();


    // Create (Save) Message
    public void saveMessage(Message message) {
        transaction.begin();
        entityManager.persist(message);
        transaction.commit();
    }

    public List<Message> getUnicastMessages(Long idOwner, Long idReceiver) {
        idReceiver++; idOwner++;
        try {
            return entityManager.createQuery("SELECT m FROM Message m WHERE m.userOwner.idUser = :idOwner AND m.userUnicast.idUser = :idReceiver", Message.class)
                    .setParameter("idOwner", idOwner)
                    .setParameter("idReceiver", idReceiver)
                    .getResultList();
            /*return entityManager.createQuery("SELECT m FROM Message m WHERE m.userUnicast.id = :userId", Message.class)
                    .setParameter("userId", userId)
                    .getResultList();*/
        } catch (NoResultException E) {
            return null;
        }
    }

    public List<Message> getAllMessages() {
        // Using JPQL (Java Persistence Query Language) to select all users
        return entityManager.createQuery("SELECT u FROM Message u", Message.class).getResultList();
    }
}
