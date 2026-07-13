import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionDAO {


    // Get all questions
    public ArrayList<Question> getAllQuestions() {

        ArrayList<Question> questions = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM questions";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while(rs.next()) {

                Question q = new Question();

                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setAnswer(rs.getInt("answer"));

                questions.add(q);
            }


        } catch(Exception e) {

            e.printStackTrace();

        }

        return questions;
    }



    // Get question by ID
    public Question getQuestionById(int id) {

        Question q = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM questions WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();


            if(rs.next()) {

                q = new Question();

                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setAnswer(rs.getInt("answer"));
            }


        } catch(Exception e) {

            e.printStackTrace();

        }


        return q;
    }



    // Add question
    public void addQuestion(Question q) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "INSERT INTO questions(question,option1,option2,option3,option4,answer) VALUES(?,?,?,?,?,?)";


            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,q.getQuestion());
            ps.setString(2,q.getOption1());
            ps.setString(3,q.getOption2());
            ps.setString(4,q.getOption3());
            ps.setString(5,q.getOption4());
            ps.setInt(6,q.getAnswer());


            int rows = ps.executeUpdate();


            if(rows > 0) {
                System.out.println("Question Added Successfully");
            }


        } catch(Exception e) {

            e.printStackTrace();

        }

    }



    // Update question
    public void updateQuestion(Question q) {


        try {

            Connection con = DBConnection.getConnection();


            String sql =
            "UPDATE questions SET question=?,option1=?,option2=?,option3=?,option4=?,answer=? WHERE id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1,q.getQuestion());
            ps.setString(2,q.getOption1());
            ps.setString(3,q.getOption2());
            ps.setString(4,q.getOption3());
            ps.setString(5,q.getOption4());
            ps.setInt(6,q.getAnswer());
            ps.setInt(7,q.getId());


            int rows = ps.executeUpdate();


            if(rows > 0)
                System.out.println("Question Updated Successfully");
            else
                System.out.println("Question Not Found");


        } catch(Exception e) {

            e.printStackTrace();

        }

    }



    // Delete question
    public void deleteQuestion(int id) {


        try {

            Connection con = DBConnection.getConnection();


            String sql = "DELETE FROM questions WHERE id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1,id);


            int rows = ps.executeUpdate();


            if(rows > 0)
                System.out.println("Question Deleted Successfully");
            else
                System.out.println("Question Not Found");


        } catch(Exception e) {

            e.printStackTrace();

        }

    }



    // Start Quiz
    public int startQuiz() {


        int score = 0;


        ArrayList<Question> questions = getAllQuestions();


        Scanner sc = new Scanner(System.in);



        for(Question q : questions) {


            System.out.println("--------------------------------");

            System.out.println(q.getQuestion());


            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());


            System.out.print("Enter your answer: ");


            int answer = sc.nextInt();



            if(answer == q.getAnswer()) {

                System.out.println("Correct Answer");
                score++;

            }
            else {

                System.out.println("Wrong Answer");

            }

        }



        System.out.println("--------------------------------");
        System.out.println("Quiz Completed");
        System.out.println("Your Score: " + score + "/" + questions.size());


        return score;

    }

}