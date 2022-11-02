package com.example.demo.serviceimpl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Officer;
import com.example.demo.repository.OfficerRepository;
import com.example.demo.service.OfficerService;

import java.util.List;

public class OfficerServiceImpl implements OfficerService {

    private final OfficerRepository officerRepository;

    public OfficerServiceImpl(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }


    @Override
    public List<Officer> findAll() {
        return officerRepository.findAll();
    }

    @Override
    public Officer findById(int officerId) throws ResourceNotFoundException {
        return officerRepository.findById(officerId).orElseThrow(() -> new ResourceNotFoundException("Officer not found for this id :: " + officerId));
    }

    @Override
    public Officer save(Officer officer) {
        return officerRepository.save(officer);
    }

    @Override
    public Officer findByFirstName(String userName) throws ResourceNotFoundException {
        return officerRepository.findByFirstName(userName).orElseThrow(() -> new ResourceNotFoundException("Officer not found for this name :: " + userName));

    }


}
