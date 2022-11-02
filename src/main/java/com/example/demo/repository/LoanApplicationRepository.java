package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.LoanApplication;
import com.example.demo.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {

    List<LoanApplication> findByCustomer(Customer customer);

    List<LoanApplication> findByLoanStatus(LoanStatus initiated);
}
