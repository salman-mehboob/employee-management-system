package com.econception.employemanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "leavesCategories")
public class LeavesCategories implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leaveType")
    private String leaveType;

    @Column(name = "quota")
    private String quota;

}
