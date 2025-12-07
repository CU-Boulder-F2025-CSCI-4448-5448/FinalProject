package ems;

import ems.PayStrategies.HourlyPayStrategy;
import ems.PayStrategies.SalaryPayStrategy;

//Design Pattern: Factory
//Employee factory uses the factory pattern to create types
//we have different employee types ie manager, engineer, intern
public class EmployeeFactory {

    public Employee createSoftwareEngineer(String name, double id) {
        return new Employee.EmployeeBuilder(name, id)
                .employeeDepartment("Engineering")
                .employeePosition("Software Engineer")
                .employeeSalary(105000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();
    }

    public Employee createManager(String name, double id) {
        return new Employee.EmployeeBuilder(name, id)
                .employeeDepartment("Management")
                .employeePosition("Project Manager")
                .employeeSalary(120000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();
    }

    public Employee createIntern(String name, double id) {
        return new Employee.EmployeeBuilder(name, id)
                .employeeDepartment("Engineering")
                .employeePosition("Intern")
                .employeeSalary(20)
                .employeeSalaryStrategy(new HourlyPayStrategy(40))
                .buildEmployee();
    }

    public Employee createNetworkEngineer(String name, double id) {
        return new Employee.EmployeeBuilder(name, id)
                .employeeDepartment("Engineering")
                .employeePosition("Network Engineer")
                .employeeSalary(115000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();
    }

}
