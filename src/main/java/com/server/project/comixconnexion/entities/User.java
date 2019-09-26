package com.server.project.comixconnexion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany()
    private List<Comic> comicbooks = new ArrayList<>();

    public User () {}

    public User (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.comicbooks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comic> getComicbooks() {
        return comicbooks;
    }

    public void setComicbooks(List<Comic> comicbooks) {
        this.comicbooks = comicbooks;
    }
}
