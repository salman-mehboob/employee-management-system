package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.HistoryType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employeeWebHistroy")
@Data
public class EmployeeWebHistroy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeWebHistroy", allowSetters = true)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "title", length = 50, nullable = false)
    private HistoryType title;


    @Column(name = "dateCreated", length = 250)
    private String dataCreated;


}
