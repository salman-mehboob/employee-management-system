package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.EmployeeLeaves;
import com.econception.employemanagement.domain.LeavesCategories;
import com.econception.employemanagement.dto.EmployeeBillsMonthlyCount;
import com.econception.employemanagement.dto.LeavesCount;

import com.econception.employemanagement.enumeration.ApprovedStatus;
import com.econception.employemanagement.enumeration.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeLeavesRepository extends JpaRepository<EmployeeLeaves, Long> {
    List<EmployeeLeaves> findAllById(Long id);
    List<EmployeeLeaves> findAllByEmployeeIdOrderByIdDesc(Long id);
    List<EmployeeLeaves> findAllByStatusAndEmployeeId(ApprovedStatus approvedStatus, Long id);
    List<EmployeeLeaves> findAllByOrderByDateCreatedDesc();

    List<EmployeeLeaves> findAllByStatus(ApprovedStatus approvedStatus);
   Long countAllByLeavesCategories_Id(long a);
    Long countAllByLeavesCategories_IdAndStatus(long a,ApprovedStatus b);
    Long countAllByStatus(ApprovedStatus approvedStatus);
    Long countAllByStatusAndEmployeeId(ApprovedStatus a, Long b);
    Long countAllByEmployeeId(Long id);
    Long countAllByStatusAndLeavesCategories(ApprovedStatus approvedStatus, LeavesCategories leavesCategories);

    @Query(value = "Select month(t.date_created) as leaveMonth, t.status as leaveStatus, count(t.status) as leaveCount from employee_leaves as t " +
            "where t.date_created > :date group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<LeavesCount> countMonthlyLeave(@Param("date") String dateCreated);

    @Query(value = "Select month(t.date_created) as leaveMonth, t.status as leaveStatus, count(t.status) as leaveCount from employee_leaves as t " +
            "where t.date_created > :date and t.employee_id = :id group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<LeavesCount> countMonthlyLeaveByEmployeeId(@Param("date") String dateCreated,@Param("id") Long id);


    @Query(value = "Select  t.status as leaveStatus , count(t.status) as  leaveCount from employee_leaves as t " +
            "where t.date_created > :fromDate and t.date_created < :toDate and t.leaves_categories_id = :id group by t.leaves_categories_id,t.status order by t.leaves_categories_id", nativeQuery = true)
    List<LeavesCount> countAllByDateCreatedAndLeavesCategoriesId(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("id") Long id);

    @Query(value = "Select  t.status as leaveStatus , count(t.status) as  leaveCount from employee_leaves as t " +
            "where t.date_created > :fromDate and t.date_created < :toDate and t.leaves_categories_id = :id and t.employee_id = :empId group by t.leaves_categories_id,t.status order by t.leaves_categories_id", nativeQuery = true)
    List<LeavesCount> countAllByDateCreatedAndLeavesCategoriesIdAndEmployeeId(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("id") Long id, @Param("empId") Long empId);

    @Query(value = "Select t.leaves_categories_id as leaveType, t.status as leaveStatus , count(t.status) as  leaveCount from employee_leaves as t where t.date_created > :fromDate and t.date_created < :toDate group by t.status;", nativeQuery = true)
    List<LeavesCount> countAllByLeavesCategoriesId(@Param("fromDate") String fromDate,@Param("toDate") String toDate);


    @Query(value = "Select t.leaves_categories_id as leaveType, t.status as leaveStatus , count(t.status) as  leaveCount from employee_leaves as t where t.date_created > :fromDate and t.date_created < :toDate and t.employee_id = :id   group by t.status;", nativeQuery = true)
    List<LeavesCount> countAllByLeavesCategoriesIdAndEmployeeId(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("id") Long id);



}
