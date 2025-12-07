package ems;

import ems.PayStrategies.SalaryPayStrategy;
import ems.PayStrategies.SalaryStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseInteractionTest {
    // worked with chat to develop sql db test to make sure we can push and retrieve employee data
    private DatabaseInteraction database;

    @BeforeEach
    void setup() throws SQLException {
        Database db = Database.getInstance();
        db.initializeDatabase();
        database = new DatabaseInteraction();
    }

    @Test
    void testAddAndRetrieveEmployee() {
        Employee emp = new Employee.EmployeeBuilder("John Doe", 1001)
                .employeeAddress("123 Main St")
                .employeeAge(28)
                .employeeSalary(55000)
                .employeePhoneNumber("555-1234")
                .employeeDepartment("IT")
                .employeePosition("Developer")
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();

        database.addEmployee(emp);

        Employee retrieved = database.getEmployee(1001);

        assertNotNull(retrieved);
        assertEquals("John Doe", retrieved.getName());
        assertEquals(55000, retrieved.getSalary());
        assertEquals("IT", retrieved.getDepartment());
    }
}
