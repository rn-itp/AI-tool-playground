package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Map<String, Object> evaluateQuiz(Long quizId, Map<Long, String> answers) {
        Quiz quiz = getQuiz(quizId);
        int correctAnswers = 0;
        Map<Long, Boolean> results = new HashMap<>();

        for (Question question : quiz.getQuestions()) {
            String userAnswer = answers.get(question.getId());
            boolean isCorrect = question.getCorrectOption().equals(userAnswer);
            results.put(question.getId(), isCorrect);
            if (isCorrect) {
                correctAnswers++;
            }
        }

        Map<String, Object> evaluation = new HashMap<>();
        evaluation.put("totalQuestions", quiz.getQuestions().size());
        evaluation.put("correctAnswers", correctAnswers);
        evaluation.put("score", (double) correctAnswers / quiz.getQuestions().size() * 100);
        evaluation.put("questionResults", results);

        return evaluation;
    }
} 