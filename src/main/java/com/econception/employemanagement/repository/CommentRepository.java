package com.econception.employemanagement.repository;

import com.econception.employemanagement.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByParentAndModuleId(Long a, Long c);
}
