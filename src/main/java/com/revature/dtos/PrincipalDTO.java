package com.revature.dtos;

import com.revature.entities.Role;
import com.revature.entities.User;

import java.util.Objects;

public class PrincipalDTO {

    private String id;
    private String username;
    private Role role;

    public PrincipalDTO(){}
    public PrincipalDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
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

    @Override
    public String toString() {
        return "PrincipalDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrincipalDTO)) return false;
        PrincipalDTO that = (PrincipalDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUsername(), that.getUsername()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRole());
    }
}
