package com.miDNA.andrew.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="IEMGene")
@Data
@ToString
@NoArgsConstructor
public class IEMGene {

    @Id
    String iem_geneName;
    String iem_id;

}
