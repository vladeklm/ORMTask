package com.example.educationalplatform.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(length = 1000)
    private String description;

    // Связь один ко многим с уроками
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    // Связь один к одному с викториной
    @OneToOne(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Quiz quiz;

    // Связь один ко многим с заданиями
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    // Конструкторы
    public Module() {}

    public Module(Course course, String title, Integer orderIndex) {
        this.course = course;
        this.title = title;
        this.orderIndex = orderIndex;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}