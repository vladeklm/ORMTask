package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Quiz;
import com.example.educationalplatform.entity.Module;
import com.example.educationalplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByModule(Module module);
    List<Quiz> findByCourse(Course course);
}