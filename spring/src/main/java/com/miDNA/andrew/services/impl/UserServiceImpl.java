package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.User;
import com.miDNA.andrew.repositories.UserRepo;
import com.miDNA.andrew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo UserRepo;

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public void saveUser(User User) {
        UserRepo.save(User);
        System.out.println("User saved");
    }

    @Override
    public ArrayList<User> getAllUser() {
        return null;
    }
}
