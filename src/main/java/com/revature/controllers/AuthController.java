package com.revature.controllers;


import com.revature.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService as;

    @Autowired
    public AuthController(AuthService as){
        this.as = as;
    }


}
