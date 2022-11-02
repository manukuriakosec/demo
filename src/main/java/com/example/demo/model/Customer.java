package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)

    private String firstName;
    private String lastName;
    private String gender;
    private long phone;
    private String email;
    private float salary;
    private long aadhaar;
    private String pan;
    private double walletAmt;

    private int age;
    private int income;
    private int cibilScore;
    private boolean liabilities;


    public Customer() {

    }

    public Customer(String firstName, String lastName, String gender, long phone, String email, float salary, long aadhaar, String pan, double walletAmt, int age, int income, int cibilScore, boolean liabilities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.aadhaar = aadhaar;
        this.pan = pan;
        this.walletAmt = walletAmt;
        this.age = age;
        this.income = income;
        this.cibilScore = cibilScore;
        this.liabilities = liabilities;
    }


}
