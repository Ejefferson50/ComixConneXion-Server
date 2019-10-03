package com.server.project.comixconnexion.exceptions.exists;

public class ComicExists extends Exception {

    @Override
    public String toString() {
        return "ERROR: Comic Book Already Exists!";
    }
}
