package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String loanName;
    private double loanAmt;
    private LoanType loanType;
    private int duration;
    private double monthlyEMI;


    public Loan() {

    }

    public Loan(String loanName, double loanAmt, LoanType loanType, int duration, double monthlyEMI) {
        this.loanName = loanName;
        this.loanAmt = loanAmt;
        this.loanType = loanType;
        this.duration = duration;
        this.monthlyEMI = monthlyEMI;
    }


}
