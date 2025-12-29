package com.example.educationalplatform.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(length = 2000, nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    // Связь один ко многим с вариантами ответов
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AnswerOption> options;

    // Конструкторы
    public Question() {}

    public Question(Quiz quiz, String text, QuestionType type) {
        this.quiz = quiz;
        this.text = text;
        this.type = type;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOption> options) {
        this.options = options;
    }
}