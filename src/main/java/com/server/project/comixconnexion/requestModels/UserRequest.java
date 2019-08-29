package com.server.project.comixconnexion.requestModels;

import com.server.project.comixconnexion.entities.Comic;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Comic> comicbooks = new ArrayList<>();

    public UserRequest(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comic> getComicbooks() {
        return comicbooks;
    }

    public void setComicbooks(List<Comic> comicbooks) {
        this.comicbooks = comicbooks;
    }
}
