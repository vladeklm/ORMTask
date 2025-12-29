package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.entity.Lesson;
import com.example.educationalplatform.entity.Module;
import com.example.educationalplatform.repository.AssignmentRepository;
import com.example.educationalplatform.repository.SubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if (assignment.isPresent()) {
            return assignment.get();
        } else {
            throw new EntityNotFoundException("Assignment with id " + id + " not found");
        }
    }

    public Assignment createAssignment(Assignment assignment) {
        Assignment createdAssignment = assignmentRepository.save(assignment);
        // Здесь можно добавить логику отправки уведомлений студентам,
        // записанным на курс, к которому относится задание
        return createdAssignment;
    }

    public void deleteAssignment(Long id) {
        if (assignmentRepository.existsById(id)) {
            assignmentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Assignment with id " + id + " not found");
        }
    }

    public Submission submitAssignment(Long assignmentId, Submission submission) {
        Assignment assignment = getAssignmentById(assignmentId);
        submission.setAssignment(assignment);
        Submission savedSubmission = submissionRepository.save(submission);
        // Отправляем уведомление студенту об оценке
        if (submission.getGrade() != null && !submission.getGrade().isEmpty()) {
            notificationService.sendGradeNotification(submission.getStudent(), savedSubmission);
        }
        return savedSubmission;
    }

    public List<Assignment> getAssignmentsByLesson(Lesson lesson) {
        return assignmentRepository.findByLesson(lesson);
    }

    public List<Assignment> getAssignmentsByModule(Module module) {
        return assignmentRepository.findByModule(module);
    }

    public Submission saveSubmission(Submission submission) {
        Submission savedSubmission = submissionRepository.save(submission);
        // Отправляем уведомление студенту об оценке
        if (submission.getGrade() != null && !submission.getGrade().isEmpty()) {
            notificationService.sendGradeNotification(submission.getStudent(), savedSubmission);
        }
        return savedSubmission;
    }

    public List<Submission> getSubmissionsByAssignment(Assignment assignment) {
        return submissionRepository.findByAssignment(assignment);
    }

    public List<Submission> getSubmissionsByStudent(com.example.educationalplatform.entity.User student) {
        return submissionRepository.findByStudent(student);
    }
}