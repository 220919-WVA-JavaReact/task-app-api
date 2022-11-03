package com.revature.services;

import com.revature.dtos.PrincipalDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private UserRepository ur;

    @Autowired
    public TokenService(UserRepository ur) {
        this.ur = ur;
    }

    public String generateToken(PrincipalDTO principal){

        // NOT GOOD TOKEN, EXAMPLE'S SAKE FOR TOKEN VALIDATION

        return principal.getId()+":"+ principal.getRole();
    }

    public PrincipalDTO extractTokenDetails(String token){

        if(token == null){
            throw new AuthenticationException();
        }

        String[] tokens = token.split(":");
        String id = tokens[0];
        Role role = Role.valueOf(tokens[1]);

        User principal = ur.findById(id).orElseThrow(() -> new AuthenticationException());

        // validation behavior make sure that principal has the right role, otherwise throw another exception
        if(!principal.getRole().equals(role)){
            new AuthenticationException();
        }

        return new PrincipalDTO(principal);
    }
}
