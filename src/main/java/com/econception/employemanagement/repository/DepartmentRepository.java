package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.Department;
import org.mockito.InjectMocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllById(Long id);
}
