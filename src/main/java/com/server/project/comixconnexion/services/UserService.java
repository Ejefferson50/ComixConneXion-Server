package com.server.project.comixconnexion.services;


import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.entities.User;
import com.server.project.comixconnexion.requestModels.ComicRequest;
import com.server.project.comixconnexion.requestModels.UserRequest;

import com.server.project.comixconnexion.exceptions.exists.UserExists;
import com.server.project.comixconnexion.exceptions.notfound.ComicNotFoundException;
import com.server.project.comixconnexion.exceptions.notfound.UserNotFoundException;

import com.server.project.comixconnexion.repositories.UserRepository;
import com.server.project.comixconnexion.repositories.ComicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;
    private ComicRepository comicRepository;

    @Autowired
    public UserService(UserRepository userRepo, ComicRepository comicRepo){

        this.userRepo = userRepo;
        this.comicRepository = comicRepo;
    }

    // ****** User CRUD Methods ******
    // CREATE - Add a new User
    public User addUser(UserRequest userRequest) throws UserExists {
        User user = new User();
        if(findByUsername(userRequest.getUsername()) || findByEmail(userRequest.getEmail())){
            throw new UserExists();
        } else {
            String userName = userRequest.getUsername();
            String pw = userRequest.getPassword();
            String email = userRequest.getEmail();

            user.setUsername(userName);
            user.setEmail(email);
            user.setPassword(pw);
        }
        return this.userRepo.save(user);
    }

    // READ - Get a User by Id
    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = this.userRepo.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    // READ(all) - Get all Users
    public Iterable<User> findAllUsers(){
        return this.userRepo.findAll();
    }


    // UPDATE - Update User
    public User updateUser(Long id, UserRequest newUser) throws UserNotFoundException {
        Optional<User> checkedUser = this.userRepo.findById(id);
        if(!checkedUser.isPresent()) {
            throw new UserNotFoundException();
        } else {
            checkedUser.get().setUsername(newUser.getUsername());
            checkedUser.get().setEmail(newUser.getEmail());
            checkedUser.get().setPassword(newUser.getPassword());
        }
        return this.userRepo.save(checkedUser.get());
    }

    // DELETE - Remove a User
    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> checkedUser = this.userRepo.findById(id);
        if(!checkedUser.isPresent()) {
            throw new UserNotFoundException();
        } else {
            this.userRepo.deleteById(id);
        }
    }

    // ****** Bonus Methods To Find User Info ******
    // Find By Username
    public Boolean findByUsername(String username){
        Boolean usernameNotInUse = true;
        Optional<User> user = this.userRepo.findByUsername(username);
        if(!user.isPresent()){
            usernameNotInUse = false;
        } else{
            usernameNotInUse = true;
        }
        return usernameNotInUse;
    }

    // Find By Email
    public Boolean findByEmail(String email){
        Boolean emailNotInUse = true;
        Optional<User> user = this.userRepo.findByEmail(email);
        if(!user.isPresent()){
            emailNotInUse = false;
        } else{
            emailNotInUse = true;
        }
        return emailNotInUse;
    }

    // ****** Service Methods CRUD Methods For User Comic Book Collection
    // CREATE - Adding a comic book to collection
    public void addComicToUser(Long userId, Long comicId) throws UserNotFoundException, ComicNotFoundException {
        Optional<User> checkUser = this.userRepo.findById(userId);
        Optional<Comic> checkComic = this.comicRepository.findById(comicId);

        // Check if User and Comic exist, if not throw appropriate Exceptions
       if(!checkUser.isPresent()){
           throw new UserNotFoundException();
       } else if(!checkComic.isPresent()){
           throw new ComicNotFoundException();
       } else {
           checkUser.get().getComicbooks().add(checkComic.get());
           this.userRepo.save(checkUser.get());
       }
    }

    // READ - retrieve a comic book from User's collection
    public Comic getComicFromCollection(Long userId, String title, Integer issue) throws UserNotFoundException, ComicNotFoundException {
        Optional<User> user = this.userRepo.findById(userId);
        Comic comicToRetrieve = null;

        // Check if User exist or if collection is empty, if not throw appropriate Exceptions
        if(!user.isPresent()){
            throw new UserNotFoundException();
        } else if(user.get().getComicbooks().size() == 0){
            throw new ComicNotFoundException();
        }

        for (Comic c: user.get().getComicbooks()){
            if(c.getSeriesTitle().equals(title) && c.getIssue() == issue){
                comicToRetrieve = c;
            }
        }
        return comicToRetrieve;
    }

    // READ(all) - Retrieve entire User collection
    public List<Comic> getCollection(Long userId) throws UserNotFoundException {
        Optional<User> user = this.userRepo.findById(userId);

        // Check if User, if not throw appropriate Exception
        if(!user.isPresent()){
            throw new UserNotFoundException();
        } else
            return user.get().getComicbooks();
    }

    // UPDATE - Update a comic book from User's collection
    public Comic updateComicInCollection(Long userId, Long comicId, ComicRequest updateDetails) throws UserNotFoundException, ComicNotFoundException {
        Optional<User> checkUser = this.userRepo.findById(userId);
        Optional<Comic> checkComic = this.comicRepository.findById(comicId);
        Comic comicToUpdate = new Comic();

        // Check if User and Comic exist, if not throw appropriate Exceptions
        if(!checkUser.isPresent()){
            throw new UserNotFoundException();
        } else if(!checkComic.isPresent()){
            throw new ComicNotFoundException();
        } else
            comicToUpdate = checkComic.get();

        // Search collection for comic, if comic in collection is there, update the details
        for (Comic c:
             checkUser.get().getComicbooks()) {
            if(c.getSeriesTitle().equals(comicToUpdate.getSeriesTitle()) && c.getIssue() == comicToUpdate.getIssue()){
                comicToUpdate = c;
                comicToUpdate.setIssue(updateDetails.getIssue());
                comicToUpdate.setSeriesTitle(updateDetails.getSeriesTitle());
            }
        }
        return comicToUpdate;
    }
}
