package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.IEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEMRepo extends JpaRepository<IEM,String> {
}
