package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
    Role findById(int id);
}

