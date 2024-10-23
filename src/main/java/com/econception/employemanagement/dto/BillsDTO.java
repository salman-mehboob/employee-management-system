package com.econception.employemanagement.dto;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import lombok.Data;

@Data
public class BillsDTO {
    private long employeeID;
    private long commentID;
    private long parentID;
    private String title;
    private String dateCreated;
    private double billAmount;
    private double approvedAmount;
    private String billDetail;
    private String billType;
    private String billFrequency;
    private long moduleId;
    private Long id;
    private ApprovedStatus status;
//    private String file;
    //private String image1;
}
