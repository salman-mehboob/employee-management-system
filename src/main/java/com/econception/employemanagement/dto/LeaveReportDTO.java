package com.econception.employemanagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveReportDTO {
    String id;
    String fromDate;
    String toDate;
   String empId;
}
