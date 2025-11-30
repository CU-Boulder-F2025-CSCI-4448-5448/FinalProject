package ems;

import ems.PayStrategies.SalaryStrategy;

import java.sql.SQLException;

public class Employee {
    private final String name;
    private final double employeeID;
    private final String address;
    private final double age;
    private final double salary;
    private final String phoneNumber;
    private final String department;
    private final String position;

    private SalaryStrategy salaryStrategy;

    public Employee(String name, double employeeID, String address,
                    double age, double salary, String phoneNumber,
                    String department, String position) {
        this.name = name;
        this.employeeID = employeeID;
        this.address = address;
        this.age = age;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
    };

    public String getName() {
        return name;
    }
    public double getEmployeeID() {
        return employeeID;
    }
    public double getSalary() {
        return salary;
    }
    public String getDepartment() {
        return department;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPosition() {
        return position;
    }
    public double getAge() {
        return age;
    }
    public SalaryStrategy getSalaryStrategy() {
        return salaryStrategy;
    }
    public void setSalaryStrategy(SalaryStrategy salaryStrategy) {
        this.salaryStrategy = salaryStrategy;
    }

    private Employee(EmployeeBuilder employeeBuilder) {
        this.name = employeeBuilder.name;
        this.employeeID = employeeBuilder.employeeID;
        this.address = employeeBuilder.address;
        this.age = employeeBuilder.age;
        this.salary = employeeBuilder.salary;
        this.phoneNumber = employeeBuilder.phoneNumber;
        this.department = employeeBuilder.department;
        this.position = employeeBuilder.position;
        this.salaryStrategy = employeeBuilder.salaryStrategy;
    }

    public static class EmployeeBuilder {
        private final String name;
        private final double employeeID;

        private String address = "";
        private double age = 0;
        private double salary = 0;
        private String phoneNumber = "";
        private String department = "";
        private String position = "";

        private SalaryStrategy salaryStrategy = null;

        public EmployeeBuilder(String name, double employeeID) {
            this.name = name;
            this.employeeID = employeeID;
        }

        public EmployeeBuilder employeeAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder employeeAge(double age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder employeeSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder employeePhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeBuilder employeeDepartment(String department) {
            this.department = department;
            return this;
        }

        public EmployeeBuilder employeePosition(String position) {
            this.position = position;
            return this;
        }

        public EmployeeBuilder employeeSalaryStrategy(SalaryStrategy salaryStrategy) {
            this.salaryStrategy = salaryStrategy;
            return this;
        }

        public Employee buildEmployee() {
            return new Employee(this);
        }
    }

}