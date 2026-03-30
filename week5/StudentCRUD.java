import java.sql.*;

public class StudentCRUD {

    public static void insert(int id, String name, int age) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO student VALUES(?,?,?)");

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, age);
        ps.executeUpdate();
        con.close();
    }

    public static void read() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM student");

        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " " +
                rs.getString(2) + " " +
                rs.getInt(3)
            );
        }
        con.close();
    }

    public static void update(int id, int age) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("UPDATE student SET age=? WHERE id=?");

        ps.setInt(1, age);
        ps.setInt(2, id);
        ps.executeUpdate();
        con.close();
    }

    public static void delete(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM student WHERE id=?");

        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }
}
