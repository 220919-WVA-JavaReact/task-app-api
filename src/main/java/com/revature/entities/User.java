package com.revature.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity // Tells our ORM (Object Relational Mapper, in this case Hibernate) that this is an object that maps to a relational entity
@Table(name="users") // optional annotation, used to specify a different name for the table that this entity maps to
public class User {

    @Id //indicates that this field is the primary key
    @Column(name="user_id") // optional annotation, used to specify name/constraints for this column
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
//    @Column(columnDefinition = "VARCHAR NOT NULL CHECK(LENGTH(PASSWORD)>=3")
    private String password;

    @Enumerated(EnumType.STRING) // store the enum value as String instead of 0, 1, ...
    private Role role;

    @ManyToOne
    private User manager;

    public User() {
        this.id = UUID.randomUUID().toString();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && getRole() == user.getRole() && Objects.equals(getManager(), user.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getRole(), getManager());
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", manager=" + manager +
                '}';
    }
}
