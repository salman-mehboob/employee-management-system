package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Department;
import com.econception.employemanagement.domain.EmployeeBillsClaim;
import com.econception.employemanagement.domain.Files;
import com.econception.employemanagement.repository.FilesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilesServics {
    @Autowired
    FilesRepository filesRepository;

    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    public Files save(Files files) {
        log.debug("Request to save department : {}", files);
        return filesRepository.save(files);
    }

    public Files findById(long id) {
        Optional<Files> files = filesRepository.findById(id);
        if (files.isPresent()) {
            return files.get();
        }
        return null;
    }
}
