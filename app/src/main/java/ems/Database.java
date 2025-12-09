package ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ems.Observers.DatabaseObserver;

//Design Pattern: Singleton and Observer
//The Database class uses the Singleton pattern
//One instance of the database can exist
//Database.getInstance() ensures database across the app consistently

public class Database {

    private static Database instance;

    private static final String URL = "jdbc:h2:./employeeDB"; // creates a file in project folder
    private static final String USER = "sa";
    private static final String PASS = "";



    // initializing an object for Observer support
    private final List<DatabaseObserver> observers = new ArrayList<>();

    //Private Static Instance
    public static synchronized Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //Private constructor (Singleton)
    private Database () {}

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    //apis for the observer
    public void addObserver(DatabaseObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DatabaseObserver observer) {
        observers.remove(observer);
    }

    // package-private helpers so DatabaseInteraction can call them
    void notifyEmployeeAdded(ems.Employee employee) {
        System.out.println(">>> notifyEmployeeAdded called, observers = " + observers.size());
        for (DatabaseObserver o : observers) {
            o.onEmployeeAdded(employee);
        }
    }

    public void notifyEmployeeFetched(Employee employee) {
        for (DatabaseObserver o : observers) {
            o.onEmployeeFetched(employee);
        }
    }

    public void notifyAllEmployeesFetched(List<Employee> employees) {
        for (DatabaseObserver o : observers) {
            o.onAllEmployeesFetched(employees);
        }
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
