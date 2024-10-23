package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "employeeLeaves")
public class EmployeeLeaves implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeLeaves", allowSetters = true)
    private Employee employee;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeLeaves", allowSetters = true)
    private LeavesCategories leavesCategories;

//    @ManyToOne
//    @JsonIgnoreProperties(value = "employeeLeaves", allowSetters = true)
//    private Comments comments;


    @Column(name = "dateFrom")
    private String dateFrom;


    @Column(name = "dateTo")
    private String dateTo;

    @Column(name = "dateCreated")
    private String dateCreated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApprovedStatus status;


    @Column(name = "approvedBy")
    private Long approvedBy;
}
