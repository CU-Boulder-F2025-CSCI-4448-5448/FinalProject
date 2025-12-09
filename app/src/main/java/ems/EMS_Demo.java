package ems;

import ems.Observers.*;
import ems.PayStrategies.*;

import java.sql.SQLException;


public class EMS_Demo {
    public static void main(String[] args) throws SQLException {

        //Initialze the Database with Singleton Pattern
        DatabaseInteraction database;
        Database employeeDB = Database.getInstance();
        employeeDB.initializeDatabase();
        database = new DatabaseInteraction();

        //Observer
        PayrollSystem payroll = new PayrollSystem();
        payroll.addObserver(new EmailNotifer());
        payroll.addObserver(new AccountingLogger());


        //Salary Strategy
        Employee Jose = new Employee.EmployeeBuilder("Jose", 101)
                .employeeSalary(30000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();
        database.addEmployee(Jose);

        System.out.println("SalaryPayStrategy");

        payroll.calculateMonthlyPay(Jose);

        //Bonus Pay Strategy
        Employee bonusEmployee = new Employee.EmployeeBuilder("Elmo", 102)
                .employeeSalary(30000)
                .employeeSalaryStrategy(new BonusPayStrategy(500))
                .buildEmployee();
        System.out.println("BonusPayStrategy");
        payroll.calculateMonthlyPay(bonusEmployee);

        Employee overtimeEmployee = new Employee.EmployeeBuilder("Jane", 103)
                .employeeSalary(25) //hourly rate given
                .employeeSalaryStrategy(new OvertimePayStrategy(50))
                .buildEmployee();
        System.out.println("OvertimePayStrategy");
        payroll.calculateMonthlyPay(overtimeEmployee);

        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee travis = employeeFactory.createNetworkEngineer("Travis", 1001);
        database.addEmployee(travis);
    }


}
