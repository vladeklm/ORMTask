package com.example.educationalplatform.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Связь многие ко многим с курсами
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Course> courses;

    // Конструкторы
    public Tag() {}

    public Tag(String name) {
        this.name = name;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}