package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.EmployeeTasks;
import com.econception.employemanagement.dto.EmployeeTasksCount;
import com.econception.employemanagement.dto.EmployeeTasksMonthlyCount;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeTasksRepository extends JpaRepository<EmployeeTasks,Long> {
    List<EmployeeTasks> findAllById(Long id);
    List<EmployeeTasks> findAllByEmployeeIdOrderByIdDesc(long id);
    List<EmployeeTasks> findAllByStatusAndEmployeeIdOrderByDateCreatedDesc(String d, Long id);
    EmployeeTasks findOneById(long id);
    List<EmployeeTasks> findByStatus(String a);
    List<EmployeeTasks> findByStatusAndEmployeeId(String a, Long b);
    Long countAllByStatus(String a);
    Long countAllByStatusAndEmployeeId(String a, Long b);
    Long countAllByEmployeeId(Long id);
    List<EmployeeTasks> findAllByOrderByIdDesc();



    @Query(value = "Select t.status as taskStatus, count(t.status) as taskCount from employee_tasks as t group by t.status", nativeQuery = true)
    List<EmployeeTasksCount> countTasks();

    @Query(value = "Select month(t.date_created) as taskMonth, t.status as taskStatus, count(t.status) as taskCount from employee_tasks as t where t.date_created > :date group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<EmployeeTasksMonthlyCount> countMonthlyTasks(@Param("date") String dateCreated);

    @Query(value = "Select month(t.date_created) as taskMonth, t.status as taskStatus, count(t.status) as taskCount from employee_tasks as t " +
            "where t.date_created > :date and t.employee_id =:id group by month(t.date_created), t.status order by t.date_created", nativeQuery = true)
    List<EmployeeTasksMonthlyCount> countMonthlyTasksByEmployeeId(@Param("date") String dateCreated, @Param("id") Long id);
}
