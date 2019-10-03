package com.server.project.comixconnexion.exceptions.notfound;

public class ComicNotFoundException extends Exception {

    @Override
    public String toString(){
        return "ERROR: Invalid Comic book";
    }
}
