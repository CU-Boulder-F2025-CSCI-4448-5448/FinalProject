package ems.Observers;

import ems.Employee;

import java.util.List;

public interface DatabaseObserver {
    void onEmployeeAdded(Employee employee);
    void onEmployeeFetched(Employee employee);
    void onAllEmployeesFetched(List<Employee> employees);
}
