package com.server.project.comixconnexion.services;

import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.exceptions.exists.ComicExists;
import com.server.project.comixconnexion.exceptions.notfound.ComicNotFoundException;
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

    public Comic addComic(ComicRequest comicRequest) throws ComicExists {
        Comic comic = new Comic();
        if(comicSeriesTitleAndIssueExists(comicRequest.getSeriesTitle(),comicRequest.getIssue())){
            throw new ComicExists();
        } else {
            String publish = comicRequest.getPublisher();
            String seriesTitle = comicRequest.getSeriesTitle();
            int seriesNo = comicRequest.getIssue();

            comic.setPublisher(publish);
            comic.setSeriesTitle(seriesTitle);
            comic.setIssue(seriesNo);
        }
        return this.comicRepository.save(comic);
    }

    public Comic getComic(Long id) throws ComicNotFoundException {
        Optional<Comic> comic = this.comicRepository.findById(id);
        if (!comic.isPresent()) {
            throw new ComicNotFoundException();
        }
        return comic.get();
    }

    public Comic updateComic(Long id, ComicRequest newInfo) throws ComicNotFoundException {
        Optional<Comic> comic = this.comicRepository.findById(id);
        if(!comic.isPresent()){
            throw new ComicNotFoundException();
        } else{
            comic.get().setPublisher(newInfo.getPublisher());
            comic.get().setSeriesTitle(newInfo.getSeriesTitle());
            comic.get().setIssue(newInfo.getIssue());
        }
        return this.comicRepository.save(comic.get());
    }

    public void deleteComic(Long id) throws ComicNotFoundException {

        if (!this.comicRepository.existsById(id)) {
            throw new ComicNotFoundException();
        } else {
            this.comicRepository.deleteById(id);
        }
    }

    public Iterable<Comic> getAllComics(){  return this.comicRepository.findAll(); }

    public Boolean comicSeriesTitleAndIssueExists(String seriesTitle, Integer issue){
        Boolean comicDoesNotExist = true;
        Optional<Comic> comic = this.comicRepository.findComicBySeriesTitleAndIssue(seriesTitle,issue);
        if(comic.isPresent()){
            comicDoesNotExist = false;
        } else {
            comicDoesNotExist = true;
        }
        return comicDoesNotExist;
    }

}
