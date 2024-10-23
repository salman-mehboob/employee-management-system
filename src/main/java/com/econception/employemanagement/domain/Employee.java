package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;


    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;


    @Size(max = 50)
    @Column(name = "gender", length = 50, nullable = false)
    private String gender;


    @Size(max = 15)
    @Column(name = "secondryContactNumber", length = 150, nullable = false)
    private String secondryContactNumber;

    @Size(max = 15)
    @Column(name = "primaryContactNumber", length = 150, nullable = false)
    private String primaryContactNumber;


    @Column(name = "cnic", length = 100, nullable = false)
    private String cnic;


    @Size(max = 200)
    @Column(name = "address", length = 200, nullable = false)
    private String address;


    @Size(max = 200)
    @Column(name = "line_2", length = 200, nullable = false)
    private String line2;


    @Size(max = 100)
    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "workEmail", length = 250)
    private String workEmail;

    @Column(name = "personalEmail", length = 250)
    private String personalEmail;

    @Column(name = "skype", length = 250)
    private String skype;

    @Column(name = "linkedin", length = 250)
    private String linkedin;

    @Column(name = "status", length = 250)
    private Status status;

    @Column(name = "dateCreated", length = 250)
    private String dataCreated;

    @Column(name = "designation")
    private String designation;

    @Column(name = "photo")
    private String photo;


    @Transient
    public String getImagePath() {
        if (photo == null) return null;


        return "/upload-photos/" + photo;


    }


    @ManyToOne
    @JsonIgnoreProperties(value = "employee", allowSetters = true)
    private Department department;

    public String joined() {
        return firstName + " " + middleName + " " + lastName;
    }


}
