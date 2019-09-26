package com.server.project.comixconnexion.services;


import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.entities.User;

import com.server.project.comixconnexion.exceptions.ComicNotFoundException;
import com.server.project.comixconnexion.exceptions.UserNotFoundException;
import com.server.project.comixconnexion.repositories.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.server.project.comixconnexion.repositories.UserRepository;
import com.server.project.comixconnexion.requestModels.UserRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;
    private ComicRepository comicRepository;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User addUser(UserRequest userRequest){
        String userName = userRequest.getUsername();
        String pw = userRequest.getPassword();
        String email = userRequest.getEmail();

        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(pw);
        return this.userRepo.save(user);
    }

    public User findUserById(Long id){
        Optional<User> user = this.userRepo.findById(id);
        if(!user.isPresent()){
            System.out.println("User does not exist");
        }
        return user.get();
    }

    public Iterable<User> findAllUsers(){
        try{
            this.userRepo.findAll();
        } catch (Exception e){
            System.out.println("No Users Exists");
        }
        return this.userRepo.findAll();
    }

    public Boolean findByUsername(String username){
        Boolean usernameNotInUse = true;
        Optional<User> user = this.userRepo.findByUsername(username);
        if(!user.isPresent()){
            usernameNotInUse = false;
            System.out.println("Username does not exist");
        } else{
            usernameNotInUse = true;
        }

        return usernameNotInUse;
    }

    public User updateUser(Long id, UserRequest newUser){
        User user = this.userRepo.getOne(id);
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        return this.userRepo.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if(!this.userRepo.existsById(id)) {
            throw new UserNotFoundException();
        } else {
            this.userRepo.deleteById(id);
        }
    }

    public void addComicToUser(Long userId, Long comicId) throws UserNotFoundException, ComicNotFoundException {
       if(!this.userRepo.existsById(userId)){
           throw new UserNotFoundException();
       } else if(!this.comicRepository.existsById(comicId)){
           throw new ComicNotFoundException();
       } else {
           User user = this.userRepo.getOne(userId);
           Comic comicToAdd = this.comicRepository.getOne(comicId);
           user.getComicbooks().add(comicToAdd);
           this.userRepo.save(user);
       }

    }


}
