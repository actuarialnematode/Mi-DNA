package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.IEM;
import com.miDNA.andrew.entities.IEMSymptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEMSymptomsRepo extends JpaRepository<IEMSymptom,String> {
}
