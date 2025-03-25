package com.firebender.quiz.controller;

import com.firebender.quiz.model.Quiz;
import com.firebender.quiz.model.QuizResult;
import com.firebender.quiz.service.QuizResultService;
import com.firebender.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WebController {

    private final QuizService quizService;
    private final QuizResultService quizResultService;

    @Autowired
    public WebController(QuizService quizService, QuizResultService quizResultService) {
        this.quizService = quizService;
        this.quizResultService = quizResultService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Quiz> recentQuizzes = quizService.getAllQuizzes();
        model.addAttribute("recentQuizzes", recentQuizzes);
        return "index";
    }

    @GetMapping("/quizzes")
    public String quizzesList(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "quizzes";
    }

    @GetMapping("/quizzes/{id}")
    public String quizDetail(@PathVariable Long id, Model model) {
        return quizService.getQuizById(id)
                .map(quiz -> {
                    model.addAttribute("quiz", quiz);
                    return "quiz";
                })
                .orElse("redirect:/quizzes");
    }

    @GetMapping("/quiz-results/{id}")
    public String quizResult(@PathVariable Long id, Model model) {
        return quizResultService.getResultById(id)
                .map(result -> {
                    model.addAttribute("result", result);
                    return "quiz-result";
                })
                .orElse("redirect:/quizzes");
    }
}