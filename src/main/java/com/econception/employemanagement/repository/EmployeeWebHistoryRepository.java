package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.EmployeeWebHistroy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeWebHistoryRepository extends JpaRepository<EmployeeWebHistroy, Long> {
    List<EmployeeWebHistroy> findAllById(Long id);
}
