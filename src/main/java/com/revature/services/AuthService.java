package com.revature.services;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.User;
import com.revature.exceptions.LoginException;
import com.revature.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository ur;

    @Autowired
    public AuthService(UserRepository ur) {
        this.ur = ur;
    }

    public UserDTO authenticate(CredentialsDTO creds){
        User principal = ur.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword()).orElseThrow(() -> new LoginException());
        return new UserDTO(principal);
    }
}
