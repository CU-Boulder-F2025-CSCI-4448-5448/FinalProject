package ems.PayStrategies;

import ems.Employee;

public class BonusPayStrategy implements SalaryStrategy {
    private final double bonusAmount;

    public BonusPayStrategy(double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    @Override
    public double calculatePay(Employee employee) {
        return (employee.getSalary() / 12) + bonusAmount;
    }

    @Override
    public SalaryType getSalaryType() {return SalaryType.BONUS;}
}
