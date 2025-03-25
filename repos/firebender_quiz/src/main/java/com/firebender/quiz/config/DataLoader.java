package com.firebender.quiz.config;

import com.firebender.quiz.model.Question;
import com.firebender.quiz.model.Quiz;
import com.firebender.quiz.repository.QuestionRepository;
import com.firebender.quiz.repository.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(QuestionRepository questionRepository, QuizRepository quizRepository) {
        return args -> {
            // Create some questions
            Question q1 = new Question(
                    "What is the capital of France?",
                    Arrays.asList("London", "Berlin", "Paris", "Madrid"),
                    2 // Paris (index 2) is correct
            );
            
            Question q2 = new Question(
                    "Which programming language is this application built with?",
                    Arrays.asList("Python", "Java", "JavaScript", "C#"),
                    1 // Java (index 1) is correct
            );
            
            Question q3 = new Question(
                    "What does JVM stand for?",
                    Arrays.asList("Java Virtual Machine", "JavaScript Virtual Machine", "Java Visual Model", "Java Vector Model"),
                    0 // Java Virtual Machine (index 0) is correct
            );
            
            Question q4 = new Question(
                    "Which of these is NOT a Spring Framework module?",
                    Arrays.asList("Spring Boot", "Spring MVC", "Spring Hibernate", "Spring Security"),
                    2 // Spring Hibernate (index 2) is not a real Spring module
            );
            
            Question q5 = new Question(
                    "What annotation is used to define a Spring Boot application?",
                    Arrays.asList("@SpringBootApp", "@Application", "@SpringApplication", "@SpringBootApplication"),
                    3 // @SpringBootApplication (index 3) is correct
            );
            
            // Save questions to repository
            List<Question> questions = Arrays.asList(q1, q2, q3, q4, q5);
            questionRepository.saveAll(questions);
            
            // Create quizzes
            Quiz generalKnowledge = new Quiz("General Knowledge", "Test your general knowledge with this quiz");
            generalKnowledge.addQuestion(q1);
            
            Quiz javaQuiz = new Quiz("Java Programming", "Test your Java knowledge");
            javaQuiz.addQuestion(q2);
            javaQuiz.addQuestion(q3);
            
            Quiz springQuiz = new Quiz("Spring Framework", "Test your Spring Framework knowledge");
            springQuiz.addQuestion(q4);
            springQuiz.addQuestion(q5);
            springQuiz.addQuestion(q3); // Adding Java VM question to Spring quiz as well
            
            // Save quizzes to repository
            quizRepository.saveAll(Arrays.asList(generalKnowledge, javaQuiz, springQuiz));
        };
    }
}