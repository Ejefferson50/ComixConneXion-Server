package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.server.project.comixconnexion.requestModels.UserRequest;
import com.server.project.comixconnexion.services.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(this.userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
        public ResponseEntity<User> getUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(this.userService.findUserById(userRequest.getId()), HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")

    public ResponseEntity<User> updateUser(@RequestBody UserRequest userUpdateDetails, @PathVariable Long userId){
        return new ResponseEntity<>(this.userService.updateUser(userId, userUpdateDetails), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
