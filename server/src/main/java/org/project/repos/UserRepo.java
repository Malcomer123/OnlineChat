package org.project.repos;

import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.project.Models.User;

import jakarta.persistence.EntityManager;

import java.util.List;

@Transactional
public class UserRepo{
    @PersistenceContext(unitName = "database")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("database").createEntityManager();
    // Create (Save) User
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    // Read (Find) User by ID
    public User findUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }

    // Read (Find) User by Username
    public User findUserByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
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
}
