package student;

import java.sql.SQLException;

import bca.jdbc.studentdb;

public class main {
    public static void main(String[] args) throws SQLException {
        studentdb studentDatabase = new studentdb();
        studentDatabase.runMenu();
    }
}
