package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.entities.PhenGene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PhenGeneRepo extends JpaRepository<PhenGene,String> {
    ArrayList<PhenGene> getAllByHpoID(String hpoID);
}
