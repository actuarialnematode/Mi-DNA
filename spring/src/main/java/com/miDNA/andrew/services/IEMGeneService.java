package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.IEMGene;

public interface IEMGeneService {
    IEMGene getIEMGeneByID(String name);
    void saveIEMGene(IEMGene iemGene);
}
