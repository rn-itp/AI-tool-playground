package com.firebender.quiz.service;

import com.firebender.quiz.model.Quiz;
import com.firebender.quiz.model.QuizResult;
import com.firebender.quiz.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;

    @Autowired
    public QuizResultService(QuizResultRepository quizResultRepository) {
        this.quizResultRepository = quizResultRepository;
    }

    public List<QuizResult> getAllResults() {
        return quizResultRepository.findAll();
    }

    public Optional<QuizResult> getResultById(Long id) {
        return quizResultRepository.findById(id);
    }

    public QuizResult saveResult(QuizResult result) {
        return quizResultRepository.save(result);
    }

    public QuizResult submitQuiz(Quiz quiz, List<Integer> answers) {
        QuizResult result = new QuizResult(quiz);
        
        for (int i = 0; i < answers.size() && i < quiz.getQuestions().size(); i++) {
            result.addAnswer(new QuizResult.QuestionAnswer(quiz.getQuestions().get(i), answers.get(i)));
        }
        
        return saveResult(result);
    }
}