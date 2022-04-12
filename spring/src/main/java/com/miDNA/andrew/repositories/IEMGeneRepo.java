package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.IEMGene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEMGeneRepo extends JpaRepository<IEMGene,String> {
}
