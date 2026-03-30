package dao;
import java.sql.*;
import util.DBConnection;

public class UserDAO {

    public static void register(String name, String email, String pass) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO users(name,email,password) VALUES(?,?,?)");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, pass);
        ps.executeUpdate();
        con.close();
    }

    public static ResultSet login(String email, String pass) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE email=? AND password=?");
        ps.setString(1, email);
        ps.setString(2, pass);
        return ps.executeQuery();
    }
}