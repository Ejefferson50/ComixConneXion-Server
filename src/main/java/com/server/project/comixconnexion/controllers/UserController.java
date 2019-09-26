package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.User;
import com.server.project.comixconnexion.exceptions.CxHttpResponse;
import com.server.project.comixconnexion.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.server.project.comixconnexion.requestModels.UserRequest;
import com.server.project.comixconnexion.services.UserService;

import java.util.Arrays;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(this.userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> findUsername(@PathVariable String username){
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserRequest userUpdateDetails, @PathVariable Long id){
        return new ResponseEntity<>(this.userService.updateUser(id, userUpdateDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CxHttpResponse> deleteUser(@PathVariable Long id){

        CxHttpResponse response = new CxHttpResponse();
        try {
            this.userService.deleteUser(id);
            response.setSuccess(true);
            response.setMessage("User Successfully Removed");
            response.setStatus(HttpStatus.OK);
        } catch (UserNotFoundException e){
            response.setSuccess(false);
            response.setMessage("User Does Not Exist");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<ComixHttpResponse> deleteUser(@PathVariable Long id){
//
//        ComixHttpResponse response = new ComixHttpResponse();
//        try {
//            this.userService.deleteUser(id);
//            response.success = true;
//        } catch(UserDoesExistException e) {
//            response.success = false;
//            errorMessage = "User does exist";
//        } catch(Exception e) {
//            LOGGER.error(e.getMessage(), e);
//
//        }
//        return ResponseEntity.noContent().build();
//    }

}
