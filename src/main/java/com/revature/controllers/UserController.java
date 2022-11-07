package com.revature.controllers;

import com.revature.annotations.Authenticated;
import com.revature.annotations.Secured;
import com.revature.dtos.AUserDTO;
import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService us;
    private TokenService ts;

    @Autowired
    public UserController(UserService us, TokenService ts) {
        this.us = us;
        this.ts = ts;
    }

    @Secured(rolesAllowed = "ADMIN")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<AUserDTO>> getUsers(@RequestParam(name = "role", required = false) Role role) {
        if (role == null) {
            return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(us.getAllUsersByRole(role), HttpStatus.OK);
        }
    }

    @Secured(rolesAllowed = {"ADMIN"})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AUserDTO> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody CredentialsDTO creds) {

        return new ResponseEntity<>(us.createUser(creds), HttpStatus.CREATED);
    }

    @Authenticated
    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> updateUserCredentials(@PathVariable String id, @RequestBody CredentialsDTO creds){
        UserDTO principal = us.updateUserCredentials(id, creds);

        // regenerate token in case username has been changed
        String token = ts.generateToken(principal);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return new ResponseEntity<>(principal, headers, HttpStatus.OK);
    }
}
