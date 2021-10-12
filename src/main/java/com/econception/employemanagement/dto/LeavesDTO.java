package com.econception.employemanagement.dto;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import lombok.Data;

import javax.persistence.Column;

@Data
public class LeavesDTO {

    private long employeeLeaveId;
    private long moduleId;
    private long commentId;
    private long employeeID;
    private String dateFrom;
    private String text;
    private long parentID;
    private long moduleParent;
    private Long leaveCatID;
    private ApprovedStatus status;
    private Long id;
    private String userRole;
    private String dateTo;
    private String dateCreated;
    private String datefilter;
}
