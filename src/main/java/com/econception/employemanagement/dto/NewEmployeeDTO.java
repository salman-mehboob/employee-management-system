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

    public NewEmployeeDTO(long id, String active1, String photo, Long departmentId, String firstName, String middleName, String lastName, String gender, String cnic, String email, String address, String line2, String city, String DOB, String resume, String picture, MultipartFile multipartFile, String username, String password, Boolean active, String message, String secondryContactNumber, String primaryContactNumber, String workEmail, String personalEmail, String skype, String linkedin, Status status, String dataCreated, String role, String designation, Long empID) {
        this.id = id;
        this.active1 = active1;
        this.photo = photo;
        this.departmentId = departmentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.cnic = cnic;
        this.email = email;
        this.address = address;
        this.line2 = line2;
        this.city = city;
        this.DOB = DOB;
        this.resume = resume;
        this.picture = picture;
        this.multipartFile = multipartFile;
        this.username = username;
        this.password = password;
        this.active = active;
        this.message = message;
        this.secondryContactNumber = secondryContactNumber;
        this.primaryContactNumber = primaryContactNumber;
        this.workEmail = workEmail;
        this.personalEmail = personalEmail;
        this.skype = skype;
        this.linkedin = linkedin;
        this.status = status;
        this.dataCreated = dataCreated;
        this.role = role;
        this.designation = designation;
        EmpID = empID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActive1() {
        return active1;
    }

    public void setActive1(String active1) {
        this.active1 = active1;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSecondryContactNumber() {
        return secondryContactNumber;
    }

    public void setSecondryContactNumber(String secondryContactNumber) {
        this.secondryContactNumber = secondryContactNumber;
    }

    public String getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(String primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(String dataCreated) {
        this.dataCreated = dataCreated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long empID) {
        EmpID = empID;
    }
}
