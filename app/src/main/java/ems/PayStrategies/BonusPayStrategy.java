package ems.PayStrategies;

import ems.Employee;

public class BonusPay implements SalaryStrategy {
    private final double bonusAmmount;

    public BonusPayStrategy(double bonusAmmount) {
        this.bonusAmmount = bonusAmmount;
    }

    @Override
    public double calculatePay(Employee employee) {
        return (employee.getSalary() / 12) + bonusAmmount;
    }
}
