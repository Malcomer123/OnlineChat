package org.project.repos;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.project.Models.User;

import java.util.List;

@Transactional
public class UserRepo {
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    // Create (Save) User
    public void saveUser(User user) {
        transaction.begin();
        entityManager.persist(user);
        /*String sql = "INSERT INTO users (username, password, email) VALUES (:value1, :value2, :value3)";

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("value1", user.getUsername())
                .setParameter("value2", user.getPassword())
                .setParameter("value3", user.getEmail());

        query.executeUpdate();*/
        transaction.commit();
    }

    // Read (Find) User by ID
    public User findUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }

    // Read (Find) User by Username
    public User findUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException E) {
            return null;
        }
    }

    public List<User> findUsersByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username LIKE :username", User.class)
                    .setParameter("username", username+"%")
                    .getResultList();
        } catch (NoResultException E) {
            return null;
        }
    }

    // Update User
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    // Delete User by ID
    public boolean deleteUserById(Long userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    // Retrieve all Users
    public List<User> getAllUsers() {
        // Using JPQL (Java Persistence Query Language) to select all users
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<Object[]> findUsersConnectedToServer(Long serverId) {
        String sql = "SELECT u.*, s.* " +
                "FROM users u " +
                "JOIN user_servers us ON u.iduser = us.id_user " +
                "JOIN server s ON s.idserver = us.id_server where idserver = " + serverId + ";";

        Query query = entityManager.createNativeQuery(sql);


        @SuppressWarnings("unchecked")
        List<Object[]> resultList = query.getResultList();
        return resultList;
    }
}
