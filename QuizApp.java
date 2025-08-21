package quizApp;

import java.util.*;

//Class to represent a Quiz Question
class Question {
 String questionText;
 String[] options;
 int correctAnswer; // index of correct answer (0 to 3 for A-D)

 public Question(String questionText, String[] options, int correctAnswer) {
     this.questionText = questionText;
     this.options = options;
     this.correctAnswer = correctAnswer;
 }

 // Display the question with options (A, B, C, D)
 public void displayQuestion() {
     System.out.println(questionText);
     char optionLabel = 'A';
     for (String option : options) {
         System.out.println(optionLabel + ". " + option);
         optionLabel++;
     }
 }

 // Check if user answer is correct
 public boolean checkAnswer(int userAnswer) {
     return userAnswer == correctAnswer;
 }
}

//Main Quiz Application
public class QuizApp {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // List of questions
     List<Question> questions = new ArrayList<>();
     questions.add(new Question("What is the capital of India?",
             new String[]{"Mumbai", "Delhi", "Kolkata", "Chennai"}, 1));
     questions.add(new Question("Which language is used for Android development?",
             new String[]{"Python", "Kotlin", "Ruby", "Swift"}, 1));
     questions.add(new Question("Who is known as the father of Java?",
             new String[]{"James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum"}, 0));
     questions.add(new Question("Which company developed Java?",
             new String[]{"Microsoft", "Sun Microsystems", "Apple", "Google"}, 1));
     questions.add(new Question("Which keyword is used to inherit a class in Java?",
             new String[]{"this", "super", "extends", "implements"}, 2));

     int score = 0;
     Map<Question, Integer> userAnswers = new HashMap<>();

     // Loop through each question
     for (int i = 0; i < questions.size(); i++) {
         System.out.println("\nQuestion " + (i + 1) + ":");
         Question q = questions.get(i);
         q.displayQuestion();

         System.out.print("Enter your answer (A-D): ");
         String input = sc.next().toUpperCase();

         int answerIndex = -1;
         if (input.matches("[A-D]")) {
             answerIndex = input.charAt(0) - 'A'; // Convert A-D → 0-3
         } else {
             System.out.println("Invalid input! Skipping question.");
         }

         userAnswers.put(q, answerIndex);

         if (q.checkAnswer(answerIndex)) {
             System.out.println("✅ Correct!");
             score++;
         } else {
             System.out.println("❌ Wrong! Correct answer is: " +
                     (char) ('A' + q.correctAnswer) + ". " + q.options[q.correctAnswer]);
         }
     }

     // Final result
     System.out.println("\n=== Quiz Over ===");
     System.out.println("Your Score: " + score + " / " + questions.size());
     double percentage = (score * 100.0) / questions.size();
     System.out.printf("Percentage: %.2f%%\n", percentage);

     // Correct answers review
     System.out.println("\n--- Correct Answers Review ---");
     int i = 1;
     for (Question q : questions) {
         System.out.println("Q" + (i++) + ": " + q.questionText);
         char correct = (char) ('A' + q.correctAnswer);
         System.out.println("Correct Answer: " + correct + ". " + q.options[q.correctAnswer]);

         int userAns = userAnswers.get(q);
         if (q.checkAnswer(userAns)) {
             System.out.println("Your Answer: " + (char) ('A' + userAns) + ". " + q.options[userAns] + " ✅\n");
         } else if (userAns >= 0 && userAns <= 3) {
             System.out.println("Your Answer: " + (char) ('A' + userAns) + ". " + q.options[userAns] + " ❌\n");
         } else {
             System.out.println("Your Answer: Invalid ❌\n");
         }
     }

 }
}
