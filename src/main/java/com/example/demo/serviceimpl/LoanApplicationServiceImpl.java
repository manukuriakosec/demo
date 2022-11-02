package com.example.demo.serviceimpl;

import com.example.demo.configuration.PropertiesFileConfiguration;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.LoanApplicationRepository;
import com.example.demo.service.LoanApplicationService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    private final PropertiesFileConfiguration propertiesFileConfiguration;

    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, PropertiesFileConfiguration propertiesFileConfiguration) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.propertiesFileConfiguration = propertiesFileConfiguration;
    }


    @Override
    public LoanApplication save(LoanApplication loanApplication) {

        return loanApplicationRepository.save(loanApplication);

    }

    @Override
    public void validateAndSave(Customer customer, Loan loan) {
        // TODO: 02-11-2022  add validations
        //validateLoanRequest(customer,loan);
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setLoan(loan);
        loanApplication.setCustomer(customer);
        loanApplication.setLoanStatus(LoanStatus.INITIATED);
        loanApplication.setTransTime(new Timestamp(new Date().getTime()));
        loanApplication.setSanctioned(false);
        loanApplicationRepository.save(loanApplication);
    }

    @Override
    public List<LoanApplication> findAll() {
        return loanApplicationRepository.findAll();
    }

    @Override
    public List<LoanApplication> findByLoanStatus(LoanStatus initiated) throws ResourceNotFoundException {
        List<LoanApplication> list = loanApplicationRepository.findByLoanStatus(initiated);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Loan Application not found for loan status :: " + initiated);
        }
        return list;
    }

    @Override
    public LoanApplication findById(int id) throws ResourceNotFoundException {
        return loanApplicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan application not found for this id :: " + id));
    }

    @Override
    public void validate(LoanApplication loanApplication, Officer officer) {

        Customer customer = loanApplication.getCustomer();
        if (customer.isLiabilities() || customer.getAge() < propertiesFileConfiguration.getMinAge() || customer.getAge() > propertiesFileConfiguration.getMaxAge() ||
                customer.getIncome() < propertiesFileConfiguration.getIncome() || customer.getCibilScore() < propertiesFileConfiguration.getCibilScore()) {
            loanApplication.setLoanStatus(LoanStatus.REJECTED);
            loanApplication.setSanctioned(false);
        } else {
            loanApplication.setSanctioned(true);
            loanApplication.setLoanStatus(LoanStatus.APPROVED);
        }
        loanApplication.setOfficer(officer);
        loanApplicationRepository.save(loanApplication);

    }


    @Override
    public List<LoanApplication> findByCustomer(Customer customer) throws ResourceNotFoundException {
        List<LoanApplication> list = loanApplicationRepository.findByCustomer(customer);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Loan Application not found for this customer id :: " + customer.getId());
        }
        return list;
    }


}
