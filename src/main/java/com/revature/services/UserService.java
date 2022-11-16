package com.revature.services;

import com.revature.dtos.AUserDTO;
import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.ManagerAssignmentException;
import com.revature.exceptions.RegisterException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public List<AUserDTO> getAllUsers() {
        List<User> users = ur.findAll();
        List<AUserDTO> usersDTO = users.stream()
                .map(user -> new AUserDTO(user))
                .collect(Collectors.toList());
        return usersDTO;
    }

    public List<AUserDTO> getAllUsersByRole(Role role) {
        List<User> users = ur.findUsersByRole(role);
        List<AUserDTO> usersDTO = users.stream()
                .map(user -> new AUserDTO(user))
                .collect(Collectors.toList());
        return usersDTO;
    }

    public AUserDTO getUserById(String id) {
        User user = ur.findById(id).orElseThrow(() -> new UserNotFoundException());
        AUserDTO userDTO = new AUserDTO(user);
        return userDTO;
    }

    @Transactional
    public UserDTO createUser(CredentialsDTO creds) {
        if (creds.getUsername() == null || creds.getUsername().equals("") || creds.getPassword() == null || creds.getPassword().equals("")) {
            throw new RegisterException();
        }
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

    @Transactional
    public UserDTO updateUserCredentials(String id, CredentialsDTO creds) {

        User existingUser = ur.findById(id).orElseThrow(() -> new UserNotFoundException());

        if (creds.getUsername() != null && !creds.getUsername().equals("")) {
            existingUser.setUsername(creds.getUsername());
        }
        if (creds.getPassword() != null && !creds.getPassword().equals("")) {
            existingUser.setPassword(creds.getPassword());
        }

        return new UserDTO(ur.save(existingUser));
    }

    @Transactional
    public AUserDTO assignManager(String userId, String managerId){

        User user = ur.findById(userId).orElseThrow(() -> new UserNotFoundException());
        User manager = ur.findById(managerId).orElseThrow(() -> new UserNotFoundException());
        if(!manager.getRole().equals(Role.MANAGER) || user.getId().equals(manager.getId())){
            throw new ManagerAssignmentException();
        }

        user.setManager(manager);

        return new AUserDTO(ur.save(user));
    }
}
