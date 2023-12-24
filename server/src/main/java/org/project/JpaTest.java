package org.project;

import org.project.Models.Server;
import org.project.Models.User;
import org.project.repos.UserRepo;

import java.util.Arrays;

public class JpaTest {
    public static void main(String[] args) {
        try {
            UserRepo userRepository = new UserRepo();

            // Create entities
            User user = new User();
            user.setUsername("john_doe");
            user.setEmail("john.doe@example.com");

            // Save entities (cascade operation will save servers as well)
            userRepository.saveUser(user);

            // Retrieve user and associated servers
            User retrievedUser = userRepository.findUserById(user.getIdUser());

            // Do something with retrieved entities
            System.out.println("Retrieved User: " + retrievedUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
