package services;

import entities.Comic;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ComicRepository;
import requestModels.ComicRequest;

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
        Integer seriesNo = comicRequest.getSeriesNum();
        User owner = comicRequest.getUser();

        Comic comic = new Comic();
        comic.setPublisher(publish);
        comic.setTitle(title);
        comic.setSeriesNum(seriesNo);
        comic.setUser(owner);

        return this.comicRepository.save(comic);
    }

    public Comic getComic(ComicRequest comicRequest){
        Optional<Comic> comic = this.comicRepository.findById(comicRequest.getId());
        if (!comic.isPresent()) {
            System.out.println("Comic is not in collection");
        }
        return comic.get();
    }

    public Comic updateComic(ComicRequest oldInfo, ComicRequest newInfo){
        Comic update = this.comicRepository.getOne(oldInfo.getId());
        update.setPublisher(newInfo.getPublisher());
        update.setTitle(newInfo.getTitle());
        update.setSeriesNum(newInfo.getSeriesNum());
        update.setUser(newInfo.getUser());

        return this.comicRepository.save(update);
    }

    public void deleteComic(ComicRequest comicRequest){
        try{
            this.comicRepository.existsById(comicRequest.getId());
        } catch (Exception e){
            System.out.println("Can not find this comic book in your collection.");
        }
        this.comicRepository.deleteById(comicRequest.getId());
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
