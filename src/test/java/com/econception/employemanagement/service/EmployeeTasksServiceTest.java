package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Department;
import com.econception.employemanagement.domain.Employee;
import com.econception.employemanagement.domain.EmployeeTasks;
import com.econception.employemanagement.repository.EmployeeRepository;
import com.econception.employemanagement.repository.EmployeeTasksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeTasksServiceTest {

    @InjectMocks
    @Autowired
    private EmployeeTasksService employeeTasksService;
    @MockBean
    private EmployeeTasksRepository employeeTasksRepository;

//    @Test
//    void save() {
//
//        EmployeeTasks employeeTasks = new EmployeeTasks();
//        employeeTasks.setId((long) 1);
//        employeeTasks.setDescription("do me tonight");
//        employeeTasks.setDateDue(java.time.LocalDate.now());
//        employeeTasks.setDateSubmitted(java.time.LocalDate.now());
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employeeTasks.setEmployee(employee);
//
//
//        Mockito.when(employeeTasksRepository.save(employeeTasks)).thenReturn(employeeTasks);
//        assertThat(employeeTasksService.save(employeeTasks)).isEqualTo(employeeTasks);
//    }

//    @Test
//    void findAll() {
//
//        EmployeeTasks employeeTasks = new EmployeeTasks();
//        employeeTasks.setId((long) 1);
//        employeeTasks.setDescription("do me tonight");
//        employeeTasks.setDateDue(java.time.LocalDate.now());
//        employeeTasks.setDateSubmitted(java.time.LocalDate.now());
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employeeTasks.setEmployee(employee);
//
//        EmployeeTasks employeeTasks1 = new EmployeeTasks();
//        employeeTasks1.setId((long) 2);
//        employeeTasks1.setDescription("do me tonight");
//        employeeTasks1.setDateDue(java.time.LocalDate.now());
//        employeeTasks1.setDateSubmitted(java.time.LocalDate.now());
//        Employee employee1 = new Employee();
//        employee.setId((long) 1);
//        employeeTasks1.setEmployee(employee1);
//
//
//        List<EmployeeTasks> employeeTasksList = new ArrayList<>();
//        employeeTasksList.add(employeeTasks);
//        employeeTasksList.add(employeeTasks1);
//
//        Mockito.when(employeeTasksRepository.findAll()).thenReturn(employeeTasksList);
//        assertThat(employeeTasksService.findAll()).isEqualTo(employeeTasksList);
//
//    }
//
//    @Test
//    void delete() {
//        EmployeeTasks employeeTasks = new EmployeeTasks();
//        employeeTasks.setId((long) 1);
//        employeeTasks.setDescription("do me tonight");
//        employeeTasks.setDateDue(java.time.LocalDate.now());
//        employeeTasks.setDateSubmitted(java.time.LocalDate.now());
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        employeeTasks.setEmployee(employee);
//
//        Mockito.when(employeeTasksRepository.save(employeeTasks)).thenReturn(employeeTasks);
//        boolean present1 = employeeTasksRepository.findById((long) 1).isPresent();
//        employeeTasksRepository.deleteById((long) 1);
//        boolean present2 = employeeTasksRepository.findById((long) 1).isPresent();
//        assertThat(present1).isEqualTo(present2);
//    }
}
