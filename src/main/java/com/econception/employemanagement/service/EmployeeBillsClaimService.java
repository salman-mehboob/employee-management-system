package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.EmployeeBillsClaim;
import com.econception.employemanagement.repository.EmployeeBillsClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeBillsClaimService {

    private final Logger log = LoggerFactory.getLogger(EmployeeBillsClaimService.class);

    private final EmployeeBillsClaimRepository employeeBillsClaimRepository;

    public EmployeeBillsClaimService(EmployeeBillsClaimRepository employeeBillsClaimRepository) {
        this.employeeBillsClaimRepository = employeeBillsClaimRepository;
    }


    public EmployeeBillsClaim save(EmployeeBillsClaim employeeBillsClaim) {
        log.debug("Request to save employeeBillsClaim : {}", employeeBillsClaim);
        return employeeBillsClaimRepository.save(employeeBillsClaim);
    }

    public List<EmployeeBillsClaim> findAll() {
        log.debug("Request to get all employeeBills");
        return employeeBillsClaimRepository.findAll();
    }

    public void delete(Long id) {
        log.debug("Request to delete employeeBill : {}", id);
        employeeBillsClaimRepository.deleteById(id);
    }

    public EmployeeBillsClaim findById(long id) {
        Optional<EmployeeBillsClaim> employeeBillsClaim = employeeBillsClaimRepository.findById(id);
        if (employeeBillsClaim.isPresent()) {
            return employeeBillsClaim.get();
        }
        return null;
    }
}
