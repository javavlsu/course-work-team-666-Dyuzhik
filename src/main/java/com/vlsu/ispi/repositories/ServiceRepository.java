package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.Service;
import com.vlsu.ispi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    User findByName(String name);
    User findById(int id);
}
