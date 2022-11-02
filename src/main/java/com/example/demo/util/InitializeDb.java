package com.example.demo.util;

import com.example.demo.model.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.LoanApplicationService;
import com.example.demo.service.LoanService;
import com.example.demo.service.OfficerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class InitializeDb {

    private final LoanService loanService;
    private final CustomerService customerService;
    private final OfficerService officerService;
    private final LoanApplicationService loanApplicationService;

    public InitializeDb(LoanService loanService, CustomerService customerService, OfficerService officerService, LoanApplicationService loanApplicationService) {
        this.loanService = loanService;
        this.customerService = customerService;
        this.officerService = officerService;
        this.loanApplicationService = loanApplicationService;
    }


    @PostConstruct
    private void addDataToDB() {
        addCustomer();
        addLoan();
        addOfficer();
        addLoanApplication();
    }

    private void addLoanApplication() {
        LoanApplication loanApplication = new LoanApplication();
        List<Customer> customerList = customerService.findAll();
        List<Loan> loanList = loanService.findAll();
        List<Officer> officerList = officerService.findAll();
        loanApplication.setCustomer(customerList.get(0));
        loanApplication.setLoan(loanList.get(0));
        loanApplication.setOfficer(officerList.get(0));
        loanApplication.setLoanStatus(LoanStatus.REJECTED);
        loanApplication.setTransTime(new Timestamp(new Date().getTime()));
        loanApplication.setSanctioned(false);
        try {
            loanApplicationService.save(loanApplication);

        } catch (Exception e) {

        }

    }

    private void addOfficer() {

        Officer officer = new Officer("officer", "kuriakose", "male", 9745727559l, "manukuriakosec@gmail.com");
        try {
            officerService.save(officer);

        } catch (Exception e) {

        }

    }

    private void addLoan() {
        Loan loan = new Loan("A", 100000, LoanType.HOME, 20, 15000);
        try {
            loanService.save(loan);

        } catch (Exception e) {

        }

    }

    private void addCustomer() {

        Customer customer = new Customer("manu", "kuriakose", "male", 9745727559l, "manukuriakosec@gmail.com", 1200000, 123456789, "ayq2376hg", 3222222, 30, 100000, 600, false);

        try {
            customerService.save(customer);

        } catch (Exception e) {

        }
    }
}
