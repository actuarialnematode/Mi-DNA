package com.miDNA.andrew.repositories;

import com.miDNA.andrew.entities.PMIMEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PMIMEntryRepo extends JpaRepository<PMIMEntry,String> {
    ArrayList<PMIMEntry> getAllByOmimId(String id);
}
