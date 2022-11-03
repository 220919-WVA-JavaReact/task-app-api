package com.revature.services;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.RegisterException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = ur.findAll();
        List<UserDTO> usersDTO = users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return usersDTO;
    }

    public List<UserDTO> getAllUsersByRole(Role role) {
        List<User> users = ur.findUsersByRole(role);
        List<UserDTO> usersDTO = users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return usersDTO;
    }

    public UserDTO getUserById(String id) {
        User user = ur.findById(id).orElseThrow(() -> new UserNotFoundException());
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    public UserDTO createUser(CredentialsDTO creds) {

        if (ur.findUserByUsername(creds.getUsername()).isPresent()) {
            throw new RegisterException();
        }

        User newUser = new User();
        newUser.setUsername(creds.getUsername());
        newUser.setPassword(creds.getPassword());
        newUser.setRole(Role.BASIC_USER);
        newUser.setManager(null); // could define a default manager

        return new UserDTO(ur.save(newUser));
    }
}
