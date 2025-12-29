package com.example.educationalplatform.mapper;

import com.example.educationalplatform.dto.CourseDto;
import com.example.educationalplatform.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        if (course.getCategory() != null) {
            dto.setCategoryId(course.getCategory().getId());
        }

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());
        }

        // Note: Module and Tag mapping would require additional logic
        // to extract IDs from collections

        return dto;
    }

    public Course toEntity(CourseDto dto) {
        if (dto == null) {
            return null;
        }

        Course course = new Course();
        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        // Note: Category, Teacher, Modules, and Tags would need to be
        // set separately by the service layer using their IDs

        return course;
    }
}