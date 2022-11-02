package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.LoanApplication;
import com.example.demo.model.LoanStatus;
import com.example.demo.model.Officer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.LoanApplicationService;
import com.example.demo.service.LoanService;
import com.example.demo.service.OfficerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class LoanApplicationValidationController {

    private final LoanService loanService;
    private final CustomerService customerService;
    private final OfficerService officerService;
    private final LoanApplicationService loanApplicationService;


    public LoanApplicationValidationController(LoanService loanService, CustomerService customerService, OfficerService officerService, LoanApplicationService loanApplicationService) {
        this.loanService = loanService;
        this.customerService = customerService;
        this.officerService = officerService;
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping()
    public List<LoanApplication> getAllLoansApplications() {
        return loanApplicationService.findAll();
    }

    @GetMapping("/new")
    public List<LoanApplication> getAllNewLoansApplications() throws ResourceNotFoundException {
        return loanApplicationService.findByLoanStatus(LoanStatus.INITIATED);
    }

    @GetMapping("/{id}/validate")
    public ResponseEntity<String> loansApplicationValidate(@PathVariable int id)
            throws ResourceNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        LoanApplication loanApplication = loanApplicationService.findById(id);
        Officer officer = officerService.findByFirstName(userName);

        loanApplicationService.validate(loanApplication, officer);

        return ResponseEntity.ok("Success");
    }


}
