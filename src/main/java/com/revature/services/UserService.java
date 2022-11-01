package com.revature.services;

import com.revature.entities.Role;
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
        // Do not run this in a constructor, this is a temporary solution until we talk about SpringMVC
        this.ur = ur;

        User kev = new User();
        kev.setUsername("Kevin");
        kev.setPassword("pass");
        kev.setRole(Role.ADMIN);

        // persist Kevin to DB
        ur.save(kev);

        User bryan = new User();
        bryan.setUsername("Bryan");
        bryan.setPassword("pass");
        bryan.setRole(Role.BASIC_USER);
        bryan.setManager(kev);

        // persist Bryan to DB
        ur.save(bryan);


        // retrieve from DB
        System.out.println(ur.findUserByUsername("Kevin"));
        System.out.println((ur.findUserByUsername("Bryan")));

        List<User> users = ur.findAll();

        users.forEach(u -> System.out.println(u));
        // BAD PRACTICE, PLEASE DON'T DO IT, I BEG YOU
    }
}
