package com.miDNA.andrew.controllers;

import com.miDNA.andrew.entities.UserData;
import com.miDNA.andrew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public UserData login(@RequestBody UserData user){
       return userService.getUserById(user);
    }

    @PostMapping(value = "/register")
    public UserData register(@RequestBody UserData user){
        return userService.saveUser(user);
    }

    @PostMapping(value = "/checkSession")
    public UserData checkSession(@RequestBody UserData user){
        return userService.checkSession(user);
    }



}
