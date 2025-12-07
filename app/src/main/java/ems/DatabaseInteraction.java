package ems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteraction {

    private final Database database = Database.getInstance();

    // ADD employee
    public void addEmployee(Employee e) {
        String sql = """
            INSERT INTO employees
            (name, employeeID, address, age, salary, phoneNumber, department, position, salaryStrategy)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getName());
            stmt.setDouble(2, e.getEmployeeID());
            stmt.setString(3, e.getAddress());
            stmt.setDouble(4, e.getAge());
            stmt.setDouble(5, e.getSalary());
            stmt.setString(6, e.getPhoneNumber());
            stmt.setString(7, e.getDepartment());
            stmt.setString(8, e.getPosition());
            stmt.setString(9, e.getSalaryStrategy().getClass().getSimpleName());

            stmt.executeUpdate();
            System.out.println("Employee added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // GET employee by employeeID
    public Employee getEmployee(double employeeID) {
        String sql = "SELECT * FROM employees WHERE employeeID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, employeeID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee e = new Employee.EmployeeBuilder(
                        rs.getString("name"),
                        rs.getDouble("employeeID"))
                        .employeeAddress(rs.getString("address"))
                        .employeeAge(rs.getDouble("age"))
                        .employeeSalary(rs.getDouble("salary"))
                        .employeePhoneNumber(rs.getString("phoneNumber"))
                        .employeeDepartment(rs.getString("department"))
                        .employeePosition(rs.getString("position"))
                        .buildEmployee();

                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // GET ALL employees
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> list = new ArrayList<>();

        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee e = new Employee.EmployeeBuilder(
                        rs.getString("name"),
                        rs.getDouble("employeeID"))
                        .employeeAddress(rs.getString("address"))
                        .employeeAge(rs.getDouble("age"))
                        .employeeSalary(rs.getDouble("salary"))
                        .employeePhoneNumber(rs.getString("phoneNumber"))
                        .employeeDepartment(rs.getString("department"))
                        .employeePosition(rs.getString("position"))
                        .buildEmployee();

                list.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
