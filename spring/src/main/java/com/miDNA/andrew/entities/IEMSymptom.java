package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="IemSymptom")
public class IEMSymptom {

    @Id
    String iemSymptom_name;
    String iemSymptom_system;
    String iemSymptom_biochemicalTest;
    String iemSymptom_hmdb;
    String iemSymptom_neonatal;
    String iemSymptom_infancy;
    String iemSymptom_childhood;
    String iemSymptom_adolescence;
    String iemSymptom_adulthood;

}

