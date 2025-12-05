package ems;

import ems.Observers.*;
import ems.PayStrategies.*;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        //Initialze the Database with Singleton Pattern
        Database.getInstance().initializeDatabase();

        //Observer
        PayrollSystem payroll = new PayrollSystem();
        payroll.addObserver(new EmailNotifer());
        payroll.addObserver(new AccountingLogger());

        //Salary Strategy
        Employee e = new Employee.EmployeeBuilder("Jose", 101)
                .employeeSalary(30000)
                .employeeSalaryStrategy(new SalaryPayStrategy())
                .buildEmployee();
        System.out.println("SalaryPayStrategy");

        payroll.calculateMonthlyPay(e);

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
    }


}
