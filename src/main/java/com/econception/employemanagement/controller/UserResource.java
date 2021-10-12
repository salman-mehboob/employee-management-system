package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.Department;
import com.econception.employemanagement.domain.Employee;
import com.econception.employemanagement.domain.Role;
import com.econception.employemanagement.domain.User;
import com.econception.employemanagement.dto.NewEmployeeDTO;
import com.econception.employemanagement.dto.PasswordDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.repository.RoleRepository;
import com.econception.employemanagement.repository.UserRepository;
import com.econception.employemanagement.service.DepartmentService;
import com.econception.employemanagement.service.EmployeeService;
import com.econception.employemanagement.service.MailService;
import com.econception.employemanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.econception.employemanagement.enumeration.ApprovedStatus.PENDING;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    MailService mailService;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {


        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        List<Department> departmentList = departmentService.findAll();
        List<Role> roleList = roleRepository.findAll();

        modelAndView.addObject("user", user);
        modelAndView.addObject("dep", departmentList);
        modelAndView.addObject("role", roleList);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ModelAndView addNEwEmployee() {

        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        List<Department> departmentList = departmentService.findAll();
        List<Role> roleList = roleRepository.findAll();

        modelAndView.addObject("user", user);
        modelAndView.addObject("dep", departmentList);
        modelAndView.addObject("role", roleList);
        modelAndView.setViewName("addNewEmployee");
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> createNewUser(@Valid NewEmployeeDTO newEmployeeDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);


        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(newEmployeeDTO.getUsername());
        List<Department> departmentList = departmentService.findAll();
        List<Role> roleList = roleRepository.findAll();
        modelAndView.addObject("dep", departmentList);
        modelAndView.addObject("role", roleList);
        if (userExists != null) {
            return ResponseEntity.ok("Error");
        } else {
            Employee employee = new Employee();
            employee.setFirstName(newEmployeeDTO.getFirstName());
            employee.setMiddleName(newEmployeeDTO.getMiddleName());
            employee.setLastName(newEmployeeDTO.getLastName());
            employee.setGender(newEmployeeDTO.getGender());
            employee.setDOB(newEmployeeDTO.getDOB());
            employee.setPrimaryContactNumber(newEmployeeDTO.getPrimaryContactNumber());
            employee.setSecondryContactNumber(newEmployeeDTO.getSecondryContactNumber());
            employee.setPersonalEmail(newEmployeeDTO.getPersonalEmail());
            employee.setWorkEmail(newEmployeeDTO.getWorkEmail());
            employee.setSkype(newEmployeeDTO.getSkype());
            employee.setLinkedin(newEmployeeDTO.getLinkedin());
            employee.setStatus(newEmployeeDTO.getStatus());
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();
            String date = df.format(dateobj);
            employee.setDataCreated(date);
            employee.setCnic(newEmployeeDTO.getCnic());
            employee.setAddress(newEmployeeDTO.getAddress());
            employee.setLine2(newEmployeeDTO.getLine2());
            employee.setCity(newEmployeeDTO.getCity());
            Department department = new Department();
            department.setId(newEmployeeDTO.getDepartmentId());
            employee.setDesignation(newEmployeeDTO.getDesignation());
            employee.setDepartment(department);
            Employee employeeSaved = employeeService.save(employee);

            User realUser = new User();
            Employee employee1 = new Employee();
            employee1.setId(employeeSaved.getId());
            realUser.setEmployee(employee1);
            realUser.setUsername(newEmployeeDTO.getUsername());
            realUser.setPassword(bCryptPasswordEncoder.encode(newEmployeeDTO.getPassword()));
            realUser.setActive(true);
            realUser.setStatus(ApprovedStatus.APPROVED);
            realUser.setRoleName(newEmployeeDTO.getRole().toUpperCase());
            Role userRole = roleRepository.findByRole(newEmployeeDTO.getRole());
            realUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userService.saveUser(realUser);

            String toEmail = employeeSaved.getWorkEmail();
            String subject = "Registration Confirmation!!!";
            String text = "Hello " + employeeSaved.getFirstName() + ", Your username and password are activated, Your username and password is "+
                    realUser.getUsername() + " and " + newEmployeeDTO.getPassword() +". Thank You.";
            mailService.sendEmail(toEmail, subject, text);


            return ResponseEntity.ok("Success");

        }
    }


    @RequestMapping(value = "/addNewEmployee")
    public ResponseEntity<String> requestForNewEmployee(@Valid NewEmployeeDTO newEmployeeDTO, Model modelAndView) {
        User userExists = userService.findUserByUserName(newEmployeeDTO.getUsername());
        List<Department> departmentList = departmentService.findAll();
        modelAndView.addAttribute("dep", departmentList);
        modelAndView.addAttribute("addNewEmployee");
        if (userExists != null) {
            return ResponseEntity.ok("Error");
        } else {
            Employee employee = new Employee();
            employee.setFirstName(newEmployeeDTO.getFirstName());
            employee.setMiddleName(newEmployeeDTO.getMiddleName());
            employee.setLastName(newEmployeeDTO.getLastName());
            employee.setGender(newEmployeeDTO.getGender());
            employee.setDOB(newEmployeeDTO.getDOB());
            employee.setPrimaryContactNumber(newEmployeeDTO.getPrimaryContactNumber());
            employee.setSecondryContactNumber(newEmployeeDTO.getSecondryContactNumber());
            employee.setPersonalEmail(newEmployeeDTO.getPersonalEmail());
            employee.setWorkEmail(newEmployeeDTO.getWorkEmail());
            employee.setSkype(newEmployeeDTO.getSkype());
            employee.setLinkedin(newEmployeeDTO.getLinkedin());
            employee.setStatus(newEmployeeDTO.getStatus());
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();
            String date = df.format(dateobj);
            employee.setDataCreated(date);
            employee.setCnic(newEmployeeDTO.getCnic());
            employee.setAddress(newEmployeeDTO.getAddress());
            employee.setLine2(newEmployeeDTO.getLine2());
            employee.setCity(newEmployeeDTO.getCity());
            Department department = new Department();
            department.setId(newEmployeeDTO.getDepartmentId());
            employee.setDesignation(newEmployeeDTO.getDesignation());
            employee.setDepartment(department);
            Employee employeeSaved = employeeService.save(employee);

            User realUser = new User();
            Employee employee1 = new Employee();
            employee1.setId(employeeSaved.getId());
            realUser.setEmployee(employee1);
            ApprovedStatus status = PENDING;
            realUser.setStatus(status);
            realUser.setRoleName(newEmployeeDTO.getRole());
            realUser.setUsername(newEmployeeDTO.getUsername());
            realUser.setPassword(bCryptPasswordEncoder.encode(newEmployeeDTO.getPassword()));
            realUser.setActive(false);
            userService.saveUser(realUser);

            return ResponseEntity.ok("Success");

        }

    }


    //handling in WebSecurityConfiguration class
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUsername() + "/" + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping("/changePassword")
    public ModelAndView savePassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("changePassword");
        return modelAndView;
    }


    @PostMapping("/savePassword")
    public ResponseEntity<?> changePasswordSave(@Valid PasswordDTO password, Errors errors, HttpServletRequest request) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userService.findUserByUserName(currentUserName);


        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining("<br>"));
            return ResponseEntity.ok(msg);
        }


        String username = loggedUser.getUsername();
        String currPasswd = password.getCurrPassword();
        String newPasswd = password.getNewPassword();
        String confPasswd = password.getConfirmPassword();


        try {
            boolean passwordMatch = BCrypt.checkpw(currPasswd, loggedUser.getPassword());

            if (!passwordMatch) {
                errors.rejectValue("currPassword", "currPassword.incorrect", "");
                return ResponseEntity.ok("Current password is not valid, please provide correct password");
            }
            if (currPasswd.equals(newPasswd)) {
                errors.rejectValue("currPassword", "currPassword.incorrect", "");
                return ResponseEntity.ok("New password is the same as current.");
            }
            if (!newPasswd.equals(confPasswd)) {
                errors.rejectValue("newPassword", "newPassword.incorrect", "");
                return ResponseEntity.ok("New Password does not match with Confirm Password.");
            }

//            User foundUser = user.get();
            newPasswd = bCryptPasswordEncoder.encode(newPasswd);
            loggedUser.setPassword(newPasswd);
            userService.save(loggedUser);
            return ResponseEntity.ok("Password has been changed successfully.");

        } catch (Exception e) {
            log.error("Save Password Error", e);
            return ResponseEntity.ok("Error: " + e.getMessage());
        }
    }


}
