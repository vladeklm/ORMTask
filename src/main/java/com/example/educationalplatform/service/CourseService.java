package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.entity.Category;
import com.example.educationalplatform.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        // Обновляем поля курса
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setCategory(courseDetails.getCategory());
        course.setTeacher(courseDetails.getTeacher());
        course.setModules(courseDetails.getModules());
        course.setTags(courseDetails.getTags());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }
    }

    public List<Course> getCoursesByTeacher(User teacher) {
        return courseRepository.findByTeacher(teacher);
    }

    public List<Course> getCoursesByCategory(Category category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }
}