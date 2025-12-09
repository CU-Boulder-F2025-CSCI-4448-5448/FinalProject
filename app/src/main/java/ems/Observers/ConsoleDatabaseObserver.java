package ems.Observers;

import ems.Employee;

import java.util.List;

public class ConsoleDatabaseObserver implements DatabaseObserver {

    @Override
    public void onEmployeeAdded(Employee employee) {
        if (employee != null) {
            System.out.println("[DB] Employee ADDED: "
                    + employee.getName()
                    + " (ID " + employee.getEmployeeID() + ")");
        }
    }

    @Override
    public void onEmployeeFetched(Employee employee) {
        if (employee != null) {
            System.out.println("[DB] Employee FETCHED: "
                    + employee.getName()
                    + " (ID " + employee.getEmployeeID() + ")");
        } else {
            System.out.println("[DB] Employee FETCHED: null (no match)");
        }
    }

    @Override
    public void onAllEmployeesFetched(List<Employee> employees) {
        System.out.println("[DB] All employees FETCHED, count = " + employees.size());
    }
}
