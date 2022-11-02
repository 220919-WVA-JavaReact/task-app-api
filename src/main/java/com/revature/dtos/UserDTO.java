package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", managerId='" + managerId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getId(), userDTO.getId()) && Objects.equals(getUsername(), userDTO.getUsername()) && getRole() == userDTO.getRole() && Objects.equals(getManagerId(), userDTO.getManagerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRole(), getManagerId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
