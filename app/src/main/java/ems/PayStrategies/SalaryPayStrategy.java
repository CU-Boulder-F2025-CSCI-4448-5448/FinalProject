package ems.PayStrategies;

import ems.Employee;

public class SalaryPayStrategy implements SalaryStrategy {
    @Override
    public double calculatePay(Employee employee) {
        return employee.getSalary() / 12;
    }

    @Override
    public SalaryType getSalaryType() {return SalaryType.SALARY;}
}
