package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.User;
import com.econception.employemanagement.enumeration.ApprovedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    Optional<User> findALLById(Long aLong);

    User findOneById(Long id);

    List<User> findAllByStatusOrderByIdDesc(ApprovedStatus approvedStatus);

    List<User> findAllByRoleName(String roleName);

    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findAllByActive(boolean a);


}
