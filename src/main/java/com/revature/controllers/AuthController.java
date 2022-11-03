package com.revature.controllers;

import com.revature.dtos.CredentialsDTO;
import com.revature.dtos.PrincipalDTO;
import com.revature.services.AuthService;
import com.revature.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController // assume that every mapping has the @ResponseBody annotation
@RequestMapping("/auth")
public class AuthController {

    private AuthService as;
    private TokenService ts;

    @Autowired
    public AuthController(AuthService as, TokenService ts){
        this.as = as;
        this.ts = ts;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PrincipalDTO> authenticate(@RequestBody CredentialsDTO creds){
        PrincipalDTO principal = as.authenticate(creds);

        // if principal is authenticated, generate token
        String token = ts.generateToken(principal);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return new ResponseEntity<>(principal, headers, HttpStatus.OK);
    }
}
