package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.entities.PhenGene;
import com.miDNA.andrew.repositories.PhenGeneRepo;
import com.miDNA.andrew.services.PhenGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PhenGeneServiceImpl implements PhenGeneService {

    @Autowired
    private PhenGeneRepo PhenGeneRepo;

    @Override
    public PhenGene getPhenGeneById(String id) {
        return null;
    }

    @Override
    public ArrayList<PhenGene> getAllPhenGenesByHpoID(String hpoID) {
        return PhenGeneRepo.getAllByHpoID(hpoID);
    }

    @Override
    public void savePhenGene(PhenGene PhenGene) {
        PhenGeneRepo.save(PhenGene);
        // System.out.println("Phen2Gene gene saved");
    }

    @Override
    public ArrayList<PhenGene> getAllPhenGene() {
        return null;
    }
}
