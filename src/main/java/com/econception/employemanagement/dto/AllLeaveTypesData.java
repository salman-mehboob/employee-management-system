package com.econception.employemanagement.dto;

import lombok.Data;

@Data
public class AllLeaveTypesData {
    String leaveType;
    int approved,pending,rejected;
    int total;
    int pro;

    public AllLeaveTypesData(String leaveType, int approved, int pending, int rejected, int total, int pro) {
        this.leaveType = leaveType;
        this.approved = approved;
        this.pending = pending;
        this.rejected = rejected;
        this.total = total;
        this.pro = pro;
    }
}
