# Quiz Application

A simple online quiz application built with Java, Spring Boot, and Gradle.

## Features

- Take multiple-choice quizzes
- View detailed results after completing a quiz
- RESTful API for questions, quizzes, and quiz results
- In-memory H2 database for data storage

## Requirements

- Java 17 or higher
- Gradle

## Getting Started

### Running the Application

1. Clone this repository
2. Navigate to the project directory
3. Run the application using Gradle:

```bash
./gradlew bootRun
```

4. Access the application in your browser at http://localhost:8080

### Using the Application

1. Browse available quizzes from the home page
2. Select a quiz to start
3. Answer all questions and submit
4. View your results and correct answers

## API Documentation

The application provides RESTful APIs for managing quizzes:

### Questions API

- `GET /api/questions` - Get all questions
- `GET /api/questions/{id}` - Get a specific question
- `POST /api/questions` - Create a new question
- `PUT /api/questions/{id}` - Update a question
- `DELETE /api/questions/{id}` - Delete a question

### Quizzes API

- `GET /api/quizzes` - Get all quizzes
- `GET /api/quizzes/{id}` - Get a specific quiz
- `POST /api/quizzes` - Create a new quiz
- `PUT /api/quizzes/{id}` - Update a quiz
- `DELETE /api/quizzes/{id}` - Delete a quiz

### Quiz Results API

- `GET /api/quiz-results` - Get all quiz results
- `GET /api/quiz-results/{id}` - Get a specific quiz result
- `POST /api/quiz-results/submit?quizId={quizId}` - Submit a quiz with answers

## Project Structure

```
src/main/
├── java/com/firebender/quiz/
│   ├── QuizApplication.java           # Main application class
│   ├── controller/                    # REST controllers
│   │   ├── QuestionController.java    # Question API endpoints
│   │   ├── QuizController.java        # Quiz API endpoints
│   │   ├── QuizResultController.java  # Quiz result API endpoints
│   │   └── WebController.java         # Web page controllers
│   ├── model/                         # Entity classes
│   │   ├── Question.java              # Question entity
│   │   ├── Quiz.java                  # Quiz entity
│   │   └── QuizResult.java            # Quiz result entity
│   ├── repository/                    # Data repositories
│   │   ├── QuestionRepository.java    # Question repository
│   │   ├── QuizRepository.java        # Quiz repository
│   │   └── QuizResultRepository.java  # Quiz result repository
│   ├── service/                       # Business logic
│   │   ├── QuestionService.java       # Question service
│   │   ├── QuizService.java           # Quiz service
│   │   └── QuizResultService.java     # Quiz result service
│   └── config/                        # Configuration
│       └── DataLoader.java            # Initial data loader
└── resources/
    ├── application.properties         # Application configuration
    └── templates/                     # Thymeleaf templates
        ├── index.html                 # Home page
        ├── quizzes.html               # Quiz listing page
        ├── quiz.html                  # Quiz taking page
        └── quiz-result.html           # Quiz results page
```

## Future Improvements

- User authentication and user-specific quiz history
- Quiz categorization and filtering
- Quiz creation through the UI
- Timed quizzes
- More question types (true/false, multiple selection, etc.)