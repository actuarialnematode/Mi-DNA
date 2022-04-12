package com.miDNA.andrew.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.jdbc.Blob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="HpoTerm")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HpoTerm {
    @Id
    String hpo_id="";
    String hpo_name;
    @JsonIgnore
    @Lob
    @Column(name = "text", columnDefinition="BLOB")
    byte[] text;
    // text = doctor's notes
}
