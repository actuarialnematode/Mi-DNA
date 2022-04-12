package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.GenomeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GenomeLocationRepo extends JpaRepository<GenomeLocation,String> {
    ArrayList<GenomeLocation> getAllByCaseNumber(String caseNumber);
}
