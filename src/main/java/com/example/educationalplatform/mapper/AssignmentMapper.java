package com.example.educationalplatform.mapper;

import com.example.educationalplatform.dto.AssignmentDto;
import com.example.educationalplatform.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public AssignmentDto toDto(Assignment assignment) {
        if (assignment == null) {
            return null;
        }

        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());

        if (assignment.getLesson() != null) {
            dto.setLessonId(assignment.getLesson().getId());
        }

        if (assignment.getModule() != null) {
            dto.setModuleId(assignment.getModule().getId());
        }

        return dto;
    }

    public Assignment toEntity(AssignmentDto dto) {
        if (dto == null) {
            return null;
        }

        Assignment assignment = new Assignment();
        assignment.setId(dto.getId());
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());

        // Note: Lesson and Module would need to be
        // set separately by the service layer using their IDs

        return assignment;
    }
}