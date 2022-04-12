package com.miDNA.andrew.controllers;

import com.miDNA.andrew.entities.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @PostMapping(value = "/login")
    public User login(@RequestBody User user){
        if (user.getEmail().length()>0 && user.getPassword().length()>0) {
            return user;
        }else
        {
            return null;
        }
    }

}
