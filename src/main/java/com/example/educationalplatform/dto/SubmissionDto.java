package com.example.educationalplatform.dto;

public class SubmissionDto {
    private Long id;
    private String content;
    private Long assignmentId;
    private Long studentId;
    private String grade;

    // Constructors
    public SubmissionDto() {}

    public SubmissionDto(Long id, String content, Long assignmentId, Long studentId, String grade) {
        this.id = id;
        this.content = content;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.grade = grade;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}