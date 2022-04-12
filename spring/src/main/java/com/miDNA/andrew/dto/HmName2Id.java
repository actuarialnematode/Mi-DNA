package com.miDNA.andrew.dto;

import lombok.Data;

@Data
public class HmName2Id {

    public Integer start;
    public Integer length;
    public String hpoId;
    public String hpoName;
    public Boolean negated;

}