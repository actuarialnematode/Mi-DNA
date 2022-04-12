package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.HpoTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HpoTermRepo extends JpaRepository<HpoTerm,String> {
    ArrayList<HpoTerm> getAllByText(byte[] text);
}
