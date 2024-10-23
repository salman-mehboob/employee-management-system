package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {
    List<Files> findAllById(Long id);

    Files findByParent(Long id);

    List<Files> findAllByParent(long a);

    List<Files> findByParentAndEmployeeId(long a, long b);

    Long countAllByParent(long a);

}
