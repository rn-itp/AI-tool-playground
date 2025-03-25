package com.firebender.quiz.model;

import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private LocalDateTime submittedAt;

    private int totalQuestions;

    private int correctAnswers;

    @ElementCollection
    @CollectionTable(name = "quiz_result_answers", joinColumns = @JoinColumn(name = "quiz_result_id"))
    private List<QuestionAnswer> answers = new ArrayList<>();

    public QuizResult() {
    }

    public QuizResult(Quiz quiz) {
        this.quiz = quiz;
        this.submittedAt = LocalDateTime.now();
        this.totalQuestions = quiz.getQuestions().size();
    }

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

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    public void addAnswer(QuestionAnswer answer) {
        this.answers.add(answer);
        if (answer.isCorrect()) {
            this.correctAnswers++;
        }
    }

    public double getScore() {
        return totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
    }

    @Embeddable
    public static class QuestionAnswer {
        
        private Long questionId;
        private String questionText;
        private int selectedOptionIndex;
        private boolean correct;

        public QuestionAnswer() {
        }

        public QuestionAnswer(Question question, int selectedOptionIndex) {
            this.questionId = question.getId();
            this.questionText = question.getQuestionText();
            this.selectedOptionIndex = selectedOptionIndex;
            this.correct = question.isCorrect(selectedOptionIndex);
        }

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public String getQuestionText() {
            return questionText;
        }

        public void setQuestionText(String questionText) {
            this.questionText = questionText;
        }

        public int getSelectedOptionIndex() {
            return selectedOptionIndex;
        }

        public void setSelectedOptionIndex(int selectedOptionIndex) {
            this.selectedOptionIndex = selectedOptionIndex;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }
}