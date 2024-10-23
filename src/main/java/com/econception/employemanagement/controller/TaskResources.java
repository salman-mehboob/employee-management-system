package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.domain.Module;
import com.econception.employemanagement.dto.LeavesDTO;
import com.econception.employemanagement.dto.TasksDTO;
import com.econception.employemanagement.enumeration.ModuleTitles;
import com.econception.employemanagement.repository.*;
import com.econception.employemanagement.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.econception.employemanagement.enumeration.ModuleTitles.Leaves;
import static java.time.ZoneId.systemDefault;

@Controller
@RequestMapping("/tasks")
public class TaskResources {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeTasksRepository employeeTasksRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;

    @RequestMapping("/showTasks")
    public String showTasks(Model model) {

        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("depList", departmentList);

        return "Tasks";
    }

    @GetMapping("/findEmployeeByDepartment")
    @JsonIgnore
    public ResponseEntity<?> findEmployeeByDepartment(Long dep_id) {
        var employees = employeeRepository.findAllByDepartmentId(dep_id);
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.ok("There is no employee in this department!");
        }
    }

    @RequestMapping("/saveTask")
    public ResponseEntity<String> saveTask(TasksDTO tasksDTO) {
      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss");
        EmployeeTasks employeeTasks = new EmployeeTasks();
        employeeTasks.setDescription(tasksDTO.getDescription());
        Employee employee = new Employee();
        employee.setId(tasksDTO.getEmployee_id());
        employeeTasks.setEmployee(employee);
        employeeTasks.setDateDue(tasksDTO.getDueDate());
        employeeTasks.setStatus("pending");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime cdate = LocalDateTime.now();
        cdate.format(formatter);
        employeeTasks.setDateCreated(cdate);
        EmployeeTasks savedTask = employeeTasksRepository.save(employeeTasks);

        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);
        Employee loggedUser = new Employee();
        loggedUser.setId(userList.getEmployee().getId());
if(!tasksDTO.getText().isEmpty()) {
    Comments comments = new Comments();
    comments.setEmployee(loggedUser);
    comments.setText(tasksDTO.getText());
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date dateObj = new Date();
    String date = df.format(dateObj);
    comments.setDateCreated(date);
    Module module1 = new Module();
    module1.setId((long) 3);
    comments.setModule(module1);
    comments.setParent(savedTask.getId());
    commentRepository.save(comments);
}
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/taskDone")
    public String taskDone(Long id, Model model) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);
        Long employeeID = userList.getEmployee().getId();
        EmployeeTasks foundEmployeeTasks = employeeTasksRepository.findOneById(id);
        System.out.println(foundEmployeeTasks);
        foundEmployeeTasks.setStatus("done");
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        foundEmployeeTasks.setDateSubmitted(date);
        employeeTasksRepository.save(foundEmployeeTasks);
        List<EmployeeTasks> employeeTasksList = employeeTasksRepository.findAllByStatusAndEmployeeIdOrderByDateCreatedDesc("pending", employeeID);
        model.addAttribute("taskList", employeeTasksList);
        return "redirect:/welcome";
    }

    @PostMapping("/taskDetail")
    public String authorizeForm(@RequestParam(value = "taskId") Long taskId, Model model) {

        EmployeeTasks employeeTasks = employeeTasksRepository.findById(taskId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(taskId, (long) 3);

        model.addAttribute("taskId", taskId);
        model.addAttribute("employeeTask", employeeTasks);
        model.addAttribute("comtList", commentsList);


        return "taskForm";
    }

    @RequestMapping(value = "/saveTaskUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> saveDepartment(@Valid LeavesDTO leavesDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);
        Long id = userList.getId();
        if(!leavesDTO.getText().isEmpty()) {
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

    @RequestMapping("/tasksDetails")
    public String tasksDetals(Model model) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);
        Long id = userList.getEmployee().getId();
        List<EmployeeTasks> tasks=employeeTasksRepository.findAllByOrderByIdDesc();
        List<EmployeeTasks> tasks1=employeeTasksRepository.findAllByEmployeeIdOrderByIdDesc(id);
       // List<EmployeeTasks> employeeTasks = employeeTasksRepository.findByStatus("pending");
       // List<EmployeeTasks> employeeTasks1 = employeeTasksRepository.findByStatus("done");
       // List<EmployeeTasks> employeeTasksList = employeeTasksRepository.findByStatusAndEmployeeId("done", id);


      //  model.addAttribute("taskList", employeeTasks);
      //  model.addAttribute("taskList2", employeeTasks1);
       // model.addAttribute("taskList3", employeeTasksList);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasks1", tasks1);

        return "tasksDetails";
    }

}
