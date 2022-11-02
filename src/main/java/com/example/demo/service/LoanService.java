package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> findAll();

    Loan findById(int loanId) throws ResourceNotFoundException;

    Loan update(int loanId, Loan loan) throws ResourceNotFoundException;

    void delete(int loanId) throws ResourceNotFoundException;

    Loan save(Loan loan);

}
