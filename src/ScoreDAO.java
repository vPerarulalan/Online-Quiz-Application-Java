import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScoreDAO {


    public void saveScore(String username, int score, int total) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO scores(username, score, total) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setInt(2, score);
            ps.setInt(3, total);


            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Score Saved Successfully");
            }


        } catch(Exception e) {

            e.printStackTrace();

        }

    }

}