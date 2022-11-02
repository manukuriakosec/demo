package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Officer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String firstName;
    private String lastName;
    private String gender;
    private long phone;
    private String email;


    public Officer() {

    }

    public Officer(String firstName, String lastName, String gender, long phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }


}
