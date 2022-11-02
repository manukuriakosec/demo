package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Officer;

import java.util.List;

public interface OfficerService {
    List<Officer> findAll();

    Officer findById(int officerId) throws ResourceNotFoundException;

    Officer save(Officer officer);

    Officer findByFirstName(String userName) throws ResourceNotFoundException;


}
