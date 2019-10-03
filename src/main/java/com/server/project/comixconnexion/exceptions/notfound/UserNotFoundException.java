package com.server.project.comixconnexion.exceptions.notfound;

public class UserNotFoundException extends Exception {

    @Override
    public String toString(){
        return "ERROR: Invalid User";
    }
}
