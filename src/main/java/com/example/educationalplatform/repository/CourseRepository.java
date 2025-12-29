package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
    List<Course> findByCategory(Category category);
    List<Course> findByTitleContainingIgnoreCase(String title);
}