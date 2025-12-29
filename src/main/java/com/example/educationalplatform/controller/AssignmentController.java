package com.example.educationalplatform.controller;

import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        try {
            Assignment assignment = assignmentService.getAssignmentById(id);
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<Submission> submitAssignment(@PathVariable Long id, @RequestBody Submission submission) {
        try {
            Submission createdSubmission = assignmentService.submitAssignment(id, submission);
            return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}