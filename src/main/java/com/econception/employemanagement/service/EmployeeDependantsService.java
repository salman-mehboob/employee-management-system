package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.EmployeeDependants;
import com.econception.employemanagement.repository.EmployeeDependantsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDependantsService {

    private final Logger log = LoggerFactory.getLogger(EmployeeDependantsService.class);

    private final EmployeeDependantsRepository employeeDependantsRepository;

    public EmployeeDependantsService(EmployeeDependantsRepository employeeDependantsRepository) {
        this.employeeDependantsRepository = employeeDependantsRepository;
    }

    public EmployeeDependants save(EmployeeDependants employeeDependants) {
        log.debug("Request to save employee Dependant: {}", employeeDependants);
        return employeeDependantsRepository.save(employeeDependants);
    }

    public List<EmployeeDependants> findAll() {
        log.debug("Request to get all employee dependants");
        return employeeDependantsRepository.findAll();
    }

    public void delete(Long id) {
        log.debug("Request to delete employee dependant : {}", id);
        employeeDependantsRepository.deleteById(id);
    }
}
