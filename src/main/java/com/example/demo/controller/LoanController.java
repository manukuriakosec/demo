package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Loan;
import com.example.demo.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loan")

public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable int id)
            throws ResourceNotFoundException {
        Loan loan = loanService.findById(id);
        return ResponseEntity.ok().body(loan);
    }

    @PostMapping()
    public Loan createLoan(@Valid @RequestBody Loan loan) {
        return loanService.save(loan);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable int id,
                                           @Valid @RequestBody Loan loan) throws ResourceNotFoundException {
        Loan updatedLoan = loanService.update(id, loan);

        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable int id)
            throws ResourceNotFoundException {

        loanService.delete(id);
        return ResponseEntity.ok("Loan with Id  " + id + " is deleted");
    }
}
