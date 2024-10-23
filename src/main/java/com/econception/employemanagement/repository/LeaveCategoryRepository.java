package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.LeavesCategories;
import com.econception.employemanagement.enumeration.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveCategoryRepository extends JpaRepository<LeavesCategories, Long> {

    LeavesCategories findOneById(long id);

}
