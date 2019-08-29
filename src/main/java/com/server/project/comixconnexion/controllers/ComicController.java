package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.Comic;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.server.project.comixconnexion.requestModels.ComicRequest;
import com.server.project.comixconnexion.services.ComicService;

import java.util.logging.Logger;

@RestController
public class ComicController {

    private ComicService comicService;
    //private Logger log = new Logger();

    @Autowired
    public ComicController(ComicService comicService){ this.comicService = comicService; }

    @PutMapping("/comics")
    public ResponseEntity<Comic> createComic(@RequestBody ComicRequest comicRequest){
        //log.info("Commence createComic() method");
        return new ResponseEntity<>(this.comicService.addComic(comicRequest), HttpStatus.CREATED);
    }

    @GetMapping("/comics/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable Long id){
        return new ResponseEntity<>(this.comicService.getComic(id), HttpStatus.OK);
    }
    @GetMapping("/comics/all")
    public ResponseEntity<Iterable<Comic>> getAllComics(){
        return new ResponseEntity<>(this.comicService.getAllComics(), HttpStatus.OK);
    }

    @PostMapping("/comics/{id}")
    public ResponseEntity<Comic> updateComic(@RequestBody ComicRequest updatedInfo, @PathVariable Long id){
        return new ResponseEntity<>(this.comicService.updateComic(id, updatedInfo), HttpStatus.OK);
    }

    @DeleteMapping("/comics/{id}")
    public ResponseEntity<Void> deleteComic(ComicRequest comicRequest){
       this.comicService.deleteComic(comicRequest);
       return ResponseEntity.noContent().build();
    }
}
