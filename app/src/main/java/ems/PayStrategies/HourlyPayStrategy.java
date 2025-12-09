package ems.PayStrategies;

import ems.Employee;

public class HourlyPayStrategy implements SalaryStrategy {

    private final double totalMonthlyHoursWorked;

    public HourlyPayStrategy(double hoursWorked) {
        this.totalMonthlyHoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay(Employee employee){
        return employee.getSalary() * totalMonthlyHoursWorked;
    }

    @Override
    public SalaryType getSalaryType() {return SalaryType.HOURLY;}
}
//time stamp comment ignore