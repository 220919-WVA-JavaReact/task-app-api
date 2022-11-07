package com.revature.services;

import com.revature.dtos.UserDTO;
import com.revature.entities.Role;
import com.revature.entities.User;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.repositories.UserRepository;
import com.revature.util.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private UserRepository ur;
    private JwtConfig jc;

    @Autowired
    public TokenService(UserRepository ur, JwtConfig jc) {
        this.ur = ur;
        this.jc = jc;
    }

    public String generateToken(UserDTO principal) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setId(principal.getId())
                .claim("username", principal.getUsername())
                .claim("role", principal.getRole())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jc.getExpiration()))
                .signWith(jc.getSigningKey(), jc.getSigAlg())
                .compact();
    }

    public UserDTO extractTokenDetails(String token) {

        if (token == null || token.isEmpty()) {
            throw new AuthenticationException();
        }


        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jc.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        User principal = ur.findById(claims.getId()).orElseThrow(() -> new AuthenticationException());

        UserDTO subject = new UserDTO();
        subject.setId(claims.getId());
        subject.setUsername(claims.get("username", String.class));
        subject.setRole(Role.valueOf(claims.get("role", String.class)));

        return subject;
    }
}
