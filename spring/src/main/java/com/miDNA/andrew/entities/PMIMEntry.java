package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="PMIMEntry")
public class PMIMEntry {

    // Marinesco-Sjogren syndrome, 248800 (3)|SIL1, BAP, MSS|608005|5q31.2
    // omim_name, omim_phenMIM, omim_mapkey, omim_genes, omim_geneMIM, omim_location

    @Id
    String pmim_id;
    String pmim_name;
    String pmim_genes;
    String pmim_location;
    String omimId;

}

/*

package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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
    @JsonIgnore
    @ManyToMany(mappedBy = "iemSymptoms")
    Set<IEM> iems = new HashSet<>();
}

 */