package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.Comic;

import com.server.project.comixconnexion.exceptions.exists.ComicExists;
import com.server.project.comixconnexion.exceptions.notfound.ComicNotFoundException;
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
    public ResponseEntity<CxHttpResponse> createComic(@RequestBody ComicRequest comicRequest){
        CxHttpResponse res = new CxHttpResponse();
        try{
            this.comicService.addComic(comicRequest);
            res.setSuccess(true);
            res.setStatus(HttpStatus.CREATED);
            res.setMessage("Comic Book Has Successfully Been Added");
        } catch (ComicExists e){
            res.setSuccess(false);
            res.setStatus(HttpStatus.CONFLICT);
            res.setMessage("Comic Book Invalid");
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CxHttpResponse> getComicById(@PathVariable Long id){
        CxHttpResponse res = new CxHttpResponse();
        try {
            this.comicService.getComic(id);
            res.setSuccess(true);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Comic Has Been Successfully Retrieved");
        } catch (ComicNotFoundException e) {
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Comic Can Not Be Found");
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());}

    @GetMapping("/all")
    public ResponseEntity<Iterable<Comic>> getAllComics(){
        return new ResponseEntity<>(this.comicService.getAllComics(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CxHttpResponse> updateComic(@RequestBody ComicRequest updatedInfo, @PathVariable Long id){
        CxHttpResponse res = new CxHttpResponse();
        try {
            this.comicService.updateComic(id, updatedInfo);
            res.setSuccess(true);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Comic Has Been Successfully Updated");
        } catch (ComicNotFoundException e) {
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Comic Can Not Be Found");
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
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
