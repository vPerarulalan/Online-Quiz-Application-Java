import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminDAO {

    Scanner sc = new Scanner(System.in);


    // Admin Login
    public boolean login(String username, String password) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        return status;
    }



    // Add Question
    public void addQuestion() {

        try {

            System.out.println("\n===== ADD QUESTION =====");


            System.out.print("Enter Question: ");
            String question = sc.nextLine();


            System.out.print("Option 1: ");
            String option1 = sc.nextLine();


            System.out.print("Option 2: ");
            String option2 = sc.nextLine();


            System.out.print("Option 3: ");
            String option3 = sc.nextLine();


            System.out.print("Option 4: ");
            String option4 = sc.nextLine();


            System.out.print("Correct Answer: ");
            int answer = Integer.parseInt(sc.nextLine());


            Connection con = DBConnection.getConnection();


            String sql =
            "INSERT INTO questions(question,option1,option2,option3,option4,answer) VALUES(?,?,?,?,?,?)";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, question);
            ps.setString(2, option1);
            ps.setString(3, option2);
            ps.setString(4, option3);
            ps.setString(5, option4);
            ps.setInt(6, answer);


            int rows = ps.executeUpdate();


            if(rows > 0) {

                System.out.println("Question Added Successfully");

            }


        } catch(Exception e) {

            e.printStackTrace();

        }

    }




    // View Questions
    public void viewQuestions() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM questions";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            System.out.println("\n===== ALL QUESTIONS =====");


            while(rs.next()) {


                System.out.println("--------------------------------");

                System.out.println("ID : " + rs.getInt("id"));

                System.out.println("Question : " + rs.getString("question"));

                System.out.println("1. " + rs.getString("option1"));
                System.out.println("2. " + rs.getString("option2"));
                System.out.println("3. " + rs.getString("option3"));
                System.out.println("4. " + rs.getString("option4"));

                System.out.println("Answer : " + rs.getInt("answer"));

            }


        } catch(Exception e) {

            e.printStackTrace();

        }

    }




    // Temporary methods

   public void updateQuestion() {

    try {

        System.out.println("\n===== UPDATE QUESTION =====");


        System.out.print("Enter Question ID: ");
        int id = Integer.parseInt(sc.nextLine());


        System.out.print("Enter New Question: ");
        String question = sc.nextLine();


        System.out.print("Option 1: ");
        String option1 = sc.nextLine();


        System.out.print("Option 2: ");
        String option2 = sc.nextLine();


        System.out.print("Option 3: ");
        String option3 = sc.nextLine();


        System.out.print("Option 4: ");
        String option4 = sc.nextLine();


        System.out.print("Correct Answer: ");
        int answer = Integer.parseInt(sc.nextLine());



        Connection con = DBConnection.getConnection();


        String sql =
        "UPDATE questions SET question=?, option1=?, option2=?, option3=?, option4=?, answer=? WHERE id=?";


        PreparedStatement ps = con.prepareStatement(sql);


        ps.setString(1, question);
        ps.setString(2, option1);
        ps.setString(3, option2);
        ps.setString(4, option3);
        ps.setString(5, option4);
        ps.setInt(6, answer);
        ps.setInt(7, id);



        int rows = ps.executeUpdate();


        if(rows > 0) {

            System.out.println("Question Updated Successfully");

        }
        else {

            System.out.println("Question ID Not Found");

        }


    } catch(Exception e) {

        e.printStackTrace();

    }

}


    public void deleteQuestion() {

    try {

        System.out.println("\n===== DELETE QUESTION =====");


        System.out.print("Enter Question ID to Delete: ");

        int id = Integer.parseInt(sc.nextLine());


        Connection con = DBConnection.getConnection();


        String sql = "DELETE FROM questions WHERE id=?";


        PreparedStatement ps = con.prepareStatement(sql);


        ps.setInt(1, id);


        int rows = ps.executeUpdate();


        if(rows > 0) {

            System.out.println("Question Deleted Successfully");

        }
        else {

            System.out.println("Question ID Not Found");

        }


    } catch(Exception e) {

        e.printStackTrace();

    }

}


    public void viewScores() {

    try {

        Connection con = DBConnection.getConnection();


        String sql = "SELECT * FROM scores";


        PreparedStatement ps = con.prepareStatement(sql);


        ResultSet rs = ps.executeQuery();



        System.out.println("\n===== USER SCORES =====");


        while(rs.next()) {


            System.out.println("--------------------------------");


            System.out.println("ID       : " + rs.getInt("id"));

            System.out.println("Username : " + rs.getString("username"));

            System.out.println("Score    : " 
                    + rs.getInt("score") 
                    + "/" 
                    + rs.getInt("total"));

        }


    } catch(Exception e) {

        e.printStackTrace();

    }

}


}