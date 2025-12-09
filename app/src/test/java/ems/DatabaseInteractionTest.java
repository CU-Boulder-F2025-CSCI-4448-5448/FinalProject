package ems;

import ems.PayStrategies.SalaryPayStrategy;
import ems.PayStrategies.SalaryStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ems.Observers.DatabaseObserver;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        assertTrue(retrieved.getSalaryStrategy() instanceof SalaryPayStrategy);
        assertTrue(retrieved.calculatePay() == (retrieved.getSalary()/12));
    }

    @Test
    void testObserverNotifiedWhenEmployeeAdded() throws SQLException {
        Database db = Database.getInstance();
        db.initializeDatabase();

        // Local flag we can assert on
        AtomicBoolean added = new AtomicBoolean(false);
        AtomicBoolean fetched = new AtomicBoolean(false);
        AtomicBoolean allFetched = new AtomicBoolean(false);

        // Simple inline DatabaseObserver just for the test
        DatabaseObserver testObserver = new DatabaseObserver() {
            @Override
            public void onEmployeeAdded(Employee employee) {
                added.set(true);
            }

            @Override
            public void onEmployeeFetched(Employee employee) {
                // not needed in this test
            }

            @Override
            public void onAllEmployeesFetched(List<Employee> employees) {
                // not needed in this test
            }
        };

        // Register the observer on the singleton database
        db.addObserver(testObserver);

        // Use the normal DatabaseInteraction to add an employee
        DatabaseInteraction database = new DatabaseInteraction();

        Employee emp = new Employee.EmployeeBuilder("Alan Ferguson", 6767)
                .employeeDepartment("QA")
                .employeeSalary(50000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();

        database.addEmployee(emp);

        // Now we expect the observer to have been called
        assertTrue(added.get(), "DatabaseObserver should be notified when an employee is added.");
    }
}
