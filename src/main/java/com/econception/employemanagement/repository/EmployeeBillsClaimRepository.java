package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.EmployeeBillsClaim;
import com.econception.employemanagement.domain.EmployeeLeaves;
import com.econception.employemanagement.dto.EmployeeBillsCount;
import com.econception.employemanagement.dto.EmployeeBillsMonthlyCount;
import com.econception.employemanagement.dto.EmployeeTasksCount;
import com.econception.employemanagement.dto.EmployeeTasksMonthlyCount;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeBillsClaimRepository extends JpaRepository<EmployeeBillsClaim, Long> {

    List<EmployeeBillsClaim> findAllByEmployeeIdOrderByIdDesc(Long id);

    List<EmployeeBillsClaim> findAllByStatusAndEmployeeId(ApprovedStatus approvedStatus, Long id);

    List<EmployeeBillsClaim> findAllById(Long id);

    List<EmployeeBillsClaim> findAllByStatus(ApprovedStatus approvedStatus);
    List<EmployeeBillsClaim> findAllByOrderByDateCreatedDesc();
    EmployeeBillsClaim findALLByEmployeeId(Long id);

    EmployeeBillsClaim findOneById(long a);

    Long countAllByStatus(ApprovedStatus approvedStatus);
    Long countAllByStatusAndEmployeeId(ApprovedStatus a, Long b);
    Long countAllByEmployeeId(Long id);

    @Query(value = "Select b.status as billStatus, count(b.status) as billCount from employee_bills_claim as b group by b.status", nativeQuery = true)
    List<EmployeeBillsCount> countBills();

    @Query(value = "Select month(t.date_created) as billMonth, t.status as billStatus, count(t.status) as billCount from employee_bills_claim as t " +
            "where t.date_created > :date  group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<EmployeeBillsMonthlyCount> countMonthlyBills(@Param("date") String dateCreated);

    @Query(value = "Select month(t.date_created) as billMonth, t.status as billStatus, count(t.status) as billCount from employee_bills_claim as t " +
            "where t.date_created > :date and t.employee_id = :id group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<EmployeeBillsMonthlyCount> countMonthlyBillsByEmployeeId(@Param("date") String dateCreated, @Param("id") Long id);



    @Query(value = "Select b.bill_type as BillType,b.status as BillStatus,count(b.status) as billCount,sum(b.amount) as ClaimAmount,sum(b.approved_amount) as ApprovedAmount from employee_bills_claim as " +
            "b where b.date_created > :fromDate and b.date_created < :toDate and b.bill_type='MEDICAL' " +
            "group by b.bill_type,b.status",nativeQuery = true)
    List<EmployeeBillsCount> countAllByDateCreated(@Param("fromDate") String fromDate,@Param("toDate") String toDate);


    @Query(value = "Select b.bill_type as BillType,b.status as BillStatus,count(b.status) as billCount,sum(b.amount) as ClaimAmount,sum(b.approved_amount) as ApprovedAmount from employee_bills_claim as " +
            "b where b.date_created > :fromDate and b.date_created < :toDate and b.bill_type='TRAVEL' " +
            "group by b.bill_type,b.status",nativeQuery = true)
    List<EmployeeBillsCount> countAllByDateCreatedAndBillType(@Param("fromDate") String fromDate,@Param("toDate") String toDate);



    @Query(value = "Select b.bill_type as BillType,b.status as BillStatus,count(b.status) as billCount,sum(b.amount) as ClaimAmount,sum(b.approved_amount) as ApprovedAmount from employee_bills_claim as " +
            "b where b.date_created > :fromDate and b.date_created < :toDate "  +
            " and b.employee_id = :empId and b.bill_type='MEDICAL' group by b.bill_type,b.status order by b.employee_id",nativeQuery = true)
    List<EmployeeBillsCount> countAllByDateCreatedAndEmployeeId(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empId") Long empId);


    @Query(value = "Select b.bill_type as BillType,b.status as BillStatus,count(b.status) as billCount,sum(b.amount) as ClaimAmount,sum(b.approved_amount) as ApprovedAmount from employee_bills_claim as " +
            "b where b.date_created > :fromDate and b.date_created < :toDate "  +
            " and b.employee_id = :empId and b.bill_type='TRAVEL' group by b.bill_type,b.status order by b.employee_id",nativeQuery = true)
    List<EmployeeBillsCount> countAllByDateCreatedAndEmployeeIdAndBillType(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empId") Long empId);
}
//    select status as billStatus , count(status) as billCount from employee_bills_claim group by status;
//        select month(date_created) as billMonth, status as billStatus, count(status) as billCount
//        from employee_bills_claim where date_created  > 2005 group by month(date_created),status order by date_created; 