package com.revature.controllers;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.exceptions.LoginException;
import com.revature.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService as;
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse res;

    @Autowired
    public AuthController(AuthService as, HttpServletRequest request){
        this.as = as;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO creds){
        User principal = null;
        try {
            principal = as.login(creds.getUsername(), creds.getPassword());

        } catch (UserNotFoundException | LoginException  e) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(new UserDTO(principal), HttpStatus.OK);
    }
}
