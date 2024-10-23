package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.EmployeeDependants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDependantsRepository extends JpaRepository<EmployeeDependants, Long> {
    List<EmployeeDependants> findAllById(Long id);

    List<EmployeeDependants> findAllByEmployeeId(Long id);
}
