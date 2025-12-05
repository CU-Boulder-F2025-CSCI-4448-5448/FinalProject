package ems.Observers;
import ems.Employee;

public class EmailNotifer implements PayObserver {

    @Override
    public void onPayCalculated(Employee employee, double amount){
        System.out.println("Email send to: " + employee.getName() + ": Monthly pay is $" + amount);
    }
}
