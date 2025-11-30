package ems;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeFactoryTest {

    @Test
    void createEmployee() {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee joe = employeeFactory.createNetworkEngineer("Joe", 1010);
        assertTrue(joe.getEmployeeID() == 1010);
        assertTrue(joe.getSalary() == 115000);
        assertTrue(Objects.equals(joe.getDepartment(), "Engineering"));
        assertTrue(Objects.equals(joe.getPosition(), "Network Engineer"));
        assertTrue(joe.getName().equals("Joe"));
    }
}
