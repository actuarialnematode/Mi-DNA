package com.miDNA.andrew.services.impl;

import com.miDNA.andrew.entities.IEM;
import com.miDNA.andrew.entities.IEMSymptom;
import com.miDNA.andrew.repositories.IEMRepo;
import com.miDNA.andrew.repositories.IEMSymptomsRepo;
import com.miDNA.andrew.services.IEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IEMServiceImpl implements IEMService {

    @Autowired
    private IEMRepo IEMRepo;

    @Autowired
    private IEMSymptomsRepo IEMSymptomRepo;

    @Override
    public IEM getAllIemsByGeneID(String id) {
        return IEMRepo.getById(id);
    }

    @Override
    public IEM saveIEM(IEM IEM) {
        IEM iemSave = IEMRepo.save(IEM);
        //System.out.println("IEM saved");
        return iemSave;
    }

    @Override
    public void saveIEMSymptom(IEMSymptom iemSymptom) {
        IEMSymptomRepo.save(iemSymptom);
        //System.out.println("IEM saved");
    }

    @Override
    public ArrayList<IEM> getAllIEM() {
        return null;
    }
}
