import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertRow {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "akash123");

            Statement st = con.createStatement();
            
            String query = "Insert into student values(011,'Pratik',68,'Male','1999-04-22');";
            
          int rows = st.executeUpdate(query);
            
            System.out.println(rows+" Data Inserted");
            con.close();
    }
}
