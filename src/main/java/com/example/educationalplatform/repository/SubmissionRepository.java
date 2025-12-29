package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment(Assignment assignment);
    List<Submission> findByStudent(User student);
    List<Submission> findByAssignmentAndStudent(Assignment assignment, User student);
}