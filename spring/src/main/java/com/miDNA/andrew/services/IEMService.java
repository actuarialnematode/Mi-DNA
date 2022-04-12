package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.IEM;
import com.miDNA.andrew.entities.IEMSymptom;

import java.util.ArrayList;

public interface IEMService {
    IEM getAllIemsByGeneID(String id);
    IEM saveIEM(IEM iem);
    void saveIEMSymptom(IEMSymptom iemSymptom);
    ArrayList<IEM>getAllIEM();

}
