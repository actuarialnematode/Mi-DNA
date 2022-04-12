package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.GenomeLocation;

import java.util.ArrayList;

public interface GenomeLocationService {
    GenomeLocation getGenomeLocationById(String id);
    void saveGenomeLocation(GenomeLocation genomeLocation);
    ArrayList<GenomeLocation> getAllLocationsByCase(String caseNumber);
    ArrayList<GenomeLocation> getAllLocations();
}
