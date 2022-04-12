package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.repositories.HpoTermRepo;
import com.miDNA.andrew.services.HpoTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HpoTermServiceImpl implements HpoTermService {

    @Autowired
    private HpoTermRepo HpoTermRepo;

    @Override
    public HpoTerm getHpoTermById(String id) {
        return null;
    }

    // Pass in text, returns array list of HPO terms and does this by the HpoTermRepo.getAllByText(text)
    @Override
    public ArrayList<HpoTerm> getAllHpoByText(byte[] text) {
        return HpoTermRepo.getAllByText(text);
    }

    @Override
    public void saveHpoTerm(HpoTerm HpoTerm) {
        HpoTermRepo.save(HpoTerm);
        System.out.println("HPO term saved");
    }

    @Override
    public ArrayList<HpoTerm> getAllHpoTerm() {
        return null;
    }
}
