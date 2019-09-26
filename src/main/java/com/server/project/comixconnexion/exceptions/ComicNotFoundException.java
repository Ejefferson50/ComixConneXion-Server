package com.server.project.comixconnexion.exceptions;

public class ComicNotFoundException extends Exception {

    public String toString(){
        return "ERROR: Invalid Comic book";
    }
}
