package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.ModuleTitles;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "module")
@Data
public class Module implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "parent")
    private Long parent;

}
