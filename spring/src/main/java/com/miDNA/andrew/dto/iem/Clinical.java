
package com.miDNA.andrew.dto.iem;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Clinical {

    @SerializedName("Developmental delay")
    @Expose
    public DevelopmentalDelay developmentalDelay;
    @SerializedName("Dysarthria")
    @Expose
    public Dysarthria dysarthria;
    @SerializedName("Dystonia")
    @Expose
    public Dystonia dystonia;
    @SerializedName("Hyperkinesia")
    @Expose
    public Hyperkinesia hyperkinesia;
    @SerializedName("Insomnia")
    @Expose
    public Insomnia insomnia;

}
