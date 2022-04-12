package com.miDNA.andrew.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.List;
@Data
@ToString
public class PhenGeneDto {
    public List<Phene2Gene> results = null;
    public List<Object> errors = null;
}

