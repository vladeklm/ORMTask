package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.Lesson;
import com.example.educationalplatform.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLesson(Lesson lesson);
    List<Assignment> findByModule(Module module);
}