package com.example.educationalplatform.repository;

import com.example.educationalplatform.entity.QuizSubmission;
import com.example.educationalplatform.entity.Quiz;
import com.example.educationalplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByQuiz(Quiz quiz);
    List<QuizSubmission> findByStudent(User student);
    List<QuizSubmission> findByQuizAndStudent(Quiz quiz, User student);
}