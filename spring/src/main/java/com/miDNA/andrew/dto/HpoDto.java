package com.miDNA.andrew.dto;

import lombok.Data;

import java.util.List;
@Data
public class HpoDto {
    public List<HmName2Id> hmName2Id = null;
    public Boolean hpoOption;
}

