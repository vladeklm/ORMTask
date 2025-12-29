package com.example.educationalplatform.mapper;

import com.example.educationalplatform.dto.SubmissionDto;
import com.example.educationalplatform.entity.Submission;
import org.springframework.stereotype.Component;

@Component
public class SubmissionMapper {

    public SubmissionDto toDto(Submission submission) {
        if (submission == null) {
            return null;
        }

        SubmissionDto dto = new SubmissionDto();
        dto.setId(submission.getId());
        dto.setContent(submission.getContent());
        dto.setGrade(submission.getGrade());

        if (submission.getAssignment() != null) {
            dto.setAssignmentId(submission.getAssignment().getId());
        }

        if (submission.getStudent() != null) {
            dto.setStudentId(submission.getStudent().getId());
        }

        return dto;
    }

    public Submission toEntity(SubmissionDto dto) {
        if (dto == null) {
            return null;
        }

        Submission submission = new Submission();
        submission.setId(dto.getId());
        submission.setContent(dto.getContent());
        submission.setGrade(dto.getGrade());

        // Note: Assignment and Student would need to be
        // set separately by the service layer using their IDs

        return submission;
    }
}