package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Quiz;
import com.example.educationalplatform.entity.QuizSubmission;
import com.example.educationalplatform.entity.Module;
import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.repository.QuizRepository;
import com.example.educationalplatform.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public List<Quiz> getQuizzesByModule(Module module) {
        return quizRepository.findByModule(module);
    }

    public List<Quiz> getQuizzesByCourse(Course course) {
        return quizRepository.findByCourse(course);
    }

    public QuizSubmission submitQuiz(QuizSubmission quizSubmission) {
        return quizSubmissionRepository.save(quizSubmission);
    }

    public List<QuizSubmission> getSubmissionsByQuiz(Quiz quiz) {
        return quizSubmissionRepository.findByQuiz(quiz);
    }

    public List<QuizSubmission> getSubmissionsByStudent(com.example.educationalplatform.entity.User student) {
        return quizSubmissionRepository.findByStudent(student);
    }
}