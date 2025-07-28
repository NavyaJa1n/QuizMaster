/*
 *Quiz class to conduct the quiz
 *Author 1: Ritu Nandhan D S, 23011101115 contributed loadQuestions() function.
 *Author 2: Shree Karshin S, 23011101136 contributed to startQuiz() function.
 *All three authors jointly contributed to rigorous testing of code to ensure robustness.
 *This class imports the questions and answers from a text file, stored in a pre- defined format via loadQuestions() function. 
 The startQuiz() function registers the user and records score based on number of correct answers. At the end of the quiz, th username and score is displayed.
 The main method invokes class functions. Proper measures have been taken to ensure that all possible and predictable errors are handled.
*/
package projectquiz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Quiz {
    private List<Question> questionDatabase;
    private User user;
    private final int pointsPerQuestion = 10;

    public Quiz(User user) {
        this.user = user;
        this.questionDatabase = loadQuestions();
    }

    // Method to load questions from a file
    private List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        String fileName = "quiz.txt";  // Updated file name

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Each line should have the format: question|answer
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String questionText = parts[0].trim();
                    String correctAnswer = parts[1].trim();
                    questions.add(new Question(questionText, correctAnswer));
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading questions from file: " + e.getMessage());
        }

        return questions;
    }

    // Method to conduct the quiz
    public void startQuiz() {
        if (questionDatabase.isEmpty()) {
            System.out.println("No questions available. Please check the file.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + user.getUsername() + "! Let's start the quiz.");

        for (int i = 0; i < questionDatabase.size(); i++) {
            Question question = questionDatabase.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();

            // Check if the answer is correct
            if (question.isCorrectAnswer(answer)) {
                System.out.println("Correct!");
                user.addScore(pointsPerQuestion);  // Add points for correct answer
            } else {
                System.out.println("Wrong! The correct answer is: " + question.getCorrectAnswer());//display correct answer if user gives wrong answer
            }
            System.out.println();
        }

        // Display the final score
        System.out.println("Quiz over! " + user.getUsername() + ", your total score is: " + user.getScore());
        if (user.getScore() == questionDatabase.size() * pointsPerQuestion) {
            System.out.println("Excellent! You got all answers correct!");
        } else if (user.getScore() > 0) {
            System.out.println("Good job! You got some answers correct.");
        } else {
            System.out.println("Better luck next time!");
        }
        scanner.close();
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Prompt for the user's name
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();

        // Create a user and start the quiz
        User user = new User(username);
        Quiz quiz = new Quiz(user);
        quiz.startQuiz();
        
        scanner.close();
    }
}