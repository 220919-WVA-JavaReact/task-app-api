package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
@Data
public class PrincipalDTO {

    private String id;
    private String username;
    private Role role;
    public PrincipalDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
