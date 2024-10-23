package com.econception.employemanagement.service;

import com.econception.employemanagement.repository.EmployeeBillsClaimRepository;
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
public class EmployeeBillsClaimServiceTest {
    @InjectMocks
    @Autowired
    private EmployeeBillsClaimService employeeBillsClaimService;
    @MockBean
    private EmployeeBillsClaimRepository employeeBillsClaimRepository;

//    @Test
//    void save() {
//        EmployeeBillsClaim employeeBillsClaim = new EmployeeBillsClaim();
//        Employee employee = new Employee();
//        employeeBillsClaim.setId((long) 1);
//        BillFrequency billFrequency = MONTHLY;
//        employeeBillsClaim.setBillFrequency(billFrequency);
//        employeeBillsClaim.setBillPicture("image1");
//        BillType billType = TRAVEL;
//        employeeBillsClaim.setBillType(billType);
//        employee.setId((long)2);
//        employeeBillsClaim.setEmployee(employee);
//
//
//        Mockito.when(employeeBillsClaimRepository.save(employeeBillsClaim)).thenReturn(employeeBillsClaim);
//        assertThat(employeeBillsClaimService.save(employeeBillsClaim)).isEqualTo(employeeBillsClaim);
//    }
//
//    @Test
//    void findAll() {
//
//        EmployeeBillsClaim employeeBillsClaim1 = new EmployeeBillsClaim();
//        employeeBillsClaim1.setId((long) 1);
//        BillFrequency billFrequency = MONTHLY;
//        employeeBillsClaim1.setBillFrequency(billFrequency);
//        employeeBillsClaim1.setBillPicture("image1");
//        BillType billType = TRAVEL;
//        employeeBillsClaim1.setBillType(billType);
//
//        EmployeeBillsClaim employeeBillsClaim = new EmployeeBillsClaim();
//        employeeBillsClaim.setId((long) 2);
//        BillFrequency billFrequency1 = MONTHLY;
//        employeeBillsClaim.setBillFrequency(billFrequency1);
//        employeeBillsClaim.setBillPicture("image2");
//        BillType billType1 = TRAVEL;
//        employeeBillsClaim.setBillType(billType1);
//
//        List<EmployeeBillsClaim> employeeBillsClaimList = new ArrayList<>();
//       employeeBillsClaimList.add(employeeBillsClaim);
//       employeeBillsClaimList.add(employeeBillsClaim1);
//
//        Mockito.when(employeeBillsClaimRepository.findAll()).thenReturn(employeeBillsClaimList);
//        assertThat(employeeBillsClaimService.findAll()).isEqualTo(employeeBillsClaimList);
//
//    }
//
//    @Test
//    void delete() {
//        EmployeeBillsClaim employeeBillsClaim = new EmployeeBillsClaim();
//        employeeBillsClaim.setId((long) 1);
//        BillFrequency billFrequency = MONTHLY;
//        employeeBillsClaim.setBillFrequency(billFrequency);
//        employeeBillsClaim.setBillPicture("image1");
//        BillType billType = TRAVEL;
//        employeeBillsClaim.setBillType(billType);
//
//        Mockito.when(employeeBillsClaimRepository.save(employeeBillsClaim)).thenReturn(employeeBillsClaim);
//        boolean present1 = employeeBillsClaimRepository.findById((long) 1).isPresent();
//        employeeBillsClaimRepository.deleteById((long) 1);
//        boolean present2 = employeeBillsClaimRepository.findById((long) 1).isPresent();
//        assertThat(present1).isEqualTo(present2);
//    }
}
