package com.revature.services;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.PrincipalDTO;
import com.revature.entities.User;
import com.revature.exceptions.LoginException;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository ur;

    @Autowired
    public AuthService(UserRepository ur) {
        this.ur = ur;
    }

    public PrincipalDTO authenticate(CredentialsDTO creds){
        User principal = ur.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword()).orElseThrow(() -> new LoginException());
        return new PrincipalDTO(principal);
    }
}
