package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.entity.User;

public interface NotificationService {
    void sendAssignmentNotification(User student, Assignment assignment);
    void sendGradeNotification(User student, Submission submission);
}