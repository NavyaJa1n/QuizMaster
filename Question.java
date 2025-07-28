/*
 * Question class to represent each quiz question
 * Contributed by Navya Jain, registration number 23011101084 contributed to designing class Question.
 * This class provides a base class to represent the question and correct answer.
 */
package projectquiz;
class Question {
    private String questionText;
    private String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
