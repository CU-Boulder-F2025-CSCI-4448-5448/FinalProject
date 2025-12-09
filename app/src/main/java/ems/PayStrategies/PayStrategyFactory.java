package ems.PayStrategies;

public class PayStrategyFactory {
    public static SalaryStrategy fromType(SalaryType salaryType) {
        final double DEFAULT_BONUS_PAY = 700;
        final double DEFAULT_SALES = 10;
        final double DEFAULT_COMMISSION_RATE = 15;
        final double DEFAULT_HOURS_WORKED = 160;
        final double DEFAULT_OVERTIME_PAY = 200;


        return switch (salaryType) {
            case BONUS -> new BonusPayStrategy(DEFAULT_BONUS_PAY);
            case COMMISSION -> new CommissionPayStrategy(DEFAULT_SALES, DEFAULT_COMMISSION_RATE);
            case HOURLY -> new HourlyPayStrategy(DEFAULT_HOURS_WORKED);
            case OVERTIME -> new OvertimePayStrategy(DEFAULT_BONUS_PAY);
            case SALARY -> new SalaryPayStrategy();
        };
    }
}
