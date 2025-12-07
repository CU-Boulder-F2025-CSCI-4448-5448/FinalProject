package ems;

import ems.PayStrategies.SalaryPayStrategy;
import ems.PayStrategies.SalaryStrategy;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeTest {

    @Test
    void testEmployeeCreation() {
        Employee joe = new Employee("Joe", 1001, "123 Main St", 34,
                75000, "303-303-3030", "Engineering", "Developer");

        assertTrue(Objects.equals(joe.getName(), "Joe"));
        assertTrue(joe.getEmployeeID() == 1001);
    }
    @Test
    void testEmployeeBuilder(){
        Employee bob = new Employee.EmployeeBuilder("Joe", 1001)
                .employeeDepartment("Engineering")
                .employeeSalary(75000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();

        assertTrue(Objects.equals(bob.getName(), "Joe"));
        assertTrue(bob.getEmployeeID() == 1001);
        assertTrue(bob.getSalary() == 75000);
        assertTrue(Objects.equals(bob.getDepartment(), "Engineering"));
        assertTrue(bob.getAge() == 0);
        assertTrue(Objects.equals(bob.getPosition(), ""));
        assertTrue(bob.getSalaryStrategy() instanceof SalaryPayStrategy);
        assertTrue(bob.calculatePay() == (bob.getSalary()/12));
    }
}
