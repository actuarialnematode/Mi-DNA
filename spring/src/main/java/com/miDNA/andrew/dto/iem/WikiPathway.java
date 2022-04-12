
package com.miDNA.andrew.dto.iem;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class WikiPathway {

    @SerializedName("pathway_id")
    @Expose
    public String pathwayId;
    @SerializedName("node_names")
    @Expose
    public String nodeNames;
    @SerializedName("primary_pathway")
    @Expose
    public Boolean primaryPathway;

}
