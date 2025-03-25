# Quiz Application

A simple web-based quiz application built with Spring Boot that allows users to take quizzes and administrators to manage questions and quizzes.

## Features

- Take quizzes with multiple-choice questions
- View quiz results with correct/incorrect answers highlighted
- Admin panel for managing questions and quizzes
- Persistent storage using H2 database

## Prerequisites

- Java 17 or higher
- Gradle

## Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Gradle:
   ```bash
   ./gradlew bootRun
   ```
4. Access the application at `http://localhost:8080`

## Usage

### Taking a Quiz
1. Visit the home page at `http://localhost:8080`
2. Select a quiz from the available quizzes
3. Answer all questions
4. Submit the quiz to see your results

### Managing Questions and Quizzes (Admin)
1. Access the admin panel at `http://localhost:8080/admin`
2. Add new questions with multiple-choice options
3. Create new quizzes by selecting questions
4. View existing questions and quizzes

### Database Access
- H2 Console is available at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./quizdb`
- Username: sa
- Password: password

## Project Structure

```
src/main/java/com/windsurf/quiz/
├── QuizApplication.java
├── controller/
│   └── QuizController.java
├── model/
│   ├── Question.java
│   └── Quiz.java
├── repository/
│   ├── QuestionRepository.java
│   └── QuizRepository.java
└── service/
    └── QuizService.java
```
