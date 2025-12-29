package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.Enrollment;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    List<Enrollment> findByCourse(Course course);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}