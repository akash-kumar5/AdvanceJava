package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Student {
    Scanner sc = new Scanner(System.in);
    Connection con;

    public void runMenu() throws SQLException {
        connectToDatabase();

        int choice;
        do {
            displayMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> insertStudent();
                case 2 -> updateStudent();
                case 3 -> readstudent();
                case 4 -> removeStudent();
                case 5 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        closeDatabasecon();
        sc.close();
    }

    private void connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String password = "akash123";
        con = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the database.");
    }

    private void closeDatabasecon() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
            System.out.println("Disconnected from the database.");
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Insert Student");
        System.out.println("2. Update Student");
        System.out.println("3. Read student");
        System.out.println("4. Remove Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void insertStudent() throws SQLException {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter student marks: ");
        double marks = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter student gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter student age: ");
        int age = sc.nextInt();

        String query = String.format("INSERT INTO student VALUES (%d, '%s', %f, '%s', %d)", id, name, marks, gender, age);

        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        System.out.println("Student added successfully.");
    }
    
    private void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new name for student: ");
        String newName = sc.nextLine();
        System.out.print("Enter new marks for student: ");
        double newMarks = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter new gender for student: ");
        String newGender = sc.nextLine();
        System.out.print("Enter new age for student: ");
        int newAge = sc.nextInt();

        String query = String.format("UPDATE student SET name='%s', marks=%f, gender='%s', age=%d WHERE id=%d", newName, newMarks, newGender, newAge, id);

        Statement statement = con.createStatement();
        int rowsAffected = statement.executeUpdate(query);

        if (rowsAffected > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found for updating.");
        }
    }


    private void readstudent() throws SQLException {
        String query = "SELECT * FROM student;";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);

        // Process and display the result set
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double marks = rs.getDouble("marks");
            String gender = rs.getString("gender");
            int age  = rs.getInt("age");

            System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Gender: " + gender + ", Age: " + age);
        }

        // Close resources
        rs.close();
        statement.close();
    }

    private void removeStudent() throws SQLException {
        System.out.print("Enter student ID to remove: ");
        int id = sc.nextInt();

        String query = String.format("DELETE FROM student WHERE id=%d", id);

        Statement statement = con.createStatement();
        int rowsAffected = statement.executeUpdate(query);

        if (rowsAffected > 0) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}
