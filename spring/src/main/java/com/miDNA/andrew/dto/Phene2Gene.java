package com.miDNA.andrew.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@ToString
public class Phene2Gene {

    public String gene;
    public String rank;
    public String geneID;
    public String score;
    public String status;

}