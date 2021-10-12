package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByFirstName(String firstName);

    List<Employee> findAllByDepartmentId(Long id);

    Employee findOneById(Long a);
}
