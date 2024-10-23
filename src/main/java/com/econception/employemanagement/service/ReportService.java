package com.econception.employemanagement.service;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.repository.EmployeeBillsClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    EmployeeBillsClaimRepository employeeBillsClaimRepository;

    public Map<String, Long> getUserBillCounts(long empId){
        Map<String,Long> res = new HashMap<>();
        res.put("TotalBills", employeeBillsClaimRepository.countAllByEmployeeId(empId));
        res.put("pendingBills", employeeBillsClaimRepository.countAllByStatusAndEmployeeId(ApprovedStatus.PENDING, empId));
        res.put("rejectedBills", employeeBillsClaimRepository.countAllByStatusAndEmployeeId(ApprovedStatus.REJECTED, empId));
        res.put("approvedBills", employeeBillsClaimRepository.countAllByStatusAndEmployeeId(ApprovedStatus.APPROVED, empId));
        res.put("totalProcessedBills", res.get("rejectedBills") + res.get("approvedBills"));

        return res;
    }

    public Map<String, Long> getBillCounts(){

        Map<String,Long> res = new HashMap<>();
        res.put("TotalBills", employeeBillsClaimRepository.count());
        res.put("pendingBills", employeeBillsClaimRepository.countAllByStatus(ApprovedStatus.PENDING));
        res.put("rejectedBills", employeeBillsClaimRepository.countAllByStatus(ApprovedStatus.REJECTED));
        res.put("approvedBills", employeeBillsClaimRepository.countAllByStatus(ApprovedStatus.APPROVED));
        res.put("totalProcessedBills", res.get("rejectedBills") + res.get("approvedBills"));

        return res;
    }
}
