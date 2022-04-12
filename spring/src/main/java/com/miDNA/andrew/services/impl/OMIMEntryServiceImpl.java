package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.PMIMEntry;
import com.miDNA.andrew.repositories.PMIMEntryRepo;
import com.miDNA.andrew.services.OMIMEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class OMIMEntryServiceImpl implements OMIMEntryService {


    @Autowired
    private PMIMEntryRepo PMIMEntryRepo;


    @Override
    public ArrayList<PMIMEntry> getAllEntriesByOMIMId(String id) {
        return PMIMEntryRepo.getAllByOmimId(id);
    }

    @Override
    public void savePMIMEntry(PMIMEntry PMIMEntry) {
        PMIMEntryRepo.save(PMIMEntry);
        System.out.println("PMIM entry saved");
    }

}
