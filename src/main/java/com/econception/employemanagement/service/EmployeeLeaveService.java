package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Employee;
import com.econception.employemanagement.domain.EmployeeLeaves;
import com.econception.employemanagement.repository.EmployeeLeavesRepository;
import com.econception.employemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLeaveService {

    @Autowired
    EmployeeLeavesRepository employeeLeavesRepository;

    public EmployeeLeaves save(EmployeeLeaves employee) {

        return employeeLeavesRepository.save(employee);
    }
}
