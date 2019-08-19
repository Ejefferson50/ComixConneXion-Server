package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requestModels.UserRequest;
import services.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(this.userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(this.userService.findUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest userUpdateDetails, @PathVariable Long userId){
        return new ResponseEntity<>(this.userService.updateUser(userId, userUpdateDetails), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(Long userId){
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
