package com.econception.employemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "employeeTasks")
@Data

public class EmployeeTasks implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "employeeTasks", allowSetters = true)
    private Employee employee;


    @Size(max = 300)
    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "dateDue")
    private String dateDue;

    @Column(name = "dateSubmitted")
    private String dateSubmitted;

    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;

    @Column(name = "Status")
    private String status;


}
