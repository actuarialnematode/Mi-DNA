package com.miDNA.andrew.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="GenomeLocation")
@Data
@ToString
@NoArgsConstructor
public class GenomeLocation {

    public GenomeLocation(ArrayList<String>data)
    {
        coord_position = data.get(0);
        coord_ref = data.get(1);
        coord_alt = data.get(2);
        clinvar_signrating = data.get(3);
        clinvar_phenotype = data.get(4);
        exac_freq = data.get(5);
        exac_hom = data.get(6);
        gnomad_exomes_freq = data.get(7);
        gnomad_exomes_hom = data.get(8);
        gnomad_genomes_freq = data.get(9);
        gnomad_genomes_hom = data.get(10);
        thousgenome_freq = data.get(11);
        thousgenome_hom = data.get(12);
        inhouse_carriers = data.get(13);
        inhouse_hom = data.get(14);
        mtDB_freq = data.get(15);
        mtDB_carriers = data.get(16);
        helixMTdb_freq = data.get(17);
        helixMTdb_hom = data.get(18);
        mitomap_freq = data.get(19);
        mitomap_carriers = data.get(20);
        exac_pli = data.get(21);
        exac_zmis = data.get(22);
        exac_zsyn = data.get(23);
        gnomad_LOEUF = data.get(24);
        gnomad_pLI = data.get(25);
        gnomad_zmis = data.get(26);
        gnomad_zsyn = data.get(27);
        gene_DG = data.get(28);
        effect = data.get(29);
        effect_text = data.get(30);
        effect_protein = data.get(31);
        effect_cDNA = data.get(32);
        dist_splice_site = data.get(33);
        genotype = data.get(34);
        caseNumber= data.get(35);
    }
    @Id
    String coord_position;
    String coord_ref;
    String coord_alt;
    String clinvar_signrating;
    String clinvar_phenotype;
    String exac_freq;
    String exac_hom;
    String gnomad_exomes_freq;
    String gnomad_exomes_hom;
    String gnomad_genomes_freq;
    String gnomad_genomes_hom;
    String thousgenome_freq;
    String thousgenome_hom;
    String inhouse_carriers;
    String inhouse_hom;
    String mtDB_freq;
    String mtDB_carriers;
    String helixMTdb_freq;
    String helixMTdb_hom;
    String mitomap_freq;
    String mitomap_carriers;
    String exac_pli;
    String exac_zmis;
    String exac_zsyn;
    String gnomad_LOEUF;
    String gnomad_pLI;
    String gnomad_zmis;
    String gnomad_zsyn;
    String gene_DG;
    String effect;
    String effect_text;
    String effect_protein;
    String effect_cDNA;
    String dist_splice_site;
    String genotype;
    String caseNumber;

}
