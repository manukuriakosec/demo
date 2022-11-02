package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;

import java.util.List;

public interface LoanApplicationService {
    LoanApplication save(LoanApplication loanApplication);

    List<LoanApplication> findByCustomer(Customer customer) throws ResourceNotFoundException;

    void validateAndSave(Customer customer, Loan loan);

    List<LoanApplication> findAll();

    List<LoanApplication> findByLoanStatus(LoanStatus initiated) throws ResourceNotFoundException;

    LoanApplication findById(int id) throws ResourceNotFoundException;

    void validate(LoanApplication loanApplication, Officer officer);


}
