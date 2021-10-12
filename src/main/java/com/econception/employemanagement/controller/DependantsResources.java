package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.repository.EmployeeDependantsRepository;
import com.econception.employemanagement.service.EmployeeDependantsService;
import com.econception.employemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

import javax.persistence.Access;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/dependants")
public class DependantsResources {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeDependantsService dependantsService;
    @Autowired
    private EmployeeDependantsRepository dependantsRepository;

    @RequestMapping("/showDependants")
    public String showDependants(Model model) {

        User userList = userService.findUserByCurrentUserName();
        model.addAttribute("userList", userList);


        return "Dependants";
    }

    @RequestMapping("/showModal")
    public String showModal(@RequestParam(value = "depId") Long depId, Model model) {
        model.addAttribute("depId", depId);
        return "cofirmationModal";
    }

    @RequestMapping("/showDependantsList")
    public String showDependantsList(Model model) {

        User userList = userService.findUserByCurrentUserName();
        List<EmployeeDependants> employeeDependantsList = dependantsRepository.findAllByEmployeeId(userList.getEmployee().getId());
        model.addAttribute("dep", employeeDependantsList);


        return "dependantsList";
    }

    @RequestMapping("/addDependent")
    public ResponseEntity<String> addDependent(EmployeeDependants employeeDependants) {
        EmployeeDependants saveDependant = new EmployeeDependants();
        Employee employee = new Employee();
        employee.setId(employeeDependants.getEmployee().getId());
        saveDependant.setEmployee(employee);
        saveDependant.setFirstName(employeeDependants.getFirstName());
        saveDependant.setLastName(employeeDependants.getLastName());
        saveDependant.setDependantsRelation(employeeDependants.getDependantsRelation());
        saveDependant.setDOB(employeeDependants.getDOB());
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        saveDependant.setDataCreated(date);
        dependantsService.save(saveDependant);

        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/delete")
    public String deleteDep(long id, Model model) {
        dependantsService.delete(id);
        return "redirect:/dependants/showDependantsList";
    }

    @PostMapping("/depUpdate")
    public String update(@RequestParam(value = "userId") Long userId, Model model) {

        EmployeeDependants employeeDependants = dependantsRepository.findById(userId)
                .map(iss -> iss)
                .orElseThrow();
        model.addAttribute("userId", userId);
        model.addAttribute("Dep", employeeDependants);


        return "depUpdate";
    }

    @RequestMapping(value = "/saveDepUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> saveDep(@Valid EmployeeDependants employeeLeaves) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        employeeLeaves.setDataCreated(date);
        dependantsService.save(employeeLeaves);

        return ResponseEntity.ok("Success");
    }

}
