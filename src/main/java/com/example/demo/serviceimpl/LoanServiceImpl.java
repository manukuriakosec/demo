package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepository;
import com.example.demo.service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(int loanId) throws ResourceNotFoundException {
        return loanRepository.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id :: " + loanId));
    }

    @Override
    public Loan update(int loanId, Loan loan) throws ResourceNotFoundException {

        Loan loanFromDb = loanRepository.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id :: " + loanId));
        loanFromDb.setLoanAmt(loan.getLoanAmt());
        loanFromDb.setLoanType(loan.getLoanType());
        loanFromDb.setDuration(loan.getDuration());
        loanFromDb.setMonthlyEMI(loan.getMonthlyEMI());
        return loanRepository.save(loanFromDb);
    }

    @Override
    public void delete(int loanId) throws ResourceNotFoundException {
        Loan loanFromDb = loanRepository.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id :: " + loanId));
        loanRepository.delete(loanFromDb);
    }

    @Override
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

}
