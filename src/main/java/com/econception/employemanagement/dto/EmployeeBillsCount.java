package com.econception.employemanagement.dto;


import org.springframework.stereotype.Component;


public interface EmployeeBillsCount {

    String getBillType();
    String getBillStatus();
    Long getBillCount();
    double getClaimAmount();
    double getApprovedAmount();

}
