package com.server.project.comixconnexion.services;


import com.server.project.comixconnexion.entities.Comic;
import com.server.project.comixconnexion.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.server.project.comixconnexion.repositories.UserRepository;
import com.server.project.comixconnexion.requestModels.UserRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;

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

    public User updateUser(Long id, UserRequest newUser){
        User user = this.userRepo.getOne(id);
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        return this.userRepo.save(user);
    }

    public void deleteUser(Long id){
        try {
           this.userRepo.deleteById(id);
        } catch (Exception e){
            System.out.println("User does not exist");
        }
    }


}
