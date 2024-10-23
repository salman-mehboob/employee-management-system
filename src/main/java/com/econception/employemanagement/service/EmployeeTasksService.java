package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.EmployeeTasks;
import com.econception.employemanagement.repository.EmployeeTasksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTasksService {

    private final Logger log = LoggerFactory.getLogger(EmployeeTasksService.class);

    private final EmployeeTasksRepository employeeTasksRepository;

    public EmployeeTasksService(EmployeeTasksRepository employeeTasksRepository) {
        this.employeeTasksRepository = employeeTasksRepository;
    }


    public EmployeeTasks save(EmployeeTasks employeeTasks) {
        log.debug("Request to save employeeTasks : {}", employeeTasks);
        return employeeTasksRepository.save(employeeTasks);
    }

    public List<EmployeeTasks> findAll() {
        log.debug("Request to get all employeeTasks");
        return employeeTasksRepository.findAll();
    }

    public void delete(Long id) {
        log.debug("Request to delete employee task: {}", id);
        employeeTasksRepository.deleteById(id);
    }
}
