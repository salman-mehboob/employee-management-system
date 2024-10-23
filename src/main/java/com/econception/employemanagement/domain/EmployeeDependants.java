package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.DependantsRelation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "employeeDependants")
@Data
public class EmployeeDependants implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 50)
    @Column(name = "DOB", length = 50)
    private String DOB;

    @Column(name = "dateCreated", length = 250)
    private String dataCreated;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation")
    private DependantsRelation dependantsRelation;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeDependants", allowSetters = true)
    private Employee employee;


}
