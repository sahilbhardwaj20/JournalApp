package com.abacus.journalApp.controller;


import com.abacus.journalApp.entity.User;
import com.abacus.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(){
       return userService.findUser();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEntry(){
        return userService.deleteByUsername();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}