package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.GenomeLocation;
import com.miDNA.andrew.repositories.GenomeLocationRepo;
import com.miDNA.andrew.services.GenomeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GenomeLocationServiceImpl implements GenomeLocationService {

    @Autowired
    private GenomeLocationRepo genomeLocationRepo;

    @Override
    public GenomeLocation getGenomeLocationById(String id) {
        return null;
    }

    @Override
    public void saveGenomeLocation(GenomeLocation genomeLocation) {
        genomeLocationRepo.save(genomeLocation);
        System.out.println("Genome location saved");
    }

    @Override
    public ArrayList<GenomeLocation> getAllLocationsByCase(String caseNumber) {
        return genomeLocationRepo.getAllByCaseNumber(caseNumber);
    }

    @Override
    public ArrayList<GenomeLocation> getAllLocations() {
        return (ArrayList<GenomeLocation>) genomeLocationRepo.findAll();
    }


}
