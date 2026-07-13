import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    public static int startQuiz() {

        QuestionDAO dao = new QuestionDAO();

        ArrayList<Question> questions = dao.getAllQuestions();

        Scanner sc = new Scanner(System.in);

        int score = 0;

        for (Question q : questions) {

            System.out.println("--------------------------------");
            System.out.println(q.getQuestion());

            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());

            System.out.print("Enter your answer: ");

            int userAnswer = sc.nextInt();

            if(userAnswer == q.getAnswer()) {
                System.out.println("Correct Answer ✅");
                score++;
            }
            else {
                System.out.println("Wrong Answer ❌");
            }
        }

        System.out.println("--------------------------------");
        System.out.println("Quiz Completed");
        System.out.println("Your Score: " + score + "/" + questions.size());

return score;

    }
}