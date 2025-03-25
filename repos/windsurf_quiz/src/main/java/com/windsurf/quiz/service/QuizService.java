package com.windsurf.quiz.service;

import com.windsurf.quiz.model.Question;
import com.windsurf.quiz.model.Quiz;
import com.windsurf.quiz.repository.QuestionRepository;
import com.windsurf.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public int calculateScore(Quiz quiz, List<Integer> userAnswers) {
        int score = 0;
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size() && i < userAnswers.size(); i++) {
            if (questions.get(i).getCorrectOptionIndex() == userAnswers.get(i)) {
                score++;
            }
        }
        return score;
    }
}
