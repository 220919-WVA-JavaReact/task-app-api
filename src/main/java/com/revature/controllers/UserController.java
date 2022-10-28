package com.revature.controllers;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = us.getUsers();
        List<UserDTO> userDTOs = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        User u = null;
        try {
            u = us.getUserById(id);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); // return 400 status code
        }
        return ResponseEntity.ok(new UserDTO(u));
    }
}
