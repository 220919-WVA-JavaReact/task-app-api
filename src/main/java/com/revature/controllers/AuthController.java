package com.revature.controllers;


import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.UserDTO;
import com.revature.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService as;
    private HttpServletRequest req;
    private HttpServletResponse res;

    @Autowired
    public AuthController(AuthService as, HttpServletRequest req, HttpServletResponse res){
        this.as = as;
        this.req = req;
        this.res = res;
    }


    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO creds){

        UserDTO principal = as.login(creds);
        HttpSession session = req.getSession();
//        session.setAttribute("id", principal.getId());
//        session.setAttribute("role", principal.getRole());
        // To make Chrome work with our cookie
//        String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
//        res.setHeader("Set-Cookie", cookie);;

        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
