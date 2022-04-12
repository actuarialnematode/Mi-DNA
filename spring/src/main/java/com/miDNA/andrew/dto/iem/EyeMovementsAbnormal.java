
package com.miDNA.andrew.dto.iem;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class EyeMovementsAbnormal {

    @SerializedName("neonatal")
    @Expose
    public String neonatal;
    @SerializedName("infancy")
    @Expose
    public String infancy;
    @SerializedName("childhood")
    @Expose
    public String childhood;
    @SerializedName("adolescence")
    @Expose
    public String adolescence;
    @SerializedName("adulthood")
    @Expose
    public String adulthood;
    @SerializedName("was_char")
    @Expose
    public Boolean wasChar;
    @SerializedName("symptom")
    @Expose
    public Symptom__9 symptom;

}
