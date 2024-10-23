package com.econception.employemanagement.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    private Employee employee;


    @Column(name = "text")
    private String text;

    @Column(name = "dateCreated")
    private String dateCreated;

    @Column(name = "parent")
    private Long parent;

    @ManyToOne
    @JsonIgnoreProperties(value = "module", allowSetters = true)
    private Module module;


}
