package com.revature.controllers;

import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us){
        System.out.println("UserController created!");
        this.us = us;
    }
}
