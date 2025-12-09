package ems.PayStrategies;
import ems.Employee;

public class OvertimePayStrategy implements SalaryStrategy{

    private final double hoursWorked;

    public OvertimePayStrategy(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay(Employee employee) {
        double rate = employee.getSalary();
        double regularPay = Math.min(40, hoursWorked);

        double overtimePay = Math.max(0, hoursWorked - 40);

        return regularPay * rate + (overtimePay * rate * 1.5);
    }

    @Override
    public SalaryType getSalaryType() {return SalaryType.OVERTIME;}
}
