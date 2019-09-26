package com.server.project.comixconnexion.services;

import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.exceptions.ComicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.server.project.comixconnexion.repositories.ComicRepository;
import com.server.project.comixconnexion.requestModels.ComicRequest;

import java.util.Optional;

@Service
public class ComicService {

    private ComicRepository comicRepository;

    @Autowired
    public ComicService(ComicRepository comicRepository){
        this.comicRepository = comicRepository;
    }

    public Comic addComic(ComicRequest comicRequest){
        String publish = comicRequest.getPublisher();
        String title = comicRequest.getTitle();
        int seriesNo = comicRequest.getSeriesNum();

        Comic comic = new Comic();
        comic.setPublisher(publish);
        comic.setTitle(title);
        comic.setSeriesNum(seriesNo);

        return this.comicRepository.save(comic);
    }

    public Comic getComic(Long id){
        Optional<Comic> comic = this.comicRepository.findById(id);
        if (!comic.isPresent()) {
            System.out.println("Comic is not in collection");
        }
        return comic.get();
    }

    public Comic updateComic(Long id, ComicRequest newInfo){
        Comic update = this.comicRepository.getOne(id);
        update.setPublisher(newInfo.getPublisher());
        update.setTitle(newInfo.getTitle());
        update.setSeriesNum(newInfo.getSeriesNum());

        return this.comicRepository.save(update);
    }

    public void deleteComic(Long id) throws ComicNotFoundException {

        if (!this.comicRepository.existsById(id)) {
            throw new ComicNotFoundException();
        } else {
            this.comicRepository.deleteById(id);
        }
    }

    public Iterable<Comic> getAllComics(){
        try{
            this.comicRepository.findAll();
        } catch (Exception e){
            System.out.println("Your collection is empty");
        }
        return this.comicRepository.findAll();
    }

}
