package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Employee;
import com.econception.employemanagement.domain.EmployeeDependants;
import com.econception.employemanagement.enumeration.DependantsRelation;
import com.econception.employemanagement.repository.EmployeeDependantsRepository;
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

import static com.econception.employemanagement.enumeration.DependantsRelation.WIFE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeDependantsServiceTest {
    @InjectMocks
    @Autowired
    private EmployeeDependantsService employeeDependantsService;
    @MockBean
    private EmployeeDependantsRepository employeeDependantsRepository;

    @Test
    void save() {

        EmployeeDependants employeeDependants = new EmployeeDependants();
        employeeDependants.setId((long) 1);
        employeeDependants.setFirstName("Aiza");
        employeeDependants.setLastName("Kaleem");
        DependantsRelation dependantsRelation = WIFE;
        employeeDependants.setDependantsRelation(dependantsRelation);
        Employee employee =new Employee();
        employee.setId((long) 2);
        employeeDependants.setEmployee(employee);


        Mockito.when(employeeDependantsRepository.save(employeeDependants)).thenReturn(employeeDependants);
        assertThat(employeeDependantsService.save(employeeDependants)).isEqualTo(employeeDependants);
    }

    @Test
    void findAll() {

        EmployeeDependants employeeDependants = new EmployeeDependants();
        employeeDependants.setId((long) 1);
        employeeDependants.setFirstName("Aiza");
        employeeDependants.setLastName("Kaleem");
        DependantsRelation dependantsRelation = WIFE;
        employeeDependants.setDependantsRelation(dependantsRelation);
        Employee employee =new Employee();
        employee.setId((long) 2);
        employeeDependants.setEmployee(employee);

        EmployeeDependants employeeDependants1 = new EmployeeDependants();
        employeeDependants1.setId((long) 2);
        employeeDependants1.setFirstName("Ayesha");
        employeeDependants1.setLastName("Kaleem");
        DependantsRelation dependantsRelation1 = WIFE;
        employeeDependants1.setDependantsRelation(dependantsRelation1);
        Employee employee1 =new Employee();
        employee.setId((long) 3);
        employeeDependants1.setEmployee(employee1);


        List<EmployeeDependants> employeeDependantsList = new ArrayList<>();
        employeeDependantsList.add(employeeDependants);
        employeeDependantsList.add(employeeDependants1);

        Mockito.when(employeeDependantsRepository.findAll()).thenReturn(employeeDependantsList);
        assertThat(employeeDependantsService.findAll()).isEqualTo(employeeDependantsList);

    }

    @Test
    void delete() {
        EmployeeDependants employeeDependants = new EmployeeDependants();
        employeeDependants.setId((long) 1);
        employeeDependants.setFirstName("Aiza");
        employeeDependants.setLastName("Kaleem");
        DependantsRelation dependantsRelation = WIFE;
        employeeDependants.setDependantsRelation(dependantsRelation);
        Employee employee =new Employee();
        employee.setId((long) 2);
        employeeDependants.setEmployee(employee);

        Mockito.when(employeeDependantsRepository.save(employeeDependants)).thenReturn(employeeDependants);
        boolean present1 = employeeDependantsRepository.findById((long) 1).isPresent();
        employeeDependantsRepository.deleteById((long) 1);
        boolean present2 = employeeDependantsRepository.findById((long) 1).isPresent();
        assertThat(present1).isEqualTo(present2);
    }

}
