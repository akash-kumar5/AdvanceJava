import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createTable {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "akash123");

            Statement st = con.createStatement();
            
            String query = "create table Employee ( e_id int,e_name varchar(30), salary double , addr varchar(30))";
            
           st.executeUpdate(query);
            
            System.out.println("Table Created");
            con.close();
    }
}
