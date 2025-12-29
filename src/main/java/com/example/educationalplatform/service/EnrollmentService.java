package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Enrollment;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.EnrollmentStatus;
import com.example.educationalplatform.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByStudent(User student) {
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        return enrollmentRepository.findByCourse(course);
    }

    public List<Enrollment> getEnrollmentsByStatus(EnrollmentStatus status) {
        return enrollmentRepository.findByStatus(status);
    }

    public Optional<Enrollment> getEnrollmentByStudentAndCourse(User student, Course course) {
        return enrollmentRepository.findByStudentAndCourse(student, course);
    }

    public Enrollment enrollStudentToCourse(User student, Course course) {
        // Проверяем, не записан ли уже студент на этот курс
        Optional<Enrollment> existingEnrollment = getEnrollmentByStudentAndCourse(student, course);
        if (existingEnrollment.isPresent()) {
            return existingEnrollment.get();
        }

        // Создаем новую запись
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        return saveEnrollment(enrollment);
    }
}