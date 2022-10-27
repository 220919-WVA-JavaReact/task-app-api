package com.revature.dtos;

import com.revature.models.Role;
import com.revature.models.User;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String username;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(User u){
        this.username = u.getUsername();
        this.id = u.getId();
        this.role = u.getRole();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getId() == userDTO.getId() && Objects.equals(getUsername(), userDTO.getUsername()) && getRole() == userDTO.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRole());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
