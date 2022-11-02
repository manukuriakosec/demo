package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customeRepository;

    public CustomerServiceImpl(CustomerRepository customeRepository) {
        this.customeRepository = customeRepository;
    }


    @Override
    public List<Customer> findAll() {
        return customeRepository.findAll();
    }

    @Override
    public Customer findById(int customerId) throws ResourceNotFoundException {
        return customeRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
    }

    @Override
    public Customer save(Customer customer) {
        return customeRepository.save(customer);
    }

}
