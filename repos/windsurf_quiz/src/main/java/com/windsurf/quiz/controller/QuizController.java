package com.windsurf.quiz.controller;

import com.windsurf.quiz.model.Question;
import com.windsurf.quiz.model.Quiz;
import com.windsurf.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "home";
    }

    @GetMapping("/quiz/{id}")
    public String takeQuiz(@PathVariable Long id, Model model) {
        quizService.getQuizById(id).ifPresent(quiz -> model.addAttribute("quiz", quiz));
        return "quiz";
    }

    @PostMapping("/quiz/{id}/submit")
    public String submitQuiz(@PathVariable Long id, @RequestParam List<Integer> answers, Model model) {
        Quiz quiz = quizService.getQuizById(id).orElseThrow();
        int score = quizService.calculateScore(quiz, answers);
        model.addAttribute("quiz", quiz);
        model.addAttribute("score", score);
        model.addAttribute("totalQuestions", quiz.getQuestions().size());
        model.addAttribute("userAnswers", answers);
        return "result";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        model.addAttribute("questions", quizService.getAllQuestions());
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        model.addAttribute("newQuestion", new Question());
        model.addAttribute("newQuiz", new Quiz());
        return "admin";
    }

    @PostMapping("/admin/question")
    public String addQuestion(@ModelAttribute Question question) {
        quizService.saveQuestion(question);
        return "redirect:/admin";
    }

    @PostMapping("/admin/quiz")
    public String addQuiz(@ModelAttribute Quiz quiz) {
        quizService.saveQuiz(quiz);
        return "redirect:/admin";
    }
}
