package ems.Observers;
import ems.Employee;
import java.util.ArrayList;
import java.util.List;

//Design Pattern: Observer
//Defines a famiily of interchanable calculation strategies
// Different ways to pay again Hourly, Monthly, and or commissioned based
//at runtime assign different salairies to different employees

public class PayrollSystem {
    private final List<PayObserver> observers = new ArrayList<>();

    public void addObserver(PayObserver observer){
        observers.add(observer);
    }

    public void removeObserver(PayObserver observer){
        observers.remove(observer);
    }

    public void calculateMonthlyPay(Employee employee){
        if(employee.getSalaryStrategy()== null){
            System.out.println("Payroll System has no salary!");

            return;
        }

        double pay = employee.getSalaryStrategy().calculatePay(employee);

        for(PayObserver observer : observers){
            observer.onPayCalculated(employee, pay);
        }

    }
}
