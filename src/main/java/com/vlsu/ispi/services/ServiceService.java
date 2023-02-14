package com.vlsu.ispi.services;

import com.vlsu.ispi.models.Role;
import com.vlsu.ispi.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
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

    public com.vlsu.ispi.models.Service findOne(int id) {
        com.vlsu.ispi.models.Service service = serviceRepository.findById(id);
        return service;
    }

    public List<com.vlsu.ispi.models.Service> getPage(int num){
        List<com.vlsu.ispi.models.Service> services = serviceRepository.findAll().stream().skip(num*9).limit(9).toList();
        return services;
    }

    @Transactional
    public void save(com.vlsu.ispi.models.Service service){
        serviceRepository.save(service);
    }

    @Transactional
    public void update(int id, com.vlsu.ispi.models.Service updatedService) {
        updatedService.setId(id);
        serviceRepository.save(updatedService);
    }

    @Transactional
    public void delete(int id) {
        serviceRepository.deleteById(id);
    }
}
