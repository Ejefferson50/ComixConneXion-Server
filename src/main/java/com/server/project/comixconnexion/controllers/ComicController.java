package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.Comic;

import com.server.project.comixconnexion.exceptions.ComicNotFoundException;
import com.server.project.comixconnexion.exceptions.CxHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.server.project.comixconnexion.requestModels.ComicRequest;
import com.server.project.comixconnexion.services.ComicService;

import java.util.Arrays;


@RestController
@RequestMapping("api/comics")
public class ComicController {

    private ComicService comicService;


    @Autowired
    public ComicController(ComicService comicService){ this.comicService = comicService; }

    @PutMapping("/")
    public ResponseEntity<Comic> createComic(@RequestBody ComicRequest comicRequest){
        return new ResponseEntity<>(this.comicService.addComic(comicRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable Long id){
        return new ResponseEntity<>(this.comicService.getComic(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<Comic>> getAllComics(){
        return new ResponseEntity<>(this.comicService.getAllComics(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Comic> updateComic(@RequestBody ComicRequest updatedInfo, @PathVariable Long id){
        return new ResponseEntity<>(this.comicService.updateComic(id, updatedInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CxHttpResponse> deleteComic(@PathVariable Long id) {
        CxHttpResponse response = new CxHttpResponse();
        try {
            this.comicService.deleteComic(id);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Comic Has Been Successfully Deleted");
        } catch (ComicNotFoundException e) {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Comic Can Not Be Found");
            response.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
