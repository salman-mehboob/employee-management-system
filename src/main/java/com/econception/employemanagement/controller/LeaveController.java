package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.domain.Module;
import com.econception.employemanagement.dto.LeavesDTO;
import com.econception.employemanagement.dto.NewEmployeeDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.enumeration.ModuleTitles;
import com.econception.employemanagement.repository.*;
import com.econception.employemanagement.service.EmployeeLeaveService;
import com.econception.employemanagement.service.UserService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.econception.employemanagement.enumeration.ApprovedStatus.*;
import static com.econception.employemanagement.enumeration.ModuleTitles.Leaves;

@Controller

@Slf4j

@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    LeaveCategoryRepository leaveCategoryRepository;

    @Autowired
    EmployeeLeavesRepository employeeLeavesRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EmployeeLeaveService employeeLeaveService;

    private Session session;

    @RequestMapping("/showLeaves")
    public String leaves(Model model) {

        User user = userService.findUserByCurrentUserName();
        if (user != null) {
            model.addAttribute("userList", user);
            List<LeavesCategories> leavesCategoriesList = leaveCategoryRepository.findAll();
            model.addAttribute("leavesCategoriesList", leavesCategoriesList);
            return "Leaves";
        }

        return null;

    }

    @RequestMapping("/requestLeave")
    public ResponseEntity<String> requestLeave(@Valid LeavesDTO leavesDTO) {


        EmployeeLeaves employeeLeaves = new EmployeeLeaves();
        Employee employee = new Employee();
        employee.setId(leavesDTO.getEmployeeID());
        employeeLeaves.setEmployee(employee);


        LeavesCategories leavesCategories = new LeavesCategories();
        leavesCategories.setId(leavesDTO.getEmployeeLeaveId());
        employeeLeaves.setLeavesCategories(leavesCategories);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateobj = new Date();
        String date1 = df.format(dateobj);
        employeeLeaves.setDateCreated(date1);
        employeeLeaves.setDateFrom(leavesDTO.getDateFrom());
        employeeLeaves.setDateTo(leavesDTO.getDateTo());
        EmployeeLeaves savedLeave = new EmployeeLeaves();
        ApprovedStatus status = PENDING;
        employeeLeaves.setStatus(status);
        savedLeave = employeeLeavesRepository.save(employeeLeaves);

        if(!leavesDTO.getText().isEmpty()) {
            Comments comments = new Comments();
            comments.setEmployee(employee);
            comments.setText(leavesDTO.getText());
            String date = df.format(dateobj);
            comments.setDateCreated(date);
            Module module1 = new Module();
            module1.setId((long) 1);
            comments.setModule(module1);
            comments.setParent(savedLeave.getId());
            commentRepository.save(comments);
        }


        return ResponseEntity.ok("Success");

    }

    @RequestMapping("/leavesRequestList")
    public String leavesRequestList(Model model) {
       // ApprovedStatus status1 = PENDING;
       // ApprovedStatus status2 = APPROVED;
      //  ApprovedStatus status3 = REJECTED;


        User user = userService.findUserByCurrentUserName();
        if (user != null) {
            Long id = user.getEmployee().getId();

            List<EmployeeLeaves> employeeLeavesList1 = employeeLeavesRepository.findAllByEmployeeIdOrderByIdDesc(id);
            model.addAttribute("requestLeavesList", employeeLeavesList1);
           // List<EmployeeLeaves> employeeLeavesList2 = employeeLeavesRepository.findAllByStatusAndEmployeeId(status2, id);
          //  model.addAttribute("approvedLeavesList", employeeLeavesList2);
        //    List<EmployeeLeaves> employeeLeavesList3 = employeeLeavesRepository.findAllByStatusAndEmployeeId(status3, id);
        //    model.addAttribute("deniedLeavesList", employeeLeavesList3);

            return "leavesRequestList";
        }

        return "leavesRequestList";
    }


    @RequestMapping("/requestListDetail")
    public String requestListDetail(@RequestParam(value = "employeeLeaveId") Long employeeLeaveId, Model model) {

        EmployeeLeaves employeeLeaves = employeeLeavesRepository.findById(employeeLeaveId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(employeeLeaveId, (long) 1);

        model.addAttribute("employeeLeaveId", employeeLeaveId);
        model.addAttribute("employeeLeaves", employeeLeaves);
        model.addAttribute("comtList", commentsList);


        return "requestListDetailForm";
    }


}
