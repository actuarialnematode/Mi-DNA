package com.miDNA.andrew.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="User")
@Data
@ToString
@NoArgsConstructor
public class User {

    public User(ArrayList<String>data) {
        email = data.get(0);
        first_name = data.get(1);
        last_name = data.get(2);
        password = data.get(3);
    }

    @Id
    String email;
    String first_name;
    String last_name;
    String password;

}


