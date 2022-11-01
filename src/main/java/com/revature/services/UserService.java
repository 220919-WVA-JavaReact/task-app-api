package com.revature.services;

import com.revature.entities.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Scope("Singleton") - implicit
public class UserService {
    private UserRepository ur;

    @Autowired
    public UserService(UserRepository ur){
        System.out.println("UserService Created!");
        this.ur = ur;

        List<User> users = ur.findAll();
        User user = ur.findByUsername("Kevin");
        ur.s
    }
}
