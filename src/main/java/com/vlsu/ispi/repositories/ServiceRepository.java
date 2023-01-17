package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findById(int id);
}
