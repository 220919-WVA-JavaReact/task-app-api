package com.revature.controllers;

import com.revature.dtos.UserDTO;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private HttpServletRequest req;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){

            List<User> users = us.getUsers();

            List<UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

            return ResponseEntity.ok(usersDTO);
    }
}
