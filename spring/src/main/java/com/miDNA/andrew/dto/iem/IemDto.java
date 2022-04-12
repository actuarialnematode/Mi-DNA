
package com.miDNA.andrew.dto.iem;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class IemDto {

    @SerializedName("book_chapter_num")
    @Expose
    public Object bookChapterNum;
    @SerializedName("book_disorder_num")
    @Expose
    public String bookDisorderNum;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("name_alt1")
    @Expose
    public Object nameAlt1;
    @SerializedName("name_alt2")
    @Expose
    public Object nameAlt2;
    @SerializedName("abbr")
    @Expose
    public String abbr;
    @SerializedName("gene_sym")
    @Expose
    public String geneSym;
    @SerializedName("chr_local")
    @Expose
    public String chrLocal;
    @SerializedName("aff_prot")
    @Expose
    public String affProt;
    @SerializedName("omim_no")
    @Expose
    public String omimNo;
    @SerializedName("subtype")
    @Expose
    public String subtype;
    @SerializedName("hgnc_gene_sym")
    @Expose
    public String hgncGeneSym;
    @SerializedName("hgnc_gene_name")
    @Expose
    public String hgncGeneName;
    @SerializedName("ncbi_gene")
    @Expose
    public String ncbiGene;
    @SerializedName("uniprot")
    @Expose
    public String uniprot;
    @SerializedName("kegg")
    @Expose
    public String kegg;
    @SerializedName("treatability")
    @Expose
    public String treatability;
    @SerializedName("gene_reviews")
    @Expose
    public Object geneReviews;
    @SerializedName("prevalence")
    @Expose
    public Object prevalence;
    @SerializedName("orphacode")
    @Expose
    public Object orphacode;
    @SerializedName("nosology_iem_code")
    @Expose
    public String nosologyIemCode;
    @SerializedName("icimd_nosology_subgroup_code")
    @Expose
    public String icimdNosologySubgroupCode;
    @SerializedName("icimd_nosology_disorder_num")
    @Expose
    public String icimdNosologyDisorderNum;
    @SerializedName("nosology_group_code")
    @Expose
    public String nosologyGroupCode;
    @SerializedName("nosology_disorder_num")
    @Expose
    public String nosologyDisorderNum;
    @SerializedName("inheritance")
    @Expose
    public String inheritance;
    @SerializedName("wiki_pathways")
    @Expose
    public List<WikiPathway> wikiPathways = null;
    @SerializedName("symptom_specs")
    @Expose
    public SymptomSpecs symptomSpecs;
    @SerializedName("educational_documents")
    @Expose
    public List<EducationalDocument> educationalDocuments = null;

}
