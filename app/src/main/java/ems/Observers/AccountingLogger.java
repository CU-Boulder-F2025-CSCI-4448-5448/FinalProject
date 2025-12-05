package ems.Observers;

import ems.Employee;

public class AccountingLogger implements PayObserver {
    @Override
    public void onPayCalculated(Employee employee, double amount){
        System.out.println("Accounting Entry: "+ employee.getName() + " paid: " + amount);
    }
}
