export class GenomeLocation {



    constructor(data:Array<string>)
    {
        this.data = new Array<string>();
        this.coord_position = data[0];
        this.coord_ref = data[1];
        this.coord_alt = data[2];
        this.clinvar_signrating = data[3];
        this.clinvar_phenotype = data[4];
        this.exac_freq = data[5];
        this.exac_hom = data[6];
        this.gnomad_exomes_freq = data[7];
        this.gnomad_exomes_hom = data[8];
        this.gnomad_genomes_freq = data[9];
        this.gnomad_genomes_hom = data[10];
        this.thousgenome_freq = data[11];
        this.thousgenome_hom = data[12];
        this.inhouse_carriers = data[13];
        this.inhouse_hom = data[14];
        this.mtDB_freq = data[15];
        this.mtDB_carriers = data[16];
        this.helixMTdb_freq = data[17];
        this.helixMTdb_hom = data[18];
        this.mitomap_freq = data[19];
        this.mitomap_carriers = data[20];
        this.exac_pli = data[21];
        this.exac_zmis = data[22];
        this.exac_zsyn = data[23];
        this.gnomad_LOEUF = data[24];
        this.gnomad_pLI = data[25];
        this.gnomad_zmis = data[26];
        this.gnomad_zsyn = data[27];
        this.gene_DG = data[28];
        this.effect = data[29];
        this.effect_text = data[30];
        this.effect_protein = data[31];
        this.effect_cDNA = data[32];
        this.dist_splice_site = data[33];
        this.genotype = data[34];
        this.caseNumber= data[35];
    }

    public describe(): Array<string> {
        return Object.getOwnPropertyNames(this).splice(1,35);
    }

    public getAllFields(): Array<string>{
        this.data[0] = this.coord_position;
        this.data[1] = this.coord_ref;
        this.data[2] = this.coord_alt;
        this.data[3] = this.clinvar_signrating;
        this.data[4] = this.clinvar_phenotype;
        this.data[5] = this.exac_freq;
        this.data[6] = this.exac_hom;
        this.data[7] = this.gnomad_exomes_freq;
        this.data[8] = this.gnomad_exomes_hom;
        this.data[9] = this.gnomad_genomes_freq;
        this.data[10] = this.gnomad_genomes_hom;
        this.data[11] = this.thousgenome_freq;
        this.data[12] = this.thousgenome_hom;
        this.data[13] = this.inhouse_carriers;
        this.data[14] = this.inhouse_hom;
        this.data[15] = this.mtDB_freq;
        this.data[16] = this.mtDB_carriers;
        this.data[17] = this.helixMTdb_freq;
        this.data[18] = this.helixMTdb_hom;
        this.data[19] = this.mitomap_freq;
        this.data[20] = this.mitomap_carriers;
        this.data[21] = this.exac_pli;
        this.data[22] = this.exac_zmis;
        this.data[23] = this.exac_zsyn;
        this.data[24] = this.gnomad_LOEUF;
        this.data[25] = this.gnomad_pLI;
        this.data[26] = this.gnomad_zmis;
        this.data[27] = this.gnomad_zsyn;
        this.data[28] = this.gene_DG;
        this.data[29] = this.effect;
        this.data[30] = this.effect_text;
        this.data[31] = this.effect_protein;
        this.data[32] = this.effect_cDNA;
        this.data[33] = this.dist_splice_site;
        this.data[34] = this.genotype;
        return this.data;
    }
    
    data:Array<string>;
    coord_position:string;
    coord_ref:string;
    coord_alt:string;
    clinvar_signrating:string;
    clinvar_phenotype:string;
    exac_freq:string;
    exac_hom:string;
    gnomad_exomes_freq:string;
    gnomad_exomes_hom:string;
    gnomad_genomes_freq:string;
    gnomad_genomes_hom:string;
    thousgenome_freq:string;
    thousgenome_hom:string;
    inhouse_carriers:string;
    inhouse_hom:string;
    mtDB_freq:string;
    mtDB_carriers:string;
    helixMTdb_freq:string;
    helixMTdb_hom:string;
    mitomap_freq:string;
    mitomap_carriers:string;
    exac_pli:string;
    exac_zmis:string;
    exac_zsyn:string;
    gnomad_LOEUF:string;
    gnomad_pLI:string;
    gnomad_zmis:string;
    gnomad_zsyn:string;
    gene_DG:string;
    effect:string;
    effect_text:string;
    effect_protein:string;
    effect_cDNA:string;
    dist_splice_site:string;
    genotype:string;
    caseNumber:string;
}
