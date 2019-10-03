package com.server.project.comixconnexion.exceptions.exists;

public class UserExists extends Exception {

    @Override
    public String toString() {
        return "ERROR: User Already Exists!";
    }
}
