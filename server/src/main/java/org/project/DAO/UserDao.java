package org.project.DAO;

import org.project.Models.User;

public interface UserDao extends Dao<User>{
    User getByEmail(String email);
    User getByUsername(String username);

}
