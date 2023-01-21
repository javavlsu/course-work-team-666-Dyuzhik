package com.vlsu.ispi.services;

import com.vlsu.ispi.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<com.vlsu.ispi.models.Service> findAll() {
        return serviceRepository.findAll();
    }

    public List<com.vlsu.ispi.models.Service> getPage(int num){
        List<com.vlsu.ispi.models.Service> services = serviceRepository.findAll().stream().skip(num*9).limit(9).toList();
        return services;
    }


}
