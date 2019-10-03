package com.server.project.comixconnexion.services;


import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.entities.User;
import com.server.project.comixconnexion.requestModels.UserRequest;

import com.server.project.comixconnexion.exceptions.exists.UserExists;
import com.server.project.comixconnexion.exceptions.notfound.ComicNotFoundException;
import com.server.project.comixconnexion.exceptions.notfound.UserNotFoundException;

import com.server.project.comixconnexion.repositories.UserRepository;
import com.server.project.comixconnexion.repositories.ComicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




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

    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> user = this.userRepo.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    public Iterable<User> findAllUsers(){
        return this.userRepo.findAll();
    }

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

    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> checkedUser = this.userRepo.findById(id);
        if(!checkedUser.isPresent()) {
            throw new UserNotFoundException();
        } else {
            this.userRepo.deleteById(id);
        }
    }

    public void addComicToUser(Long userId, Long comicId) throws UserNotFoundException, ComicNotFoundException {
        Optional<User> checkUser = this.userRepo.findById(userId);
        Optional<Comic> checkComic = this.comicRepository.findById(comicId);

       if(!checkUser.isPresent()){
           throw new UserNotFoundException();
       } else if(!checkComic.isPresent()){
           throw new ComicNotFoundException();
       } else {
           checkUser.get().getComicbooks().add(checkComic.get());
           this.userRepo.save(checkUser.get());
       }
    }


}
