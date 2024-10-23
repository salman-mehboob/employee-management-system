package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.dto.NewEmployeeDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.repository.DepartmentRepository;
import com.econception.employemanagement.repository.EmployeeRepository;
import com.econception.employemanagement.repository.UserRepository;
import com.econception.employemanagement.service.DepartmentService;
import com.econception.employemanagement.service.EmployeeService;
import com.econception.employemanagement.service.UserService;
import com.econception.employemanagement.utils.FileUploadUtil;
import net.bytebuddy.matcher.ElementMatcher;
import org.h2.store.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.econception.employemanagement.enumeration.ApprovedStatus.PENDING;

@Controller
@RequestMapping("/employee")
public class EmployeeResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);
    private static final String ENTITY_NAME = "employee";
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public EmployeeResource(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/showEmployee")
    public String getEmployee(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);

        return "employeeList";


    }

    @RequestMapping("/showEmployeeDetail")
    public String showEmployeeDetail(Model model, @RequestParam(value = "userId") Long userId) {
        User user = userRepository.findById(userId)
                .map(iss -> iss)
                .orElseThrow();

        model.addAttribute("user", user);

        return "showEmployeeDetail";
    }

    @RequestMapping("/showOneUser/{id}")
    public ModelAndView showOneUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("singleEmployee");
        List<User> employee = userRepository.findAllById(Collections.singleton(id));
        mav.addObject("employee", employee);
        return mav;


    }

    @RequestMapping("/edit")
    public String authorizeForm(@RequestParam("employeeId") Long employeeId, Model model) {

        Employee employee = employeeRepository.findOneById(employeeId);
        List<Department> departmentList = departmentRepository.findAll();

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("dep", departmentList);


        return "editEmployee";
    }


    @RequestMapping("/editProfile")
    public String findEmployee(Model model) {

        User user = userService.findUserByCurrentUserName();

        if (user != null) {
            model.addAttribute("userList", user);
            // ApprovedStatus status = PENDING;
            //List<Employee> employeeList = employeeService.findAll();
            //model.addAttribute("employeeList", employeeList);

            return "EditProfile";
        }

        return "EditProfile";
    }


    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") long id) {


        employeeService.delete(id);


        return "redirect:/employee/editProfile";

    }


    @RequestMapping(value = "/save")
    public ResponseEntity<String> saveDepartment(@Valid Employee employee, @RequestParam("file") MultipartFile multipartFile) throws IOException {


        employee.setDepartment(employee.getDepartment());
        employee.setGender(employee.getGender());
        if(employee.getPhoto().isEmpty()){
            employee.setPhoto(null);
        }

        if(!multipartFile.isEmpty()){
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String parts[] = filename.split("\\.");
        String flName = employee.getId() + "." + parts[parts.length - 1];
        String uploadDir = "upload-photos/";
        FileUploadUtil.saveFile(uploadDir, flName, multipartFile);
        employee.setPhoto(flName);}


        employeeService.save(employee);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/delete")
    public ResponseEntity<String> deletePicture(@Valid Employee employee) throws IOException {

        if(employee.getFirstName().isEmpty() || employee.getLastName().isEmpty() || employee.getDOB().isEmpty() || employee.getPrimaryContactNumber().isEmpty() ||
         employee.getDesignation().isEmpty() || employee.getSecondryContactNumber().isEmpty() || employee.getCnic().isEmpty() ||
                employee.getAddress().isEmpty() || employee.getCity().isEmpty() || employee.getPersonalEmail().isEmpty() || employee.getWorkEmail().isEmpty()){
            return ResponseEntity.ok("Empty");

        }

        Employee oldDetail = this.employeeRepository.findById(employee.getId()).get();
        System.out.println(oldDetail);
        if (!employee.getPhoto().isEmpty()) {

            String fName = "upload-photos/" + oldDetail.getPhoto();
            System.out.println("pic=" + fName);
            File file = new File(fName);
            file.delete();
            oldDetail.setPhoto(null);
            employeeRepository.save(oldDetail);

            System.out.println("Successfully");
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.ok("Error");
    }

    @GetMapping("/sendEmail")
    private String sendEmail() {
        return "email";
    }


}
