package com.econception.employemanagement.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
