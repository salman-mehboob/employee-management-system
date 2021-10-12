package com.econception.employemanagement.controller;

import com.econception.employemanagement.dto.LeavesDTO;
import com.econception.employemanagement.dto.filesDTO;
import com.econception.employemanagement.repository.FilesRepository;
import com.econception.employemanagement.service.FilesServics;
import com.econception.employemanagement.service.SecurityService;
import com.econception.employemanagement.utils.DateUtil;
import com.econception.employemanagement.utils.FileUploadUtil;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.domain.Module;
import com.econception.employemanagement.dto.BillsDTO;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.enumeration.BillFrequency;
import com.econception.employemanagement.enumeration.BillType;
import com.econception.employemanagement.enumeration.ModuleTitles;
import com.econception.employemanagement.repository.CommentRepository;
import com.econception.employemanagement.repository.EmployeeBillsClaimRepository;
import com.econception.employemanagement.repository.ModuleRepository;
import com.econception.employemanagement.service.EmployeeBillsClaimService;
import com.econception.employemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static antlr.StringUtils.*;
import static com.econception.employemanagement.enumeration.ApprovedStatus.*;
import static com.econception.employemanagement.enumeration.BillFrequency.MONTHLY;
import static com.econception.employemanagement.enumeration.ModuleTitles.*;
import static org.springframework.util.StringUtils.cleanPath;

@Controller
@RequestMapping("/bill")
public class BillResources {
    @Autowired
    UserService userService;

    @Autowired
    EmployeeBillsClaimService employeeBillsClaimService;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    EmployeeBillsClaimRepository employeeBillsClaimRepository;
    @Autowired
    FilesRepository filesRepository;
    @Autowired
    FilesServics filesServics;
    @Autowired
    SecurityService securityService;

//    @Value("${limit}")
//    String limit;


    @RequestMapping("/showBills")
    public String showBills(Model model) {
        ApprovedStatus status1 = PENDING;

        User user = userService.findUserByCurrentUserName();
        if (user != null) {
            Long id = user.getEmployee().getId();

            List<EmployeeBillsClaim> employeeBillsClaims1 = employeeBillsClaimRepository.findAllByStatusAndEmployeeId(status1, id);
            model.addAttribute("requestBillsList", employeeBillsClaims1);

            model.addAttribute("userList", user);

            EmployeeBillsClaim employeeBillsClaims = new EmployeeBillsClaim();
            model.addAttribute("employeeBillsClaims", employeeBillsClaims);
            return "Bill";
        }

        return null;
    }

//    @RequestMapping("/billsList")
//    public String  billsHistory(Model model){
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        if(currentUserName!=null){
//            User userList=userService.findUserByUserName(currentUserName);
//            if(userList!=null){
//                Long id = userList.getEmployee().getId();
//                model.addAttribute("userList",userList);
//                List<EmployeeBillsClaim> employeeBillsClaims= employeeBillsClaimRepository.findAllByEmployeeId(id);
//
//                model.addAttribute("employeeBillsClaims",employeeBillsClaims);
//
//                return "BillsList";
//            }
//        }
//        return null;
//    }

    @PostMapping("/requestBill")
    public ResponseEntity<String> requestBill(@Valid BillsDTO billsDTO, @RequestParam("file") MultipartFile[] multipartFile) throws IOException {


        EmployeeBillsClaim employeeBillsClaim = new EmployeeBillsClaim();
        Employee employee = new Employee();
        employee.setId(billsDTO.getEmployeeID());
        employeeBillsClaim.setEmployee(employee);
        employeeBillsClaim.setAmount(billsDTO.getBillAmount());
        employeeBillsClaim.setDateCreated(DateUtil.MysqlCurrentDate());
        employeeBillsClaim.setTitle(billsDTO.getTitle());
        ApprovedStatus status = PENDING;
        employeeBillsClaim.setStatus(status);
        employeeBillsClaim.setBillFrequency(billsDTO.getBillFrequency());
        employeeBillsClaim.setBillType(billsDTO.getBillType());
        EmployeeBillsClaim savedBill = employeeBillsClaimService.save(employeeBillsClaim);


        if(!billsDTO.getBillDetail().isEmpty()) {
            Comments comments = new Comments();
            comments.setEmployee(employee);
            comments.setText(billsDTO.getBillDetail());
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dateObj = new Date();
            String date = df.format(dateObj);
            comments.setDateCreated(date);
            Module module1 = new Module();
            module1.setId((long) 2);
            comments.setModule(module1);
            comments.setParent(savedBill.getId());
            commentRepository.save(comments);
        }
        for (MultipartFile fl : multipartFile) {

            Files files = new Files();
            if(files.getFile() == null) {
                files.setParent(savedBill.getId());
                files.setEmployee(employee);
                Module module2 = new Module();
                module2.setId((long) 2);
                files.setModule(module2);
                Files filePic = filesRepository.save(files);
                String filename = StringUtils.cleanPath(fl.getOriginalFilename());
                String parts[] = filename.split("\\.");
                String flName = filePic.getId() + "." + parts[parts.length - 1];
                String uploadDir = "bill-photos/";
                FileUploadUtil.saveFile(uploadDir, flName, fl);
                files.setFile(flName);
                filesRepository.save(files);
            }

        }

        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/billHistoryList")
    public String leavesRequestList(Model model) {


        User user = userService.findUserByCurrentUserName();
        if (user != null) {
            Long id = user.getEmployee().getId();

            List<EmployeeBillsClaim> employeeBillsClaims = employeeBillsClaimRepository.findAllByEmployeeIdOrderByIdDesc(id);
            model.addAttribute("requestBillsList", employeeBillsClaims);
            return "BillsList";
        }

        return "BillsList";
    }
    


    @RequestMapping("/authorizeBill")
    public String authorizeBill(@RequestParam(value = "employeeBillId") Long employeeBillId, Model model) {
        List<Files> files = filesRepository.findAllByParent(employeeBillId);
        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findById(employeeBillId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(employeeBillId, (long) 2);
        model.addAttribute("employeeBillId", employeeBillId);
        model.addAttribute("employeeBillsClaim", employeeBillsClaim);
        model.addAttribute("comtList", commentsList);
        model.addAttribute("files", files);

        return "authorizeBillForm";
    }

    @RequestMapping("/billDetailAuthorizeForm")
    public String billDetailAuthorizeForm(@RequestParam(value = "employeeBillId") Long employeeBillId, Model model) {
        List<Files> files = filesRepository.findAllByParent(employeeBillId);
        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findById(employeeBillId)
                .map(iss -> iss)
                .orElseThrow();
        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(employeeBillId, (long) 2);
        model.addAttribute("employeeBillId", employeeBillId);
        model.addAttribute("employeeBillsClaim", employeeBillsClaim);
        model.addAttribute("comtList", commentsList);
        model.addAttribute("files", files);

        return "billDetailAuthorizeForm";
    }


    @RequestMapping(value = "/saveBillUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> save(@Valid BillsDTO billsDTO) {
        User user = userService.findUserByCurrentUserName();
        Long id = user.getId();
        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findOneById(billsDTO.getId());

        employeeBillsClaim.setApprovedAmount(billsDTO.getApprovedAmount());
        employeeBillsClaim.setId(billsDTO.getId());
        employeeBillsClaim.setApprovedBy(id);
        employeeBillsClaim.setStatus(billsDTO.getStatus());
        employeeBillsClaimService.save(employeeBillsClaim);

        if(!billsDTO.getBillDetail().isEmpty()){
        Comments comments = new Comments();
        Employee employee1 = new Employee();
        employee1.setId(user.getEmployee().getId());
        comments.setEmployee(employee1);
        comments.setText(billsDTO.getBillDetail());
        Module module = new Module();
        module.setId(billsDTO.getModuleId());
        comments.setModule(module);
        comments.setParent(billsDTO.getParentID());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateObj = new Date();
        String date = df.format(dateObj);
        comments.setDateCreated(date);
        commentRepository.save(comments);
        }


        return ResponseEntity.ok("Success");
    }


    @RequestMapping("/showBillDetail")
    public String showEmployeeDetail(Model model, @RequestParam(value = "billID") Long billID) {
        List<Files> files = filesRepository.findAllByParent(billID);
        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findById(billID)
                .map(iss -> iss)
                .orElseThrow();

        List<Comments> commentsList = commentRepository.findAllByParentAndModuleId(billID,(long) 2);

        model.addAttribute("employeeBillsClaim", employeeBillsClaim);
        model.addAttribute("comtList", commentsList);
        model.addAttribute("files", files);
        model.addAttribute("billID", billID);

        return "showBillDetail";
    }

    @PostMapping("/billUpdate")
    public String update(@RequestParam(value = "billID") Long billID, Model model) {
        List<Files> files = filesRepository.findAllByParent(billID);
        Long count = filesRepository.countAllByParent(billID);
        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimRepository.findById(billID)
                .map(iss -> iss)
                .orElseThrow();
        model.addAttribute("billID", billID);
        model.addAttribute("employeeBillsClaim", employeeBillsClaim);
        model.addAttribute("files", files);
        model.addAttribute("totalPic", count);


        return "billUpdate";
    }


    @RequestMapping(value = "/saveBill", method = RequestMethod.POST)
    public ResponseEntity<String> saveBill(@Valid BillsDTO billsDTO,@RequestParam("file") MultipartFile[] multipartFile) throws IOException {
        
       // Long employee = userList.getEmployee().getId();
        // EmployeeBillsClaim employeeBillsClaim = new EmployeeBillsClaim();
       // Employee employee = new Employee();

        EmployeeBillsClaim employeeBillsClaim = employeeBillsClaimService.findById(billsDTO.getId());
        if (employeeBillsClaim != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
            Date dateobj = new Date();
            String date = df.format(dateobj);
            employeeBillsClaim.setDateCreated(date);
            employeeBillsClaim.setBillType(billsDTO.getBillType());
            employeeBillsClaim.setBillFrequency(billsDTO.getBillFrequency());
            employeeBillsClaim.setAmount(billsDTO.getBillAmount());
            employeeBillsClaim.setTitle(billsDTO.getTitle());
            EmployeeBillsClaim savedBill = employeeBillsClaimService.save(employeeBillsClaim);


            for (MultipartFile fl : multipartFile) {
                Employee employee = new Employee();
                employee.setId(billsDTO.getEmployeeID());
                Module module1 = new Module();
                module1.setId((long) 2);
                Files files = new Files();

                    files.setParent(savedBill.getId());
                    files.setEmployee(employee);
                    files.setModule(module1);
                    Files filePic = filesRepository.save(files);
                    String filename = StringUtils.cleanPath(fl.getOriginalFilename());
                    String parts[] = filename.split("\\.");
                    String flName = filePic.getId() + "." + parts[parts.length - 1];
                    String uploadDir = "bill-photos/";
                    FileUploadUtil.saveFile(uploadDir, flName, fl);
                    files.setFile(flName);
                    filesRepository.save(files);
                    return ResponseEntity.ok("Success");

            }
            return ResponseEntity.ok("Success");
        }

        else {
            return ResponseEntity.ok("Unable to Update");
        }

    }


    @RequestMapping(value = "/saveBillComUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> saveComDepartment(@Valid BillsDTO billsDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userList = userService.findUserByUserName(currentUserName);
        Long id = userList.getId();
        String text = billsDTO.getBillDetail();


        if(!billsDTO.getBillDetail().isEmpty()) {
            Comments comments = new Comments();
            Employee employee1 = new Employee();
            employee1.setId(userList.getEmployee().getId());
            comments.setEmployee(employee1);
            comments.setText(billsDTO.getBillDetail());
            Module module = new Module();
            module.setId(billsDTO.getModuleId());
            comments.setModule(module);
            comments.setParent(billsDTO.getParentID());
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dateObj = new Date();
            String date = df.format(dateObj);
            comments.setDateCreated(date);
            commentRepository.save(comments);
        }

        return ResponseEntity.ok("Success");
    }

    @RequestMapping("/delete")
    public ResponseEntity<String> deleteBillPic(@Valid Files files, Long id ){

        Files oldDetail=this.filesRepository.findById(files.getId()).get();
        System.out.println(oldDetail.getId());



        String fName="bill-photos/"+oldDetail.getFile();
        System.out.println("pic=" +fName);
        File file=new File(fName);
        file.delete();
        filesRepository.deleteById(id);

        return ResponseEntity.ok("Success");
    }

}
