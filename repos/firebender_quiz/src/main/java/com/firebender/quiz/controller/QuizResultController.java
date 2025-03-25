package com.firebender.quiz.controller;

import com.firebender.quiz.model.Quiz;
import com.firebender.quiz.model.QuizResult;
import com.firebender.quiz.service.QuizResultService;
import com.firebender.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz-results")
public class QuizResultController {

    private final QuizResultService quizResultService;
    private final QuizService quizService;

    @Autowired
    public QuizResultController(QuizResultService quizResultService, QuizService quizService) {
        this.quizResultService = quizResultService;
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizResult> getAllResults() {
        return quizResultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResult> getResultById(@PathVariable Long id) {
        return quizResultService.getResultById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResult> submitQuiz(@RequestParam Long quizId, @RequestBody List<Integer> answers) {
        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        if (!quizOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        QuizResult result = quizResultService.submitQuiz(quizOptional.get(), answers);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}