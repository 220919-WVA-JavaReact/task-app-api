package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;
import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String username;
    private Role role;

    public UserDTO() {}
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
