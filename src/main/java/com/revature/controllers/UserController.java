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
    public UserController(UserService us){
        System.out.println("UserController created!");
        this.us = us;
    }

    /*-
        /users - Get

        retrieve all usersDTO

        /users?role=
     */


//    @RequestMapping(method= RequestMethod.GET, value="/users")
    @GetMapping
//    @ResponseBody
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(name="role", required = false) Role role){
        List<UserDTO> users = null;
        // meaning no request params (ie: no role), return all users
        if(role == null){
             users = us.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            // if the is a role, we want to send back users based on that role
            users = us.getAllUsersByRole(role);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

    }

    /*-
    Get User By ID?
    Get - /users/{id}
     */

    @GetMapping("/{id}")
//    @ResponseBody
    public ResponseEntity<UserDTO> getById(@PathVariable("id") String id){

            UserDTO userDTO = us.getUserById(id);
            // User is found, return userDTO, status 200
            return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping
//    @ResponseBody
    public ResponseEntity<UserDTO> createUser(@RequestBody CredentialsDTO creds){

        UserDTO userDTO = us.createUser(creds);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

}
