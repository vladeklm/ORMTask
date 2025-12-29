package com.example.educationalplatform.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "course_id"})
})
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enroll_date", nullable = false)
    private LocalDate enrollDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    // Конструкторы
    public Enrollment() {}

    public Enrollment(User student, Course course, LocalDate enrollDate) {
        this.student = student;
        this.course = course;
        this.enrollDate = enrollDate;
        this.status = EnrollmentStatus.ACTIVE;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}