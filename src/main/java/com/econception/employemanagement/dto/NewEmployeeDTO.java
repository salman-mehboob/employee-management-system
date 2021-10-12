package com.econception.employemanagement.dto;

import com.econception.employemanagement.enumeration.Status;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewEmployeeDTO {
    private long id;
    private String active1;
    private String photo;
    private Long departmentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String cnic;
    private String email;
    private String address;
    private String line2;
    private String city;
    private String DOB;
    private String resume;
    private String picture;
    private MultipartFile multipartFile;
    private String username;
    private String password;
    private Boolean active;
    private String message;
    private String secondryContactNumber;
    private String primaryContactNumber;
    private String workEmail;
    private String personalEmail;
    private String skype;
    private String linkedin;
    private Status status;
    private String dataCreated;
    private String role;
    private String designation;
    private Long EmpID;

}
