package com.revature.controllers;

import com.revature.dtos.Credentials;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.exceptions.LoginException;
import com.revature.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService as;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody Credentials creds){
        UserDTO principalDTO = null; // UserDTO, has no password
        try {
            User principal = as.login(creds.getUsername(), creds.getPassword()); // retrieve user from db and checks credentials
            if(principal == null){
                return ResponseEntity.badRequest().build(); // return 400 status code
            } else {
                principalDTO = new UserDTO(principal); // if success convert to DTO
                return ResponseEntity.ok(principalDTO); // ok: Status code 200, body include the Principal userDTO
            }
        } catch (UserNotFoundException | LoginException e) {
            return ResponseEntity.badRequest().build(); // return 400 status code
        }
    }
}
