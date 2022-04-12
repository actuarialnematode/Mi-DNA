
package com.miDNA.andrew.dto.iem;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SymptomSpecs {

    @SerializedName("clinical")
    @Expose
    public Clinical clinical;
    @SerializedName("clinicalchar")
    @Expose
    public Clinicalchar clinicalchar;
    @SerializedName("biochemical")
    @Expose
    public Biochemical biochemical;

}
