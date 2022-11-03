package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;
import lombok.Data;

import java.util.Objects;

@Data
public class UserDTO {

    private String id;
    private String username;
    private Role role;
    private String managerId;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        if(user.getManager() != null) {
            this.managerId = user.getManager().getId();
        }
    }
}
