package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requestModels.UserRequest;
import services.UserService;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path="/add")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(this.userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> getUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(this.userService.findUserById(userRequest.getId()), HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest userUpdateDetails, @PathVariable Long userId){
        return new ResponseEntity<>(this.userService.updateUser(userId, userUpdateDetails), HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
