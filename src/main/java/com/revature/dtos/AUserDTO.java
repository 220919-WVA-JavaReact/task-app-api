package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;
import lombok.Data;

@Data
public class AUserDTO {

    private String id;
    private String username;
    private Role role;
    private String managerUsername;
    private String managerId;

    public AUserDTO(){}
    public AUserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        if(user.getManager() != null) {
            this.managerId = user.getManager().getId();
            this.managerUsername = user.getManager().getUsername();
        }
    }
}
