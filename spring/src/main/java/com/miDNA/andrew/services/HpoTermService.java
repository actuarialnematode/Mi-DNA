package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.HpoTerm;

import java.util.ArrayList;

public interface HpoTermService {
    HpoTerm getHpoTermById(String id);
    ArrayList<HpoTerm>getAllHpoByText(byte[] text);
    void saveHpoTerm(HpoTerm hpoTerm);
    ArrayList<HpoTerm>getAllHpoTerm();
}
