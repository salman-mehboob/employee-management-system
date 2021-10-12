package com.econception.employemanagement.service;

import com.econception.employemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
    @InjectMocks
    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;
//
//    @Test
//    void save() {
//
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employee.setFirstName("salman");
//        employee.setLastName("Mehboob");
//        employee.setMiddleName("");
//        employee.setGender("Male");
//        employee.setContactNo("03085139071");
//        employee.setCnic("37237137813691");
//        employee.setAddress("house 737");
//        employee.setLine2("");
//        employee.setCity("Multan");
//        employee.setDOB("java.time.LocalDate.now()");
//        //employee.setEmail("salmanmehboob60@gmail.com");
//        employee.setPicture("image1.png");
//        employee.setResume("resume.pdf");
//        Department department = new Department();
//        department.setId((long) 1);
//        employee.setDepartment(department);
//
//
//
//
//        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
//        assertThat(employeeService.save(employee)).isEqualTo(employee);
//    }
//
//    @Test
//    void findAll() {
//
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employee.setFirstName("salman");
//        employee.setLastName("Mehboob");
//        employee.setMiddleName("");
//        employee.setGender("Male");
//        employee.setContactNo("03085139071");
//        employee.setCnic("37237137813691");
//        employee.setAddress("house 737");
//        employee.setLine2("");
//        employee.setCity("Multan");
//        employee.setDOB("java.time.LocalDate.now()");
//      //  employee.setEmail("salmanmehboob60@gmail.com");
//        employee.setPicture("image1.png");
//        employee.setResume("resume.pdf");
//        Department department = new Department();
//        department.setId((long) 1);
//        employee.setDepartment(department);
//
//        Employee employee1 = new Employee();
//        employee1.setId((long) 2);
//        employee1.setFirstName("salman");
//        employee1.setLastName("Mehboob");
//        employee1.setMiddleName("");
//        employee1.setGender("Male");
//        employee1.setContactNo("03085139071");
//        employee1.setCnic("37237137813691");
//        employee1.setAddress("house 737");
//        employee1.setLine2("");
//        employee1.setCity("Multan");
//        employee1.setDOB("java.time.LocalDate.now()");
//        //employee1.setEmail("salmanmehboob60@gmail.com");
//        employee1.setPicture("image1.png");
//        employee1.setResume("resume.pdf");
//        Department department1 = new Department();
//        department.setId((long) 1);
//        employee1.setDepartment(department1);
//
//
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(employee);
//        employeeList.add(employee1);
//
//        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
//        assertThat(employeeService.findAll()).isEqualTo(employeeList);
//
//    }
//
//    @Test
//    void delete() {
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employee.setFirstName("salman");
//        employee.setLastName("Mehboob");
//        employee.setMiddleName("");
//        employee.setGender("Male");
//        employee.setContactNo("03085139071");
//        employee.setCnic("37237137813691");
//        employee.setAddress("house 737");
//        employee.setLine2("");
//        employee.setCity("Multan");
//        employee.setDOB("java.time.LocalDate.now()");
//        //employee.setEmail("salmanmehboob60@gmail.com");
//        employee.setPicture("image1.png");
//        employee.setResume("resume.pdf");
//        Department department = new Department();
//        department.setId((long) 1);
//        employee.setDepartment(department);
//
//        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
//        boolean present1 = employeeRepository.findById((long) 1).isPresent();
//        employeeRepository.deleteById((long) 1);
//        boolean present2 = employeeRepository.findById((long) 1).isPresent();
//        assertThat(present1).isEqualTo(present2);
//    }
}
