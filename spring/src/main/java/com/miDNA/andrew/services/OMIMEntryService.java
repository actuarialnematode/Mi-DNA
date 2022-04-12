package com.miDNA.andrew.services;

import com.miDNA.andrew.entities.PMIMEntry;

import java.util.ArrayList;

public interface OMIMEntryService {
    ArrayList<PMIMEntry> getAllEntriesByOMIMId(String id);
    void savePMIMEntry(PMIMEntry PMIMEntry);
}
