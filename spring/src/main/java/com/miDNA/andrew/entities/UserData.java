package com.miDNA.andrew.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="UserData")
@Data
@ToString
@NoArgsConstructor
public class UserData {
    @Id
    String email;
    String first_name;
    String last_name;
    String password;
    String token;

//    @Override
//    public int compareTo(@NotNull User user) {
//        if(this.id.equals(user.id) && this.password.equals(user.password))
//        {
//            return 0;
//        }else {
//            return -1;
//        }
//    }
}


