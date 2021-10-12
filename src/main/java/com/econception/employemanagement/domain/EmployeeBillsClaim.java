package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.enumeration.BillFrequency;
import com.econception.employemanagement.enumeration.BillType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employeeBillsClaim")
@Data
public class EmployeeBillsClaim implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeBillsClaim", allowSetters = true)
    private Employee employee;


    @Column(name = "billType")
    private String billType;


    @Column(name = "billFrequency")
    private String billFrequency;

    @Column(name = "title")
    private String title;


    @Column(name = "dateCreated")
    private String dateCreated;

    @Column(name = "amount")
    private double amount;


    @Column(name = "approvedAmount")
    private double approvedAmount;

    @Column(name = "approvedBy")
    private Long approvedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApprovedStatus status;


}
