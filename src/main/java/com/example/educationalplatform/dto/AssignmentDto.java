package com.example.educationalplatform.dto;

public class AssignmentDto {
    private Long id;
    private String title;
    private String description;
    private Long lessonId;
    private Long moduleId;

    // Constructors
    public AssignmentDto() {}

    public AssignmentDto(Long id, String title, String description, Long lessonId, Long moduleId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lessonId = lessonId;
        this.moduleId = moduleId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}