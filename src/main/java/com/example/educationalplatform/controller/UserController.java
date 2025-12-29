package com.example.educationalplatform.controller;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{id}/courses/{courseId}/enroll")
    public ResponseEntity<Course> enrollUserToCourse(@PathVariable Long id, @PathVariable Long courseId) {
        try {
            Course enrolledCourse = userService.enrollUserToCourse(id, courseId);
            return new ResponseEntity<>(enrolledCourse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/assignments")
    public ResponseEntity<List<Submission>> getUserSubmissions(@PathVariable Long id) {
        try {
            List<Submission> submissions = userService.getUserSubmissions(id);
            return new ResponseEntity<>(submissions, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}