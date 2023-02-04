package com.vlsu.ispi.services;

import com.vlsu.ispi.models.Status;
import com.vlsu.ispi.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public Status findOne(int id) {
        Status status = statusRepository.findById(id);
        return status;
    }
}
