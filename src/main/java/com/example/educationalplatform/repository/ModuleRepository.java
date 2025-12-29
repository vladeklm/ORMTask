package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Module;
import com.example.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourse(Course course);
    List<Module> findByCourseOrderByOrderIndex(Course course);
}