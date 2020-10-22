package Q5;
import java.sql.*;
import java.rmi.RemoteException;
public class ConnectionImplement implements ConnectInterface {
    @Override
    public void insert(int id, String name, String surname, int age, int cell, String degree) throws RemoteException {
        final String DB_URL = "jdbc:mysql://localhost:3306/registrants?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // Database credentials 
        final String USER = "root";
        final String PASS = "";
        final Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println("Connected to the database STUDENTS");
                //use prepared statement to inster into database
                String query = "INSERT INTO `students`(`idnumber`, `name`, `surname`, `age`, `cellnumber`, `degree`) VALUES (?,?,?,?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(query)) {
                    st.setInt(1, id);
                    st.setString(2, name);
                    st.setString(3, surname);
                    st.setInt(4, age);
                    st.setInt(5, cell);
                    st.setString(6, degree);
                    st.executeUpdate();
                }
                System.out.println("Student has been added to database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred connecting to database: " + ex.getMessage());

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
