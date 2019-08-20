package controllers;

import entities.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requestModels.ComicRequest;
import services.ComicService;

@RestController
public class ComicController {

    private ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService){ this.comicService = comicService; }

    @PostMapping("/comics")
    public ResponseEntity<Comic> postComic(@RequestBody ComicRequest comicRequest){
        return new ResponseEntity<>(this.comicService.addComic(comicRequest), HttpStatus.CREATED);
    }

    @GetMapping("/comics/{id}")
    public ResponseEntity<Comic> getComic(ComicRequest comicRequest){
        return new ResponseEntity<>(this.comicService.getComic(comicRequest), HttpStatus.OK);
    }
    @GetMapping("/comics/{id}")
    public ResponseEntity<Iterable<Comic>> getAllComics(ComicRequest comicRequest){
        return new ResponseEntity<>(this.comicService.getAllComics(), HttpStatus.OK);
    }

    @PutMapping("/comics/{id}")
    public ResponseEntity<Comic> putComic(ComicRequest currentInfo, ComicRequest updatedInfo){
        return new ResponseEntity<>(this.comicService.updateComic(currentInfo, updatedInfo), HttpStatus.OK);
    }

    @DeleteMapping("/comics/{id}")
    public ResponseEntity<Void> deleteComic(ComicRequest comicRequest){
       this.comicService.deleteComic(comicRequest);
       return ResponseEntity.noContent().build();
    }
}
