package com.econception.employemanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "files")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "files", allowSetters = true)
    private Employee employee;

    @Column(name = "parent")
    private Long parent;

    @ManyToOne
    @JsonIgnoreProperties(value = "files", allowSetters = true)
    private Module module;

    @Column(name = "file")
    private String file;

    @Transient
    public String getPhotosImagePath() {
        if (file == null) return null;

        return "/bill-photos/" + file;
    }
}
