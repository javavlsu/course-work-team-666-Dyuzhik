package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findById(int id);
}
