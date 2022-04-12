package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="PhenGene")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhenGene {
    @Id
    String phen_gene;
    String phen_rank;
    String phen_score;
    String phen_status;
    @JsonIgnore
    String hpoID;
    // I think this is a problem as the DB requires a scalar argument
}
