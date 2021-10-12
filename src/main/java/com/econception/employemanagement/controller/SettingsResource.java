package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.domain.Module;
import com.econception.employemanagement.dto.LeavesDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.repository.DepartmentRepository;
import com.econception.employemanagement.repository.LeaveCategoryRepository;
import com.econception.employemanagement.repository.ModuleRepository;
import com.econception.employemanagement.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.econception.employemanagement.enumeration.ApprovedStatus.APPROVED;
import static com.econception.employemanagement.enumeration.ApprovedStatus.REJECTED;

@Controller
@Slf4j
public class SettingsResource {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    LeaveCategoryRepository leaveCategoryRepository;

    @GetMapping(value = "/departmentSetting")
    public String departmentSetting(Model model) {

        List<Department> departmentList = departmentRepository.findAll();

        model.addAttribute("dList", departmentList);
        return "departmentSetting";
    }

    @RequestMapping(value = "/saveDepartment", method = RequestMethod.POST)
    public ResponseEntity<String> saveDepartment(@Valid Department department) {
        departmentRepository.save(department);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/deleteDepartment")
    public String deleteDep(long id, Model model) {
        departmentRepository.deleteById(id);
        return "redirect:/departmentSetting";
    }


    @GetMapping(value = "/roleSetting")
    public String roleSetting(Model model) {

        List<Role> roleList = roleRepository.findAll();

        model.addAttribute("rList", roleList);
        return "RolesSetting";
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public ResponseEntity<String> saveRole(@Valid LeavesDTO role) {
        String role1 = role.getUserRole().toUpperCase();
        Role role2 = new Role();
        role2.setRole(role1);
        roleRepository.save(role2);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(long id, Model model) {
        roleRepository.deleteById(id);
        return "redirect:/roleSetting";
    }


    @GetMapping(value = "/moduleSetting")
    public String moduleSetting(Model model) {

        List<Module> moduleList = moduleRepository.findAll();

        model.addAttribute("mList", moduleList);
        return "moduleSetting";
    }

    @RequestMapping(value = "/saveModule", method = RequestMethod.POST)
    public ResponseEntity<String> saveModule(@Valid Module module) {
        moduleRepository.save(module);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/deleteModule")
    public String deleteMod(long id, Model model) {
        moduleRepository.deleteById(id);
        return "redirect:/moduleSetting";
    }

    @GetMapping(value = "/leaveCategorySetting")
    public String leaveSetting(Model model) {

        List<LeavesCategories> leavesCategoriesList = leaveCategoryRepository.findAll();

        model.addAttribute("lList", leavesCategoriesList);
        return "LeaveCategorySettings";
    }

    @RequestMapping(value = "/saveLeaveCategory", method = RequestMethod.POST)
    public ResponseEntity<String> saveLeaveCategory(@Valid LeavesCategories leavesCategories) {
        leaveCategoryRepository.save(leavesCategories);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/deleteLeaveCategory")
    public String deleteLeaveCategory(long id, Model model) {
        leaveCategoryRepository.deleteById(id);
        return "redirect:/leaveCategorySetting";
    }


}
