package ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Design Pattern: Singleton
//The Database class uses the Singleton pattern
//One one instance of the database can exist
//Database.getInstance() ensures database across the app consistently

public class Database {

    private static Database instance;

    private static final String URL = "jdbc:h2:./employeeDB"; // creates a file in project folder
    private static final String USER = "sa";
    private static final String PASS = "";

    //Private constructor (Singleton)
    private Database () {}

    //Private Static Instance
    public static synchronized Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void initializeDatabase() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS employees (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255),
                employeeID DOUBLE,
                address VARCHAR(255),
                age DOUBLE,
                salary DOUBLE,
                phoneNumber VARCHAR(50),
                department VARCHAR(255),
                position VARCHAR(255),
                salaryType VARCHAR(255)
            )
        """;


        //uses instance inside InitizedDatabase
        try (Connection conn = getConnection()) {
            conn.createStatement().execute(sql);
            System.out.println("ems.Database initialized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
