package ems;

import ems.Observers.ConsoleDatabaseObserver;
import ems.PayStrategies.SalaryPayStrategy;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class EMS_Demo {
    public static void main(String[] args) throws SQLException {

// Initialize the database (Singleton)
        Database employeeDB = Database.getInstance();
        employeeDB.initializeDatabase();

        // Attach observer so it prints when employees are added / fetched
        employeeDB.addObserver(new ConsoleDatabaseObserver());

        DatabaseInteraction database = new DatabaseInteraction();

        // Simple terminal UI
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addEmployeeFlow(scanner, database);
                case "2" -> getEmployeeFlow(scanner, database);
                case "3" -> listEmployeesFlow(database);
                case "4" -> running = false;
                default -> System.out.println("Invalid option, please try again.");
            }

            System.out.println();
        }

        System.out.println("Exiting Employee Management System. Goodbye!");
    }

    // -------- UI helpers --------

    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("      Employee Management System    ");
        System.out.println("====================================");
        System.out.println("1) Add employee");
        System.out.println("2) Find employee by ID");
        System.out.println("3) List all employees");
        System.out.println("4) Exit");
        System.out.print("====================================\n");
    }

    private static void addEmployeeFlow(Scanner scanner, DatabaseInteraction database) {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Employee ID (number): ");
            double id = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Department: ");
            String department = scanner.nextLine().trim();

            System.out.print("Yearly Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            // We can expand this to ask for address, age, etc. if needed
            Employee employee = new Employee.EmployeeBuilder(name, id)
                    .employeeDepartment(department)
                    .employeeSalary(salary)
                    .employeeSalaryStrategy(new SalaryPayStrategy())
                    .buildEmployee();

            database.addEmployee(employee);
            System.out.println("Employee saved to database.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input, employee not saved.");
        }
    }

    private static void getEmployeeFlow(Scanner scanner, DatabaseInteraction database) {
        try {
            System.out.print("Employee ID (number): ");
            double id = Double.parseDouble(scanner.nextLine().trim());

            Employee employee = database.getEmployee(id);
            if (employee == null) {
                System.out.println("No employee found with ID " + id);
            } else {
                printEmployee(employee);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void listEmployeesFlow(DatabaseInteraction database) {
        List<Employee> employees = database.getAllEmployees();

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees in the database.");
            return;
        }

        System.out.println("=== All Employees ===");
        for (Employee e : employees) {
            printEmployee(e);
        }
    }

    private static void printEmployee(Employee e) {
        System.out.println("---------------------------");
        System.out.println("Name      : " + e.getName());
        System.out.println("ID        : " + e.getEmployeeID());
        System.out.println("Department: " + e.getDepartment());
        System.out.println("Salary    : " + e.getSalary());
        System.out.println("---------------------------");
    }

}
