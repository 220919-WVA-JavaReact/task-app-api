package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserPostgres;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.exceptions.UserNotCreatedException;
import com.revature.util.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private UserDao ud = new UserPostgres();

    public User createUser(User u) throws UserNotCreatedException {
        // by default, created account will be basic Users
        u.setRole(Role.BASIC_USER);

        User createdUser = ud.insertUser(u);
        if(createdUser.getId() == -1) {
            throw new UserNotCreatedException();
        }
        return createdUser;
    }

    public User getUserById(int id) throws UserNotFoundException {
        User u = ud.getUserById(id);
        if (u == null) {
            throw new UserNotFoundException();
        }
        return u;
    }

    public List<User> getUsers() {
        List<User> users = ud.getUsers();
        return users;
    }
}
