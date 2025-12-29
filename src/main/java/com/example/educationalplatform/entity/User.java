package com.example.educationalplatform.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    // Связь один ко многим с курсами, которые ведет преподаватель
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> coursesTaught;

    // Связь один ко многим с отправленными решениями заданий
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Submission> submissions;

    // Связь один ко многим с результатами тестов
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<QuizSubmission> quizSubmissions;

    // Связь один ко многим с отзывами на курсы
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<CourseReview> reviews;

    // Связь один к одному с профилем пользователя
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profile;

    // Конструкторы
    public User() {}

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<QuizSubmission> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void setQuizSubmissions(List<QuizSubmission> quizSubmissions) {
        this.quizSubmissions = quizSubmissions;
    }

    public List<CourseReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<CourseReview> reviews) {
        this.reviews = reviews;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }
}