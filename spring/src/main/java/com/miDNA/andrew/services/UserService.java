package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.User;

import java.util.ArrayList;

public interface UserService {
    User getUserById(String id);
    void saveUser(User User);
    ArrayList<User>getAllUser();
}
