package org.project.repos;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.project.Models.Server;

import java.util.List;

@Transactional
public class ServerRepo{
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();

    // Create (Save) Server
    public void saveServer(Server server) {
        entityManager.persist(server);
    }

    // Read (Find) Server by ID
    public Server findServerById(Long serverId) {
        return entityManager.find(Server.class, serverId);
    }

    // Read (Find) Server by Port
    public Server findServerByPort(int port) {
        return entityManager.createQuery("SELECT s FROM Server s WHERE s.port = :port", Server.class)
                .setParameter("port", port)
                .getSingleResult();
    }

    // Update Server
    public void updateServer(Server server) {
        entityManager.merge(server);
    }

    // Delete Server by ID
    public void deleteServerById(Long serverId) {
        Server server = entityManager.find(Server.class, serverId);
        if (server != null) {
            entityManager.remove(server);
        }
    }

    // Retrieve all Servers
    public List<Server> getAllServers() {
        // Using JPQL (Java Persistence Query Language) to select all servers
        return entityManager.createQuery("SELECT s FROM Server s", Server.class).getResultList();
    }

}
