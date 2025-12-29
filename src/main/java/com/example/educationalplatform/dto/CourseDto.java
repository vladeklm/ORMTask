package com.example.educationalplatform.dto;

import java.util.List;

public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long teacherId;
    private List<Long> moduleIds;
    private List<Long> tagIds;

    // Constructors
    public CourseDto() {}

    public CourseDto(Long id, String title, String description, Long categoryId, Long teacherId, List<Long> moduleIds, List<Long> tagIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.teacherId = teacherId;
        this.moduleIds = moduleIds;
        this.tagIds = tagIds;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Long> getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(List<Long> moduleIds) {
        this.moduleIds = moduleIds;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}