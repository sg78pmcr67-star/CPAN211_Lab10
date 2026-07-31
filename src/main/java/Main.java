import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * CPAN 211 Lab 10 - DB Connectivity
 * 
 * Goals:
 * - Practice JDBC
 * 
 * Instructions:
 * 1. Load the MySQL database driver.
 * 2. Connect to the database.
 * 3. Create a table named 'YourStudentNumber_Orders' (e.g. n01234567_Orders).
 * 4. Populate the table with the data from 'salesScripts.sql' located in resources.
 * 5. Retrieve all the records through an SQL query.
 * 6. Provide a printout of all customers with their final bills (total price summed in Java, not SQL).
 * 7. Ensure each customer is listed only once by using a Java Collection (e.g., Map).
 * 8. Handle all exceptions cleanly so that main does not throw any exceptions.
 */
public class Main {

    // TODO: Update connection details as per your MySQL local server settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    // TODO: Set your actual student number
    private static final String STUDENT_NUMBER = "n01234567";

    public static void main(String[] args) {
        // Implement the JDBC logic and Java collections aggregation here
        System.out.println("CPAN 211 Lab 10 - DB Connectivity");
    }
}
