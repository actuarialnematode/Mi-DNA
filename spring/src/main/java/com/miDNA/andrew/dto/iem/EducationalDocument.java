
package com.miDNA.andrew.dto.iem;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class EducationalDocument {

    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;

}
