package com.vlsu.ispi.services;

import org.springframework.stereotype.Component;

@Component
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
