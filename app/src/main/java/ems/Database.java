package ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:h2:./employeeDB"; // creates a file in project folder
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void initializeDatabase() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS employees (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255),
                salary DOUBLE,
                department VARCHAR(255),
                title VARCHAR(255)
            )
        """;

        try (Connection conn = getConnection()) {
            conn.createStatement().execute(sql);
            System.out.println("ems.Database initialized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
