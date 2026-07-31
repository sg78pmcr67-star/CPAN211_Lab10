import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    // Update with your local MySQL server details if needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    // Set your actual student number here
    private static final String STUDENT_NUMBER = "n01234567";

    public static void main(String[] args) {
        System.out.println("CPAN 211 Lab 10 - DB Connectivity");
        executeLabWorkflow();
    }

    private static void executeLabWorkflow() {
        String tableName = STUDENT_NUMBER + "_Orders";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Successfully connected to the database.");

            createOrderTable(conn, tableName);
            loadAndExecuteSqlScript(conn, tableName);
            
            Map<String, Integer> customerBills = aggregateCustomerBills(conn, tableName);
            printCustomerBills(customerBills);

        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createOrderTable(Connection conn, String tableName) {
        String dropTableSql = "DROP TABLE IF EXISTS " + tableName;
        String createTableSql = "CREATE TABLE " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "customer_name VARCHAR(100), " +
                "item_name VARCHAR(100), " +
                "price INT" +
                ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(dropTableSql);
            stmt.executeUpdate(createTableSql);
            System.out.println("Table '" + tableName + "' created successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
        }
    }

    private static void loadAndExecuteSqlScript(Connection conn, String tableName) {
        String resourcePath = "/salesScripts.sql";
        try (InputStream inputStream = Main.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.err.println("Could not find " + resourcePath + " under src/main/resources.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 Statement stmt = conn.createStatement()) {
                
                StringBuilder sqlBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("--")) {
                        continue; // Skip comments and empty lines
                    }
                    sqlBuilder.append(line).append(" ");
                    if (line.endsWith(";")) {
                        String sql = sqlBuilder.toString().replace("orders", tableName);
                        stmt.execute(sql);
                        sqlBuilder.setLength(0);
                    }
                }
                System.out.println("SQL script executed and table populated successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error reading or executing salesScripts.sql: " + e.getMessage());
        }
    }

    private static Map<String, Integer> aggregateCustomerBills(Connection conn, String tableName) {
        Map<String, Integer> billMap = new HashMap<>();
        String query = "SELECT customer_name, price FROM " + tableName;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                int price = rs.getInt("price");

                // Using Java Collections (HashMap) to perform the aggregation instead of SQL SUM
                billMap.put(customerName, billMap.getOrDefault(customerName, 0) + price);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving records for aggregation: " + e.getMessage());
        }

        return billMap;
    }

    private static void printCustomerBills(Map<String, Integer> billMap) {
        System.out.println("\n----------------------------------------");
        System.out.println(" Final Bill Summary (Aggregated in Java)");
        System.out.println("----------------------------------------");
        if (billMap.isEmpty()) {
            System.out.println("No customer records found.");
            return;
        }
        for (Map.Entry<String, Integer> entry : billMap.entrySet()) {
            System.out.printf("Customer: %-15s | Total Bill: $%d%n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------------------");
    }
}