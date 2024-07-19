package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLWithJDBC {

    // Database URL, username, and password
    private static final String URL = "jdbc:postgresql://localhost:5432/slicrofinancedb";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    // SQL statement to insert a record into the employee table
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee(id, name, department, salary) VALUES (?, ?, ?, ?)";

    public static void main(String[] args) {
        // Insert a record into the employee table
        insertEmployee(2, "Sonu Lodhi", "CS", 95000.00);
    }

    public static void insertEmployee(int id, String name, String department, double salary) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create the PreparedStatement
            preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);

            // Set the values for the PreparedStatement
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, department);
            preparedStatement.setDouble(4, salary);

            // Execute the insert operation
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } finally {
            // Close the PreparedStatement and Connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
