package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Department;
import com.econception.employemanagement.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DepartmentServiceTest {

    @InjectMocks
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void save() {
        Department department = new Department();
        department.setId((long) 1);
        department.setName("JAVA TEAM");

        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        assertThat(departmentService.save(department)).isEqualTo(department);
    }

    @Test
    void findAll() {

        Department department1 = new Department();
        department1.setId((long) 1);
        department1.setName("JAVA TEAM");

        Department department2 = new Department();
        department2.setId((long) 2);
        department2.setName("React TEAM");

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        departmentList.add(department2);

        Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
        assertThat(departmentService.findAll()).isEqualTo(departmentList);

    }

    @Test
    void delete() {
        Department department = new Department();
        department.setId((long) 1);
        department.setName("JAVA TEAM");
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        boolean present1 = departmentRepository.findById((long) 1).isPresent();
        departmentRepository.deleteById((long) 1);
        boolean present2 = departmentRepository.findById((long) 1).isPresent();
        assertThat(present1).isEqualTo(present2);
    }
}