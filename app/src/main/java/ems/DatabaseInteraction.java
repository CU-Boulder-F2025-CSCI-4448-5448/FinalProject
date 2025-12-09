package ems;

import ems.PayStrategies.PayStrategyFactory;
import ems.PayStrategies.SalaryType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteraction {

    private final Database database = Database.getInstance();

    // ADD employee
    public void addEmployee(Employee employee) {
        String sql = """
            INSERT INTO employees
            (name, employeeID, address, age, salary, phoneNumber, department, position, salaryType)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setDouble(2, employee.getEmployeeID());
            stmt.setString(3, employee.getAddress());
            stmt.setDouble(4, employee.getAge());
            stmt.setDouble(5, employee.getSalary());
            stmt.setString(6, employee.getPhoneNumber());
            stmt.setString(7, employee.getDepartment());
            stmt.setString(8, employee.getPosition());
            stmt.setString(9, employee.getSalaryType() != null ? employee.getSalaryType().name() : null);


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
                Employee employee = new Employee.EmployeeBuilder(
                        rs.getString("name"),
                        rs.getDouble("employeeID"))
                        .employeeAddress(rs.getString("address"))
                        .employeeAge(rs.getDouble("age"))
                        .employeeSalary(rs.getDouble("salary"))
                        .employeePhoneNumber(rs.getString("phoneNumber"))
                        .employeeDepartment(rs.getString("department"))
                        .employeePosition(rs.getString("position"))
                        .buildEmployee();

                // Recreate SalaryStrategy from enum
                String typeStr = rs.getString("salaryType");
                if (typeStr != null) {
                    SalaryType type = SalaryType.valueOf(typeStr);
                    employee.setSalaryStrategy(PayStrategyFactory.fromType(type));
                }

                return employee;
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
                Employee employee = new Employee.EmployeeBuilder(
                        rs.getString("name"),
                        rs.getDouble("employeeID"))
                        .employeeAddress(rs.getString("address"))
                        .employeeAge(rs.getDouble("age"))
                        .employeeSalary(rs.getDouble("salary"))
                        .employeePhoneNumber(rs.getString("phoneNumber"))
                        .employeeDepartment(rs.getString("department"))
                        .employeePosition(rs.getString("position"))
                        .buildEmployee();

                // Recreate SalaryStrategy
                String typeStr = rs.getString("salaryType");
                if (typeStr != null) {
                    SalaryType type = SalaryType.valueOf(typeStr);
                    employee.setSalaryStrategy(PayStrategyFactory.fromType(type));
                }

                list.add(employee);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
