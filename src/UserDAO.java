import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {


    // Register User object
    public void register(User user) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("User Registered Successfully");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    // Register using username and password
    public void register(String username, String password) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        register(user);
    }



    // User Login
    public boolean login(String username, String password) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

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
}