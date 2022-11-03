package com.revature.controllers;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(name = "role", required = false) Role role) {
        if (role == null) {
            return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(us.getAllUsersByRole(role), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody CredentialsDTO creds) {
        return new ResponseEntity<>(us.createUser(creds), HttpStatus.CREATED);
    }

}
