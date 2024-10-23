package com.econception.employemanagement.controller;

import com.econception.employemanagement.domain.*;
import com.econception.employemanagement.dto.*;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.repository.*;
import com.econception.employemanagement.service.ReportService;
import com.econception.employemanagement.service.StatsService;
import com.econception.employemanagement.service.BillsStatsService;
import com.econception.employemanagement.service.UserService;
import com.econception.employemanagement.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

import static com.econception.employemanagement.enumeration.ApprovedStatus.*;
import static com.econception.employemanagement.enumeration.ApprovedStatus.PENDING;
import static java.lang.Integer.parseInt;

@Slf4j
@Controller
public class ReportsResource {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeBillsClaimRepository employeeBillsClaimRepository;
    @Autowired
    EmployeeTasksRepository employeeTasksRepository;
    @Autowired
    EmployeeLeavesRepository employeeLeavesRepository;
    @Autowired
    LeaveCategoryRepository leaveCategoryRepository;

    @Autowired
    StatsService statsService;
    @Autowired
    BillsStatsService billsStatsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

    @GetMapping(value = "/reports")
    public String reports(Model model) {

        return "reports";
    }

    @RequestMapping(value = "/billsReports")
    public String billsReports(Model model) throws JSONException {

        User user = userService.findUserByCurrentUserName();
        String role = user.getRoleName();
        Long empId = user.getEmployee().getId();

        Map<String,Long> billCounts = new HashMap<>();

        //bills
        if(role.equals("USER")){
            billCounts = reportService.getUserBillCounts(empId);
        }else {
            billCounts = reportService.getBillCounts();
        }
        List<EmployeeBillsMonthlyCount> monthlyBills = employeeBillsClaimRepository.countMonthlyBills(
                LocalDateTime.now().minusYears(1).format(DateUtil.MysqlDate));

        List<EmployeeBillsMonthlyCount> EmpMonthlyCountList = employeeBillsClaimRepository.countMonthlyBillsByEmployeeId(
                LocalDateTime.now().minusYears(1).format(DateUtil.MysqlDate), empId);

        Map<String, Map<String, Integer>> initMapBills = billsStatsService.initialBillsMap();

        if (role.equals("USER")) {
            for (var bill : EmpMonthlyCountList) {
                String monthNameB = Month.of(parseInt(bill.getBillMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMapBill = initMapBills.get(monthNameB);

                Integer billCnt = bill.getBillCount().intValue();
                //   log.info(String.format("before SubmapBill for month %s is %s and count is %d", monthNameB, subMapBill, billCnt));

                switch (bill.getBillStatus()) {
                    case "APPROVED":
                        subMapBill.replace("Approved", billCnt);
                        break;
                    case "REJECTED":
                        subMapBill.replace("Rejected", billCnt);
                        break;
                    case "PENDING":
                        subMapBill.replace("Pending", billCnt);
                        break;
                }

            }
        } else {
            for (var bill : monthlyBills) {
                String monthNameB = Month.of(parseInt(bill.getBillMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMapBill = initMapBills.get(monthNameB);

                Integer billCnt = bill.getBillCount().intValue();
                //   log.info(String.format("before SubmapBill for month %s is %s and count is %d", monthNameB, subMapBill, billCnt));

                switch (bill.getBillStatus()) {
                    case "APPROVED":
                        subMapBill.replace("Approved", billCnt);
                        break;
                    case "REJECTED":
                        subMapBill.replace("Rejected", billCnt);
                        break;
                    case "PENDING":
                        subMapBill.replace("Pending", billCnt);
                        break;
                }

            }
        }
        // log.info("bill mapp" , initMapBills );
        String billLabels = billsStatsService.createBillsLabels();
        String billData = billsStatsService.createBillJson(initMapBills);

        // log.info(billData);
        model.addAttribute("billLabels", billLabels);
        model.addAttribute("billData", billData);


        model.addAttribute("TB", billCounts.get("TotalBills"));
        model.addAttribute("PB", billCounts.get("pendingBills"));
        model.addAttribute("RB", billCounts.get("rejectedBills"));
        model.addAttribute("AB", billCounts.get("approvedBills"));
        model.addAttribute("TPB", billCounts.get("totalProcessedBills"));
//leaves

        Long TotalLeaves = 0L;
        Long pendingLeaves = 0L;
        Long approvedLeaves = 0L;
        Long rejectedLeaves = 0L;
        Long totalProcessedLeaves =0L;

        if(role.equals("USER")) {
            TotalLeaves = employeeLeavesRepository.countAllByEmployeeId(empId);
            pendingLeaves = employeeLeavesRepository.countAllByStatusAndEmployeeId(ApprovedStatus.PENDING, empId);
            approvedLeaves = employeeLeavesRepository.countAllByStatusAndEmployeeId(ApprovedStatus.APPROVED, empId);
            rejectedLeaves = employeeLeavesRepository.countAllByStatusAndEmployeeId(ApprovedStatus.REJECTED, empId);
            totalProcessedLeaves = approvedLeaves + rejectedLeaves;
        }else {
            TotalLeaves = employeeLeavesRepository.count();
            pendingLeaves = employeeLeavesRepository.countAllByStatus(ApprovedStatus.PENDING);
            approvedLeaves = employeeLeavesRepository.countAllByStatus(ApprovedStatus.APPROVED);
            rejectedLeaves = employeeLeavesRepository.countAllByStatus(ApprovedStatus.REJECTED);
            totalProcessedLeaves = approvedLeaves + rejectedLeaves;
        }


        List<LeavesCount> monthlyLeaves = employeeLeavesRepository.countMonthlyLeave(
                LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        List<LeavesCount> EmpMonthlyLeaves = employeeLeavesRepository.countMonthlyLeaveByEmployeeId(
                LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), empId);

        //System.out.println("bill query output" + monthlyBills);

        Map<String, Map<String, Integer>> initMapLeave = billsStatsService.initialBillsMap();

        if (role.equals("USER")) {
            for (var leave : EmpMonthlyLeaves) {
                String monthNameL = Month.of(parseInt(leave.getLeaveMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMapLeave = initMapLeave.get(monthNameL);

                Integer leaveCnt = leave.getLeaveCount().intValue();
                // log.info(String.format("BEFORE SubMapLeave for month %s is %s and count is %d", monthNameL, subMapLeave, leaveCnt));

                switch (leave.getLeaveStatus()) {
                    case "APPROVED":
                        subMapLeave.replace("Approved", leaveCnt);
                        break;
                    case "REJECTED":
                        subMapLeave.replace("Rejected", leaveCnt);
                        break;
                    case "PENDING":
                        subMapLeave.replace("Pending", leaveCnt);
                        break;
                }
                // log.info(String.format("AFTER SubMapLeave for month %s is %s and count is %d", monthNameL, subMapLeave, leaveCnt));

            }
        } else {
            for (var leave : monthlyLeaves) {
                String monthNameL = Month.of(parseInt(leave.getLeaveMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMapLeave = initMapLeave.get(monthNameL);

                Integer leaveCnt = leave.getLeaveCount().intValue();
                // log.info(String.format("BEFORE SubMapLeave for month %s is %s and count is %d", monthNameL, subMapLeave, leaveCnt));

                switch (leave.getLeaveStatus()) {
                    case "APPROVED":
                        subMapLeave.replace("Approved", leaveCnt);
                        break;
                    case "REJECTED":
                        subMapLeave.replace("Rejected", leaveCnt);
                        break;
                    case "PENDING":
                        subMapLeave.replace("Pending", leaveCnt);
                        break;
                }
                // log.info(String.format("AFTER SubMapLeave for month %s is %s and count is %d", monthNameL, subMapLeave, leaveCnt));

            }
        }
        //  log.info("leavesMap" + initMapLeave);
        String leaveLabels = billsStatsService.createBillsLabels();
        String leaveData = billsStatsService.createBillJson(initMapLeave);
        //  log.info("leave json" + initMapLeave);

        // log.info(leaveData);
        model.addAttribute("lLabels", leaveLabels);
        model.addAttribute("lData", leaveData);


        model.addAttribute("TL", TotalLeaves);
        model.addAttribute("PL", pendingLeaves);
        model.addAttribute("RL", rejectedLeaves);
        model.addAttribute("AL", approvedLeaves);
        model.addAttribute("TPL", totalProcessedLeaves);


//tasks
        Long totalTask = 0L;//employeeTasksRepository.count();
        Long pendingTasks = 0L;//employeeTasksRepository.countAllByStatus("pending");
        Long completeTasks = 0L;//employeeTasksRepository.countAllByStatus("done");

        if(role.equals("USER")){
           totalTask = employeeTasksRepository.countAllByEmployeeId(empId);
           pendingTasks = employeeTasksRepository.countAllByStatusAndEmployeeId("pending", empId);
           completeTasks = employeeTasksRepository.countAllByStatusAndEmployeeId("done", empId);

        }else {
            List<EmployeeTasksCount> employeeTasksList = employeeTasksRepository.countTasks();
            for (var task : employeeTasksList) {
                if (task.getTaskStatus().equals("pending")) {
                    pendingTasks = task.getTaskCount();
                } else if (task.getTaskStatus().equals("done")) {
                    completeTasks = task.getTaskCount();
                }
            }
            totalTask = pendingTasks + completeTasks;
        }


        model.addAttribute("TT", totalTask);
        model.addAttribute("PT", pendingTasks);
        model.addAttribute("CT", completeTasks);

        List<EmployeeTasksMonthlyCount> monthlyTasks = employeeTasksRepository.countMonthlyTasks(
                LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        List<EmployeeTasksMonthlyCount> EmpMonthlyTasks = employeeTasksRepository.countMonthlyTasksByEmployeeId(
                LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), empId);

        Map<String, Map<String, Integer>> initMap = statsService.initialTasksMap();

        //log.info("Initial Map" + initMap.toString());
        if (role.equals("USER")) {
            for (var task : EmpMonthlyTasks) {
                String monthName = Month.of(parseInt(task.getTaskMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMap = initMap.get(monthName);

                Integer taskCnt = task.getTaskCount().intValue();
                //log.info(String.format("Submap for month %s is %s and count is %d", monthName, subMap, taskCnt));
                if (task.getTaskStatus().equals("pending")) {
                    subMap.replace("Pending", taskCnt);
                } else if (task.getTaskStatus().equals("done")) {
                    subMap.replace("Done", taskCnt);
                }
                subMap.merge("Total", taskCnt, Integer::sum);
            }
        } else {
            for (var task : monthlyTasks) {
                String monthName = Month.of(parseInt(task.getTaskMonth())).getDisplayName(TextStyle.SHORT, Locale.getDefault());

                Map<String, Integer> subMap = initMap.get(monthName);

                Integer taskCnt = task.getTaskCount().intValue();
                //log.info(String.format("Submap for month %s is %s and count is %d", monthName, subMap, taskCnt));
                if (task.getTaskStatus().equals("pending")) {
                    subMap.replace("Pending", taskCnt);
                } else if (task.getTaskStatus().equals("done")) {
                    subMap.replace("Done", taskCnt);
                }
                subMap.merge("Total", taskCnt, Integer::sum);
            }

        }
        String taskLabels = statsService.createTaskLabels();
        String taskData = statsService.createTaskJson(initMap);

        model.addAttribute("taskLabels", taskLabels);
        model.addAttribute("taskData", taskData);

        //log.info(taskData);

        //List<EmployeeTasksCount> employeeTasksList = employeeTasksRepository.countTasks();

        //  log.info(initMap.toString());


        return "billsReport";
    }


    @GetMapping(value = "/leavesClaimsHistory")
    public String leavesClaimsHistory(Model model) {
        List<LeavesCategories> leave = leaveCategoryRepository.findAll();
             model.addAttribute("leaveCatList",leave);
             int check = 0;
             model.addAttribute("check",check);
        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("depList", departmentList);

        return "adminLRequestHistory";
    }

    @GetMapping(value = "/findLeaveReport")
    public String findLeaveReport(@Valid LeaveReportDTO leaveReportDTO,  Model model){
        List<LeavesCategories> leave = leaveCategoryRepository.findAll();
        Long totalL = 0L;
        Long processedL = 0L;
        Long pendingL = 0L;
        Long approvedL = 0L;
        Long rejectedL = 0L;
        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("depList", departmentList);

        int check = 0;
        Map<String, Map<String, Integer>> initMapLeaves = billsStatsService.initialLeaveRMap(leave);

        if(leaveReportDTO.getId().equals("com")){
            check = 2;
            List<LeavesCount> tempForComLeaves = new ArrayList<>();
            if (leaveReportDTO.getEmpId().equals("0")) {
               tempForComLeaves = employeeLeavesRepository.countAllByLeavesCategoriesId(leaveReportDTO.getFromDate(), leaveReportDTO.getToDate());
            }
            if (!leaveReportDTO.getEmpId().equals("0")) {
                tempForComLeaves = employeeLeavesRepository.countAllByLeavesCategoriesIdAndEmployeeId(leaveReportDTO.getFromDate(), leaveReportDTO.getToDate(), Long.parseLong(leaveReportDTO.getEmpId()));
            }

            for (var leaveC : tempForComLeaves) {
                LeavesCategories leavesCategories = leaveCategoryRepository.findOneById(leaveC.getLeaveType());
                String leaveType = leavesCategories.getLeaveType();

                Map<String, Integer> subMapLeave = initMapLeaves.get(leaveType);

                Integer leaveCnt = leaveC.getLeaveCount().intValue();
                // log.info(String.format("BEFORE SubMapLeave for month %s is %s and count is %d", monthNameL, subMapLeave, leaveCnt));

                switch (leaveC.getLeaveStatus()) {
                    case "APPROVED":
                        subMapLeave.replace("Approved", leaveCnt);
                        break;
                    case "REJECTED":
                        subMapLeave.replace("Rejected", leaveCnt);
                        break;
                    case "PENDING":
                        subMapLeave.replace("Pending", leaveCnt);
                        break;
                }
             //   log.info(String.format("AFTER SubMapLeave for leaves types %s is %s and count is %d", leaveType, subMapLeave, leaveCnt));

            }
          //  log.info("result map" + initMapLeaves);
            List<AllLeaveTypesData> allLeaveTypesDataList = new ArrayList<>();
            for(Map.Entry<String, Map<String, Integer>> entry : initMapLeaves.entrySet()){
                allLeaveTypesDataList.add(new AllLeaveTypesData(
                        entry.getKey(),
                        entry.getValue().get("Approved").byteValue(),
                        entry.getValue().get("Pending").byteValue(),
                        entry.getValue().get("Rejected").byteValue(),
                        entry.getValue().get("Approved").byteValue()+entry.getValue().get("Rejected").byteValue()+entry.getValue().get("Pending").byteValue(),
                        entry.getValue().get("Approved").byteValue()+entry.getValue().get("Rejected").byteValue()
                ));
            }
          //  log.info("Result List" + allLeaveTypesDataList);
            model.addAttribute("leaveMap" , allLeaveTypesDataList);

        }else {
            LeavesCategories cat = leaveCategoryRepository.findOneById(Long.parseLong(leaveReportDTO.getId()));


            List<LeavesCount> temp = new ArrayList<>();

            if (leaveReportDTO.getEmpId().equals("0")) {
                temp = employeeLeavesRepository.countAllByDateCreatedAndLeavesCategoriesId(leaveReportDTO.getFromDate(), leaveReportDTO.getToDate(), Long.parseLong(leaveReportDTO.getId()));
            }
            if (!leaveReportDTO.getEmpId().equals("0")) {
                temp = employeeLeavesRepository.countAllByDateCreatedAndLeavesCategoriesIdAndEmployeeId(leaveReportDTO.getFromDate(), leaveReportDTO.getToDate(), Long.parseLong(leaveReportDTO.getId()), Long.parseLong(leaveReportDTO.getEmpId()));
            }

            for (var a : temp) {
                if (PENDING.name().equals(a.getLeaveStatus())) {
                    pendingL = a.getLeaveCount();
                    System.out.println();
                }
                if (APPROVED.name().equals(a.getLeaveStatus())) {
                    approvedL = a.getLeaveCount();
                }
                if (REJECTED.name().equals(a.getLeaveStatus())) {
                    rejectedL = a.getLeaveCount();
                }
            }
            totalL = pendingL + approvedL + rejectedL;
            processedL = approvedL + rejectedL;
            model.addAttribute("cat", cat.getLeaveType());


            check = 1;
        }

        model.addAttribute("TL", totalL);
        model.addAttribute("proL", processedL);
        model.addAttribute("PL", pendingL);
        model.addAttribute("AL", approvedL);
        model.addAttribute("RL", rejectedL);

        model.addAttribute("leaveCatList",leave);
        model.addAttribute("check", check);
        return "adminLRequestHistory";
    }











    @GetMapping(value = "/billsClaimsHistory")
    public String BillsClaimsHistory(Model model) {
        ApprovedStatus status1 = APPROVED;
        ApprovedStatus status2 = REJECTED;

        List<EmployeeBillsClaim> employeeBillsClaimsList1 = employeeBillsClaimRepository.findAllByStatus(status1);
        List<EmployeeBillsClaim> employeeBillsClaimsList2 = employeeBillsClaimRepository.findAllByStatus(status2);
        List<Department> departmentList = departmentRepository.findAll();

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("AList", employeeBillsClaimsList1);
        model.addAttribute("RList", employeeBillsClaimsList2);

        return "adminBRequestHistory";
    }
    @GetMapping(value = "/findBillReport")
    public String findBillReport(@Valid BIllReportDTO bIllReportDTO,  Model model){

        List<Department> departmentList = departmentRepository.findAll();

        model.addAttribute("departmentList", departmentList);

        List<EmployeeBillsCount> temp=new ArrayList<>();
        List<EmployeeBillsCount> temp1=new ArrayList<>();

        if(bIllReportDTO.getEmpId().equals("0")) {
            temp = employeeBillsClaimRepository.countAllByDateCreated(bIllReportDTO.getFromDate(), bIllReportDTO.getToDate());
            temp1=employeeBillsClaimRepository.countAllByDateCreatedAndBillType(bIllReportDTO.getFromDate(),bIllReportDTO.getToDate());

        }
        if(!bIllReportDTO.getEmpId().equals("0")){
            temp=employeeBillsClaimRepository.countAllByDateCreatedAndEmployeeId(bIllReportDTO.getFromDate(),bIllReportDTO.getToDate(),Long.parseLong(bIllReportDTO.getEmpId()));
            temp1=employeeBillsClaimRepository.countAllByDateCreatedAndEmployeeIdAndBillType(bIllReportDTO.getFromDate(),bIllReportDTO.getToDate(),Long.parseLong(bIllReportDTO.getEmpId()));
       }
        model.addAttribute("temp1",temp1);
        model.addAttribute("temp", temp);
        int check = 1;
        model.addAttribute("check", check);

        return "adminBRequestHistory";
        }


    }



