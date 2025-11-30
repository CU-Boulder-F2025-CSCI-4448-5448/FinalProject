package ems.PayStrategies;

import ems.Employee;

public class CommissionPayStrategy implements SalaryStrategy {
    private final double sales;
    private final double commissionRate;

    public CommissionPayStrategy(double sales, double commissionRate) {
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatePay(Employee employee) {
        return employee.getSalary() + (sales * commissionRate);
    }

}
