package com.econception.employemanagement.domain;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "approvalStatus")
    private ApprovedStatus status;


    @JsonIgnore
    @Column(name = "username", length = 60, nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "passowrd", length = 60, nullable = false)
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "role_name")
    private String roleName;


    @ManyToOne
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Employee employee;

}
