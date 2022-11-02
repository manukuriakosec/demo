package com.example.demo.configuration;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LoanApplicationRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.OfficerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.LoanApplicationService;
import com.example.demo.service.LoanService;
import com.example.demo.service.OfficerService;
import com.example.demo.serviceimpl.CustomerServiceImpl;
import com.example.demo.serviceimpl.LoanApplicationServiceImpl;
import com.example.demo.serviceimpl.LoanServiceImpl;
import com.example.demo.serviceimpl.OfficerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public LoanService loanService(LoanRepository loanRepository) {
        return new LoanServiceImpl(loanRepository);
    }

    @Bean
    public CustomerService CustomerService(CustomerRepository customerRepository) {
        return new CustomerServiceImpl(customerRepository);
    }

    @Bean
    public OfficerService officerService(OfficerRepository officerRepository) {
        return new OfficerServiceImpl(officerRepository);
    }

    @Bean
    public LoanApplicationService loanApplicationService(LoanApplicationRepository loanApplicationRepository, PropertiesFileConfiguration propertiesFileConfiguration) {
        return new LoanApplicationServiceImpl(loanApplicationRepository, propertiesFileConfiguration);
    }


}

