package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserPostgres;
import com.revature.models.User;
import com.revature.util.exceptions.LoginException;
import com.revature.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao ud;

    /*-
     * if the user is found by username and the password matches, returns that user
     */
    public User login(String username, String password) throws UserNotFoundException, LoginException {

        // principal refers to "currently logged in user"
        User principal = ud.getUserByUsername(username);

        System.out.println(principal);

        if(principal == null) {
            throw new UserNotFoundException();
        }

        if(!principal.getPassword().equals(password)){
            throw new LoginException();
        }

        return principal;
    }
}
