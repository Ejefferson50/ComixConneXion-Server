package com.server.project.comixconnexion.controllers;

import com.server.project.comixconnexion.entities.User;
import com.server.project.comixconnexion.exceptions.exists.UserExists;
import com.server.project.comixconnexion.exceptions.notfound.ComicNotFoundException;
import com.server.project.comixconnexion.exceptions.CxHttpResponse;
import com.server.project.comixconnexion.exceptions.notfound.UserNotFoundException;
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
    public ResponseEntity<CxHttpResponse> createUser(@RequestBody UserRequest userRequest){
        CxHttpResponse res = new CxHttpResponse();
        try{
            this.userService.addUser(userRequest);
            res.setSuccess(true);
            res.setMessage("User Successfully Added");
            res.setStatus(HttpStatus.CREATED);
        } catch (UserExists e){
            res.setSuccess(false);
            res.setMessage("User Already Exists!");
            res.setStatus(HttpStatus.CONFLICT);
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
    }

    @GetMapping("/{id}")
        public ResponseEntity<CxHttpResponse> getUserById(@PathVariable Long id){
        CxHttpResponse res = new CxHttpResponse();
        try{
            this.userService.findUserById(id);
            res.setSuccess(true);
            res.setMessage("User Successfully Found" + this.userService.findUserById(id));
            res.setStatus(HttpStatus.OK);
        } catch (UserNotFoundException e){
            res.setSuccess(false);
            res.setMessage("User Does Not Exist");
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
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
    public ResponseEntity<CxHttpResponse> updateUser(@RequestBody UserRequest userUpdateDetails, @PathVariable Long id){
        CxHttpResponse res = new CxHttpResponse();
        try{
            this.userService.updateUser(id, userUpdateDetails);
            res.setSuccess(true);
            res.setMessage("User Successfully Updated" + this.userService.updateUser(id, userUpdateDetails));
            res.setStatus(HttpStatus.OK);
        } catch (UserNotFoundException e){
            res.setSuccess(false);
            res.setMessage("User Does Not Exist");
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CxHttpResponse> deleteUser(@PathVariable Long id){

        CxHttpResponse res = new CxHttpResponse();
        try {
            this.userService.deleteUser(id);
            res.setSuccess(true);
            res.setMessage("User Successfully Removed");
            res.setStatus(HttpStatus.OK);
        } catch (UserNotFoundException e){
            res.setSuccess(false);
            res.setMessage("User Does Not Exist");
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PutMapping("{userId}/comics/{comicId}")
    public ResponseEntity<CxHttpResponse> addComicToCollection(@PathVariable Long userId, @PathVariable Long comicId){
        CxHttpResponse res = new CxHttpResponse();
        try{
            this.userService.addComicToUser(userId,comicId);
            res.setSuccess(true);
            res.setStatus(HttpStatus.OK);
            res.setMessage("Comic Has Successfully Been Added To User's Collection");
        } catch (UserNotFoundException userExc){
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("User Not Found");
            res.setErrors(Arrays.asList(userExc.toString()));
        } catch (ComicNotFoundException comicExc){
            res.setSuccess(false);
            res.setStatus(HttpStatus.NOT_FOUND);
            res.setMessage("Comic Book Not Found");
            res.setErrors(Arrays.asList(comicExc.toString()));
        } catch (Exception e){
            res.setSuccess(false);
            res.setStatus(HttpStatus.CONFLICT);
            res.setMessage(e.getMessage());
            res.setErrors(Arrays.asList(e.toString()));
        }
        return new ResponseEntity<>(res, res.getStatus());
    }

//    @GetMapping("{userId}/comics/{comicId}")
//    public ResponseEntity<User> findComicInCollection(@PathVariable Long userId, @PathVariable Long comicId){
//        CxHttpResponse res = new CxHttpResponse();
//        try{
//            this.userService.addComicToUser(userId,comicId);
//            res.setSuccess(true);
//            res.setStatus(HttpStatus.OK);
//            res.setMessage("Comic Has Successfully Been Added To User's Collection");
//        } catch (UserNotFoundException userExc){
//            res.setSuccess(false);
//            res.setStatus(HttpStatus.NOT_FOUND);
//            res.setMessage("User Not Found");
//            res.setErrors(Arrays.asList(userExc.toString()));
//        } catch (ComicNotFoundException comicExc){
//            res.setSuccess(false);
//            res.setStatus(HttpStatus.NOT_FOUND);
//            res.setMessage("Comic Book Not Found");
//            res.setErrors(Arrays.asList(comicExc.toString()));
//        }
//        return new ResponseEntity<>(this.userService.findUserById(userId), res.getStatus());
//    }

}
