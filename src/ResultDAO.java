import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultDAO {


    public void saveResult(String username, int score, double percentage, String status) {


        try {


            Connection con = DBConnection.getConnection();


            String sql =
            "INSERT INTO results(username,score,percentage,status) VALUES(?,?,?,?)";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, username);

            ps.setInt(2, score);

            ps.setDouble(3, percentage);

            ps.setString(4, status);



            int rows = ps.executeUpdate();


            if(rows > 0) {

                System.out.println("Result Saved Successfully");

            }



        } catch(Exception e) {

            e.printStackTrace();

        }


    }

}