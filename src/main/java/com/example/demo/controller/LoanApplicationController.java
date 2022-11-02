package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Loan;
import com.example.demo.model.LoanApplication;
import com.example.demo.pojo.LoanApplicationPojo;
import com.example.demo.service.CustomerService;
import com.example.demo.service.LoanApplicationService;
import com.example.demo.service.LoanService;
import com.example.demo.service.OfficerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanApplicationController {

    private final LoanService loanService;
    private final CustomerService customerService;
    private final OfficerService officerService;
    private final LoanApplicationService loanApplicationService;


    public LoanApplicationController(LoanService loanService, CustomerService customerService, OfficerService officerService, LoanApplicationService loanApplicationService) {
        this.loanService = loanService;
        this.customerService = customerService;
        this.officerService = officerService;
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LoanApplication>> getLoanApplicationByCustomerId(@PathVariable int id)
            throws ResourceNotFoundException {
        Customer customer = customerService.findById(id);
        System.out.println(customer.getId());
        List<LoanApplication> loanApplications = loanApplicationService.findByCustomer(customer);
        return ResponseEntity.ok().body(loanApplications);
    }

    @PostMapping("/apply")
    public ResponseEntity<String> createLoanApplication(@Valid @RequestBody LoanApplicationPojo loanApplicationPojo) throws ResourceNotFoundException {
        Customer customer = customerService.findById(loanApplicationPojo.getCustomerId());
        Loan loan = loanService.findById(loanApplicationPojo.getLoanId());
        loanApplicationService.validateAndSave(customer, loan);
        return ResponseEntity.ok("Loan request created");
    }


}
