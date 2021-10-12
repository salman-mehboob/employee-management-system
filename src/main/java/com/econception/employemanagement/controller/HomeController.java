package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.domain.Module;
import com.econception.employemanagement.dto.LeavesDTO;
import com.econception.employemanagement.dto.NewEmployeeDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.enumeration.LeaveType;
import com.econception.employemanagement.repository.*;
import com.econception.employemanagement.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.econception.employemanagement.enumeration.ApprovedStatus.*;

@Controller
@Component
@Slf4j
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    LeaveCategoryRepository leaveCategoryRepository;
    @Autowired
    EmployeeLeaveService employeeLeaveService;
    @Autowired
    MailService mailService;
    @Autowired
    CommentRepository commentRepository;


    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    EmployeeLeavesRepository employeeLeavesRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EmployeeBillsClaimRepository employeeBillsClaimRepository;
    @Autowired
    EmployeeTasksRepository employeeTasksRepository;


    @RequestMapping("/")
    public String homePage(Model model) {
            User user = userService.findUserByCurrentUserName();
            model.addAttribute("userProfile", user);
        return "main";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


    @GetMapping("/welcome")
    public String welcome(Model model) {


        User user = userService.findUserByCurrentUserName();
        if (user != null) {
            model.addAttribute("userList", user);
            Long employeeID = user.getEmployee().getId();
            ApprovedStatus status = PENDING;

            List<EmployeeLeaves> employeeLeavesList = employeeLeavesRepository.findAllByOrderByDateCreatedDesc();
            List<EmployeeBillsClaim> employeeBillsClaimsList = employeeBillsClaimRepository.findAllByOrderByDateCreatedDesc();
            List<User> userList1 = userRepository.findAllByStatusOrderByIdDesc(status);
            List<LeavesCategories> leavesCategoriesList = leaveCategoryRepository.findAll();
            List<EmployeeTasks> employeeTasksList = employeeTasksRepository.findAllByStatusAndEmployeeIdOrderByDateCreatedDesc("pending", employeeID);


            // List<Comments> commentsList = commentRepository.findAllByParent(employeeID);
            model.addAttribute("leavesCategoriesList", leavesCategoriesList);
            model.addAttribute("leavesList", employeeLeavesList);
            model.addAttribute("claimList", employeeBillsClaimsList);
            model.addAttribute("userList1", userList1);
            model.addAttribute("taskList", employeeTasksList);

            return "home";
        }

        return "home";
    }

    @PostMapping("/authorizationForm")
    public String authorizeForm(@RequestParam(value = "employeeLeaveId") Long employeeLeaveId, Model model) {

        EmployeeLeaves employeeLeaves = employeeLeavesRepository.findById(employeeLeaveId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(employeeLeaveId, (long) 1);

        model.addAttribute("employeeLeaveId", employeeLeaveId);
        model.addAttribute("employeeLeaves", employeeLeaves);
        model.addAttribute("comtList", commentsList);


        return "authorizeForm";
    }

    @PostMapping("/leaveDetailAuthorizeForm")
    public String leaveDetailForm(@RequestParam(value = "employeeLeaveId") Long employeeLeaveId, Model model) {

        EmployeeLeaves employeeLeaves = employeeLeavesRepository.findById(employeeLeaveId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(employeeLeaveId, (long) 1);

        model.addAttribute("employeeLeaveId", employeeLeaveId);
        model.addAttribute("employeeLeaves", employeeLeaves);
        model.addAttribute("comtList", commentsList);


        return "leaveDetailAuthorizeForm";
    }

    @PostMapping("/authorizeRegistrationForm")
    public String authorizeRegistraionForm(@RequestParam(value = "userId") Long userId, Model model) {

        User user1 = userService.findUserByCurrentUserName();
        model.addAttribute("userList", user1);

        List<Role> roleList = roleRepository.findAll();
        User user = userRepository.findById(userId)
                .map(iss -> iss)
                .orElseThrow();

        model.addAttribute("userId", userId);
        model.addAttribute("user", user);
        model.addAttribute("role", roleList);

        return "authorizeRegistrationForm";
    }


    @RequestMapping(value = "/saveLeaveUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> saveDepartment(@Valid LeavesDTO leavesDTO) {

        User user = userService.findUserByCurrentUserName();
        Long id = user.getId();
        EmployeeLeaves employeeLeaves = new EmployeeLeaves();
        employeeLeaves.setApprovedBy(id);
        Employee employee = new Employee();
        employee.setId(leavesDTO.getEmployeeID());
        employeeLeaves.setEmployee(employee);
        LeavesCategories leavesCategories = new LeavesCategories();
        leavesCategories.setId(leavesDTO.getLeaveCatID());
        employeeLeaves.setLeavesCategories(leavesCategories);
        employeeLeaves.setId(leavesDTO.getId());
        employeeLeaves.setDateCreated(leavesDTO.getDateCreated());
        employeeLeaves.setDateFrom(leavesDTO.getDateFrom());
        employeeLeaves.setDateTo(leavesDTO.getDateTo());
        employeeLeaves.setStatus(leavesDTO.getStatus());
        employeeLeaveService.save(employeeLeaves);
        String text = leavesDTO.getText();
        int len = text.length();
        if (len >= 1) {
            Comments comments = new Comments();
            Employee employee1 = new Employee();
            employee1.setId(user.getEmployee().getId());
            comments.setEmployee(employee1);
            comments.setText(leavesDTO.getText());
            Module module = new Module();
            module.setId(leavesDTO.getModuleId());
            comments.setModule(module);
            comments.setParent(leavesDTO.getParentID());
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dateObj = new Date();
            String date = df.format(dateObj);
            comments.setDateCreated(date);
            commentRepository.save(comments);
        }


        return ResponseEntity.ok("Success");
    }

    @RequestMapping(value = "/saveRegRequestUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> saveRegRequestUpdate(@Valid NewEmployeeDTO user) {

        User userList = userService.findUserByCurrentUserName();
        User userExists = userService.findUserByUserName(user.getUsername());

        User realUser = new User();
        ApprovedStatus status = APPROVED;
        Employee employee = new Employee();
        employee.setId(user.getEmpID());
        realUser.setEmployee(employee);
        realUser.setId(user.getId());
        realUser.setStatus(status);
        realUser.setRoleName(user.getRole());
        realUser.setUsername(user.getUsername());
        realUser.setPassword(user.getPassword());
        realUser.setActive(true);
        Role userRole = roleRepository.findByRole(user.getRole());
        realUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        User saveduser = userService.save(realUser);

        String senderEmail = "eConceptionsG8@gmail.com";
        String senderName = userList.getEmployee().getFirstName();
        String toEmail = user.getPersonalEmail();
        String subject = "Registration Confirmation!!!";
        String text = "Hello " + saveduser.getEmployee().getFirstName() + ", Your username and password are activated. Thank You.";
        mailService.sendEmail(toEmail, subject, text);

        return ResponseEntity.ok("Success");

    }

    @RequestMapping(value = "/saveLeaveComUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> savecomDepartment(@Valid LeavesDTO leavesDTO) {

        User userList = userService.findUserByCurrentUserName();
        Long id = userList.getId();
        String text = leavesDTO.getText();
        int len = text.length();
        if (len >= 1) {
            Comments comments = new Comments();
            Employee employee1 = new Employee();
            employee1.setId(userList.getEmployee().getId());
            comments.setEmployee(employee1);
            comments.setText(leavesDTO.getText());
            Module module = new Module();
            module.setId(leavesDTO.getModuleId());
            comments.setModule(module);
            comments.setParent(leavesDTO.getParentID());
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dateObj = new Date();
            String date = df.format(dateObj);
            comments.setDateCreated(date);
            commentRepository.save(comments);
        }


        return ResponseEntity.ok("Success");
    }

    //this will run every 24hr = 86400000 sec
    @Scheduled(fixedRate = 86400000, initialDelay = 30000000)
    public void reportCurrentPendingRequests() {
        String adminEmails = userRepository.findAllByRoleName("ADMIN")
                .stream()
                .filter(s -> !s.getEmployee().getWorkEmail().equals(""))
                .map(s -> s.getEmployee().getWorkEmail())
                .collect(Collectors.joining(","));
        log.info("Admin emails are " + adminEmails);
        ApprovedStatus status = PENDING;
        List<EmployeeLeaves> employeeLeavesList = employeeLeavesRepository.findAllByStatus(status);
        String[] emailArray = adminEmails.split(",");


        for (String a : emailArray) {
            if (employeeLeavesList != null) {
                //String senderName = userList.getEmployee().getFirstName();
                String toEmail1 = a;
                String subject = "Pending Request Alert!!!";
                String text = "Hello Team, Please review pending leaves requests. Thank You.";
//                   log.info("Sending email for request " + e.getEmployee().getFirstName());
                mailService.sendEmail(toEmail1, subject, text);
            }

            List<EmployeeBillsClaim> employeeBillsClaimList = employeeBillsClaimRepository.findAllByStatus(status);
            if (employeeBillsClaimList != null) {
                //String senderName = userList.getEmployee().getFirstName();
                String toEmail2 = a;
                String subject = "Pending Request Alert!!!";
                String text = "Hello Team, Please review pending bill requests. Thank You.";
//                   log.info("Sending email for request " + e.getEmployee().getFirstName());
                mailService.sendEmail(toEmail2, subject, text);
            }

            List<User> userList = userRepository.findAllByStatusOrderByIdDesc(status);
            if (userList != null) {
                //String senderName = userList.getEmployee().getFirstName();
                String toEmail3 = a;
                String subject = "Pending Request Alert!!!";
                String text = "Hello Team, Please review pending registration requests. Thank You.";
//                   log.info("Sending email for request " + e.getEmployee().getFirstName());
                mailService.sendEmail(toEmail3, subject, text);
            }
        }


        System.out.println("Scheduling is done.");
    }


    @PostMapping("/userSettings")
    public String userSettings(@RequestParam(value = "userId") Long userId, Model model) {
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("role" , roleList);
        model.addAttribute("userId", userId);

        return "userSettings";
    }

    @RequestMapping(value = "/saveUserSettings", method = RequestMethod.POST)
    public ResponseEntity<String> saveUserSetting(@Valid NewEmployeeDTO user) {
        User user1 = userRepository.findOneById(user.getId());
        if(user.getActive1().equals("Yes")){
            user1.setActive(false);
            userRepository.save(user1);
        }
        if(user.getActive1().equals("No")){
            user1.setActive(true);
            userRepository.save(user1);
        }
        if(!user.getRole().equals("not")) {
            Role userRole = roleRepository.findByRole(user.getRole());
            user1.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            user1.setRoleName(user.getRole().toUpperCase());
            userRepository.save(user1);
        }

        return ResponseEntity.ok("Success");
    }

}
