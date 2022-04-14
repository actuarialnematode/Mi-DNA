package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.UserData;

import java.util.ArrayList;

public interface UserService {
    UserData getUserById(UserData id);
    UserData saveUser(UserData userData);
    ArrayList<UserData>getAllUser();
    UserData checkSession(UserData userData);
}
