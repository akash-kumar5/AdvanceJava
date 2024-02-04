package bca.jdbc;

import java.sql.*;
import java.util.Scanner;

public class studentdb {
    Scanner scanner = new Scanner(System.in);
    Connection connection;

    public void runMenu() throws SQLException {
        connectToDatabase();

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> insertStudent();
                case 2 -> updateStudent();
                case 3 -> readstudent();
                case 4 -> removeStudent();
                case 5 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        closeDatabaseConnection();
        scanner.close();
    }

    private void connectToDatabase() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/student";
            String user = "root";
            String password = "akash123";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
    }

    private void closeDatabaseConnection() throws SQLException {
                 if (connection != null && !connection.isClosed()) {
                connection.close();
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
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter student gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();

        String query = String.format("INSERT INTO student (id, name, marks, gender, age) VALUES (%d, '%s', %.2f, '%s', %d)", id, name, marks, gender, age);

        Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student added successfully.");
       
    }

    private void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name for student: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new marks for student: ");
        double newMarks = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter new gender for student: ");
        String newGender = scanner.nextLine();
        System.out.print("Enter new age for student: ");
        int newAge = scanner.nextInt();

        String query = String.format("UPDATE student SET name='%s', marks=%.2f, gender='%s', age=%d WHERE id=%d", newName, newMarks, newGender, newAge, id);

        Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            
    }

    private void readstudent() throws SQLException {
        String query = "SELECT * FROM student;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
    }

    private void removeStudent() throws SQLException {
        System.out.print("Enter student ID to remove: ");
        int id = scanner.nextInt();

        String query = String.format("DELETE FROM student WHERE id=%d", id);

        Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
           
    }
}