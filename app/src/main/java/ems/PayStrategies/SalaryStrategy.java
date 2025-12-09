package ems.PayStrategies;

import ems.Employee;

//Design Pattern: Strategy
//SalaryStrategy define the family of interchangeable pay calculations
//Can impliment different strategies if need be
//ie commision based, hourly, salary
//PayStategy files implement differently

public interface SalaryStrategy {
    double calculatePay(Employee employee);
    SalaryType getSalaryType();
}
