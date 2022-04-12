package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.entities.PhenGene;

import java.util.ArrayList;

public interface PhenGeneService {
    PhenGene getPhenGeneById(String id);
    ArrayList<PhenGene>getAllPhenGenesByHpoID(String hpoID);
    void savePhenGene(PhenGene PhenGene);
    ArrayList<PhenGene>getAllPhenGene();
}
