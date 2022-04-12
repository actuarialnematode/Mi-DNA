package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="IEM")
@Data
@NoArgsConstructor
public class IEM {
    @Id
    String iem_geneid;
    String iem_omim;
    @ManyToMany
    @JoinTable(
    name = "iem_symptoms",
    joinColumns = @JoinColumn(name = "iem_geneid"),
    inverseJoinColumns = @JoinColumn(name = "iemSymptom_name"))
    Set<IEMSymptom> iemSymptoms = new HashSet<>();
}
