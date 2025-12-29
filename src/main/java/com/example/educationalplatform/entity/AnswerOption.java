package com.example.educationalplatform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answer_options")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(length = 1000, nullable = false)
    private String text;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    // Конструкторы
    public AnswerOption() {}

    public AnswerOption(Question question, String text, Boolean isCorrect) {
        this.question = question;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}