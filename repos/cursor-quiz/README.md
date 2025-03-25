# Quiz Application

A simple Spring Boot-based quiz application that allows users to create and take quizzes.

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
4. The application will be available at `http://localhost:8080`

## API Endpoints

### Questions
- POST `/api/quizzes/questions` - Add a new question
  ```json
  {
    "questionText": "What is the capital of France?",
    "optionA": "London",
    "optionB": "Paris",
    "optionC": "Berlin",
    "optionD": "Madrid",
    "correctOption": "B"
  }
  ```

### Quizzes
- POST `/api/quizzes` - Create a new quiz
  ```json
  {
    "title": "Geography Quiz",
    "description": "Test your knowledge of world capitals",
    "questions": [
      {"id": 1},
      {"id": 2}
    ]
  }
  ```
- GET `/api/quizzes` - Get all quizzes
- GET `/api/quizzes/{id}` - Get a specific quiz
- POST `/api/quizzes/{id}/submit` - Submit quiz answers
  ```json
  {
    "1": "B",
    "2": "A"
  }
  ```

## Database Access

The application uses H2 in-memory database. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:quizdb`
- Username: `sa`
- Password: ` ` (empty)

## Example Usage

1. First, create some questions using the POST `/api/quizzes/questions` endpoint
2. Create a quiz using the POST `/api/quizzes` endpoint, referencing the question IDs
3. Get the quiz details using GET `/api/quizzes/{id}`
4. Submit answers using POST `/api/quizzes/{id}/submit`
5. View the results in the response, including score and correct/incorrect answers 