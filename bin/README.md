# CPAN 211 - Database Connectivity

## Course Information
* **Course:** CPAN 211
* **Topic:** Database Connectivity (JDBC, SQL, Java Collections, Exception Handling)

---

## Getting Started

Follow the GitHub setup below before beginning your work.

### GitHub Setup

#### Step 1: Fork and Clone
1. Go to the repository on GitHub.
2. Click the **Fork** button in the top-right corner (VERY IMPORTANT!!!!). This creates your own copy of the project.
3. **Clone Your Fork:**
   ```bash
   git clone https://github.com/YOUR-USERNAME/CPAN211_Lab10.git
   cd CPAN211_Lab10
   ```
4. **Add Upstream Remote:**
   ```bash
   git remote add upstream https://github.com/ChristinHumber/CPAN211_Lab10.git
   ```
5. **Pull Latest Changes:**
   ```bash
   git pull upstream main
   ```
6. **Create a Feature Branch:**
   ```bash
   git checkout -b feature/lab-yourname
   ```
   *Replace `yourname` with your actual name (e.g., `feature/lab10-john-doe` or `feature/lab-john-doe`).*

#### Step 2: Open a Pull Request
1. Go to the upstream repository at `ChristinHumber/CPAN211_Lab10`.
2. You should see a prompt banner saying **"Compare & pull request"** for your pushed branch. Click it.
3. If you don't see the banner:
   * Go to your GitHub fork page.
   * Switch to your feature branch using the branch dropdown menu.
   * Click **Contribute** and then select **Open pull request**.
4. Verify the base repository is `ChristinHumber/CPAN211_Lab10` and the base branch is `main`.
5. Set the title of your pull request to: `Lab Submission - [Your Name]` (e.g., `Lab Submission - John Doe`).
6. Click **Create pull request** to complete the submission.

---

## Lab 10: Database Connectivity and Java Collections Aggregation

### Instructions
1. Load the MySQL database driver.
2. Establish a connection to your local database using JDBC.
3. Write a program that will create a table in the database named `YourStudentNumber_Orders` (e.g., `n01234567_Orders`).
4. Read the SQL script `salesScripts.sql` (located under `src/main/resources`) and execute the DDL/DML queries to populate your table.
5. Retrieve all the records through an SQL query.
6. Provide a printout of all the customers with their final bills.
7. Every customer must be mentioned in the printout only once - you **MUST** use Java collections (such as `Map<String, Integer>`) instead of the SQL `SUM` statement to perform the aggregation.
8. The `main` method should be as short as possible and it **must not** throw any exceptions – all the exceptions must be caught and handled cleanly.

### Running the Project
You can open this project in IntelliJ IDEA as a Maven project, or compile and run it from the command line using:
```bash
mvn compile exec:java -Dexec.mainClass="Main"
```

### Starter Code Skeleton
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
```
