package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.IEMGene;
import com.miDNA.andrew.repositories.IEMGeneRepo;
import com.miDNA.andrew.services.IEMGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEMGeneServiceImpl implements IEMGeneService {

    @Autowired
    private IEMGeneRepo iemGeneRepo;

    @Override
    public IEMGene getIEMGeneByID(String name) {
        return iemGeneRepo.getById(name);
    }

    @Override
    public void saveIEMGene(IEMGene iemGene) {
        iemGeneRepo.save(iemGene);
    }
}
