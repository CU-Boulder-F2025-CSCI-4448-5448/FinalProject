package ems.Observers;

import ems.Employee;

public interface PayObserver {
    void onPayCalculated(Employee employee, double amount);
}