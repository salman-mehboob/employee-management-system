package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.User;
import com.econception.employemanagement.repository.RoleRepository;
import com.econception.employemanagement.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    @InjectMocks
    @Autowired
    private UserService userServiceUnderTest;
    @Mock
    @MockBean
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    private User user;

   

//    @Test
//    void save() {
//
//        User user = new User();
//        user.setId((long) 1);
//        user.setUsername("salman01!");
//        user.setPassword("1234");
//        UserType userType = ADMIN;
//       // user.setUserType(userType);
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        user.setEmployee(employee);
//
//
//        Mockito.when(mockUserRepository.save(user)).thenReturn(user);
//        assertThat(userServiceUnderTest.save(user)).isEqualTo(user);
//    }
//
//    @Test
//    void findAll() {
//
//        User user = new User();
//        user.setId((long) 1);
//        user.setUsername("salman01!");
//        user.setPassword("1234");
//        UserType userType = ADMIN;
//        //user.setUserType(userType);
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        user.setEmployee(employee);
//
//        User user1 = new User();
//        user.setId((long) 1);
//        user.setUsername("salman01!");
//        user.setPassword("1234");
//        UserType userType1 = ADMIN;
//       // user.setUserType(userType1);
//        Employee employee1 = new Employee();
//        employee.setId((long) 2);
//        user.setEmployee(employee);
//
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user1);
//
//        Mockito.when(mockUserRepository.findAll()).thenReturn(users);
//        assertThat(userServiceUnderTest.findAll()).isEqualTo(users);
//
//    }
//
//    @Test
//    void delete() {
//        User user = new User();
//        user.setId((long) 1);
//        user.setUsername("salmanM");
//        user.setPassword("1234");
//        UserType userType = ADMIN;
//       // user.setUserType(userType);
//        Employee employee = new Employee();
//        employee.setId((long) 1);
//        user.setEmployee(employee);
//
//
//        Mockito.when(mockUserRepository.save(user)).thenReturn(user);
//        boolean present1 = mockUserRepository.findById((long) 1).isPresent();
//        mockUserRepository.deleteById((long) 1);
//        boolean present2 = mockUserRepository.findById((long) 1).isPresent();
//        assertThat(present1).isEqualTo(present2);
//    }
}
