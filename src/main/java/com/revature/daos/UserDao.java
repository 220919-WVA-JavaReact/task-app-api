package com.revature.daos;

import com.revature.models.User;

import java.util.List;

public interface UserDao {
    User insertUser(User u);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getUsers();
}
