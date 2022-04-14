package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.UserData;
import com.miDNA.andrew.repositories.UserRepo;
import com.miDNA.andrew.services.UserService;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserData getUserById(UserData userData) {
        Optional<UserData> existingUserOptional = userRepo.findById(userData.getEmail());
        if (existingUserOptional.isPresent()) {
            UserData existingUser = existingUserOptional.get();
            if (userData.getPassword().equals(existingUser.getPassword())) {
                existingUser.setToken(encrypt(userData.getPassword(),userData.getPassword()));
                return userRepo.save(existingUser);
            }
        }
        return new UserData();
    }

    @Override
    public UserData saveUser(UserData userData) {
        Optional<UserData> existingUserOptional = userRepo.findById(userData.getEmail());
        if (existingUserOptional.isPresent()) {
            return new UserData();
        }
        else {
            userData.setToken(encrypt(userData.getPassword(),userData.getPassword()));
            return userRepo.save(userData);
        }
    }

    @Override
    public ArrayList<UserData> getAllUser() {
        return null;
    }

    @Override
    public UserData checkSession(UserData userData) {
        Optional<UserData> existingUserOptional = userRepo.findById(userData.getEmail());
        if (existingUserOptional.isPresent()) {
            UserData existingUser = existingUserOptional.get();
            if (userData.getToken().equals(existingUser.getToken())) {
                return userData;
            }
        }
        return new UserData();
    }

    public String encrypt(String text,String key)
    {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String privateData = text;
        textEncryptor.setPasswordCharArray(key.toCharArray());
        String myEncryptedText = textEncryptor.encrypt(privateData);
        return myEncryptedText;
    }

    public String decrypt(String token,String key)
    {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String privateData = token;
        textEncryptor.setPasswordCharArray(key.toCharArray());
        String plainText = textEncryptor.decrypt(privateData);
        return plainText;
    }

}
