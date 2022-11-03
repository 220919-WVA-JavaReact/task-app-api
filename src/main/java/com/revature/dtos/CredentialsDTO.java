package com.revature.dtos;

import java.io.Serializable;
import java.util.Objects;

public class CredentialsDTO implements Serializable {
    private String username;
    private String password;

    public CredentialsDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsDTO)) return false;
        CredentialsDTO that = (CredentialsDTO) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }


    @Override
    public String toString() {
        return "CredentialsDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
