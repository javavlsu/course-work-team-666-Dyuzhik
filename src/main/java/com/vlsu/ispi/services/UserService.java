package com.vlsu.ispi.services;

import com.vlsu.ispi.models.User;

public interface UserService {
    void save(User user);

    void update(int id, User user);

    User findByUsername(String username);
}